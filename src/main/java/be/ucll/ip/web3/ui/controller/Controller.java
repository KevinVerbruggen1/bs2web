package be.ucll.ip.web3.ui.controller;

import be.ucll.ip.web3.domain.model.User;
import be.ucll.ip.web3.domain.service.AppService;
import be.ucll.ip.web3.domain.service.UserServiceInMemory;

import java.io.*;
import java.util.Objects;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private AppService service = new AppService();
    private HandlerFactory handlerFactory = new HandlerFactory();

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String destination = "index.jsp";
        String command = request.getParameter("command");

        //Check if user is logged in, if not, redirect to index/login page
        User loggedIn = (User) request.getSession().getAttribute("user");

        //If user is logged in, let them access the other pages
        if (command != null && loggedIn != null || Objects.equals(command, "Login")) {
            RequestHandler handler = handlerFactory.getHandler(command, service);
            destination = handler.handleRequest(request, response);
        }

        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);

    }

}
