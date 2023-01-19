package filter;

import command.page.PageConstants;
import models.entity.Client;
import models.entity.User;
import models.entity.enums.UserRole;
import models.view.ClientView;
import models.view.UserView;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/client")
public class ClientFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        System.out.println("client");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        UserView userView = (UserView) session.getAttribute("user");
        ClientView clientView = (ClientView) session.getAttribute("client");
        if (userView.getRole() == UserRole.client && clientView != null) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(PageConstants.HOME_PAGE);
        }
    }
}
