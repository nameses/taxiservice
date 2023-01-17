<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<jsp:include page="/common/header.jsp"/>
<div class="container col-md-5">
    <div class="text-center h2">
        <b>${sessionScope.user.username}'s</b> profile
    </div>
    <c:choose>
        <c:when test="${sessionScope.user.role=='client'}">
            <p>
                Full name: ${sessionScope.user.fullname}<br/>
                Phone: ${sessionScope.user.phone}<br/>
                Email: ${sessionScope.user.email}<br/>
                Bonus points: ${sessionScope.client.bonusPoints}
            </p>
        </c:when>
        <c:when test="${sessionScope.user.role=='admin'}">
        </c:when>
        <c:when test="${sessionScope.user.role=='driver'}">
            <div class="border-bottom h4">
                <p>
                    Full name: ${sessionScope.user.fullname}<br/>
                    Phone: ${sessionScope.user.phone}<br/>
                    Email: ${sessionScope.user.email}<br/>
                    Your current status: ${sessionScope.driver.driverStatus}
                </p>
            </div>
            <c:if test="${requestScope.taxi==null}">
                <a href="${pageContext.request.contextPath}/driver?command=newTaxiPage">Register your taxi</a>
            </c:if>
            <c:if test="${requestScope.taxi!=null}">
                <div class="border-bottom h4">
                    <p>
                        Taxi's license plate: ${requestScope.taxi.licensePlate}<br/>
                        Category: ${requestScope.taxi.category}<br/>
                        Capacity: ${requestScope.taxi.capacity}<br/>
                        Fare (UAH per km): ${requestScope.taxi.fare}
                    </p>
                </div>
            </c:if>
        </c:when>
    </c:choose>
</div>
</body>
</html>
