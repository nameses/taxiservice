package models.DTO;

public class RouteDTO {
    private Integer routeID;
    private Double[] startMarker;
    private Double[] finalMarker;
    private Integer length;
    private Integer orderID;
    private String message;
    private Boolean success;

    public RouteDTO(Integer routeID, Integer orderID) {
        this.routeID = routeID;
        this.orderID = orderID;
    }

    public RouteDTO() {
    }

    public RouteDTO(Boolean success, Integer routeID) {
        this.success = success;
        this.routeID = routeID;
    }

    public RouteDTO(Boolean success) {
        this.success = success;
    }

    public RouteDTO(Boolean success, String message) {
        this.message = message;
        this.success = success;
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

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
