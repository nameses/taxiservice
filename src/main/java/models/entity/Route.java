package models.entity;

import java.util.Arrays;
import java.util.Objects;

public class Route {
    private Integer routeID;
    private Double[] startMarker;
    private Double[] finalMarker;
    private Integer length;
    private Integer orderID;

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
}