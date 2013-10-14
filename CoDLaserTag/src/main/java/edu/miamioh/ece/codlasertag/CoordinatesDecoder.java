/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.miamioh.ece.codlasertag;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author Kyle
 */
public class CoordinatesDecoder implements Decoder.Text<Coordinates>    {

    @Override
    public Coordinates decode(String arg0) throws DecodeException {
        JsonObject jsonObject = Json.createReader(new StringReader(arg0)).readObject();
        System.out.println(jsonObject.toString());
        return  new Coordinates(jsonObject);
    }

    @Override
    public boolean willDecode(String arg0) {
        try {
            Json.createReader(new StringReader(arg0)).readObject();
            return true;
        } catch (JsonException ex) {
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
