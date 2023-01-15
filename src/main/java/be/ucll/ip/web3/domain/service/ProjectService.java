package be.ucll.ip.web3.domain.service;

import be.ucll.ip.web3.domain.model.Project;
import be.ucll.ip.web3.domain.model.User;

import java.util.List;

public interface ProjectService {

    List<Project> getAllProjects();

    Project getProject(int projectId);

    void addProject(Project project);

    void updateProject(Project project);

    void deleteProject(int projectId);
}
