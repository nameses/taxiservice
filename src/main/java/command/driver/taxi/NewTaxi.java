package command.driver.taxi;

import models.DTO.DriverDTO;
import models.DTO.TaxiDTO;
import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import models.converters.DriverConverter;
import models.entity.Driver;
import models.entity.enums.CarCategory;
import models.view.DriverView;
import service.TaxiService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class NewTaxi implements Command {
    TaxiService taxiService= new TaxiService();

    @Override
    public PageUrl execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        DriverDTO driverDTO = DriverConverter.toDTO((DriverView) session.getAttribute("driver"));
        TaxiDTO taxiDTO = buildTaxi(request, driverDTO);

        PageUrl page = validate(taxiDTO);
        if (page != null) return page;

        TaxiDTO responseTaxi = taxiService.newTaxi(taxiDTO);
        if (!responseTaxi.getSuccess()) {
            return new PageUrl(PageConstants.NEW_TAXI, false, responseTaxi.getMessage());
        }
        return new PageUrl(PageConstants.PROFILE_PAGE_GET, true);
    }

    private PageUrl validate(TaxiDTO taxiDTO) {
        //TODO add validating of taxi license plate
        TaxiDTO response = taxiService.validate(taxiDTO);
        if (!response.getSuccess()) {
            return new PageUrl(PageConstants.NEW_TAXI, false, response.getMessage());
        }
        return null;
    }

    private TaxiDTO buildTaxi(HttpServletRequest request, DriverDTO driverDTO) {
        return new TaxiDTO(
                driverDTO.getDriverID(),
                Integer.valueOf(request.getParameter("capacity")),
                Integer.valueOf(request.getParameter("fare")),
                request.getParameter("licensePlate"),
                CarCategory.valueOf(request.getParameter("category"))
        );
    }
}

