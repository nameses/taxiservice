package filter;

import command.page.PageConstants;
import models.entity.User;
import models.entity.enums.UserRole;
import models.view.UserView;

import javax.servlet.Filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter("/admin")
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        System.out.println("admin");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        UserView userView = (UserView) session.getAttribute("user");
        if (userView.getRole() == UserRole.admin) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(PageConstants.HOME_PAGE);
        }
    }
}