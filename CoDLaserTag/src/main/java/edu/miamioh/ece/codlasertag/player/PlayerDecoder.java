/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.miamioh.ece.codlasertag.player;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonException;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author Kyle
 */
public class PlayerDecoder implements Decoder.Text<Player> {
    
    @Override
    public Player decode(String arg0) throws DecodeException {
        return new Player(Json.createReader(new StringReader(arg0)).readObject());
    }

    @Override
    public boolean willDecode(String arg0) {
        try {
            Json.createReader(new StringReader(arg0)).readObject();
            return true;
        } catch (JsonException ex) { // Using exception handling for navigation.
                                     // I've accepted that I'm going to Hell.
            System.out.println("Invalid input");
            return false;
        }
    }
    

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
    
}
