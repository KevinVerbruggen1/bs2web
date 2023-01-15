package be.ucll.ip.web3.ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends RequestHandler {

        @Override
        public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
            HttpSession session = request.getSession();
            //Remove the user from the session
            session.setAttribute("user", null);
            return "index.jsp";

        }

}
