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
    List<GameEntity> gamesList = GameServer.getGameEntities(); 
%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="assets/img/pinpoint-md.png">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GeoLocation Games</title>
        
    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/font-awesome.min.css">

    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/js/modernizr.custom.js"></script>
	

	
    <link href='http://fonts.googleapis.com/css?family=Oswald:400,300,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=EB+Garamond' rel='stylesheet' type='text/css'>

    </head>
    <body data-spy="scroll" data-offset="0" data-target="#theMenu">
        
        	<!-- Menu -->
	<nav class="menu" id="theMenu">
		<div class="menu-wrap">
			<h1 class="logo"><a href="index.html#home">Mobile Games</a></h1>
			<i class="icon-remove menu-close"></i>
			<a href="#" class="smoothScroll">Home</a>
			<a href="about.html#about" class="smoothScroll">About</a>
			<a href="about.html#contact" class="smoothScroll">Contact</a>
		</div>
		
		<!-- Menu button -->
		<div id="menuToggle"><i class="icon-reorder"></i></div>
	</nav>
        
        <section id="home">
            <div class="container">
                <div class="centered">
                    <div class="row"><h1>Mobile Games</h1></div>
                    <div class="row">
                        <h4>There are <%= gamesList.size() %> game(s) in session</h4>
                    </div>
                    <div class="container ">
                    <form action="playgame.jsp" method="GET">
                        <div class="row centered">
                            
                            Server name |
                            Game Type |
                            # of Players
                        </div>
                        <% for (GameEntity ge : gamesList)   { %>
                            <div class="row centered">
                                    <input type="radio" name="gameId" value="<%= ge.getId() %>" />
                                <%= ge.getGameName() %> |
                                <%= ge.getGameTypeName() %> |
                                <%= ge.getNumPlayers() %>
                            </div>
                        <% } %>

                            <input type="submit" value="Join Game" />

                    </form>
                    </div>
                </div>
                <div class="row centered">
                <br />
                <h2>Create a New Game</h2>
                <form action="CreateGame" method="POST">
                    <div class="container im-centered">
                        <div class="row">
                            Game Name: <input type="text" name="gamename" />
                        </div>
                        <div class="row">
                            Game Type:
                                <select name="gametype">
                                    <option value="humansvszombies">Humans vs Zombies</option>
                                    <option value="freeroam">Free Roam</option>
                                </select>
                        </div>
                        </div>
                    <input type="submit" value="Create Game" />
                    </div><br />
                </form>
                </div>
        </section>
        <script src="assets/js/classie.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/smoothscroll.js"></script>
        <script src="assets/js/main.js"></script>
    </body>
</html>
