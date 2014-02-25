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
public class HumansVsZombiesGame extends Game {
    
    public static final String ZOMBIES = "zombies";
    public static final String HUMANS = "humans";
    
    public HumansVsZombiesGame()    {
        this.teams.put(ZOMBIES, new Team(ZOMBIES));
        this.teams.put(HUMANS, new Team(HUMANS));
    }

    @Override
    protected void updateGame() {
        
    }
    
}
