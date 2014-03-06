/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.miamioh.ece.codlasertag.game;

import java.util.Set;
import java.util.Collection;
import edu.miamioh.ece.codlasertag.player.Player;
import java.util.HashSet;

/**
 *
 * @author kylerogers
 */
public class Team {
    //For now set to public,
    public Set<Player> players;
    private String name;
    
    public Team(String name)   {
        this.name = name;
        players = new HashSet<>();
    }
    
    public Team(String name, Collection<Player> initialPlayers)    {
        this.name = name;
        this.players = new HashSet<>();
        for (Player player : initialPlayers)    {
            this.players.add(player);
        }
    }
    
    public int size() {
        return players.size();
    }
    
    public String getName()   {
        return name;
    }
    
    public void setName(String name)   {
        this.name = name;
    }
    
    public void addPlayer(Player p) {
        players.add(p);
    }
    
    public void removePlayer(Player p)  {
        players.remove(p);
    }
    
}
