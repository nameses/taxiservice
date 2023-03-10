package command.client;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import exceptions.ServiceException;
import models.DTO.RouteDTO;
import models.converters.RouteConverter;
import service.RouteService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class OrderRoute implements Command {
    RouteService routeService = new RouteService();

    @Override
    public PageUrl execute(HttpServletRequest request) throws ServiceException {
        //DTO creating from request
        RouteDTO routeDTO = buildRoute(request);
        //saving route in service
        RouteDTO response = routeService.saveRoute(routeDTO);
        //response failing processing
        if (!response.getSuccess()) {
            return new PageUrl(PageConstants.ORDER_MAP,
                    false,
                    response.getMessage());
        }
        //saving route ID in session
        routeDTO.setRouteID(response.getRouteID());
        request.getSession().setAttribute("route", RouteConverter.toView(routeDTO));

        return new PageUrl(PageConstants.ORDER_DETAILS_PAGE_GET, true);
    }

    private RouteDTO buildRoute(HttpServletRequest request) {
        RouteDTO routeDTO = new RouteDTO();
        routeDTO.setStartMarker(castArrayToFloat(request.getParameterValues("startMarker[]")));
        routeDTO.setFinalMarker(castArrayToFloat(request.getParameterValues("finalMarker[]")));
        routeDTO.setLength(Integer.parseInt(request.getParameter("routeLength")));
        request.getSession().setAttribute("route", routeDTO);
        return routeDTO;
    }

    private Float[] castArrayToFloat(String[] array) {
        return Arrays.stream(array)
                .map(Float::valueOf)
                .toArray(Float[]::new);
    }
}
