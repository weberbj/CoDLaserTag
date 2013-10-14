/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.miamioh.ece.codlasertag;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author Kyle
 */
public class CoordinatesEncoder implements Encoder.Text<Coordinates> {

    @Override
    public String encode(Coordinates coord) throws EncodeException {
        return coord.getJson().toString();
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
    
}
