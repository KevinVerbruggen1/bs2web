package be.ucll.ip.web3.ui.controller;

import be.ucll.ip.web3.domain.model.DomainException;
import be.ucll.ip.web3.domain.model.Project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class AddProject extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        ArrayList<String> errors = new ArrayList<>();

        if (request.getParameterMap().size() > 1) {

            //Create a new project
            Project project = new Project();
            setProjectName(project, request, errors);
            //setProjectDescription(project, request, errors);
            //setProjectOwner(project, request, errors);
            //setProjectMembers(project, request, errors);

            if (errors.size() == 0) {
                try {
                    //Add the project to the database
                    //service.add(project);
                    request.setAttribute("projects", service.getAllProjects());
                    return "projectoverview.jsp";
                }
                catch (DomainException exc) {
                    request.setAttribute("errors", exc.getMessage());
                    return "addproject.jsp";
                }
            } else {
                request.setAttribute("errors", errors);
                return "addproject.jsp";
            }
        }
        return "projectregister.jsp";
    }

    public void setProjectName(Project project, HttpServletRequest request, ArrayList<String> errors) {
        String projectName = request.getParameter("projectName");
        boolean projectNameHasErrors = false;

        try {
            request.setAttribute("projectNamePreviousValue", projectName);
            project.setName(projectName);
        } catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
            projectNameHasErrors = true;
        } finally {
            request.setAttribute("projectNameHasErrors", projectNameHasErrors);
        }
    }
}
