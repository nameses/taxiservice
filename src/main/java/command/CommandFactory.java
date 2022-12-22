package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandFactory {

    public Command getCommand(HttpServletRequest request){
        Command currentCommand = CommandType.getCommand("homepage");
        String command = request.getParameter("command");
        try {
            currentCommand = CommandType.getCommand(command);
        } catch (IllegalArgumentException e) {
            request.setAttribute("MESSAGE","Wrong command name");
        }
        return currentCommand;
    }
}
