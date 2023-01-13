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
        <c:when test="${sessionScope.client.user.role=='client'}">
            <c:if test="${sessionScope.order==null}">
                <a href="${pageContext.request.contextPath}/client?command=orderMapPage"
                   class="btn btn-outline-dark btn-sm">Order taxi</a>
            </c:if>
            <c:if test="${sessionScope.order!=null}">
                <a href="${pageContext.request.contextPath}/client?command=orderMainPage"
                   class="btn btn-outline-dark btn-sm">Show current order</a>
            </c:if>
        </c:when>
        <c:when test="${sessionScope.user.role=='admin'}">
            <a class="btn btn-outline-dark btn-sm"
               href="${pageContext.request.contextPath}/admin?command=listTaxis">List of taxis</a>
            <a class="btn btn-outline-dark btn-sm"
               href="${pageContext.request.contextPath}/admin?command=listOrders">List of orders</a>
        </c:when>
        <c:otherwise>
            <a class="btn btn-outline-dark btn-sm"
               href="${pageContext.request.contextPath}/driver?command=showOrders">Show list of available orders</a>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
