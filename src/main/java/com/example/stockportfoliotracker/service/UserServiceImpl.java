package com.example.stockportfoliotracker.service;

import com.example.stockportfoliotracker.domain.Role;
import com.example.stockportfoliotracker.domain.User;
import com.example.stockportfoliotracker.repository.RoleRepository;
import com.example.stockportfoliotracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getUsername());
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getAuthority());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        User user = userRepository.findByUsername(username).orElse(null);
        Role role = roleRepository.findByAuthority(roleName).orElse(null);
        //user.getAuthorities().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public List<Role> getRoles() {
        log.info("Fetching all roles");
        return roleRepository.findAll();
    }

    @Override
    public Boolean checkIfAdmin(String username) {
        log.info("Checking ADMIN permissions for {}", username);
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            for (GrantedAuthority grantedAuthority : user.getAuthorities()) {
                if (grantedAuthority.getAuthority().equals("ADMIN")) {
                    log.info("ADMIN permissions available");
                    return true;
                }
            }
        }
        log.info("ADMIN permissions is unavailable");
        return false;
    }
}
