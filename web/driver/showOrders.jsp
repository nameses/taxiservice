<%@ page import="java.util.Objects" %>
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
    <div class="col-sm">
        <a class="btn btn-secondary"
           href="${pageContext.request.contextPath}/driver?command=showOrdersPage">Reload</a>
        <%--        <form action="${pageContext.request.contextPath}/driver?command=showOrdersPage" method="GET">--%>
        <%--            <input type="hidden" name="command" value="listOrders"/>--%>
        <%--            <input type="hidden" name="filterBy" value="username"/>--%>
        <%--            <div class="form-group">--%>
        <%--                <label for="filterValue_username">Filter by username:</label>--%>
        <%--                <input type="text" class="form-control" id="filterValue_username" placeholder="username"--%>
        <%--                       name="filterValue" required>--%>
        <%--            </div>--%>
        <%--            <button type="submit" class="btn btn-primary">Filter</button>--%>
        <%--        </form>--%>
        <%--        <form action="${pageContext.request.contextPath}/controller" method="POST">--%>
        <%--            <input type="hidden" name="command" value="listOrders"/>--%>
        <%--            <input type="hidden" name="filterBy" value="licensePlate"/>--%>
        <%--            <div class="form-group">--%>
        <%--                <label for="filterValue_licensePlate">Filter by taxi license:</label>--%>
        <%--                <input type="text" class="form-control"--%>
        <%--                       id="filterValue_licensePlate"--%>
        <%--                       placeholder="license plate"--%>
        <%--                       name="filterValue" required>--%>
        <%--            </div>--%>
        <%--            <button type="submit" class="btn btn-primary">Filter</button>--%>
        <%--        </form>--%>
    </div>
    <div class="container col-md-8">
        <h3 class="text-center">List of Orders</h3>
        <table class="table table-bordered">
            <thead>
            <tr>
                <%--                <th>Time, order opened</th>--%>
                <th>
                    <form method="get" action="${pageContext.request.contextPath}/driver">
                        <input type="hidden" name="command" value="showOrders"/>
                        <input type="hidden" name="orderByElement" value="orderopened"/>
                        <input type="hidden" name="orderBySorting"
                               value="${sessionScope.sortFilterDTO.orderBy.value}"/>
                        <a type="submit">Ordeer</a>
                    </form>
                </th>
                <th>Capacity</th>
                <th>Category</th>
                <th>Length</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${requestScope.orderList}">
                <tr>
                    <td><c:out value="${order.orderOpened}"/></td>
                    <td><c:out value="${order.carCapacity}"/></td>
                    <td><c:out value="${order.carCategory}"/></td>
                    <td><c:out value="${requestScope.mapOrderIDToRoute[order.orderID].length}"/></td>
                    <td>
                        <a href="/driver?command=viewOrderDetails&orderid=<c:out value='${order.orderID}' />">View</a>
                        <a href="/driver?command=confirmOrder&orderid=<c:out value='${order.orderID}' />">Confirm</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col-sm"></div>
</div>
</body>
</html>
