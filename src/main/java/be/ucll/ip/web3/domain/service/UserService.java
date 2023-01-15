package be.ucll.ip.web3.domain.service;

import be.ucll.ip.web3.domain.model.User;

import java.util.List;

public interface UserService {
    User get(int userid);

    List<User> getAllUsers();

    void add(User user);

    void update(User user);

    void delete(int userid);

    User checkEmail(String email);
}
