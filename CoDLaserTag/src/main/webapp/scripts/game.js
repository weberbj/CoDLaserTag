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
