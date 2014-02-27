<%-- 
    Document   : index
    Created on : Feb 6, 2014, 4:02:34 PM
    Author     : kylerogers
--%>

<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="edu.miamioh.ece.codlasertag.game.*"%>
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
            <%
                String placeholder = "<tr><td>%s</td><td>%s</td><td>%d</td><td>%d</td></tr>";
                for (GameEntity ge : gamesList) {
                    out.print(String.format(placeholder, ge.getGameName(), ge.getGameTypeName(), ge.getNumPlayers(), ge.getId()));
                    
                }
            %>
        </table>
        
    </body>
</html>
