/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.miamioh.ece.codlasertag.player;

import java.util.List;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author Kyle
 */
public class PlayerEncoder implements Encoder.Text<List<edu.miamioh.ece.codlasertag.player.Player>>{

    @Override
    public String encode(List<Player> arg0) throws EncodeException {
        return "";
    }

    @Override
    public void init(EndpointConfig config) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
