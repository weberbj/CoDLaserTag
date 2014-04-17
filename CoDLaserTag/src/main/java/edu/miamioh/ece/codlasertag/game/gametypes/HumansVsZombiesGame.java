/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package edu.miamioh.ece.codlasertag.game.gametypes;

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
    public static final double RATIO = 6.0; // Humans to Zombies ratio
    public static final double CHECK_DISTANCE = 10.0; //In Meters
    public static final String GAME_TYPE = "Humans vs Zombies";
    
    public HumansVsZombiesGame(String gameName)    {
        this.teams.put(ZOMBIES, new Team(ZOMBIES));
        this.teams.put(HUMANS, new Team(HUMANS));
        super.gameName = gameName;
    }

    @Override
    protected void updateGame() {
        if(gameOver()){
           super.gameIsOver = true;
        }
        checkDistance();
    }
    
    void checkDistance(){
        for(Player zombie : teams.get(ZOMBIES).players ){
            Coordinates tempZombieCoor = zombie.getCoord();
            for(Player human : teams.get(HUMANS).players){
                Coordinates tempHumanCoor = human.getCoord();
                double distanceAway = gps2m(tempZombieCoor.getLatitude(),tempZombieCoor.getLongitude(),
                        tempHumanCoor.getLatitude(),tempHumanCoor.getLongitude());
                if(distanceAway < CHECK_DISTANCE){
                    human.setTeam(ZOMBIES);
                    teams.get(ZOMBIES).addPlayer(human);
                    teams.get(HUMANS).removePlayer(human);
                }
            }
        }
    }
    
    boolean gameOver(){
        return (teams.get(HUMANS).players.isEmpty());
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
        String teamName;
        if (teams.get(HUMANS).size() == 0)  {
            teamName = HUMANS;
        }
        else if (teams.get(ZOMBIES).size() == 0)    {
            teamName = ZOMBIES;
        }
        else if ( (double) teams.get(HUMANS).size() / (double) teams.get(ZOMBIES).size() > RATIO)    {
            // Too many humans compared to zombies
            teamName = ZOMBIES;
        }
        else    {
            teamName = HUMANS;
        }
        teams.get(teamName).addPlayer(player);
        player.setTeam(teamName);
        System.out.println("Assigned player " + player.getId() + " to team " + teamName);
    }

    @Override
    protected String getGameTypeName() {
        return GAME_TYPE;
    }

    @Override
    protected String getName() {
        return gameName;
    }
}