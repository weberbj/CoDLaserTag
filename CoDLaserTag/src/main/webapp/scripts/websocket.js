/* 
 * This script handles the WebSocket connection to the server.
 */

var wsUri = "ws://" + document.location.host + document.location.pathname 
        + "game";

var websocket = new WebSocket(wsUri);
websocket.onerror = function(evt) { onError(evt);  };
websocket.onopen = function(evt)    { onOpen(evt);   };
websocket.onmessage = function(evt) { onMessage(evt);   };

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
    console.log("sending text: " + json);
    websocket.send(json);
}

function onMessage(evt) {
    console.log("received: " + evt.data);
    var json = JSON.parse(evt.data);
    addPlayerToMap(json.latitude, json.longitude, false);
}
