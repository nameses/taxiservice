package listeners;

import entity.Order;
import pool.ConnectionPool;
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
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        HashMap<String, HttpSession> userSessions =
                (HashMap<String, HttpSession>) ctx.getAttribute("userSessions");
        if (userSessions != null){
            userSessions.forEach((k,v)-> v.invalidate());
        }
        ctx.removeAttribute("userSessions");
    }

}

