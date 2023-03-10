<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
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
        <h3 id="main-text">Place your current place!</h3>
        <div class="md" id="map" style="height: 70vh;width: 50vw;margin-top: 10px"></div>
        <form method="POST" id="form-post" action="${pageContext.request.contextPath}/client">
            <input type="hidden" name="command" value="orderRoute" />
            <input type="hidden" name="routeLength" id="input-route-length" />
            <input type="hidden" name="startMarker[]" id="start-marker1" value="" />
            <input type="hidden" name="startMarker[]" id="start-marker2" value="" />
            <input type="hidden" name="finalMarker[]" id="final-marker1" value="" />
            <input type="hidden" name="finalMarker[]" id="final-marker2" value="" />
            <input class="btn btn-outline-dark" type="button" id="submit-button" value="Submit" />
            <button class="btn btn-outline-dark" type="submit" id="redirect-button" disabled>Next Step</button>
            <c:if test="${pageScope.MESSAGE!=null}">
                <h3>${pageScope.MESSAGE}</h3>
            </c:if>
        </form>
    </div>
    <script src="../../static/js/config.js"></script>
    <script src="../../static/js/orderMap.js"></script>
</div>
</body>
</html>
