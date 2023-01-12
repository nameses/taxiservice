package entity;

import java.sql.Array;
import java.util.Arrays;
import java.util.Objects;

public class Route {
    private Integer routeID;
    private Double[] startMarker;
    private Double[] finalMarker;
    private Integer length;
    private Integer orderID;
    private Order order;

    public Integer getRouteID() {
        return routeID;
    }

    public void setRouteID(Integer routeID) {
        this.routeID = routeID;
    }

    public Double[] getStartMarker() {
        return startMarker;
    }

    public void setStartMarker(Double[] startMarker) {
        this.startMarker = startMarker;
    }

    public Double[] getFinalMarker() {
        return finalMarker;
    }

    public void setFinalMarker(Double[] finalMarker) {
        this.finalMarker = finalMarker;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(routeID, route.routeID) && Arrays.equals(startMarker, route.startMarker) && Arrays.equals(finalMarker, route.finalMarker) && Objects.equals(length, route.length) && Objects.equals(orderID, route.orderID) && Objects.equals(order, route.order);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(routeID, length, orderID, order);
        result = 31 * result + Arrays.hashCode(startMarker);
        result = 31 * result + Arrays.hashCode(finalMarker);
        return result;
    }
}
