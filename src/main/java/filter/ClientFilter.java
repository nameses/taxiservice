package filter;

import command.page.PageConstants;
import models.entity.Client;
import models.entity.User;
import models.entity.enums.UserRole;

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
        User user = (User) session.getAttribute("user");
        Client client = (Client) session.getAttribute("client");
        if (user.getRole() == UserRole.client && client != null) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(PageConstants.HOME_PAGE);
        }
    }
}
