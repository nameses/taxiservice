package models.converters;

import models.DTO.RouteDTO;
import models.DTO.TaxiDTO;
import models.entity.Route;
import models.entity.Taxi;
import models.view.RouteView;
import models.view.TaxiView;

import java.util.Arrays;

public class RouteConverter {
    public static Route toEntity(RouteDTO routeDTO) {
        Route route = new Route();
        route.setRouteID(routeDTO.getRouteID());
        route.setStartMarker(routeDTO.getStartMarker());
        route.setFinalMarker(routeDTO.getFinalMarker());
        route.setLength(routeDTO.getLength());
        route.setOrderID(routeDTO.getOrderID());
        return route;
    }

    public static RouteDTO toDTO(RouteView routeView) {
        RouteDTO routeDTO = new RouteDTO();
        routeDTO.setRouteID(routeView.getRouteID());
        routeDTO.setStartMarker(
                convertStringToFloatArray(routeView.getStartMarker()));
        routeDTO.setFinalMarker(
                convertStringToFloatArray(routeView.getFinalMarker()));
        routeDTO.setLength(routeView.getLength());
        return routeDTO;
    }
    private static Float[] convertStringToFloatArray(String str){
        return Arrays.stream(str.replaceAll("\\[|\\]", "").split(","))
                .map(String::trim)
                .map(Float::parseFloat)
                .toArray(Float[]::new);
    }

    public static RouteDTO toDTO(Route route) {
        RouteDTO routeDTO = new RouteDTO();
        routeDTO.setRouteID(route.getRouteID());
        routeDTO.setStartMarker(route.getStartMarker());
        routeDTO.setFinalMarker(route.getFinalMarker());
        routeDTO.setLength(route.getLength());
        routeDTO.setOrderID(route.getOrderID());
        return routeDTO;
    }

    public static RouteView toView(RouteDTO routeDTO) {
        RouteView routeView = new RouteView();
        routeView.setRouteID(routeDTO.getRouteID());
        routeView.setStartMarker(routeDTO.getStartMarker());
        routeView.setFinalMarker(routeDTO.getFinalMarker());
        routeView.setLength(routeDTO.getLength());
        return routeView;
    }
}
