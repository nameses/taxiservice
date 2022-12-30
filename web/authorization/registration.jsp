<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="container">
    <h2>User Register Form</h2>
    <div class="col-md-6 col-md-offset-3">
        <c:if test="${not empty pageScope.MESSAGE}">
            <div class="alert alert-success center" role="alert">
                <p>${pageScope.MESSAGE}</p>
            </div>
        </c:if>
        <form action="${pageContext.request.contextPath}/controller" method="POST">
            <input type="hidden" name="command" value="registration"/>
            <div class="form-group">
                <label for="firstname">First Name:</label>
                <input type="text" class="form-control" id="firstname" placeholder="First Name"
                       value="<c:out value="${pageScope.firstname}"/>" name="firstname" required>
            </div>
            <div class="form-group">
                <label for="lastname">Last Name:</label>
                <input type="text" class="form-control" id="lastname" placeholder="last Name"
                       value="<c:out value="${pageScope.lastname}"/>" name="lastname" required>
            </div>
            <div class="form-group">
                <label for="username">User Name:</label>
                <input type="text" class="form-control" id="username" placeholder="Username"
                       value="<c:out value="${pageScope.username}"/>" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password"
                       placeholder="Password" name="password" required>
            </div>
            <div class="form-group">
                <label for="confirm_password">Confirm password:</label>
                <input type="password" class="form-control" id="confirm_password"
                       placeholder="Password" name="confirm_password" required>
            </div>
            <div class="form-group">
                <label for="phone">Phone:</label>
                <input type="text" class="form-control" id="phone" placeholder="Phone"
                       value="<c:out value="${pageScope.phone}"/>" name="phone" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="text" class="form-control" id="email" placeholder="Email"
                       value="<c:out value="${pageScope.email}"/>" name="email" required>
            </div>
            <div class="form-group">
                <label for="role">Role:</label>
                <select name="role" id="role" required>
                    <option>admin</option>
                    <option>client</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>

    </form>
    </div>
    </div>
    </body>
    </html>
