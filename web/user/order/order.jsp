<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
    <meta charset="UTF-8"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="https://api.tomtom.com/maps-sdk-for-web/cdn/6.x/6.6.0/maps/maps.css"
    />
    <script src="https://api.tomtom.com/maps-sdk-for-web/cdn/6.x/6.6.0/maps/maps-web.min.js"></script>
    <script src="https://api.tomtom.com/maps-sdk-for-web/cdn/6.x/6.6.0/services/services-web.min.js"></script>
    <style>
        body {
            margin: 0;
        }

        #map {
            height: 50vh;
            width: 50vw;
        }

        #submit-button {
            background: #df1b12;
            padding: 10px;
            margin-top: 10px;
            width: 100%;
            color: white;
            font-weight: bold;
            transition: background-color .15s ease-in-out;
            text-transform: uppercase;
            border: none;
            outline: none;
        }

        #submit-button:hover {
            cursor: pointer;
            background: #b1110e;
        }

        #labels-container {
            font-family: "Helvetica Neue", Arial, Helvetica, sans-serif;
            position: fixed;
            top: 10px;
            right: 10px;
            width: 400px;
            padding: 10px;
            margin: 10px;
            background-color: white;
            box-shadow: rgba(0, 0, 0, 0.45) 2px 2px 2px 0px;
        }

        #labels-container label {
            line-height: 2;
            font-size: 1.2em;
            font-weight: bold;
        }

        #labels-container #route-labels div {
            border-left: 6px solid;
            padding-left: 5px;
            margin-top: 3px;
        }

        #route-labels div:hover {
            cursor: pointer;
            box-shadow: 0px 2px #888888;
        }

        #modal {
            display: none;
            position: fixed;
            z-index: 1100;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.5);
        }

        #modal-content {
            background-color: lightgray;
            color: #555;
            font-family: "Helvetica Neue", Arial, Helvetica, sans-serif;
            font-weight: bold;
            text-align: center;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 20%;
        }
    </style>
</head>
<body>
<jsp:include page="/common/header.jsp"/>
<div class="container col-md-5">
    <div id="map"></div>
    <div id="labels-container">
        <label>Find the taxi that will arrive fastest</label>
        <div id="route-labels"></div>
        <input type="button" id="submit-button" value="Submit">
    </div>
    <div id="modal">
        <div id="modal-content"></div>
    </div>
    <script>
        const apiKey = '7KITbDwmEhgHG0ebDknTDA7L8cO1HiRP';
        const passengerInitCoordinates = [30.515614, 50.447339]
        let passengerMarker
        let passengerFirstMarker

        const map = tt.map({
            key: apiKey,
            container: "map",
            center: passengerInitCoordinates,
            zoom: 11,
        })


        passengerMarker = createMarker(
            passengerInitCoordinates,
            new tt.Popup({offset: 35}).setHTML(
                "Click anywhere on the map to change passenger location."
            )
        )


        passengerMarker.togglePopup()

        let startMapHandler = function (event) {
            const position = event.lngLat
            tt.services
                .reverseGeocode({
                    key: apiKey,
                    position: position,
                })
                .then(function (results) {
                    drawPassengerMarkerOnMap(results)
                    console.log(passengerMarker.anchor, 'start')
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
                    console.log(passengerFirstMarker.anchor, 'final')
                })
        }

        map.on("click", startMapHandler)

        const submit = document.getElementById('submit-button');

        submit.addEventListener('click', () => {

            passengerFirstMarker = createMarker(
                passengerInitCoordinates,
                new tt.Popup({offset: 35}).setHTML(
                    "Click anywhere on the map to change final location."
                )
            )

            map.off("click", startMapHandler);

            map.on("click", finalMapHandler);

            passengerFirstMarker.togglePopup();
        })

        function createMarker(markerCoordinates, popup) {
            const passengerMarkerElement = document.createElement("div")
            passengerMarkerElement.innerHTML = "<img src='dot.png' style='width: 30px; height: 30px'>"
            return new tt.Marker({element: passengerMarkerElement})
                .setLngLat(markerCoordinates)
                .setPopup(popup)
                .addTo(map)
        }


        function drawPassengerMarkerOnMap(geoResponse) {
            if (geoResponse && geoResponse.addresses &&
                geoResponse.addresses[0].address.freeformAddress
            ) {
                passengerMarker.remove()
                passengerMarker = createMarker(
                    geoResponse.addresses[0].position,
                    new tt.Popup({offset: 35}).setHTML(
                        geoResponse.addresses[0].address.freeformAddress
                    )
                )
                passengerMarker.togglePopup()
            }
        }

        function drawFinalMarkerOnMap(geoResponse) {
            if (geoResponse && geoResponse.addresses &&
                geoResponse.addresses[0].address.freeformAddress
            ) {
                passengerFirstMarker.remove()
                passengerFirstMarker = createMarker(
                    geoResponse.addresses[0].position,
                    new tt.Popup({offset: 35}).setHTML(
                        geoResponse.addresses[0].address.freeformAddress
                    )
                )
                passengerFirstMarker.togglePopup()
            }
        }



    </script>
</div>
</body>
</html>
