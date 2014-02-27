<%-- 
    Document   : index
    Created on : Feb 6, 2014, 4:02:34 PM
    Author     : kylerogers
--%>

<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="edu.miamioh.ece.codlasertag.game.*"%>
<%@page import="edu.miamioh.ece.codlasertag.game.gametypes.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    List<GameEntity> gamesList = GameServer.getInstance().getGameEntities(); 
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GeoLocation Games</title>
    </head>
    <body>
        <h1>Mobile Games</h1>
        <p>
            There are <%= gamesList.size() %> game(s) in session
        </p>
        <table>
            <tr>
                <td>Server name</td>
                <td>Game Type</td>
                <td># of Players</td>
                <td>Game ID</td>
            </tr>
            <% for (GameEntity ge : gamesList)   { %>
            <tr>
                <td><%= ge.getGameName() %></td>
                <td><%= ge.getGameTypeName() %></td>
                <td><%= ge.getNumPlayers() %></td>
                <td><%= ge.getId() %></td>
            </tr>
            <% } %>
            
        </table>

        <br />
        Create a new Game
        <form action="creategame.jsp" method="POST">
            Game Name: <input type="text" name="gamename" />
            Game Type: <select name="gametype">
                <option value="humansvszombies">Humans vs Zombies</option>
            </select>
            <input type="submit" value="Submit" />
        </form>
        
    </body>
</html>
