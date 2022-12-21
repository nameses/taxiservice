package controller;

import com.sun.mail.iap.CommandFailedException;
import command.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;

@WebServlet("/")
public class Controller extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        process(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        process(request,response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response){
        try{
            CommandFactory factory = new CommandFactory();
        }
    }
}
