package listeners;

import entity.Order;
import entity.User.User;
import entity.enums.UserRole;
import service.DriverService;
import service.OrderService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;

@WebListener
public class SessionListener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        ServletContext context = session.getServletContext();

        HashMap<String, HttpSession> userSessions =
                (HashMap<String, HttpSession>) context.getAttribute("userSessions");
        if (userSessions == null) userSessions = new HashMap<>();

        userSessions.put(session.getId(), session);
        context.setAttribute("userSessions", userSessions);
        System.out.println("Session Started:: ID=" + sessionEvent.getSession().getId());
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        //cancel current order, if exists
        Order order = (Order) session.getAttribute("order");
        if (order != null) {
            OrderService orderService = new OrderService();
            orderService.cancelOrder(order.getOrderID());
        }
        //set driver status to inactive
        User user = (User) session.getAttribute("user");
        if (user != null && user.getRole() == UserRole.driver) {
            DriverService driverService = new DriverService();
            driverService.setToInactiveStatus(user.getUserID());
        }
        //session invalidate
        session.invalidate();
        System.out.println("Session Destroyed:: ID=" + sessionEvent.getSession().getId());
    }

}
