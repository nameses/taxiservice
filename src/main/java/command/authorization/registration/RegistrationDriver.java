package command.authorization.registration;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import models.entity.User.Driver;
import models.entity.User.User;
import models.entity.enums.DriverStatus;
import service.DriverService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegistrationDriver extends Registration implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        DriverService driverService = new DriverService();
        Driver driver = buildDriver(session);
        // String error = driverService.validate(driver);
        if (!driverService.register(driver)) {
            return new PageUrl(PageConstants.REG_PAGE, false, "Unknown error.");
        }
        return new PageUrl(PageConstants.LOGIN_PAGE, true);
    }

    private Driver buildDriver(HttpSession session) {
        Driver driver = new Driver();
        User user = (User) session.getAttribute("user");
        driver.setUserID(user.getUserID());
        return driver;
    }
}
