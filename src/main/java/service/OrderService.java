package service;

import DAO.OrderDAO;
import entity.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OrderService {
    private final OrderDAO orderDAO = new OrderDAO();

    public List<Order> getList(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            String orderByString = request.getParameter("orderByString");
            String orderBySort = request.getParameter("orderBySort");
            session.setAttribute("orderByString", orderByString);
            session.setAttribute("orderBySort", orderBySort);
            String filterBy = request.getParameter("filterBy");
            String filterValue = request.getParameter("filterValue");
            return orderDAO.selectAllByString(orderByString, orderBySort, filterBy, filterValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
