package models.view;

public class RouteView {
    private Integer routeID;
    private Float[] startMarker;
    private Float[] finalMarker;
    private Integer length;

    public RouteView() {
    }

    public RouteView(Integer routeID, Float[] startMarker, Float[] finalMarker, Integer length) {
        this.routeID = routeID;
        this.startMarker = startMarker;
        this.finalMarker = finalMarker;
        this.length = length;
    }

    public Integer getRouteID() {
        return routeID;
    }

    public void setRouteID(Integer routeID) {
        this.routeID = routeID;
    }

    public Float[] getStartMarker() {
        return startMarker;
    }

    public void setStartMarker(Float[] startMarker) {
        this.startMarker = startMarker;
    }

    public Float[] getFinalMarker() {
        return finalMarker;
    }

    public void setFinalMarker(Float[] finalMarker) {
        this.finalMarker = finalMarker;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
