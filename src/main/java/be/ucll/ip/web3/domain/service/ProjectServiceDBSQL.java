package be.ucll.ip.web3.domain.service;

import be.ucll.ip.web3.domain.model.Project;
import be.ucll.ip.web3.domain.model.Team;
import be.ucll.ip.web3.domain.model.User;
import be.ucll.ip.web3.util.DbConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectServiceDBSQL implements ProjectService {

    private final Connection connection;
    private final String schema;

    public ProjectServiceDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
    }

    @Override
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        String sql = String.format("SELECT * from %s.projects", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {

                int id = result.getInt("project_id");
                String name = result.getString("name");
                String team = result.getString("team");
                Team teamObj = Team.valueOf(team);
                java.sql.Date start = result.getDate("start");
                java.sql.Date end = result.getDate("end");
                projects.add(new Project(id, name, teamObj, getDate(start), getDate(end)));
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return projects;
    }

    @Override
    public Project getProject(int projectId) {
        return null;
    }

    @Override
    public void addProject(Project project) {
        String query = String.format("INSERT INTO %s.projects (name, Team, start, end) VALUES (?, ?, ?, ?)", schema);

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, project.getName());
            preparedStatement.setString(2, project.getTeam().toString());
            preparedStatement.setString(3, getDateString(project.getStartDate()));
            preparedStatement.setString(4, getDateString(project.getEndDate()));
            preparedStatement.execute();
        } catch(SQLException exc) {
            throw new DbException(exc.getMessage());
        }
    }

    @Override
    public void updateProject(Project project) {
    }

    @Override
    public void deleteProject(int projectId) {
    }

    private Connection getConnection() {
        return this.connection;
    }

    private String getDateString(Date date) {
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate.toString();
    }

    private Date getDate(java.sql.Date sqlDate) {
        return new Date(sqlDate.getTime());
    }
}

