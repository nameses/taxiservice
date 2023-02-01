const apiKey = config.apiTomtom
const passengerInitCoordinates = [30.515614, 50.447339]

// you must define startMarkerCoord and finalMarkerCoord (2 arrays of markers) before these script

//map
const map = tt.map({
    key: apiKey,
    container: "map",
    center: passengerInitCoordinates,
    zoom: 11,
})
//add markers to map
let startMarker = new tt.Marker().setLngLat(startMarkerCoord).addTo(map);
let finalMarker = new tt.Marker().setLngLat(finalMarkerCoord).addTo(map);
//add route layer
tt.services
    .calculateRoute({
        batchMode: "sync",
        key: apiKey,
        batchItems: [{
            locations: [startMarker.getLngLat(), finalMarker.getLngLat()],
            travelMode: 'car',
            routeRepresentation: 'polyline',
            computeBestOrder: 'true',
            routeType: 'fastest',
            traffic: 'true'
        }]
    })
    .then(function (resultData) {
        resultData.batchItems.forEach(function (routeData) {
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
        })
    })