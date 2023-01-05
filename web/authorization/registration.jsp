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
<div class="row">
    <div class="col-4"></div>
    <div class="col-4 justify-content-center text-center">
        <h2>User Registration Form</h2>
        <c:if test="${not empty pageScope.MESSAGE}">
            <div class="alert alert-success center" role="alert">
                <p>${pageScope.MESSAGE}</p>
            </div>
        </c:if>
        <form action="${pageContext.request.contextPath}/controller" method="POST"
              oninput='password_conf.setCustomValidity(password_conf.value !== password.value ? "Passwords do not match." : "")'>
            <input type="hidden" name="command" value="registration"/>
            <div class="form-group">
                <label for="fullname">Full Name:</label>
                <input type="text" class="form-control" name="fullname" placeholder="Full name"
                       pattern="^[a-zA-Z ]*$" minlength="5" maxlength="36"
                       value="<c:out value="${pageScope.fullname}"/>" id="fullname" required>
            </div>
            <div class="form-group">
                <label for="username">User Name:</label>
                <input type="text" class="form-control" name="username" placeholder="Username"
                       pattern="^[a-zA-Z][A-Za-z0-9_]*$" minlength="5" maxlength="30"
                       value="<c:out value="${pageScope.username}"/>" id="username" required>
                <small>May contain letters, numbers and underscore. But can't start with number or _</small>
            </div>
            <div class="form-group">
                <label for="password">Password(from 8 to 32 symbols):</label>
                <input type="password" class="form-control" name="password"
                       minlength="8" maxlength="32" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                       placeholder="Password" id="password" required>
                <small>Must contain lower- and uppercase letters and numbers</small>
            </div>
            <div class="form-group">
                <label for="password_conf">Confirm password:</label>
                <input type="password" class="form-control" name="confirm_password"
                       minlength="8" maxlength="32" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                       placeholder="Password" id="password_conf" required>
            </div>
            <div class="form-group">
                <label for="phone">Phone:</label>
                <input type="tel" class="form-control" name="phone" placeholder="Phone"
                       pattern="^\+380\d{9}" maxlength="13"
                       value="<c:out value="${pageScope.phone}"/>" id="phone" required>
                <small>Format: +380682155488</small>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" name="email" placeholder="Email"
                       maxlength="30"
                       value="<c:out value="${pageScope.email}"/>" id="email" required>
            </div>
            <div class="form-group">
                <label for="role">Role:</label>
                <select class="form-select" name="role" id="role" required>
                    <option>client</option>
                    <option>driver</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary mt-3">Submit</button>
        </form>
    </div>
</div>
</body>
</html>
