/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var pushInterval;

function startGame()    {
    openConnectionToServer();
    startWatchingLocation();
    pushInterval = setInterval(sendAndReceiveData, 100);
}

function sendAndReceiveData()   {
   if (playerCurrentLocation !== null)
        sendText(convertCurrPlayerToJSON());
}

function endGame(json){
    document.getElementById("output").innerHTML = json['gameover'];
    clearInterval(pushInterval);
    navigator.geolocation.clearWatch(locWatchId);
    websocket.onclose = function () {}; // disable onclose handler first
    websocket.close();
}