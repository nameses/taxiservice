package models.view;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

public class RouteView implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer routeID;
    private String startMarker;
    private String finalMarker;
    private Integer length;

    public RouteView() {
    }

    public RouteView(Integer routeID, Float[] startMarker, Float[] finalMarker, Integer length) {
        this.routeID = routeID;
        this.startMarker = Arrays.toString(startMarker);
        this.finalMarker = Arrays.toString(finalMarker);
        this.length = length;
    }

    public Integer getRouteID() {
        return routeID;
    }

    public void setRouteID(Integer routeID) {
        this.routeID = routeID;
    }

    public String getStartMarker() {
        return startMarker;
    }

    public void setStartMarker(Float[] startMarker) {
        this.startMarker = Arrays.toString(startMarker);
    }

    public String getFinalMarker() {
        return finalMarker;
    }

    public void setFinalMarker(Float[] finalMarker) {
        this.finalMarker = Arrays.toString(finalMarker);
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
