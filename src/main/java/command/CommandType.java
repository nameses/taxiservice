package command;

import command.admin.*;
import command.autorization.Homepage;
import command.autorization.Login;
import command.autorization.Logout;
import command.autorization.Registration;
import command.user.OrderDetails;

import java.util.HashMap;

import static command.page.PageConstants.REGISTRATION;

public class CommandType {

    static HashMap<String, Command> commandMap = new HashMap<>();
    static{
        init();
    }
    static void init(){
        //authentication
        commandMap.put("registration", new Registration());
        commandMap.put("homepage", new Homepage());
        commandMap.put("login", new Login());
        commandMap.put("logout", new Logout());
        //taxis
        commandMap.put("listTaxis", new ListTaxis());
        commandMap.put("newTaxi", new NewTaxi());
        commandMap.put("deleteTaxi", new DeleteTaxi());
        commandMap.put("updateTaxiStatus", new UpdateTaxiStatus());
        //orders
        commandMap.put("listOrders", new ListOrders());
        //user order process
        commandMap.put("orderDetails", new OrderDetails());
    }
    public static Command getCommand(String command){
        return commandMap.get(command);
    }
}
