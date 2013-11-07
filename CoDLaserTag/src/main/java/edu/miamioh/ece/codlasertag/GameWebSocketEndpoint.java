/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.miamioh.ece.codlasertag;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


/**
 *
 * @author Kyle
 */
@ServerEndpoint(value="/game", 
        encoders={edu.miamioh.ece.codlasertag.player.PlayerEncoder.class}, 
        decoders={edu.miamioh.ece.codlasertag.player.PlayerDecoder.class})
public class GameWebSocketEndpoint {
    
    private static final int TIMEOUT_VAL = 3000;
    private static Random rnd = new Random();
    
    private static Map<Session, edu.miamioh.ece.codlasertag.player.Player> players 
            = Collections.synchronizedMap(new HashMap<Session, edu.miamioh.ece.codlasertag.player.Player>());

    @OnMessage
    public String onMessage(edu.miamioh.ece.codlasertag.player.Player message, Session session) throws IOException {
        if (!players.containsKey(session))
            return "Error: Player not in session";
        edu.miamioh.ece.codlasertag.player.Player currentPlayer = players.get(session);
        currentPlayer.setCoord(message.getCoord());
        currentPlayer.setHealth(message.getHealth());
        currentPlayer.setLastUpdate(new Date());
        currentPlayer.setJson(message.getJson());
        players.remove(session);
        players.put(session, currentPlayer);
        
        String rv = "[";
        for (Session s : players.keySet())  {
            edu.miamioh.ece.codlasertag.player.Player readPlayer = players.get(s);
            if ( (new Date().getTime() - readPlayer.getLastUpdate().getTime() ) > TIMEOUT_VAL)    {
                players.remove(s);
                s.getBasicRemote().sendText("Error: Timeout");
                System.out.println("Player left");
                continue;
            }
            if (readPlayer.getJson() != null)
                rv += readPlayer.getJson() + ",";
        }
        rv = rv.substring(0,rv.length()-1) + "]";
        return rv;
    }

    @OnClose
    public void onClose(Session s) {
        players.remove(s);
        System.out.println("Player left the game. # of players: " + players.size());
    }

    @OnOpen
    public void onOpen(Session s) throws IOException {
        edu.miamioh.ece.codlasertag.player.Player p = 
                new edu.miamioh.ece.codlasertag.player.Player();
        p.setId(rnd.nextInt());
        s.getBasicRemote().sendText(""+p.getId());
        players.put(s, p);
        System.out.println("Player connected to game. # of players: " + players.size());
    }

    @OnError
    public void onError(Session s, Throwable t) {
    }
    
}
