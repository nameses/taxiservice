package entity;

import java.util.Arrays;
import java.util.Objects;

public class Route {
    private Integer routeID;
    private Double[] startMarker;
    private Double[] finalMarker;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(routeID, route.routeID) && Arrays.equals(startMarker, route.startMarker) && Arrays.equals(finalMarker, route.finalMarker);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(routeID);
        result = 31 * result + Arrays.hashCode(startMarker);
        result = 31 * result + Arrays.hashCode(finalMarker);
        return result;
    }
}
