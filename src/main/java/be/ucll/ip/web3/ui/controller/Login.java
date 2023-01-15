package be.ucll.ip.web3.ui.controller;

import be.ucll.ip.web3.domain.model.Role;
import be.ucll.ip.web3.domain.model.Team;
import be.ucll.ip.web3.domain.model.User;
import be.ucll.ip.web3.domain.service.DbException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class Login extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        ArrayList<String> errors = new ArrayList<>();
        String destination = "index.jsp";
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if(email != null && password != null) {
            try {
                //Get the user if mail exists
                User user = service.checkEmail(email);
                if (user != null) {
                    //Check if the password is correct
                    if (user.isCorrectPassword(password)) {
                        request.getSession().setAttribute("user", user);
                    } else {
                        //If the password is incorrect, add an error
                        errors.add("Incorrect Password");
                    }
                } else {
                    //If the email does not exist, add an error
                    errors.add("Incorrect Email");
                }
                } catch(IllegalArgumentException e){
                    e.printStackTrace();
                    errors.add(e.getMessage());
                }
            request.setAttribute("errors", errors);
            }
        return destination;
        }
    }
