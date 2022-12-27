package command;

import command.admin.ListOrders;
import command.admin.ListTaxis;
import command.admin.UpdateTaxiStatus;
import command.autorization.Homepage;
import command.autorization.Login;
import command.autorization.Registration;

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
        //taxis
        commandMap.put("listTaxis", new ListTaxis());
        commandMap.put("updateTaxiStatus", new UpdateTaxiStatus());
        //orders
        commandMap.put("listOrders", new ListOrders());

    }
    public static Command getCommand(String command){
        return commandMap.get(command);
    }
}
