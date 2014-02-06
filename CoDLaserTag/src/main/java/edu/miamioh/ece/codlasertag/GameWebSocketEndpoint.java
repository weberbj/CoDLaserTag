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

import edu.miamioh.ece.codlasertag.game.Game;


/**
 *
 * @author Kyle Rogers
 */
@ServerEndpoint(value="/game", 
        encoders={edu.miamioh.ece.codlasertag.player.PlayerEncoder.class}, 
        decoders={edu.miamioh.ece.codlasertag.player.PlayerDecoder.class})
public class GameWebSocketEndpoint {
    
    public Game game = new Game();
    
    @OnMessage
    public String onMessage(edu.miamioh.ece.codlasertag.player.Player message, Session session) throws IOException {
        return game.update(session, message);
    }

    @OnClose
    public void onClose(Session s) {
        game.removePlayer(s);
    }

    @OnOpen
    public void onOpen(Session s) throws IOException {
        game.connectPlayer(s);
    }

    @OnError
    public void onError(Session s, Throwable t) {
    }
    
}
