<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of Orders</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="row">
    <div class="container col-md-8">
        <h3 class="text-center">List of Orders</h3>
        <%--        <hr>--%>
        <%--        <div class="container text-left">--%>
        <%--            <a href="<%=request.getContextPath()%>/controller?command=newTaxi"--%>
        <%--               class="btn btn-success">Add Taxi</a>--%>
        <%--        </div>--%>
        <%--        <br>--%>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>OrderID</th>
                <th>Client username</th>
                <th>Taxi License Plate</th>
                <th>Time, order opened</th>
                <th>Time, order accepted</th>
                <th>Cost</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${sessionScope.listOrders}">
                <tr>
                    <td><c:out value="${order.orderID}"/></td>
                    <td><c:out value="${}"/></td>
                    <td><c:out value="${}"/></td>
                    <td><c:out value="${order.orderOpened}"/></td>
                    <td><c:out value="${order.orderAccepted}"/></td>
                    <td><c:out value="${order.cost}"/></td>
                    <td>
                        <a href="delete?id=<c:out value='${order.taxiID}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>


</body>
</html>
