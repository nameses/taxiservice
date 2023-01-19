package filter;


import command.page.PageConstants;
import models.entity.User;
import models.view.UserView;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/app", "/admin", "/driver", "/client"})
public class LoggedInFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        System.out.println("loggedin");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        UserView userView = (UserView) session.getAttribute("user");
        if (userView != null && userView.getRole()!=null) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(PageConstants.LOGIN_PAGE_GET);
        }
    }
}
