package command;

import command.admin.*;
import command.autorization.*;
import command.autorization.registration.Registration;
import command.autorization.registration.RegistrationClient;
import command.autorization.registration.RegistrationDriver;
import command.user.OrderDetails;

import java.util.HashMap;

public class CommandType {

    static HashMap<String, Command> commandMap = new HashMap<>();
    static{
        init();
    }
    static void init(){
        //authentication
        commandMap.put("registration", new Registration());
        commandMap.put("registrationClient", new RegistrationClient());
        commandMap.put("registrationDriver", new RegistrationDriver());
        commandMap.put("homepage", new Homepage());
        commandMap.put("login", new Login());
        commandMap.put("logout", new Logout());
        //taxis
        commandMap.put("listTaxis", new ListTaxis());
        //orders
        commandMap.put("listOrders", new ListOrders());
        //user order process
        commandMap.put("orderDetails", new OrderDetails());
    }
    public static Command getCommand(String command){
        return commandMap.get(command);
    }
}
