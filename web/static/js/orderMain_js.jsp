<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    const apiKey = config.apiTomtom
    const passengerInitCoordinates = [30.515614, 50.447339]
    // convert arrays of coordinates from java session to js
    let startMarkerCoord = Array.of(${sessionScope.order.route.startMarker[0]},
        ${sessionScope.order.route.startMarker[1]});
    let finalMarkerCoord = Array.of(${sessionScope.order.route.finalMarker[0]},
        ${sessionScope.order.route.finalMarker[1]});
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
</script>