/* 
 * This script handles the GPS location of the current user
 */

var x = document.getElementById("location");
function getLocation()  {
    if (navigator.geolocation)  {
        navigator.geolocation.watchPosition(showPosition);
    }
    else    {
        x.innerHTML="Geolocation is not supported by this browser.";
    }
}

function showPosition(position) {
    x.innerHTML="Latitude: " + position.coords.latitude + 
            " <br />Longitude: " + position.coords.longitude + 
            "<br />Accuracy: " + position.coords.accuracy;
}

