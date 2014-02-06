package edu.miamioh.ece.codlasertag.game;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.websocket.Session;

/**
 * Represents a game session 
 * @author kylerogers
 */
public class Game {
    
    protected Dictionary< String, Team > teams;
        
    protected static Map<Session, edu.miamioh.ece.codlasertag.player.Player> players 
            = Collections.synchronizedMap(new HashMap<Session, edu.miamioh.ece.codlasertag.player.Player>());
    
    public static final int TIMEOUT_VAL = 3000;
    private static final Random rnd = new Random();

    
    public Game()   {
        
    }
    
    public String update(Session playerSession, edu.miamioh.ece.codlasertag.player.Player sentPlayerObject) {
        if (!players.containsKey(playerSession))
            return "Error: Player not in session";
        edu.miamioh.ece.codlasertag.player.Player currentPlayer = players.get(playerSession);
        currentPlayer.setCoord(sentPlayerObject.getCoord());
        currentPlayer.setHealth(sentPlayerObject.getHealth());
        currentPlayer.setLastUpdate(new Date());
        currentPlayer.setJson(sentPlayerObject.getJson());
        players.remove(playerSession);
        players.put(playerSession, currentPlayer);
        
        String rv = "[";
        for (Session s : players.keySet())  {
            edu.miamioh.ece.codlasertag.player.Player readPlayer = players.get(s);
            if ( (new Date().getTime() - readPlayer.getLastUpdate().getTime() ) > TIMEOUT_VAL)    {
                players.remove(s);
                try {
                    s.getBasicRemote().sendText("Error: Timeout");
                }
                catch (IOException e) {}
                System.out.println("Player left");
                continue;
            }
            if (readPlayer.getJson() != null)
                rv += readPlayer.getJson() + ",";
        }
        rv = rv.substring(0,rv.length()-1) + "]";
        return rv;
    }
    
    public void removePlayer(Session playerSession) {
        players.remove(playerSession);
        System.out.println("Player left the game. # of players: " + players.size());
    }
    
    public void connectPlayer(Session playerSession)    {
        edu.miamioh.ece.codlasertag.player.Player p = 
                new edu.miamioh.ece.codlasertag.player.Player();
        p.setId(rnd.nextInt());
        try {
            playerSession.getBasicRemote().sendText(""+p.getId());
        }
        catch (IOException e)   {}
        players.put(playerSession, p);
        System.out.println("Player connected to game. # of players: " + players.size());
    }
}
