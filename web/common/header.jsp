<header>
  <nav class="navbar navbar-expand-md navbar-dark"
       style="background-color: tomato">
    <div>
      <a href="<%= request.getContextPath() %>/index.jsp" class="navbar-brand">Taxi</a>
    </div>
    <ul class="navbar-nav navbar-collapse justify-content-end">
      <li><a href="<%= request.getContextPath() %>/authorization/login" class="nav-link">Login</a></li>
      <li><a href="<%= request.getContextPath() %>/authorization/register" class="nav-link">Signup</a></li>
    </ul>
  </nav>
</header>