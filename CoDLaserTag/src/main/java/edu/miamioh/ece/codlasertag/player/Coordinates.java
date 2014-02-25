/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.miamioh.ece.codlasertag.player;

import java.io.StringWriter;
import javax.json.*;

/**
 *
 * @author Kyle
 */
public class Coordinates    {
    private double latitude, longitude;
    private int accuracy;
    private JsonObject json;
    
    public Coordinates copy() {
        return new Coordinates(this.json);
    }

    public JsonObject getJson() {
        return json;
    }
    
    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getAccuracy() {
        return accuracy;
    }
    
    @Override
    public String toString()    {
        StringWriter writer = new StringWriter();
        Json.createWriter(writer).write(json);
        return writer.toString();
    }
    
    public Coordinates(double latitude, double longitude, int accuracy) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.accuracy = accuracy;
        json = Json.createObjectBuilder()
                .add("latitude", latitude)
                .add("longitude", longitude)
                .add("accuracy", accuracy)
                .build();
    }
    
    public Coordinates(JsonObject json) {
        this.json = json;
        this.longitude = json.getJsonNumber("longitude").doubleValue();
        this.latitude = json.getJsonNumber("latitude").doubleValue();
        this.accuracy = json.getInt("accuracy");
    }
    
    
}
