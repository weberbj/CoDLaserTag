/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.miamioh.ece.codlasertag;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.EncodeException;
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
@ServerEndpoint(value="/game", encoders={CoordinatesEncoder.class}, decoders={CoordinatesDecoder.class})
public class GameWebSocketEndpoint {
    
    private static Map<Session, edu.miamioh.ece.codlasertag.player.Player> players 
            = Collections.synchronizedMap(new HashMap<Session, edu.miamioh.ece.codlasertag.player.Player>());

    @OnMessage
    public String onMessage(Coordinates message, Session session) {
        try {
            System.out.println(message);
            players.get(session).setCoord(message);
            for (Session s : players.keySet()) 
                if (!s.equals(session)) {
                    Coordinates coord = players.get(s).getCoord();
                    if (coord != null)  {
                        s.getBasicRemote().sendObject(message.getJson());
                        session.getBasicRemote().sendObject(coord);
                    }
                }
            return message.getJson().toString();
        } catch (IOException | EncodeException ex) {
            Logger.getLogger(GameWebSocketEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            return "ERROR";
        }
    }

    @OnClose
    public void onClose(Session s) {
        players.remove(s);
        System.out.println("Player left the game. # of players: " + players.size());
    }

    @OnOpen
    public void onOpen(Session s) {
        players.put(s, new edu.miamioh.ece.codlasertag.player.Player());
        System.out.println("Player connected to game. # of players: " + players.size());
    }

    @OnError
    public void onError(Throwable t) {
    }
    
}
