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
        <h3 id="main-text">Driver proposition</h3>
        <div class="w-100 d-flex justify-content-between">
            <h4>
                <b>Full name:</b> ${requestScope.driverDetails.fullname}<br/>
                <b>Email:</b> ${requestScope.driverDetails.email}<br/>
                <b>Phone:</b> ${requestScope.driverDetails.phone}<br/>
                <b>Driver's car capacity: </b>${requestScope.driverTaxi.capacity}<br/>
                <b>Car category: </b>${requestScope.driverTaxi.category}<br/>
                Fare of this car is ${requestScope.driverTaxi.fare}for km.<br/>
                Approximate cost of full taxi ride is ${requestScope.cost}.<br/>
            </h4>
        </div>
        <div class="d-flex mt-3 justify-content-center">
            <form method="POST"
                  action="${pageContext.request.contextPath}/client?command=acceptDriver">
                <input type="hidden" value="${sessionScope.order.orderID}">
                <button class="btn btn-outline-dark" type="submit">Accept</button>
            </form>
            <form method="POST"
                  action="${pageContext.request.contextPath}/client?command=declineDriver">
                <input type="hidden" value="${sessionScope.order.orderID}">
                <button class="btn btn-outline-danger" type="submit">Decline</button>
            </form>
        </div>
        <
    </div>
</div>
</body>
</html>
