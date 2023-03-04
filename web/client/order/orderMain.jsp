<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
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
        <h3 id="main-text">Order details page</h3>
        <c:choose>
            <c:when test="${sessionScope.orderStatus=='on_route'}">
                <p><b>Driver is found.</b> Don't log out from app, this will lead to canceling of current order.</p>
                <br/>
            </c:when>
            <c:when test="${sessionScope.orderStatus=='confirmation'}">
                <a class="btn btn-outline-dark"
                   href="${pageContext.request.contextPath}/client?command=viewProposition">View driver's
                    proposition</a>
            </c:when>
            <c:otherwise>
                <p><b>Wait for driver...</b> Don't log out from app, this will lead to canceling of current order.</p>
                <br/>
            </c:otherwise>
        </c:choose>
        <p>Your order route:</p>
        <div class="md" id="map" style="height: 50vh;width: 40vw;margin-top: 5px"></div>
        <div class="w-100 d-flex justify-content-between">
            <p>
                <b>Order opened: </b>${sessionScope.order.orderOpened}<br/>
                <b>Declared car capacity: </b>${sessionScope.order.carCapacity}<br/>
                <b>Minimum car category: </b>${sessionScope.order.carCategory}<br/>
                <b>Total route length: </b>${sessionScope.route.length}<br/>
            </p>
        </div>
        <div class="d-flex mt-3 justify-content-center">
            <form method="POST" id="form-post"
                  action="${pageContext.request.contextPath}/client?command=deleteOrder">
                <input type="hidden" value="${sessionScope.order.orderID}">
                <button class="btn btn-outline-danger" type="submit" id="delete-button">Delete</button>
            </form>
        </div>
        <
    </div>

    <jsp:include page="../../static/js/jstl_sql_setDataSource.jsp"/>
    <sql:query dataSource="${sessionScope.sql_database}" var="resultRoute"
               sql="SELECT * FROM route WHERE orderid=${sessionScope.order.orderID}"/>

    <script>
        // convert arrays of coordinates from sql query to js
        <c:forEach var="route" items="${resultRoute.rows}">
        let startMarkerCoord = ("${route.startMarker}").replaceAll("{", "").replaceAll("}", "").split(",")
        let finalMarkerCoord = ("${route.finalMarker}").replaceAll("{", "").replaceAll("}", "").split(",")
        </c:forEach>
    </script>
    <script src="../../static/js/config.js"></script>
    <script src="../../static/js/mapRenderer.js"></script>
</div>
</body>
</html>
