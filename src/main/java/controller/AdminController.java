package controller;

import command.Command;
import command.CommandFactory;
import command.CommandType;
import command.page.PageUrl;
import entity.enums.UserRole;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;

@WebServlet("/admin")
public class AdminController extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Controller.process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Controller.process(request, response);
    }
}
