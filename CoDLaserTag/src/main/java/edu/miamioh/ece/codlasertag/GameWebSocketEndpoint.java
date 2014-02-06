/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.miamioh.ece.codlasertag;

import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import edu.miamioh.ece.codlasertag.game.Game;
import edu.miamioh.ece.codlasertag.game.GameServer;


/**
 *
 * @author Kyle Rogers
 */
@ServerEndpoint(value="/game", 
        encoders={edu.miamioh.ece.codlasertag.player.PlayerEncoder.class}, 
        decoders={edu.miamioh.ece.codlasertag.player.PlayerDecoder.class})
public class GameWebSocketEndpoint {
    
    static int id;
    
    static {
        id = GameServer.getInstance().addGame(new Game());
    }
    
    @OnMessage
    public String onMessage(edu.miamioh.ece.codlasertag.player.Player message, Session session) throws IOException {
        return GameServer.getInstance().getGameById(id).update(session, message);
    }

    @OnClose
    public void onClose(Session s) {
        GameServer.getInstance().getGameById(id).removePlayer(s);
    }

    @OnOpen
    public void onOpen(Session s) throws IOException {
        GameServer.getInstance().getGameById(id).connectPlayer(s);
    }

    @OnError
    public void onError(Session s, Throwable t) {
    }
    
}
