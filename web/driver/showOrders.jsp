<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>List of Orders</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="row">
    <div class="col-sm"></div>
    <div class="container col-md-8">
        <h3 class="text-center mt-3 mb-3">List of Orders</h3>
        <div class="" style="display: inline-block;">
            <a class="btn btn-secondary"
               href="${pageContext.request.contextPath}/driver?command=showOrdersPage">Reload</a>
            <div class="dropdown ml-5" style="display: inline-block;">
                <form action="${pageContext.request.contextPath}/driver" method="get" onsubmit="checkCapacity()">
                    <input type="hidden" name="command" value="showOrders"/>

                    <label>Car category: </label>
                    <select name="category">
                        <option name="category" value="" disabled selected>Select an option</option>
                        <c:forEach var="category" items="${requestScope.listCategories}">
                            <option name="category" value="${category.toString()}">${category.toString()}</option>
                        </c:forEach>
                    </select>
                    <label>Maximum capacity: </label>
                    <input type="number" id="maxCapacity" name="maxCapacity"/>

                    <input type="submit" value="Submit"/>
                </form>
                <script>
                    function checkCapacity() {
                        const maxCapacity = document.getElementById("maxCapacity")
                        if (maxCapacity.value === "")
                            maxCapacity.disabled = true
                    }
                </script>
            </div>
            <form id="formOrderBy" action="${pageContext.request.contextPath}/driver" method="get">
                <input type="hidden" name="command" value="showOrders"/>

                <label>Car category: </label>
                <select id="orderBy" onchange="updateAndSubmit()">
                    <option disabled selected>Select an option</option>
                    <option value="orderBy-time-asc">Time Ascending</option>
                    <option value="orderBy-time-desc">Time Descending</option>
                    <option value="orderBy-cap-asc">Capacity Ascending</option>
                    <option value="orderBy-cap-desc">Capacity Descending</option>
                    <option value="orderBy-length-asc">Length Ascending</option>
                    <option value="orderBy-length-desc">Length Descending</option>
                </select>
                <input type="hidden" name="inputKey" id="input-key"/>
                <input type="hidden" name="inputValue" id="input-value"/>
                <input type="submit" value="Submit" hidden/>
            </form>
            <script src="../static/js/updateAndSubmitForm.js"></script>
        </div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Date and time, order opened</th>
                <th>Capacity</th>
                <th>Category</th>
                <th>Length, m</th>
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
