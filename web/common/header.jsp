<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header>
    <nav class="navbar navbar-expand-md navbar-dark text-bg-dark">
        <c:choose>
            <c:when test="${sessionScope.user!=null}">
                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                    <li><a href="<%= request.getContextPath() %>/app?command=homepage"
                           class="nav-link px-2 text-secondary">Home</a></li>
                </ul>
                <ul class="navbar-nav navbar-collapse justify-content-end">
                    <li><a href="<%= request.getContextPath() %>/app?command=profilepage" class="nav-link">Profile</a></li>
                    <li><a href="<%= request.getContextPath() %>/app?command=logout" class="nav-link">Logout</a></li>
                </ul>
            </c:when>
            <c:otherwise>
                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                    <li><a href="<%= request.getContextPath() %>/app?command=mainpage" class="nav-link px-2 text-secondary">Home</a></li>
                </ul>
                <ul class="navbar-nav navbar-collapse justify-content-end">
                    <li><a href="<%= request.getContextPath() %>/login?command=loginpage" class="nav-link">Login</a></li>
                    <li><a href="<%= request.getContextPath() %>/login?command=regpage" class="nav-link">Signup</a></li>
                </ul>
            </c:otherwise>
        </c:choose>

    </nav>
</header>