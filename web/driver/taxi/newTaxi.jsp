<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Taxi</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<div class="container col-md-5">
    <form action="${pageContext.request.contextPath}/driver" method="post">
        <input type="hidden" name="command" value="newTaxi"/>
        <caption><h2>Add New Taxi</h2></caption>
        <div class="form-group">
            <label>License Plate</label>
            <input type="text"
                   value="<c:out value='${pageScope.taxi.licensePlate}' />"
                   class="form-control"
                   name="licensePlate" minlength="8" maxlength="8">
        </div>
        <div class="form-group">
            <label>Fare</label>
            <input type="number"
                   value="<c:out value='${pageScope.taxi.fare}' />" class="form-control"
                   name="fare" required="required">
        </div>
        <div class="form-group">
            <label>Capacity</label>
            <input type="number"
                   value="<c:out value='${pageScope.taxi.capacity}' />" class="form-control"
                   name="capacity" required="required">
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="category" value="budget" required="required"/>
            <label class="form-check-label">Budget</label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="category" value="standard" required="required"/>
            <label class="form-check-label">Standard</label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="category" value="comfort" required="required"/>
            <label class="form-check-label">Comfort</label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="category" value="premium" required="required"/>
            <label class="form-check-label">Premium</label>
        </div>

        <button type="submit" class="btn btn-success">Save</button>
    </form>
</div>
</body>
</html>
