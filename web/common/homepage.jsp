<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Taxi Service</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<jsp:include page="/common/header.jsp"/>
<div class="border-bottom pb-3 m-3 small lh-sm">
    <c:choose>
        <c:when test="${sessionScope.user.role=='client'}">
            <a href="${pageContext.request.contextPath}/user/order/order.jsp"
               class="btn btn-outline-dark btn-sm">Order taxi</a>
        </c:when>
        <c:otherwise>
            <a href="${pageContext.request.contextPath}/controller?command=listTaxis">List of taxis</a>
            <a href="${pageContext.request.contextPath}/controller?command=listOrders">List of orders</a>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
