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
        <h3 id="main-text">Order details page</h3>
        <p>Wait for driver... Don't logout from app, this leads to canceling of current order.<br/>
            Your order route:</p>
        <div class="md" id="map" style="height: 50vh;width: 40vw;margin-top: 5px"></div>
        <div class="d-flex justify-content-center">
            <form method="POST" id="form-post" action="${pageContext.request.contextPath}/client?command=deleteOrder">
                <input type="hidden" value="${sessionScope.order.orderID}">
                <button class="btn btn-outline-danger" type="submit" id="delete-button">Delete</button>
            </form>

        </div>
    </div>
    <script src="../../static/js/config.js"></script>
    <%--    <script src="../../static/js/orderMain.js"></script>--%>
    <jsp:include page="../../static/js/orderMain_js.jsp" />
</div>
</body>
</html>
