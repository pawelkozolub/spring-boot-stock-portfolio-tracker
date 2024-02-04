package com.example.stockportfoliotracker.controller;

import com.example.stockportfoliotracker.domain.Role;
import com.example.stockportfoliotracker.domain.User;
import com.example.stockportfoliotracker.repository.RoleRepository;
import com.example.stockportfoliotracker.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String list(Model model, Principal principal) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("username", principal.getName());
        model.addAttribute("isAdmin", true);
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList", userList);
        List<Role> roleList = roleRepository.findAll();
        model.addAttribute("roleList", roleList);
        return "views/admin/admin";
    }

    @PostMapping
    public String save(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "views/admin/admin";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/admin";
    }
}
