package service;

import DAO.OrderDAO;
import DAO.RouteDAO;
import exceptions.DAOException;
import exceptions.ServiceException;
import models.DTO.*;
import models.converters.DriverConverter;
import models.converters.OrderConverter;
import models.converters.RouteConverter;
import models.converters.TaxiConverter;
import models.entity.enums.OrderStatus;
import models.view.OrderRouteView;
import models.view.OrderView;
import models.view.RouteView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderService {
    private final OrderDAO orderDAO = new OrderDAO();
    private final RouteDAO routeDAO = new RouteDAO();

    public OrderRouteView showOrders(OrderStatus orderStatus, DriverDTO driverDTO,
                                     TaxiDTO taxiDTO, SortFilterDTO sortFilterDTO) throws ServiceException {
        try {
            //select list of orderDTO and convert it to orderView
            List<OrderView> orderViews = orderDAO.selectList(
                            orderStatus,
                            DriverConverter.toEntity(driverDTO),
                            TaxiConverter.toEntity(taxiDTO),
                            sortFilterDTO)
                    .stream()
                    .map(OrderConverter::toView)
                    .toList();
            // get all orderIDs
            List<Integer> orderIDs = orderViews.stream()
                    .map(OrderView::getOrderID)
                    .toList();
            //get map (orderID to RouteDTO) and convert it to another map (orderID to RouteView)
            Map<Integer, RouteView> mapRouteView = routeDAO.selectRouteByOrderID(orderIDs)
                    .entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey,
                            e -> RouteConverter.toView(e.getValue())));
            return new OrderRouteView(orderViews, mapRouteView);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }

    }

    public Boolean cancelOrder(Integer id) throws ServiceException {
        try {
            return orderDAO.updateEnumToStatus(id, OrderStatus.canceled);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public OrderDTO saveOrder(OrderDTO orderDTO, RouteDTO routeDTO) throws ServiceException {
        try {
            OrderDTO responseOrderDTO = orderDAO.insert(OrderConverter.toEntity(orderDTO));
            if (responseOrderDTO.getSuccess()) {
                RouteDTO response = new RouteDTO(routeDTO.getRouteID(), responseOrderDTO.getOrderID());
                if (routeDAO.updateOrderIDbyRouteID(RouteConverter.toEntity(response)).getSuccess()) {
                    return responseOrderDTO;
                }
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
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
