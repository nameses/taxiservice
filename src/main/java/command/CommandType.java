package command;

import command.admin.*;
import command.authorization.*;
import command.authorization.registration.Registration;
import command.authorization.registration.RegistrationClient;
import command.authorization.registration.RegistrationDriver;
import command.pageRender.*;
import command.user.OrderDetails;
import command.user.OrderRoute;

import java.util.HashMap;

public class CommandType {

    static HashMap<String, Command> commandMap = new HashMap<>();
    static{
        init();
    }
    static void init(){
        //pages rendering
        commandMap.put("homepage", new Homepage());
        commandMap.put("loginpage", new Loginpage());
        commandMap.put("mainpage", new Mainpage());
        commandMap.put("profilepage", new Profilepage());
        commandMap.put("regpage", new Regpage());
        commandMap.put("orderMapPage", new OrderMapPage());
        commandMap.put("orderOpenedPage", new OrderOpenedPage());
        commandMap.put("orderDetailsPage", new OrderDetailsPage());
        //authentication
        commandMap.put("registration", new Registration());
        commandMap.put("registrationClient", new RegistrationClient());
        commandMap.put("registrationDriver", new RegistrationDriver());
        commandMap.put("login", new Login());
        commandMap.put("logout", new Logout());
        //taxis
        commandMap.put("listTaxis", new ListTaxis());
        //orders
        commandMap.put("listOrders", new ListOrders());
        //user order process
        commandMap.put("orderRoute", new OrderRoute());
        commandMap.put("orderDetails", new OrderDetails());
    }
    public static Command getCommand(String command){
        return commandMap.get(command);
    }
}
