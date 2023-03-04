<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Main</title>
    <meta charset="UTF-8"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="https://api.tomtom.com/maps-sdk-for-web/cdn/6.x/6.6.0/maps/maps.css"/>
    <script src="https://api.tomtom.com/maps-sdk-for-web/cdn/6.x/6.6.0/maps/maps-web.min.js"></script>
    <script src="https://api.tomtom.com/maps-sdk-for-web/cdn/6.x/6.6.0/services/services-web.min.js"></script>
</head>
<body>
<jsp:include page="/common/header.jsp"/>
<div class="container col-md-5">
    <div>
        <h3 id="main-text">ID#${sessionScope.order.orderID} Order details:</h3>
        <div class="md" id="map" style="height: 50vh;width: 40vw;margin-top: 5px"></div>
        <div class="w-100 d-flex justify-content-between">
            <c:choose>
                <c:when test="${sessionScope.order.orderStatus=='on_route'}">
                    <b>Order accepted: </b>${sessionScope.order.orderAccepted}<br/>
                    <b>Cost: </b>${sessionScope.order.cost}<br/>
                    <form method="POST"
                          action="${pageContext.request.contextPath}/driver?command=endOrder">
                        <button class="btn btn-outline-danger" type="submit">End order</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <p>
                        <b>Order opened: </b>${sessionScope.order.orderOpened}<br/>
                        <b>Declared car capacity: </b>${sessionScope.order.carCapacity}<br/>
                        <b>Minimum car category: </b>${sessionScope.order.carCategory}<br/>
                        <b>Total route length: </b>${sessionScope.route.length}<br/>
                    </p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <script src="../static/js/config.js"></script>
    <script>
        let startMarkerCoord = (${requestScope.route.startMarker})
        let finalMarkerCoord = (${requestScope.route.finalMarker})
    </script>
    <script src="../static/js/mapRenderer.js"></script>
</div>
</body>
</html>
