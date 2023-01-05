package command.autorization;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import entity.Taxi;
import entity.User.Client;
import entity.User.Driver;
import entity.User.User;
import entity.enums.CarCategory;
import entity.enums.DriverStatus;
import entity.enums.UserRole;
import service.ClientService;
import service.DriverService;
import service.UserService;
import utils.EncryptionUtil;

import javax.servlet.http.HttpServletRequest;

public class RegistrationDriver implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request){
        DriverService driverService = new DriverService();
        Driver driver = getDriver(request);
        // String error = driverService.validate(driver);
        if(!driverService.register(driver)){
            return new PageUrl(PageConstants.REGISTRATION, false, "Unknown error.");
        }
        return new PageUrl(PageConstants.LOGIN, true);
    }
    private Driver getDriver(HttpServletRequest request){
        Driver driver = new Driver();
        //TODO validation
        driver.setDriverStatus(DriverStatus.valueOf(request.getParameter("driverStatus")));
        driver.setUser(getUser(request));
        driver.setUserID(driver.getUser().getUserID());
        driver.setTaxi(getTaxi(request));
        driver.setTaxiID(driver.getTaxi().getTaxiID());
        return driver;
    }

    private Taxi getTaxi(HttpServletRequest request){
        Taxi taxi = new Taxi();
        //TODO validation
        taxi.setCapacity(Integer.valueOf(request.getParameter("capacity")));
        taxi.setCategory(CarCategory.valueOf(request.getParameter("carCategory")));
        taxi.setFare(Integer.valueOf(request.getParameter("fare")));
        taxi.setLicensePlate(request.getParameter("licensePlate"));
        return taxi;
    }
    private User getUser(HttpServletRequest request){
        User user = new User();
        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        //TODO validation
        user.setFullname(fullname);
        user.setUsername(username);
        user.setPassword(EncryptionUtil.getEncrypted(password));
        user.setPhone(phone);
        user.setEmail(email);
        user.setRole(UserRole.valueOf(role));
        return user;
    }
}
