package edu.miamioh.ece.codlasertag.game;

import edu.miamioh.ece.codlasertag.player.Player;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.websocket.Session;

/**
 *
 * @author kylerogers
 */
public class GameServer {
    
    private static final GameServer instance = new GameServer();
    private final Map<Integer, Game> activeGames;
    private static final Random rnd = new Random();
    private final Map<Session, Integer> activeSessions;


    public static GameServer getInstance() {
        return instance;
    }
    
    private GameServer()    {
        activeGames = Collections.synchronizedMap(new HashMap<Integer, Game>());
        activeSessions = Collections.synchronizedMap(new HashMap<Session, Integer>());
    }
    
    public Game getGameById(int id)  {
        return activeGames.get(id);
    }
    
    /**
     * Adds a new Game to the list on the server
     * @param game Game to be added
     * @return The ID that the server will identify the game by.
     */
    public int addGame(Game game)    {
        int id = rnd.nextInt();
        synchronized (GameServer .class)    {
            while (activeGames.containsKey(id))
                id = rnd.nextInt();
            activeGames.put(id, game);
        }
        return id;
    }
    
    public boolean removeGame(int id)   {
        activeGames.remove(id);
        return true;
    }
    
    public void connectPlayerToGame(Session sess, int gameId)   {
        activeGames.get(gameId).connectPlayer(sess);
        activeSessions.put(sess, gameId);
    }
    
    public void removePlayerFromGame(Session sess)  {
        activeGames.get(activeSessions.get(sess)).removePlayer(sess);
        activeSessions.remove(sess);
    }
    
    public String updateGame(Session sess, Player sentPlayerObject)    {
        return activeGames.get(activeSessions.get(sess)).update(sess, sentPlayerObject);
    }
    
}
