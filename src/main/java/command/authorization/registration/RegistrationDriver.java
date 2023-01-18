package command.authorization.registration;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import exceptions.ServiceException;
import models.DTO.DriverDTO;
import models.entity.Driver;
import models.entity.User;
import models.view.UserView;
import service.DriverService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegistrationDriver extends Registration implements Command {
    DriverService driverService = new DriverService();
    @Override
    public PageUrl execute(HttpServletRequest request) throws ServiceException {
//        HttpSession session = request.getSession();
//        DriverDTO driverDTO = buildDriver((UserView) session.getAttribute("user"));
//        if (!driverService.register(driverDTO)) {
//            return new PageUrl(PageConstants.REG_PAGE, false, "Unknown error.");
//        }
        return new PageUrl(PageConstants.LOGIN_PAGE, true);
    }

    private DriverDTO buildDriver(UserView userView) {
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setUserID(userView.getUserID());
        return driverDTO;
    }
}
