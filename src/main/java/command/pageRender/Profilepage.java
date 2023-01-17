package command.pageRender;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import models.DTO.DriverDTO;
import models.DTO.TaxiDTO;
import models.converters.TaxiConverter;
import models.entity.User.Driver;
import models.entity.User.User;
import models.entity.enums.UserRole;
import service.TaxiService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Profilepage implements Command {
    TaxiService taxiService = new TaxiService();
    @Override
    public PageUrl execute(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(((User)session.getAttribute("user")).getRole()== UserRole.driver) {
            TaxiDTO taxiDTO = taxiService.findByDriver((Driver) session.getAttribute("driver"));
            if (taxiDTO.getSuccess()) request.setAttribute("taxi", TaxiConverter.toView(taxiDTO));
            else request.setAttribute("taxi", null);
        }
        return new PageUrl(PageConstants.PROFILE_PAGE,false);
    }
}
