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
        commandMap.put("registration", new Registration());
        commandMap.put("homepage", new Registration());
    }
    public static Command getCommand(String command){
        return commandMap.get(command);
    }
}
