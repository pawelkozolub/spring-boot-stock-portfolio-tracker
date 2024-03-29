package com.example.stockportfoliotracker.service;

import com.example.stockportfoliotracker.domain.user.Role;
import com.example.stockportfoliotracker.domain.user.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
    List<Role> getRoles();

    Boolean checkIfAdmin(String username);
}
