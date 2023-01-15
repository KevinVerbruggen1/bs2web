package be.ucll.ip.web3.domain.service;

import be.ucll.ip.web3.domain.model.Role;
import be.ucll.ip.web3.domain.model.Team;
import be.ucll.ip.web3.domain.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceInMemory implements UserService {
    private Map<Integer, User> users = new HashMap<>();
    private int userid = 1;    // als je later werkt met externe databank, wordt dit userid automatisch gegenereerd

    public UserServiceInMemory() {
        User director = new User("director@ucll.be", "t", "Ad", "Director", Team.ALPHA);
        User teamleader = new User("teamleader@ucll.be", "t", "Jef", "Vermeulen", Team.BETA);
        User employee = new User("employee@ucll.be", "t", "Kef", "Vermeulen", Team.GAMMA);
        director.setRole(Role.ADMIN);
        teamleader.setRole(Role.TEAMLEADER);
        employee.setRole(Role.EMPLOYEE);
        add(director);
        add(teamleader);
        add(employee);
    }

    @Override
    public User get(int userid) {
        return users.get(userid);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void add(User user) {
        if (user == null) {
            throw new DbException("No user given");
        }
        if (users.containsKey(user.getUserid())) {
            throw new DbException("User already exists");
        }
        user.setUserid(userid);   // user toevoegen geeft altijd nieuw userid
        users.put(user.getUserid(), user);
        userid++;
    }

    @Override
    public void update(User user) {
        if (user == null) {
            throw new DbException("No user given");
        }
        if (!users.containsKey(user.getUserid())) {
            throw new DbException("No user found");
        }
        users.put(user.getUserid(), user); // user updaten: userid blijft behouden
    }

    @Override
    public void delete(int userid) {
        users.remove(userid);   // userid gaat verloren, maar wordt niet ingenomen door eventuele nieuwe user
    }

    @Override
    public User checkEmail(String email) {
        for (User user : users.values()) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

}
