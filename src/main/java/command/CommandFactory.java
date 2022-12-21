package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandFactory {

    public Command getCommand(HttpServletRequest request){
        Command currentCommand = null;
        String command = request.getParameter("command");
        try{
            currentCommand = CommandType.getCommandType()

        }
    }
}
