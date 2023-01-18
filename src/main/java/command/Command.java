package command;

import command.page.PageUrl;
import exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    PageUrl execute(HttpServletRequest request) throws ServletException, ServiceException;
}
