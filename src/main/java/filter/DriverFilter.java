package filter;

import command.page.PageConstants;
import models.entity.Driver;
import models.entity.User;
import models.entity.enums.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/driver")
public class DriverFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        System.out.println("driver");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Driver driver = (Driver) session.getAttribute("driver");
        if (user.getRole() == UserRole.driver && driver != null) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(PageConstants.HOME_PAGE);
        }
    }
}
