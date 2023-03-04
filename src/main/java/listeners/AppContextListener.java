package listeners;

import service.DriverService;
import service.OrderService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@WebListener
public class AppContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        ctx.setAttribute("userSessions", new HashMap<String, HttpSession>());
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();

        //set status to canceled to all rows, which status!=(canceled||confirmation)
        OrderService orderService = new OrderService();
        orderService.deactivateAllOrders();
        //TODO delete all routes that are without orderid.

        //set to inactive all driver statuses
        DriverService driverService = new DriverService();
        driverService.inactivateAllDrivers();
        //userSessions invalidating
        HashMap<String, HttpSession> userSessions =
                (HashMap<String, HttpSession>) ctx.getAttribute("userSessions");
        if (userSessions != null && !userSessions.isEmpty()) {
            userSessions.forEach((str, session) -> session.invalidate());
        }
        ctx.removeAttribute("userSessions");
    }

}

