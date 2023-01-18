package service;

import DAO.RouteDAO;
import command.page.PageConstants;
import command.page.PageUrl;
import exceptions.DAOException;
import exceptions.ServiceException;
import models.DTO.RouteDTO;
import models.converters.RouteConverter;
import models.entity.Route;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

public class RouteService {
    RouteDAO routeDAO = new RouteDAO();

    /**
     * saving Route model to DB and generated routeid to session
     */
    public RouteDTO saveRoute(RouteDTO routeDTO) throws ServiceException {
        try {
            //validating length of route
            RouteDTO response = validate(routeDTO.getLength());
            if (!response.getStatus()) return response;

            Integer id = routeDAO.insert(RouteConverter.toEntity(routeDTO));

            if (id != null && id > 0) {
                return new RouteDTO(true, id);
            } else {
                return new RouteDTO(false, "Route was not saved. Try again later.");
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private RouteDTO validate(Integer routeLength) {
        if (routeLength < 3000)
            return new RouteDTO(false, "Minimal order is at least 3km");
        else if (routeLength > 30000)
            return new RouteDTO(false, "Maximum order is 30km");
        return new RouteDTO(true);
    }
}
