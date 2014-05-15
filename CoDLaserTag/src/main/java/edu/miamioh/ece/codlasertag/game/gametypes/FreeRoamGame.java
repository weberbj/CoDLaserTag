/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.miamioh.ece.codlasertag.game.gametypes;

import edu.miamioh.ece.codlasertag.game.Game;
import edu.miamioh.ece.codlasertag.game.Team;

/**
 *
 * @author kylerogers
 */
public class FreeRoamGame extends Game {
    
    public FreeRoamGame(String gameName)   {
        super.gameName = gameName;
        teams.put("all", new Team("all"));
    }

    @Override
    protected void updateGame() {

    }

    @Override
    protected String getGameTypeName() {
        return "Free Roam";
    }

    @Override
    protected String getName() {
        return gameName;
    }
    
}
