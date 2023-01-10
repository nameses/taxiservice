package service;

import DAO.RouteDAO;
import entity.Route;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

public class RouteService {
    RouteDAO routeDAO = new RouteDAO();

    /**
     * saving Route model to DB and generated routeid to session
     *
     * @param request
     * @return
     */
    public Boolean saveRoute(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Integer id = routeDAO.insert(buildRoute(request, session));
            if (id != null && id > 0) {
                Route route = (Route) session.getAttribute("route");
                route.setRouteID(id);
                session.setAttribute("route",route);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private Route buildRoute(HttpServletRequest request, HttpSession session) {
        Route route = new Route();
        route.setStartMarker(castArrayToDouble(request.getParameterValues("startMarker[]")));
        route.setFinalMarker(castArrayToDouble(request.getParameterValues("finalMarker[]")));
        route.setLength(Integer.parseInt(request.getParameter("routeLength")));
        session.setAttribute("route", route);
        return route;
    }

    private Double[] castArrayToDouble(String[] array) {
        return Arrays.stream(array)
                .map(Double::valueOf)
                .toArray(Double[]::new);
    }


}
