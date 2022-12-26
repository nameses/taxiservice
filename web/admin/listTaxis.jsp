<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of Taxis</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="row">
    <div class="container col-md-8">
        <h3 class="text-center">List of Taxis</h3>
        <%--        <hr>--%>
        <%--        <div class="container text-left">--%>
        <%--            <a href="<%=request.getContextPath()%>/controller?command=newTaxi"--%>
        <%--               class="btn btn-success">Add Taxi</a>--%>
        <%--        </div>--%>
        <%--        <br>--%>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>DriverName</th>
                <th>LicensePlate</th>
                <th>Fare, hrn per km</th>
                <th>Capacity</th>
                <th>Category</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="taxi" items="${sessionScope.listTaxis}">
                <tr>
                    <td><c:out value="${taxi.driverName}"/></td>
                    <td><c:out value="${taxi.licensePlate}"/></td>
                    <td><c:out value="${taxi.fare}"/></td>
                    <td><c:out value="${taxi.capacity}"/></td>
                    <td><c:out value="${taxi.category}"/></td>
                    <td>
                        <c:out value="${taxi.status}"/>
                        <c:choose>
                            <c:when test="${taxi.status=='available'}">
                                <br/>
                                <a href="<%=request.getContextPath()%>
                                /controller?command=updateTaxiStatus&id=${taxi.taxiID}&toStatus=inactive">disable</a>
                            </c:when>
                            <c:when test="${taxi.status=='inactive'}">
                                <br/>
                                <a href="<%=request.getContextPath()%>
                                /controller?command=updateTaxiStatus&id=${taxi.taxiID}&toStatus=available">activate</a>
                            </c:when>
                            <c:otherwise></c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="delete?id=<c:out value='${taxi.taxiID}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>


</body>
</html>
