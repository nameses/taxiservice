package service;

import DAO.OrderDAO;
import DAO.RouteDAO;
import exceptions.DAOException;
import exceptions.ServiceException;
import models.DTO.OrderDTO;
import models.DTO.RouteDTO;
import models.converters.OrderConverter;
import models.converters.RouteConverter;
import models.entity.enums.OrderStatus;

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
            if (responseOrderDTO.getSuccess()) {
                RouteDTO response = new RouteDTO(routeDTO.getRouteID(), responseOrderDTO.getOrderID());
                if(routeDAO.updateOrderIDbyRouteID(RouteConverter.toEntity(response)).getSuccess()) {
                    return responseOrderDTO;
                }
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
