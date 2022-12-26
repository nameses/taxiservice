<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">
        <c:choose>
            <c:when test="${sessionScope.user.role!=null}">
                <div>
                    <a href="<%= request.getContextPath() %>/common/homepage.jsp" class="navbar-brand">Taxi</a>
                </div>
                <ul class="navbar-nav navbar-collapse justify-content-end">
                    <li><a href="<%= request.getContextPath() %>/authorization/profile.jsp" class="nav-link">Profile</a>
                    </li>
                    <li><a href="<%= request.getContextPath() %>/controller?command=logout" class="nav-link">Logout</a>
                    </li>
                </ul>
            </c:when>
            <c:otherwise>
                <div>
                    <a href="<%= request.getContextPath() %>/index.jsp" class="navbar-brand">Taxi</a>
                </div>
                <ul class="navbar-nav navbar-collapse justify-content-end">
                    <li><a href="<%= request.getContextPath() %>/authorization/login.jsp" class="nav-link">Login</a>
                    </li>
                    <li><a href="<%= request.getContextPath() %>/authorization/registration.jsp"
                           class="nav-link">Signup</a>
                    </li>
                </ul>
            </c:otherwise>
        </c:choose>

    </nav>
</header>