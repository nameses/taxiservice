package command;

import command.page.PageUrl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    PageUrl execute(HttpServletRequest request) throws ServletException;
}
