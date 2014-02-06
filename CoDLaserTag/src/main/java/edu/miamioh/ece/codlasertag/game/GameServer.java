package edu.miamioh.ece.codlasertag.game;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author kylerogers
 */
public class GameServer {
    
    private static final GameServer instance;

    public static GameServer getInstance() {
        return instance;
    }
    
    private static final Random rnd;
    
    static {
        instance = new GameServer();
        rnd = new Random();
    }
    
    private final Map<Integer, Game> gamesInSession;
    
    private GameServer()    {
        gamesInSession = Collections.synchronizedMap(new HashMap<Integer, Game>());
    }
    
    public Game getGameById(int id)  {
        return gamesInSession.get(id);
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
    
}
