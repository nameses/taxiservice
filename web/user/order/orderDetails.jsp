<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order details</title>
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
        <h3 id="main-text">Write down some order details</h3>
        <form method="POST" id="form-post" action="${pageContext.request.contextPath}/client?command=orderDetails">
            <%--routeid in session--%>
            <%--order opened timestamp in back--%>
            <label for="car-capacity">Car capacity:</label>
            <input type="number" name="carCapacity" id="car-capacity"/>
            <div style="margin-left: 8px;">
                <label>Car category:</label>
                <div class="form-check">
                    <input type="radio" id="Budget" name="carCategory" value="budget" checked/>
                    <label for="Budget">Budget</label>
                </div>
                <div class="form-check">
                    <input type="radio" id="Standard" name="carCategory" value="standard"/>
                    <label for="Standard">Standard</label>
                </div>
                <div class="form-check">
                    <input type="radio" id="Comfort" name="carCategory" value="comfort"/>
                    <label for="Comfort">Comfort</label>
                </div>
                <div class="form-check">
                    <input type="radio" id="Premium" name="carCategory" value="premium"/>
                    <label for="Premium">Premium</label>
                </div>
            </div>
    </div>
    <button class="btn btn-outline-dark" type="submit">Submit</button>
    <c:if test="${pageScope.MESSAGE!=null}">
        <h3>${pageScope.MESSAGE}</h3>
    </c:if>
    </form>
</div>
<script src="../../static/js/config.js"></script>
<script src="../../static/js/order.js"></script>
</div>
</body>
</html>
