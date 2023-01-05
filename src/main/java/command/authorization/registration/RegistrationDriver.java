package command.authorization.registration;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import entity.User.Driver;
import entity.User.User;
import entity.enums.DriverStatus;
import service.DriverService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegistrationDriver extends Registration implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request) {
        DriverService driverService = new DriverService();
        Driver driver = getDriver(request);
        // String error = driverService.validate(driver);
        if (!driverService.register(driver)) {
            return new PageUrl(PageConstants.REGISTRATION, false, "Unknown error.");
        }
        return new PageUrl(PageConstants.LOGIN, true);
    }

    private Driver getDriver(HttpServletRequest request) {
        Driver driver = new Driver();
        //TODO validation
        driver.setDriverStatus(DriverStatus.inactive);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        session.removeAttribute("user");
        driver.setUser(user);
        driver.setUserID(user.getUserID());
//        driver.setTaxi(getTaxi(request));
//        driver.setTaxiID(driver.getTaxi().getTaxiID());
        return driver;
    }

//    private Taxi getTaxi(HttpServletRequest request) {
//        Taxi taxi = new Taxi();
//        //TODO validation
//        taxi.setCapacity(Integer.valueOf(request.getParameter("capacity")));
//        taxi.setCategory(CarCategory.valueOf(request.getParameter("carCategory")));
//        taxi.setFare(Integer.valueOf(request.getParameter("fare")));
//        taxi.setLicensePlate(request.getParameter("licensePlate"));
//        return taxi;
//    }
}
