<%-- 
    Document   : creategame
    Created on : Feb 27, 2014, 5:00:58 PM
    Author     : kylerogers
--%>

<%@page import="edu.miamioh.ece.codlasertag.game.Game"%>
<%@page import="edu.miamioh.ece.codlasertag.game.gametypes.*"%>
<%@page import="edu.miamioh.ece.codlasertag.game.GameServer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Game Creation/title>
    </head>
    <body>
        <% 
            String gameName = request.getParameter("gamename"); 
            String gameType = request.getParameter("gametype");
            
            Game gameToBeAdded;
            
            if (gameType.equals("humansvszombies"))    {
                gameToBeAdded = new HumansVsZombiesGame(gameName);
            }
            else    {
                throw new Exception();
            }
            
            GameServer.getInstance().addGame(gameToBeAdded);
        %>
    </body>
</html>
