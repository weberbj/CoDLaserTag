/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package edu.miamioh.ece.codlasertag.game.gametypes;

import java.util.*;
import edu.miamioh.ece.codlasertag.game.Game;
import edu.miamioh.ece.codlasertag.game.Team;
import edu.miamioh.ece.codlasertag.player.Player;
import edu.miamioh.ece.codlasertag.player.Coordinates;

/**
 *
 * @author kylerogers
 */
public class HumansVsZombiesGame extends Game {
    
    public static final String ZOMBIES = "zombies";
    public static final String HUMANS = "humans";
    public static final int RATIO = 1/6;
    public static final double CHECK_DISTANCE = 10.0; //In Meters
    
    public HumansVsZombiesGame()    {
        this.teams.put(ZOMBIES, new Team(ZOMBIES));
        this.teams.put(HUMANS, new Team(HUMANS));
    }

    @Override
    protected void updateGame() {
        if(gameOver()){
            //DO SOMETHING HERE TO STOP GAME
        }
        checkDistance();
    }
    
    void checkDistance(){
        for(Iterator it = teams.get(ZOMBIES).players.iterator(); it.hasNext(); ){
            Player tempZombie = (Player)it.next();
            Coordinates tempZombieCoor = tempZombie.getCoord();
           for(Iterator it2 = teams.get(HUMANS).players.iterator(); it2.hasNext();){
               Player tempHuman = (Player)it.next();
               Coordinates tempHumanCoor = tempHuman.getCoord();
               double distanceAway = gps2m(tempZombieCoor.getLatitude(),tempZombieCoor.getLongitude(),
                     tempHumanCoor.getLatitude(),tempHumanCoor.getLongitude());
               if(distanceAway < CHECK_DISTANCE){
                  teams.get(ZOMBIES).addPlayer(tempHuman);
                  teams.get(HUMANS).removePlayer(tempHuman);
             }
           }
        }
    }
    
    boolean gameOver(){
        if(teams.get(HUMANS).players.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    
    private double gps2m(double lat_a, double lng_a, double lat_b, double lng_b) {
        double pk = (double) (180/3.14169);

        double a1 = lat_a / pk;
        double a2 = lng_a / pk;
        double b1 = lat_b / pk;
        double b2 = lng_b / pk;

        double t1 = Math.cos(a1)*Math.cos(a2)*Math.cos(b1)*Math.cos(b2);
        double t2 = Math.cos(a1)*Math.sin(a2)*Math.cos(b1)*Math.sin(b2);
        double t3 = Math.sin(a1)*Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);

        return 6366000*tt;
}
// see http://androidsnippets.com/distance-between-two-gps-coordinates-in-meter
    
    //Fow now uses a simple ratio defined as a constant above, RATIO.
    @Override
    protected void assignTeam(edu.miamioh.ece.codlasertag.player.Player player){
        if(teams.get(HUMANS).size() != 0 && (double)teams.get(ZOMBIES).size()/(double)teams.get(HUMANS).size() < RATIO){
            player.setTeam(ZOMBIES);
            teams.get(ZOMBIES).addPlayer(player);
        }else{
            player.setTeam(HUMANS);
            teams.get(HUMANS).addPlayer(player);
        }
    }
}
