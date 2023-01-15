package be.ucll.ip.web3.ui.controller;

import be.ucll.ip.web3.domain.model.DomainException;
import be.ucll.ip.web3.domain.model.Role;
import be.ucll.ip.web3.domain.model.Team;
import be.ucll.ip.web3.domain.model.User;
import be.ucll.ip.web3.domain.service.DbException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class Edit extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        String userId = request.getParameter("userId");
        String editUser = request.getParameter("editUser");
        User user = service.get(Integer.parseInt(userId));
        System.out.println(user.getEmail());

        if (editUser != null && editUser.equals("true")){
            //Try to edit the user
            ArrayList<String> errors = new ArrayList<>();
            setFirstName(user, request, errors);
            setLastName(user, request, errors);
            setEmailName(user, request, errors);
            setRole(user, request, errors);
            setTeam(user, request, errors);

            request.setAttribute("userId", userId);
            if (errors.size() == 0) {
                try {
                    //If no errors, go to the useroverview page with the updated user
                    service.update(user);
                    request.setAttribute("users", service.getAllUsers());
                    return "useroverview.jsp";
                }
                catch (DomainException exc) {
                    //If there is a domain exception, the user is not updated
                    request.setAttribute("errors", exc.getMessage());
                    return "edit.jsp";
                }
            } else {
                //Set errors and return to edit.jsp
                request.setAttribute("errors", errors);
                return "edit.jsp";
            }

        } else {
            //set the attributes to view the edit page
            request.setAttribute("userId", userId);
            request.setAttribute("lastNamePreviousValue", user.getLastName());
            request.setAttribute("firstNamePreviousValue", user.getFirstName());
            request.setAttribute("emailPreviousValue", user.getEmail());
            request.setAttribute("rolePreviousValue", user.getRole().getStringValue());
            request.setAttribute("teamPreviousValue", user.getTeam().getStringValue());
            return "edit.jsp";
        }
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

            if (!email.equals(user.getEmail())){
                if(service.checkEmail(email) != null) {
                    errors.add("Email already exists");
                    emailHasErrors = true;
                } else user.setEmail(email);

            }

        } catch (DbException | IllegalArgumentException exc) {
            errors.add(exc.getMessage());
            emailHasErrors = true;
        } finally {
            request.setAttribute("emailHasErrors", emailHasErrors);
        }
    }

    private void setRole(User user, HttpServletRequest request, ArrayList<String> errors) {
        String role = request.getParameter("role");
        boolean teamHasErrors = false;

        try {
            request.setAttribute("rolePreviousValue", role);
            user.setRole(Role.valueOf(role));
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
            teamHasErrors = true;
        } finally {
            request.setAttribute("roleHasErrors", teamHasErrors);
        }
    }

    private void setTeam(User user, HttpServletRequest request, ArrayList<String> errors) {
        String team = request.getParameter("team");
        boolean teamHasErrors = false;

        try {
            request.setAttribute("teamPreviousValue", team);
            System.out.println("team: " + team);
            user.setTeam(Team.valueOf(team));
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
            teamHasErrors = true;
        } finally {
            request.setAttribute("teamHasErrors", teamHasErrors);
        }
    }
}