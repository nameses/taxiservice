package service;

import DAO.OrderDAO;
import DAO.RouteDAO;
import exceptions.DAOException;
import exceptions.ServiceException;
import models.DTO.OrderDTO;
import models.DTO.RouteDTO;
import models.converters.OrderConverter;
import models.converters.RouteConverter;
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
        try {
            return orderDAO.updateEnumToStatus(id, OrderStatus.canceled);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public OrderDTO saveOrder(OrderDTO orderDTO, RouteDTO routeDTO) throws ServiceException {
        try {
            OrderDTO responseOrderDTO = orderDAO.insert(OrderConverter.toEntity(orderDTO));
            if (responseOrderDTO.getStatus()) {
                RouteDTO response = new RouteDTO(routeDTO.getRouteID(), responseOrderDTO.getOrderID());
                return new OrderDTO(
                        routeDAO.updateOrderIDbyRouteID(RouteConverter.toEntity(response)).getStatus());
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e);
        }
        return new OrderDTO(false);
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
