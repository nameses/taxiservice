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
           href="${pageContext.request.contextPath}/controller?command=listOrders">Reload</a>
        <form action="${pageContext.request.contextPath}/controller" method="POST">
            <input type="hidden" name="command" value="listOrders"/>
            <input type="hidden" name="filterBy" value="username"/>
            <div class="form-group">
                <label for="filterValue_username">Filter by username:</label>
                <input type="text" class="form-control" id="filterValue_username" placeholder="username"
                       name="filterValue" required>
            </div>
            <button type="submit" class="btn btn-primary">Filter</button>
        </form>
        <form action="${pageContext.request.contextPath}/controller" method="POST">
            <input type="hidden" name="command" value="listOrders"/>
            <input type="hidden" name="filterBy" value="licensePlate"/>
            <div class="form-group">
                <label for="filterValue_licensePlate">Filter by taxi license:</label>
                <input type="text" class="form-control"
                       id="filterValue_licensePlate"
                       placeholder="license plate"
                       name="filterValue" required>
            </div>
            <button type="submit" class="btn btn-primary">Filter</button>
        </form>
    </div>
    <div class="container col-md-8">
        <h3 class="text-center">List of Orders</h3>
        <table class="table table-bordered">
            <%
                if (session.getAttribute("ifASC") == null) session.setAttribute("ifASC", true);
                session.setAttribute("ifASC", !(Boolean) session.getAttribute("ifASC"));
            %>
            <%
                String str = null;
                String sort = null;
                try {
                    str = session.getAttribute("orderByString").toString();
                    sort = session.getAttribute("orderBySort").toString();
                    sort = sort.equals("ASC") ? "▲" : "▼";
                } catch (Exception e) {
                    e.printStackTrace();
                }
            %>
            <thead>
            <tr>
                <th>OrderID</th>
                <th>Client username</th>
                <th>Taxi License Plate</th>
                <th>
                    <a href="${pageContext.request.contextPath}/controller?command=listOrders&orderByString=orderopened&orderBySort=${sessionScope.ifASC?"ASC":"DESC"}">
                        Time, order opened <%out.print(Objects.equals(str, "orderopened") ? sort : "");%>
                    </a>
                </th>
                <th>
                    <a href="${pageContext.request.contextPath}/controller?command=listOrders&orderByString=orderaccepted&orderBySort=${sessionScope.ifASC?"ASC":"DESC"}">
                        Time, order accepted <%out.print(Objects.equals(str, "orderaccepted") ? sort : "");%>
                    </a>
                </th>
                <th>
                    <a href="${pageContext.request.contextPath}/controller?command=listOrders&orderByString=cost&orderBySort=${sessionScope.ifASC?"ASC":"DESC"}">
                        Cost <%out.print(Objects.equals(str, "cost") ? sort : "");%>
                    </a>
                </th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${sessionScope.listOrders}">
                <tr>
                    <td><c:out value="${order.orderID}"/></td>
                    <td><c:out value="${order.user.username}"/></td>
                    <td><c:out value="${order.taxi.licensePlate}"/></td>
                    <td><c:out value="${order.orderOpened}"/></td>
                    <td><c:out value="${order.orderAccepted}"/></td>
                    <td><c:out value="${order.cost}"/></td>
                    <td><c:out value="${order.status}"/></td>
                    <td>
                        <a href="delete?id=<c:out value='${order.taxiID}' />">Delete</a>
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
