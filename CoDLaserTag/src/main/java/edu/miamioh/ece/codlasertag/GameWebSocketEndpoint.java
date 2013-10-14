/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.miamioh.ece.codlasertag;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
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
    
    private static Set<Session> players = Collections.synchronizedSet(new HashSet<Session>());

    @OnMessage
    public String onMessage(Coordinates message, Session session) {
        System.out.println(message);
        return message.getJson().toString();
    }

    @OnClose
    public void onClose(Session s) {
        players.remove(s);
        System.out.println("Player left the game. # of players: " + players.size());
    }

    @OnOpen
    public void onOpen(Session s) {
        players.add(s);
        System.out.println("Player connected to game. # of players: " + players.size());
    }

    @OnError
    public void onError(Throwable t) {
    }
    
}
