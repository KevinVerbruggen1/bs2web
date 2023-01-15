package be.ucll.ip.web3.domain.service;

import be.ucll.ip.web3.domain.model.Role;
import be.ucll.ip.web3.domain.model.Team;
import be.ucll.ip.web3.domain.model.User;
import be.ucll.ip.web3.util.DbConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceDBSQL implements UserService {

    private final Connection connection;
    private final String schema;

    public UserServiceDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
        UserServiceInMemory userServiceInMemory = new UserServiceInMemory();
    }

    @Override
    public User get(int userid) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getUserid() == userid) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = String.format("SELECT * from %s.users", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int id = result.getInt("user_id");
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                String password = result.getString("password");
                String email = result.getString("email");
                String team = result.getString("team");
                String role = result.getString("role");
                Team teamObj = Team.valueOf(team);
                Role roleObj = Role.valueOf(role);
                users.add(new User(id, email, password, firstname, lastname, teamObj, roleObj));
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return users;
    }


    @Override
    public void add(User user) {

        String query = String.format("INSERT INTO %s.users (email, firstname, lastname, password, team, role) VALUES (?, ?, ?, ?, ?, ?)", schema);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getTeam().toString());
            preparedStatement.setString(6, user.getRole().toString());
            preparedStatement.execute();
        } catch(SQLException exc) {
            throw new DbException(exc.getMessage());
        }
    }

    @Override
    public void update(User user) {
        String query = String.format("UPDATE %s.users SET email=? , firstname=?, lastname=?, password=?, team=?, role=? WHERE user_id=?", schema);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getTeam().toString());
            preparedStatement.setString(6, user.getRole().toString());
            preparedStatement.setInt(7, user.getUserid());
            System.out.println("statement: " + preparedStatement);
            preparedStatement.execute();
        } catch(SQLException exc) {
            throw new DbException(exc.getMessage());
        }

    }

    @Override
    public void delete(int userid) {
        String query = String.format("DELETE FROM %s.users WHERE user_id=?", schema);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, userid);
            preparedStatement.execute();
        } catch(SQLException exc) {
            throw new DbException(exc.getMessage());
        }
    }

    @Override
    public User checkEmail(String email) {
        List <User> users = getAllUsers();
        for (User user : users) {
            if (user.getEmail().equals(email)) {

                return user;
            }
        }

        return null;
    }

    private Connection getConnection() {
        return this.connection;
    }
}
