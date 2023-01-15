package be.ucll.ip.web3.ui.controller;

import be.ucll.ip.web3.domain.model.*;
import be.ucll.ip.web3.domain.service.DbException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class Register extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String destination = "register.jsp";

        ArrayList<String> errors = new ArrayList<>();

        if (request.getParameterMap().size() > 1) {

            //Create a new user
            User user = new User();
            setFirstName(user, request, errors);
            setLastName(user, request, errors);
            setEmailName(user, request, errors);
            setPassword(user, request, errors);
            setTeam(user, request, errors);

            if (errors.size() == 0) {
                try {
                    //Add the user to the database
                    service.add(user);
                    request.setAttribute("users", service.getAllUsers());
                    return "useroverview.jsp";
                }
                catch (DomainException exc) {
                    request.setAttribute("errors", exc.getMessage());
                    return "register.jsp";
                }
            } else {
                request.setAttribute("errors", errors);
                return "register.jsp";
            }
        }

        return destination;
    }

    /* Methodes to set the attributes of the user */
    private void setFirstName(User user, HttpServletRequest request, ArrayList<String> errors) {
        String firstname = request.getParameter("firstName");
        boolean firstNameHasErrors = false;

        try {
            request.setAttribute("firstNamePreviousValue", firstname);
            user.setFirstName(firstname);
        } catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
            firstNameHasErrors = true;
        } finally {
            request.setAttribute("firstNameHasErrors", firstNameHasErrors);
        }
    }

    private void setLastName(User user, HttpServletRequest request, ArrayList<String> errors) {
        String lastName = request.getParameter("lastName");
        boolean lastNameHasErrors = false;

        try {
            request.setAttribute("lastNamePreviousValue", lastName);
            user.setLastName(lastName);
        } catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
            lastNameHasErrors = true;
        } finally {
            request.setAttribute("lastNameHasErrors", lastNameHasErrors);
        }
    }

    private void setEmailName(User user, HttpServletRequest request, ArrayList<String> errors) {
        String email = request.getParameter("email");
        boolean emailHasErrors = false;
        try {
            request.setAttribute("emailPreviousValue", email);
            if(service.checkEmail(email) != null) {
                errors.add("Email already exists");
                emailHasErrors = true;
            }
            user.setEmail(email);
        } catch (DbException | IllegalArgumentException exc) {
            errors.add(exc.getMessage());
            emailHasErrors = true;
        } finally {
            request.setAttribute("emailHasErrors", emailHasErrors);
        }
    }

    private void setPassword(User user, HttpServletRequest request, ArrayList<String> errors) {
        String password = request.getParameter("password");
        boolean passwordHasErrors = false;

        try {
            request.setAttribute("passwordPreviousValue", password);
            user.setPassword(password);
        } catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
            passwordHasErrors = true;
        } finally {
            request.setAttribute("passwordHasErrors", passwordHasErrors);
        }
    }

    private void setTeam(User user, HttpServletRequest request, ArrayList<String> errors) {
        String team = request.getParameter("team");
        boolean teamHasErrors = false;

        try {
            request.setAttribute("teamPreviousValue", team);
            user.setTeam(Team.valueOf(team));
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
            teamHasErrors = true;
        } finally {
            request.setAttribute("teamHasErrors", teamHasErrors);
        }
    }
}
