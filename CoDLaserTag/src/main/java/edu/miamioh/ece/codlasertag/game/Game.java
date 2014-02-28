package edu.miamioh.ece.codlasertag.game;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.websocket.Session;

/**
 * Represents a game session 
 * @author Kyle Rogers
 */
public abstract class Game {
    
    protected Map<String, edu.miamioh.ece.codlasertag.game.Team> teams
            = Collections.synchronizedMap(new HashMap<String, edu.miamioh.ece.codlasertag.game.Team>());
        
    protected static Map<Session, edu.miamioh.ece.codlasertag.player.Player> players 
            = Collections.synchronizedMap(new HashMap<Session, edu.miamioh.ece.codlasertag.player.Player>());
    
    public static final int TIMEOUT_VAL = 3000; // In milliseconds
    protected String gameName;
    private static final Random rnd = new Random();
    private long lastUpdated = System.currentTimeMillis();

    public long getLastUpdated() {
        return lastUpdated;
    }
    
    /**
     * Updates the game
     * @param playerSession Client session that is sending the update
     * @param receivedPlayerObject Player object that the client sent
     * @return 
     */
    public final String update(Session playerSession, edu.miamioh.ece.codlasertag.player.Player receivedPlayerObject) {
        if (!players.containsKey(playerSession))
            return "Error: Player not in session";
        updatePlayer(playerSession, receivedPlayerObject);
        updateGame();
        lastUpdated = System.currentTimeMillis();
        return buildJSONArrayString();
    }
    
    /**
     * Combines all of the Player objects into a string that can be parsed by the
     * client.
     * @return String that represents a JSON array of Player objects 
     */
    String buildJSONArrayString() {
        String rv = "[";
        for (Session s : players.keySet())  {
            edu.miamioh.ece.codlasertag.player.Player readPlayer = players.get(s);
            if ( (new Date().getTime() - readPlayer.getLastUpdate().getTime() ) > TIMEOUT_VAL)    {
                timeout(s);
            }
            else if (readPlayer.getJson() != null)
                rv += readPlayer.getJson() + ",";
        }
        rv = rv.substring(0,rv.length()-1) + "]";
        return rv;
    }
    
    /**
     * Updates the current player in the map
     * @param playerSession The player to be updated
     * @param receivedPlayerObject The player object that the client sent. This
     * will need to be verified.
     */
    void updatePlayer(Session playerSession, edu.miamioh.ece.codlasertag.player.Player receivedPlayerObject)  {
        edu.miamioh.ece.codlasertag.player.Player currentPlayer = players.get(playerSession);
        // TODO: Verify that the received object is valid before updating the hashmap
        currentPlayer.setCoord(receivedPlayerObject.getCoord());
        currentPlayer.setHealth(receivedPlayerObject.getHealth());
        currentPlayer.setLastUpdate(new Date());
        currentPlayer.setJson(receivedPlayerObject.getJson());
        players.remove(playerSession);
        players.put(playerSession, currentPlayer);
    }
    
    /**
     * Removes a player from the game when they 
     * @param s 
     */
    protected void timeout(Session s)  {
        players.remove(s);
        try {
            s.getBasicRemote().sendText("Error: Timeout");
        }
        catch (IOException e) {}
        System.out.println("Player left");
    }
    
    /**
     * A player has left the game
     * @param playerSession The session for the exiting player
     */
    public void removePlayer(Session playerSession) {
        players.remove(playerSession);
        System.out.println("Player left the game. # of players: " + players.size());
    }
    
    /**
     * A player has joined the game
     * @param playerSession the session of the connecting player
     */
    public void connectPlayer(Session playerSession)    {
        edu.miamioh.ece.codlasertag.player.Player p = 
                new edu.miamioh.ece.codlasertag.player.Player();
        p.setId(rnd.nextInt());
        try {
            // Tell the player their ID
            playerSession.getBasicRemote().sendText(""+p.getId());
        }
        catch (IOException e)   {
            // If there has already been an issue communicating, don't add them to the game
            return;
        }
        players.put(playerSession, p);
        assignTeam(p);
        System.out.println("Player connected to game. # of players: " + players.size());
    }
    
    /**
     * Returns the number of players that are currently in-game
     * @return Size of the players map
     */
    public int getNumberOfConnectedPlayers()    {
        return players.size();
    }
    
    /**
     * Assigns players to a team. Default behavior is to assign equal sized teams.
     * @param player 
     */
    protected void assignTeam(edu.miamioh.ece.codlasertag.player.Player player)    {
        String smallestTeam = "";
        int smallestTeamSize = Integer.MAX_VALUE;
        for (String teamName : teams.keySet())   {
            int size = teams.get(teamName).size();
            if (size < smallestTeamSize)  {
                smallestTeam = teamName;
                smallestTeamSize = size;
            }
        }
        player.setTeam(smallestTeam);
        teams.get(smallestTeam).addPlayer(player);
    }
    
    /**
     * This should update the game's current state. This is run every single time
     * that a player sends an update to the server.
     */
    protected abstract void updateGame();
    
    protected abstract String getGameTypeName();
    
    protected abstract String getName();
    
    public int size() {
        return players.size();
    }
    
}
