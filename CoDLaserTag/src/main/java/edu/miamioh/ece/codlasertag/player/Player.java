/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.miamioh.ece.codlasertag.player;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author Kyle
 */
public class Player implements Comparable<Player>   {
    private Coordinates coord;
    private String team;
    private int health, id;
    private Date lastUpdate;
    JsonObject json;
    private int gameId;
    
    private void updateJson()   {
        json = Json.createObjectBuilder()
                .add("team", team)
                .add("health", health)
                .add("coords", coord.getJson())
                .add("id", id)
                .build();
    }

    public Player copy() {
        Player p = new Player();
        p.setFieldsFromJson(this.json);
        p.lastUpdate = (Date) this.lastUpdate.clone();
        return p;
    }
    
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
    
    public int getGameId()  {
        return gameId;
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
        updateJson();
        return json;
    }

    public void setJson(JsonObject json) {
        this.json = json;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord.copy();
    }
    
    public Player() {
        
    }
    
    public Player(JsonObject json)  {
        lastUpdate = new Date();
        setFieldsFromJson(json);
    }
    
    private void setFieldsFromJson(JsonObject json) {
        this.json = json;
        String s = json.getString("coords");
        this.coord = new Coordinates(Json.createReader(new StringReader(s)).readObject());
        this.team = json.getString("team");
        this.health = json.getInt("health");
        this.gameId = json.getInt("gameid");
    }

    @Override
    public int compareTo(Player o) {
        return this.id - o.id;
    }
}
