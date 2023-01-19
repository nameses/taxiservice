package command.pageRender;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import exceptions.ServiceException;
import models.DTO.DriverDTO;
import models.DTO.TaxiDTO;
import models.converters.DriverConverter;
import models.converters.TaxiConverter;
import models.entity.Driver;
import models.entity.User;
import models.entity.enums.UserRole;
import models.view.DriverView;
import models.view.UserView;
import service.TaxiService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Profilepage implements Command {
    TaxiService taxiService = new TaxiService();

    @Override
    public PageUrl execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        if (((UserView) session.getAttribute("user")).getRole() == UserRole.driver) {
            DriverDTO driverDTO = DriverConverter.toDTO((DriverView) session.getAttribute("driver"));
            TaxiDTO taxiDTO = taxiService.findByDriver(driverDTO);
            if (taxiDTO.getSuccess()) request.setAttribute("taxi", TaxiConverter.toView(taxiDTO));
            else request.setAttribute("taxi", null);
        }
        return new PageUrl(PageConstants.PROFILE_PAGE, false);
    }
}
