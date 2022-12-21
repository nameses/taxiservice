package command;

import command.autorization.Registration;

import java.util.HashMap;

import static command.page.PageConstants.REGISTRATION;

public class CommandType {

    static HashMap<String, Command> commandMap = new HashMap<>();
    static{
        init();
    }
    static void init(){
        commandMap.put(REGISTRATION, new Registration());
    }
    public static void getCommand(){

    }
}
