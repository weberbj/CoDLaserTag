/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.miamioh.ece.codlasertag.player;

import java.io.StringReader;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author Kyle
 */
public class Player {
    private Coordinates coord;
    private String team;
    private int health, id;
    private Date lastUpdate;
    JsonObject json;

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Coordinates getCoord() {
        return coord;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getHealth() {
        return health;
    }

    public JsonObject getJson() {
        return json;
    }

    public void setJson(JsonObject json) {
        this.json = json;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }
    
    public Player() {
        
    }
    
    public Player(JsonObject json)  {
        this.json = json;
        lastUpdate = new Date();
    //    this.coord = new Coordinates(json.getJsonObject("coords"));
        String s = json.getString("coords");
        this.coord = new Coordinates(Json.createReader(new StringReader(s)).readObject());
        this.team = json.getString("team");
        this.health = json.getInt("health");
    }
}
