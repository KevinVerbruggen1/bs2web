package be.ucll.ip.web3.domain.service;

import be.ucll.ip.web3.domain.model.Project;
import be.ucll.ip.web3.domain.model.User;

import java.util.ArrayList;
import java.util.List;

public class AppService {
    private UserService users = new UserServiceDBSQL();
    private ProjectService projects = new ProjectServiceDBSQL();

    public void add(User user) {
        users.add(user);
    }

    public List<User> getAllUsers() {
        return users.getAllUsers();
    }

    public void delete(int userId) {
        users.delete(userId);
    }

    public User get(int parseInt) { return users.get(parseInt); }

    public User checkEmail(String mail) {
        return users.checkEmail(mail);
    }

    public void update(User user) { users.update(user); }

    public void addProject(Project project) { projects.addProject(project); }

    public List<Project> getAllProjects() { return projects.getAllProjects(); }

    public void deleteProject(int projectId) { projects.deleteProject(projectId); }

    public Project getProject(int projectId) { return projects.getProject(projectId); }
}
