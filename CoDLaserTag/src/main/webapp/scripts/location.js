/* 
 * This script handles the GPS location of the current user
 */

var x = document.getElementById("location");
function startWatchingLocation()  {
    if (navigator.geolocation)  {
        navigator.geolocation.watchPosition(listPosition);
    }
    else    {
        x.innerHTML="Geolocation is not supported by this browser.";
    }
}

function listPosition(position) {
    x.innerHTML = "Latitude: " + position.coords.latitude + 
            "<br />Longitude: " + position.coords.longitude + 
            "<br />Accuracy: " + position.coords.accuracy +
            "<br />Altitude: " + position.coords.altitude;
    
    playerCurrentLocation = { 
        "longitude": position.coords.longitude, 
        "latitude": position.coords.latitude, 
        "accuracy": position.coords.accuracy
     };
}
