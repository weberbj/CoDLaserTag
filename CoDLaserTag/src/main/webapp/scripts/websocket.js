/* 
 * This script handles the WebSocket connection to the server.
 */

var wsUri = "ws://" + document.location.host + document.location.pathname + "game";
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
        updateMap();
        for (var i = 0 ; i < json.length ; i++) {
            var otherPlayer = json[i];
            var coords = JSON.parse(otherPlayer.coords);
            if (otherPlayer.id !== playerId)    {
                addPlayerToMap(coords.latitude, coords.longitude, otherPlayer.team === playerTeam);
            }
        }
    }
}
