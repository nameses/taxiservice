<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<jsp:include page="../../static/js/jstl_sql_setDataSource.jsp"/>
<sql:query dataSource="${sessionScope.sql_database}" var="resultRoute"
           sql="SELECT * FROM route WHERE orderid=${sessionScope.order.orderID}"/>

<script>
    const apiKey = config.apiTomtom
    const passengerInitCoordinates = [30.515614, 50.447339]

    // convert arrays of coordinates from sql query to js
    <c:forEach var="route" items="${resultRoute.rows}">
    let startMarkerCoord = ("${route.startMarker}").replaceAll("{","").replaceAll("}","").split(",")
    let finalMarkerCoord = ("${route.finalMarker}").replaceAll("{","").replaceAll("}","").split(",")
    </c:forEach>
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