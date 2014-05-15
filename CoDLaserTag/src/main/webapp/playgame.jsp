<%-- 
    Document   : playgame
    Created on : Feb 6, 2014, 1:32:16 PM
    Author     : kylerogers
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Real World Games</title>
        <link rel="shortcut icon" href="assets/img/pinpoint-md.png">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                
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
    <body>
        <h1 id="nowebsocketsupportmessage" class="centered" style="color: red;">
            Unfortunately, Javascript with WebSocket functionality is required to play this game
        </h1>
        <script type="text/javascript">
            removeJavascriptNotification();
            function removeJavascriptNotification() {
                document.getElementById("nowebsocketsupportmessage").innerHTML = "";
            }
        </script>
        <div class="centered">
            <canvas id="minimap"width="400" height="400" style="border:1px solid #000000; background: #f8f8f8;"></canvas>
        </div>
        <br />
        <div class="container centered">
            <div id="teamLoc"></div>
            <div id="output"></div>
            <div id="location"></div>
            <div id="playerCount"></div>
            <div id="xy"></div>
        </div>
        <script type="text/javascript" src="scripts/lib/jquery-1.10.2.min.js"></script>
        <script type="text/javascript" src="scripts/websocket.js"></script>
        <script type="text/javascript" src="scripts/location.js"></script>
        <script type="text/javascript" src="scripts/game.js"></script>
        <script type="text/javascript" src="scripts/minimap.js"></script>
        <script type="text/javascript" src="scripts/player.js"></script>
        <script type="text/javascript">
            $.ready(
                startGame()
            );
        </script>
    </body>
</html>

