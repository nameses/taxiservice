package command;

import command.admin.*;
import command.authorization.*;
import command.authorization.Registration;
import command.client.ViewProposition;
import command.driver.ConfirmOrder;
import command.driver.ShowOrders;
import command.driver.ViewOrder;
import command.driver.taxi.NewTaxi;
import command.pageRender.*;
import command.client.OrderDetails;
import command.client.OrderRoute;

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
        commandMap.put("orderMainPage", new OrderMainPage());
        commandMap.put("newTaxiPage", new NewTaxiPage());
        commandMap.put("showOrdersPage", new ShowOrdersPage());
        commandMap.put("confirmOrderPage", new ConfirmOrderPage());
        //authentication
        commandMap.put("registration", new Registration());
        commandMap.put("login", new Login());
        commandMap.put("logout", new Logout());
        //taxis
        commandMap.put("listTaxis", new ListTaxis());
        commandMap.put("newTaxi", new NewTaxi());
        //orders
        commandMap.put("listOrders", new ListOrders());
        //user order process
        commandMap.put("orderRoute", new OrderRoute());
        commandMap.put("orderDetails", new OrderDetails());
        commandMap.put("viewProposition", new ViewProposition());
        //driver orders process
        commandMap.put("showOrders", new ShowOrders());
        commandMap.put("viewOrder", new ViewOrder());
        commandMap.put("confirmOrder", new ConfirmOrder());
    }
    public static Command getCommand(String command){
        return commandMap.get(command);
    }
}
