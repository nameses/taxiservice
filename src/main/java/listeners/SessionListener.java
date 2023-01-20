package listeners;

import exceptions.ServiceException;
import models.entity.Order;
import models.entity.User;
import models.entity.enums.DriverStatus;
import models.entity.enums.UserRole;
import models.view.OrderView;
import models.view.UserView;
import service.DriverService;
import service.OrderService;

import javax.servlet.ServletContext;
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
        System.out.println("Session Started:: ID=" + sessionEvent.getSession().getId());
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        try {
            HttpSession session = sessionEvent.getSession();
            //cancel current order, if exists
            OrderView orderView = (OrderView) session.getAttribute("order");
            if (orderView != null) {
                OrderService orderService = new OrderService();
                orderService.cancelOrder(orderView.getOrderID());
            }
            //set driver status to inactive
            UserView userView = (UserView) session.getAttribute("user");
            if (userView != null && userView.getRole() == UserRole.driver) {
                DriverService driverService = new DriverService();
                driverService.updateDriverStatus(userView.getUserID(), DriverStatus.inactive);
            }
            //session invalidate
            session.invalidate();
            System.out.println("Session Destroyed:: ID=" + sessionEvent.getSession().getId());
        }catch (ServiceException e){
            throw new RuntimeException(e.getMessage(),e);
        }

    }

}
