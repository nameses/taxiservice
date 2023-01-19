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

@WebFilter("/login")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        System.out.println("login");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        UserView userView = (UserView) session.getAttribute("user");
        if (userView != null) {
            response.sendRedirect(PageConstants.HOME_PAGE);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}


