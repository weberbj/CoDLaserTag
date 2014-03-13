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
    
    private static final GameServer _instance = new GameServer();
    private final Map<Integer, Game> gamesInSession;
    private static final Random rnd = new Random();
    private final Map<Session, Integer> activeSessions;
    private final Map<Session, Player> sessionsToId;
    private static final int GAME_TIMEOUT = 10000;


    public static GameServer getInstance() {
        return _instance;
    }
    
    private GameServer()    {
        gamesInSession = Collections.synchronizedMap(new HashMap<Integer, Game>());
        activeSessions = Collections.synchronizedMap(new HashMap<Session, Integer>());
        sessionsToId = Collections.synchronizedMap(new HashMap<Session, Player>());
    }
    
    public static Game getGameById(int id)  {
        return getInstance().gamesInSession.get(id);
    }
    
    public static int size()   {
        return getInstance().gamesInSession.size();
    }
    
    public static List<GameEntity> getGameEntities()   {
        List<GameEntity> games = new ArrayList<>();
        Collection<Integer> gamesToRemove = new HashSet<>();
        for (Integer key : getInstance().gamesInSession.keySet()) {
            Game g = getInstance().gamesInSession.get(key);
            if (System.currentTimeMillis() > GAME_TIMEOUT + g.getLastUpdated()) {
                gamesToRemove.add(key);
            }
            else    {
                games.add( new GameEntity(g.getName(), g.getGameTypeName(), g.size(), key));
            }
        }
        for (Integer k : gamesToRemove) {
            getInstance().removeGame(k);
        }
        return games;
    }
    
    /**
     * Adds a new Game to the list on the server
     * @param game Game to be added
     * @return The ID that the server will identify the game by.
     */
    public static int addGame(Game game)    {
        int id = rnd.nextInt();
        game.setId(id);
        synchronized (GameServer .class)    {
            while (getInstance().gamesInSession.containsKey(id))
                id = rnd.nextInt();
            getInstance().gamesInSession.put(id, game);
        }
        return id;
    }
    
    public static boolean removeGame(int id)   {
        getInstance().gamesInSession.remove(id);
        return true;
    }
    
    public static void connectPlayerToServer(Session s)   {
        getInstance().sessionsToId.put(s, new Player());
    }
    
    private static void connectPlayerToGame(Session sess, int gameId)   {
        getInstance().gamesInSession.get(gameId).connectPlayer(sess);
        getInstance().sessionsToId.remove(sess);
        getInstance().activeSessions.put(sess, gameId);
    }
    
    public static void removePlayerFromGame(Session sess)  {
        getInstance().gamesInSession.get(getInstance().activeSessions.get(sess)).removePlayer(sess);
        getInstance().activeSessions.remove(sess);
    }
    
    public static String update(Session sess, Player sentPlayerObject) {
        if (getInstance().sessionsToId.containsKey(sess))
            connectPlayerToGame(sess, sentPlayerObject.getGameId());
        return updateGame(sess, sentPlayerObject);
    }
    
    private static String updateGame(Session sess, Player sentPlayerObject)    {
        return getInstance().gamesInSession.get(
                getInstance().activeSessions.get(sess)
            ).update(sess, sentPlayerObject);
    }
    
}
