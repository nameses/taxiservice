package service;

import DAO.OrderDAO;
import DAO.RouteDAO;
import models.entity.Order;
import models.entity.Route;
import models.entity.User.Client;
import models.entity.enums.CarCategory;
import models.entity.enums.OrderStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

public class OrderService {
    private final OrderDAO orderDAO = new OrderDAO();
    private final RouteDAO routeDAO = new RouteDAO();

    public Boolean cancelOrder(Integer id) {
        try{
            return orderDAO.updateEnumToStatus(id,OrderStatus.canceled);
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public Boolean saveOrder(HttpServletRequest request) {
        try {
            Integer id = orderDAO.insert(buildOrder(request));
            if(id!=null && id > 0) {
                HttpSession session = request.getSession();
                Order order = (Order) session.getAttribute("order");
                order.setOrderID(id);
                session.setAttribute("order",order);

                Integer routeID = ((Route) session.getAttribute("route")).getRouteID();
                session.removeAttribute("route");
                routeDAO.updateOrderIDbyRouteID(routeID,id);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private Order buildOrder(HttpServletRequest request) {
        Order order = new Order();
        HttpSession session = request.getSession();
        order.setClientID(((Client) session.getAttribute("client")).getClientID());
        order.setOrderOpened(new Timestamp(System.currentTimeMillis()));
        order.setCarCapacity(Integer.valueOf(request.getParameter("carCapacity")));
        order.setCarCategory(CarCategory.valueOf(request.getParameter("carCategory")));
        order.setOrderStatus(OrderStatus.processing);
        session.setAttribute("order",order);
        return order;
    }

//    public List<Order> getList(HttpServletRequest request) {
//        try {
//            HttpSession session = request.getSession();
//            String orderByString = request.getParameter("orderByString");
//            String orderBySort = request.getParameter("orderBySort");
//            session.setAttribute("orderByString", orderByString);
//            session.setAttribute("orderBySort", orderBySort);
//            String filterBy = request.getParameter("filterBy");
//            String filterValue = request.getParameter("filterValue");
//            return orderDAO.selectAllByString(orderByString, orderBySort, filterBy, filterValue);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
