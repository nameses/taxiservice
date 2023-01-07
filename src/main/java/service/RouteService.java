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
            Integer id = routeDAO.insert(buildRoute(request));
            session.setAttribute("routeid", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private Route buildRoute(HttpServletRequest request) {
        Route route = new Route();
        route.setStartMarker(castArrayToDouble(request.getParameterValues("startMarker[]")));
        route.setFinalMarker(castArrayToDouble(request.getParameterValues("startMarker[]")));
        route.setLength(Integer.parseInt(request.getParameter("routeLength")));
        return route;
    }

    private Double[] castArrayToDouble(String[] array) {
        return Arrays.stream(array)
                .map(Double::valueOf)
                .toArray(Double[]::new);
    }


}
