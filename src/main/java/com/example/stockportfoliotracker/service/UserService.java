package com.example.stockportfoliotracker.service;

import com.example.stockportfoliotracker.domain.Role;
import com.example.stockportfoliotracker.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String email, String roleName);
    User getUser(String email);
    List<User> getUsers();
}
