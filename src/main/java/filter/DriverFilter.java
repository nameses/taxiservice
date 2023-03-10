package filter;

import command.page.PageConstants;
import models.entity.Driver;
import models.entity.User;
import models.entity.enums.UserRole;
import models.view.DriverView;
import models.view.UserView;

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
        UserView userView = (UserView) session.getAttribute("user");
        DriverView driverView = (DriverView) session.getAttribute("driver");
        if (userView.getRole() == UserRole.driver && driverView != null) {

            String commandName = request.getParameter("command");
            if(commandName.equals("showOrders")){
                Boolean isBusy = (Boolean) session.getAttribute("isDriverBusy");
                if (isBusy != null && isBusy) {
                    response.sendRedirect(PageConstants.DRIVER_IS_BUSY_ALERT);
                }
            } else filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(PageConstants.HOME_PAGE);
        }
    }
}
