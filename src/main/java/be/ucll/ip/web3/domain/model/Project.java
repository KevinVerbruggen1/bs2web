package be.ucll.ip.web3.domain.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Project {

    private int projectId;
    private String name;
    private Team team;
    private Date start;
    private Date end;

    public Project() {
    }

    public Project(int projectId, String name, Team team, Date startDate, Date endDate) {
        this(name, team, startDate, endDate);
        this.setProjectId(projectId);
    }

    public Project(String name, Team team, Date startDate, Date endDate) {
        setName(name);
        setTeam(team);
        setStartDate(startDate);
        setEndDate(endDate);
    }

    public void setName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("No name given");
        }
        this.name = name;

    }

    public void setTeam(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("No team given");
        }
        this.team = team;
    }

    public void setStartDate(Date start) {
        if (start == null) {
            throw new IllegalArgumentException("No start date given");
        }
        this.start = start;
    }

    public void setEndDate(Date end) {
        if (end == null) {
            throw new IllegalArgumentException("No end date given");
        }
        this.end = end;
    }

    private void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getName() {
        return name;
    }

    public Team getTeam() {
        return team;
    }

    public Date getStartDate() {
        return start;
    }

    public String getStartDateString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(start);
    }

    public Date getEndDate() {
        return end;
    }

    public String getEndDateString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(end);
    }
}
