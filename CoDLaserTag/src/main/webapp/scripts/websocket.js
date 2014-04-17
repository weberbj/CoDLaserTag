/* 
 * This script handles the WebSocket connection to the server.
 */

var loc = document.location.pathname;
var wsUri = "ws://" + document.location.host + loc.substring(0, loc.lastIndexOf('/')) + "/game";
var websocket;

function openConnectionToServer()   {
    websocket = new WebSocket(wsUri);
    websocket.onerror = function(evt) { onError(evt);  };
    websocket.onopen = function(evt)    { onOpen(evt);   };
    websocket.onmessage = function(evt) { onMessage(evt);   };
}
function writeToScreen(message) {
    output.innerHTML += message + "<br />";
}

function onOpen()   {
    console.log("Connected to " + wsUri);
}

function onError(evt)   {
    console.log ( 'ERROR: ' + evt.data);
}

function sendText(json) {
    websocket.send(json);
}

function onMessage(evt) {
    if (!isNaN(evt.data))   {
        playerId = evt.data;
    }
    else {
        var json = JSON.parse(evt.data);
        if (json['gameover'] !== undefined){
            endGame(json);
            return;
        }
        updateMap();
        document.getElementById("playerCount").innerHTML = "Player Count: " + json.length + "<br />";
        document.getElementById("xy").innerHTML = "";
        for (var i = 0 ; i < json.length ; i++) {
            var p = json[i];
            var coords = p.coords;
            if (String(p.id) !== playerId)    {
                addPlayerToMap(coords.latitude, coords.longitude, p.team === playerTeam);
            }
            else    {// is current player  
                playerHealth = p.health;
                playerTeam = p.team;
            }
        }
    }
}
