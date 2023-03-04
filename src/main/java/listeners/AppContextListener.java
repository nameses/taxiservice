package listeners;

import service.DriverService;

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

        //TODO delete all routes that are without orderid.

        //set to inactive all driver statuses
        DriverService driverService = new DriverService();
        driverService.inactivateAllDrivers();

        HashMap<String, HttpSession> userSessions =
                (HashMap<String, HttpSession>) ctx.getAttribute("userSessions");
        if (userSessions != null && !userSessions.isEmpty()) {
            userSessions.forEach((str, session) -> session.invalidate());
        }
        ctx.removeAttribute("userSessions");
    }

}

