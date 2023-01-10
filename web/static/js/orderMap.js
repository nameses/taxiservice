const apiKey = config.apiTomtom
const passengerInitCoordinates = [30.515614, 50.447339]
const submit = document.getElementById('submit-button');
const mainText = document.getElementById('main-text');
let passengerStartMarker;
let passengerDestinationMarker;


//map
const map = tt.map({
    key: apiKey,
    container: "map",
    center: passengerInitCoordinates,
    zoom: 11,
})
//create first market
passengerStartMarker = createMarker(
    passengerInitCoordinates
    //new tt.Popup({offset: 35}).setHTML("Place your current place!")
)
//turn on marker
passengerStartMarker.togglePopup()
//1st and 2nd on click handler
let startMapHandler = function (event) {
    const position = event.lngLat
    tt.services
        .reverseGeocode({
            key: apiKey,
            position: position,
        })
        .then(function (results) {
            drawPassengerMarkerOnMap(results)
        })
}
let finalMapHandler = function (event) {
    const position = event.lngLat
    tt.services
        .reverseGeocode({
            key: apiKey,
            position: position,
        })
        .then(function (results) {
            drawFinalMarkerOnMap(results)
        })
}

//set 1st on click event
map.on("click", startMapHandler)

function submitEventListener(event) {
    if (passengerDestinationMarker != null) {
        map.off("click", finalMapHandler);
        submit.removeEventListener("click", submitEventListener);
        drawAllRoutes();
    } else {
        mainText.textContent = "Place destination place!"
        passengerDestinationMarker = createMarker(
            passengerInitCoordinates,
            new tt.Popup({offset: 35}).setHTML("Place destination!")
        )
        map.off("click", startMapHandler);
        map.on("click", finalMapHandler);
        passengerDestinationMarker.togglePopup();
    }
}

// onclick event for button 'submit'
submit.addEventListener("click", submitEventListener)

function createMarker(markerCoordinates) {
    return new tt.Marker()
        .setLngLat(markerCoordinates)
        .addTo(map)
}

function drawPassengerMarkerOnMap(geoResponse) {
    if (geoResponse && geoResponse.addresses &&
        geoResponse.addresses[0].address.freeformAddress
    ) {
        passengerStartMarker.remove()
        passengerStartMarker = createMarker(geoResponse.addresses[0].position)
        passengerStartMarker.togglePopup()
    }
}

function drawFinalMarkerOnMap(geoResponse) {
    if (geoResponse && geoResponse.addresses &&
        geoResponse.addresses[0].address.freeformAddress
    ) {
        passengerDestinationMarker.remove()
        passengerDestinationMarker = createMarker(geoResponse.addresses[0].position);
            // new tt.Popup({offset: 35}).setHTML(
            //     geoResponse.addresses[0].address.freeformAddress
        passengerDestinationMarker.togglePopup()
    }
}

function drawAllRoutes() {
    tt.services
        .calculateRoute({
            batchMode: "sync",
            key: apiKey,
            batchItems: [{
                locations: [passengerStartMarker.getLngLat(), passengerDestinationMarker.getLngLat()],
                travelMode: 'car',
                routeRepresentation: 'polyline',
                computeBestOrder: 'true',
                routeType: 'fastest',
                traffic: 'true'
            }]
        })
        .then(function (resultData) {
            resultData.batchItems.forEach(function (routeData, index) {
                const routeGeoJson = routeData.toGeoJson()
                let routeLength = routeGeoJson.features[0].properties.summary.lengthInMeters
                // routeGeoJson.features[0].properties.summary.travelTimeInSeconds
                map.addLayer({
                        id: 'route_1',
                        type: "line",
                        source: {
                            type: "geojson",
                            data: routeData.toGeoJson(),
                        },
                        paint: {
                            "line-color": "black",
                            "line-width": 5,
                        },
                        layout: {
                            "line-cap": "round",
                            "line-join": "round",
                        }
                    }
                )
                mainText.textContent = "Go to the next step to enter some details!"
                submit.disabled = true
                document.querySelector('#input-route-length').value = routeLength;
                document.querySelector('#redirect-button').disabled = false;
                map.disabled = true
                document.querySelector('#start-marker1').value = passengerStartMarker.getLngLat().toArray()[0];
                document.querySelector('#start-marker2').value = passengerStartMarker.getLngLat().toArray()[1];
                document.querySelector('#final-marker1').value = passengerDestinationMarker.getLngLat().toArray()[0];
                document.querySelector('#final-marker2').value = passengerDestinationMarker.getLngLat().toArray()[1];
            })
        })

}

