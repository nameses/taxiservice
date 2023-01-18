package models.view;

public class RouteView {
    private Integer routeID;
    private Double[] startMarker;
    private Double[] finalMarker;
    private Integer length;

    public RouteView() {
    }

    public RouteView(Integer routeID, Double[] startMarker, Double[] finalMarker, Integer length) {
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
}
