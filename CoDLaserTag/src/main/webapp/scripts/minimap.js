/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var minimap = document.getElementById("minimap");
var minimap_context = minimap.getContext("2d");
var minimap_size = 500; // Distance in feet
var degrees_per_foot = 1 / 316800; // Based on 1/60 degree = 1 mi. Roughly accurate for most areas
var degree_range = minimap_size * degrees_per_foot;
var minimap_range = {"min_latitude":0,"max_latitude":100,"min_longitude":0,"max_longitude":100};

function updateMap() {
    minimap_context.clearRect(0,0,minimap.height, minimap.width);
    minimap_range.min_latitude = playerCurrentLocation.latitude - degree_range;
    minimap_range.max_latitude = playerCurrentLocation.latitude + degree_range;
    minimap_range.min_longitude = playerCurrentLocation.longitude - degree_range;
    minimap_range.max_longitude = playerCurrentLocation.longitude + degree_range;
    addPlayerToMap(playerCurrentLocation.latitude,playerCurrentLocation.longitude,true);
}

function addPlayerToMap(latitude, longitude, friendly)   {
    var x = (latitude - minimap_range.min_latitude) / (minimap_range.max_latitude - minimap_range.min_latitude) * minimap.width;
    var y = (longitude - minimap_range.min_longitude) / (minimap_range.max_longitude - minimap_range.min_longitude) * minimap.width;
    var color = "#FF0000";
    if (friendly === true)
        color = "#0000FF";
    var json = JSON.stringify({
        "color": color,
        "coords":   {
            "x":x,
            "y":y
        }
    });
    document.getElementById("xy").innerHTML += "Player at (" + x + ", " + y + ")<br />";
    drawImageText(json);
}

function drawImageText(image)   {
    var json = JSON.parse(image);
    minimap_context.fillStyle = json.color;
    minimap_context.beginPath();
    minimap_context.arc(json.coords.x, json.coords.y, 2, 0, 2 * Math.PI, false);
    minimap_context.fill();
}