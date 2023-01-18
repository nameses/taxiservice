package models.converters;

import models.DTO.OrderDTO;
import models.DTO.TaxiDTO;
import models.entity.Order;
import models.entity.Taxi;
import models.view.OrderView;
import models.view.TaxiView;

public class OrderConverter {
    public static Order toEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderID(orderDTO.getOrderID());
        order.setClientID(orderDTO.getClientID());
        order.setDriverID(orderDTO.getDriverID());
        order.setOrderOpened(orderDTO.getOrderOpened());
        order.setOrderAccepted(orderDTO.getOrderAccepted());
        order.setCost(orderDTO.getCost());
        order.setCarCapacity(orderDTO.getCarCapacity());
        order.setOrderStatus(orderDTO.getOrderStatus());
        order.setCarCategory(orderDTO.getCarCategory());
        return order;
    }

    public static OrderDTO toDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderID(order.getOrderID());
        orderDTO.setClientID(order.getClientID());
        orderDTO.setDriverID(order.getDriverID());
        orderDTO.setOrderOpened(order.getOrderOpened());
        orderDTO.setOrderAccepted(order.getOrderAccepted());
        orderDTO.setCost(order.getCost());
        orderDTO.setCarCapacity(order.getCarCapacity());
        orderDTO.setOrderStatus(order.getOrderStatus());
        orderDTO.setCarCategory(order.getCarCategory());
        return orderDTO;
    }

    public static OrderView toView(OrderDTO orderDTO) {
        OrderView orderView = new OrderView();
        orderView.setOrderID(orderDTO.getOrderID());
        orderView.setClientID(orderDTO.getClientID());
        orderView.setDriverID(orderDTO.getDriverID());
        orderView.setOrderOpened(orderDTO.getOrderOpened());
        orderView.setOrderAccepted(orderDTO.getOrderAccepted());
        orderView.setCost(orderDTO.getCost());
        orderView.setCarCapacity(orderDTO.getCarCapacity());
        orderView.setOrderStatus(orderDTO.getOrderStatus());
        orderView.setCarCategory(orderDTO.getCarCategory());
        return orderView;
    }
}
