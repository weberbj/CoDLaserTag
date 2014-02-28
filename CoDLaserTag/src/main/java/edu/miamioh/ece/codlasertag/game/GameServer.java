package edu.miamioh.ece.codlasertag.game;

import edu.miamioh.ece.codlasertag.player.Player;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.websocket.Session;

/**
 *
 * @author kylerogers
 */
public class GameServer {
    
    private static final GameServer instance = new GameServer();
    private final Map<Integer, Game> gamesInSession;
    private static final Random rnd = new Random();
    private final Map<Session, Integer> activeSessions;
    private static final int GAME_TIMEOUT = 10000;


    public static GameServer getInstance() {
        return instance;
    }
    
    private GameServer()    {
        gamesInSession = Collections.synchronizedMap(new HashMap<Integer, Game>());
        activeSessions = Collections.synchronizedMap(new HashMap<Session, Integer>());
    }
    
    public Game getGameById(int id)  {
        return gamesInSession.get(id);
    }
    
    public int size()   {
        return gamesInSession.size();
    }
    
    public List<GameEntity> getGameEntities()   {
        List<GameEntity> games = new ArrayList<>();
        Collection<Integer> gamesToRemove = new HashSet<>();
        for (Integer key : gamesInSession.keySet()) {
            Game g = gamesInSession.get(key);
            if (System.currentTimeMillis() > GAME_TIMEOUT + g.getLastUpdated()) {
                gamesToRemove.add(key);
            }
            else    {
                games.add( new GameEntity(g.getName(), g.getGameTypeName(), g.size(), key));
            }
        }
        for (Integer k : gamesToRemove) 
            this.removeGame(k);
        return games;
    }
    
    /**
     * Adds a new Game to the list on the server
     * @param game Game to be added
     * @return The ID that the server will identify the game by.
     */
    public int addGame(Game game)    {
        int id = rnd.nextInt();
        synchronized (GameServer .class)    {
            while (gamesInSession.containsKey(id))
                id = rnd.nextInt();
            gamesInSession.put(id, game);
        }
        return id;
    }
    
    public boolean removeGame(int id)   {
        gamesInSession.remove(id);
        return true;
    }
    
    public void connectPlayerToGame(Session sess, int gameId)   {
        gamesInSession.get(gameId).connectPlayer(sess);
        activeSessions.put(sess, gameId);
    }
    
    public void removePlayerFromGame(Session sess)  {
        gamesInSession.get(activeSessions.get(sess)).removePlayer(sess);
        activeSessions.remove(sess);
    }
    
    public String updateGame(Session sess, Player sentPlayerObject)    {
        return gamesInSession.get(activeSessions.get(sess)).update(sess, sentPlayerObject);
    }
    
}
