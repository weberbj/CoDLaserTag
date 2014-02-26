/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.miamioh.ece.codlasertag.game;

/**
 *
 * @author kylerogers
 */
public class GameEntity {
    
    private final String gameTypeName;
    private final int numPlayers;
    private final int id;
    private final String gameName;

    public String getGameName() {
        return gameName;
    }

    public String getGameTypeName() {
        return gameTypeName;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public int getId() {
        return id;
    }
    
    public GameEntity(String gameName, String gameTypeName, int numPlayers, int id) {
        this.gameName = gameName;
        this.gameTypeName = gameTypeName;
        this.numPlayers = numPlayers;
        this.id = id;
    }
    
}
