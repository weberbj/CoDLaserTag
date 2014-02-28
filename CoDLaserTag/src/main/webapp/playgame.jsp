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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1 id="nowebsocketsupportmessage" style="color: red;">
            Unfortunately, Javascript with WebSocket functionality is required to play this game
        </h1>
        <script type="text/javascript">
            removeJavascriptNotification();
            function removeJavascriptNotification() {
                document.getElementById("nowebsocketsupportmessage").innerHTML = "";
            }
        </script>
        <canvas id="minimap" width="400" height="400" style="border:1px solid #000000"></canvas>
        <br />
        <div id="output"></div>
        <div id="location"></div>
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

