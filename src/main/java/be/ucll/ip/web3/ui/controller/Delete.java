package be.ucll.ip.web3.ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Delete extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        String delete = request.getParameter("delete");
        if (delete != null && delete.equals("true")) {
            try {
                //Try to delete the user
                service.delete(Integer.parseInt((request.getParameter("userId"))));
                request.setAttribute("users", service.getAllUsers());
            } catch (NumberFormatException exc) {
                //If there is a numberformat exception, the user is not deleted because it could not be found
                request.setAttribute("users", service.getAllUsers());
                request.setAttribute("errors", "No user has been found to delete");
            }
            return "useroverview.jsp";
        } else if (delete != null && delete.equals("false")) {
            //If the user does not want to delete the user, go to the useroverview page
            request.setAttribute("users", service.getAllUsers());
            return "useroverview.jsp";
        }
        //If the user wants to delete the user, go to the delete page
        request.setAttribute("userId", request.getParameter("userId"));
        return "delete.jsp";
    }
}