package command.driver.taxi;

import models.DTO.DriverDTO;
import models.DTO.TaxiDTO;
import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import models.converters.DriverConverter;
import models.entity.User.Driver;
import models.entity.enums.CarCategory;
import service.DriverService;
import service.TaxiService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class NewTaxi implements Command {
    TaxiService taxiService;

    @Override
    public PageUrl execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        this.taxiService = new TaxiService();
        TaxiDTO taxiDTO = buildTaxi(request, (Driver) session.getAttribute("driver"));
        PageUrl page = validate(taxiDTO);
        if (page != null) return page;
        TaxiDTO responseTaxi = taxiService.newTaxi(taxiDTO);
        if (!responseTaxi.getSuccess()) {
            return new PageUrl(PageConstants.NEW_TAXI, false, responseTaxi.getMessage());
        }
        return new PageUrl(PageConstants.PROFILE_PAGE_GET, true);
    }

    private PageUrl validate(TaxiDTO taxiDTO) {
        TaxiDTO response = taxiService.validate(taxiDTO);
        if (!response.getSuccess()) {
            return new PageUrl(PageConstants.NEW_TAXI, false, response.getMessage());
        }
        return null;
    }

    private TaxiDTO buildTaxi(HttpServletRequest request, Driver driver) {
        return new TaxiDTO(
                driver.getDriverID(),
                Integer.valueOf(request.getParameter("capacity")),
                Integer.valueOf(request.getParameter("fare")),
                request.getParameter("licensePlate"),
                CarCategory.valueOf(request.getParameter("category"))
        );
    }
}

