/* 
 * This is supposed to track the current user's player information.
 * It handles converting the current player to a JSON string to send to the server
 * It also handles parsing a JSON string from the server and returns a player object
 * for mapping onto the minimap. 
 */

var playerHealth, playerCurrentLocation, playerTeam, playerId; // int, coords, string, int? String?
playerHealth = 100;
playerTeam = "";
playerCurrentLocation = null;
var gameId = parseInt(getParameterByName("gameId"), 10);

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function convertCurrPlayerToJSON()  {
    return JSON.stringify({
        "health":playerHealth, 
        "coords":JSON.stringify(playerCurrentLocation), 
        "team":playerTeam, 
        "id":playerId,
        "gameid":gameId
    });
}

function convertJSONStringToPlayer(jsonString)  {
    return JSON.parse(jsonString);
}

function updatePlayerInfo(json) {
    playerHealth = json.health;
    playerTeam = json.team;
    playerId = json.id;
}
