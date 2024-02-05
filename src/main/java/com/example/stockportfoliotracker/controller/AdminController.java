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
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/delete")
    public String deleteView(@RequestParam Long id, Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        model.addAttribute("isAdmin", true);
        model.addAttribute("user", userRepository.findById(id).orElse(null));
        return "views/admin/delete";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        userRepository.findById(id).ifPresent(userRepository::delete);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable(name = "id") Long id, Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        model.addAttribute("isAdmin", true);
        model.addAttribute("user", userRepository.findById(id).orElse(null));
        return "views/admin/view";
    }

    @GetMapping("/edit")
    public String editView(@RequestParam Long id, Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        model.addAttribute("isAdmin", true);
        model.addAttribute("user", userRepository.findById(id).orElse(null));
        List<Role> roleList = roleRepository.findAll();
        model.addAttribute("roleList", roleList);
        return "views/admin/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "views/admin/edit";
        }
        userRepository.findById(user.getId()).ifPresent(
                updateUser -> {
                    updateUser.setUsername(user.getUsername());
                    updateUser.setAuthorities((Set<Role>) user.getAuthorities());
                    updateUser.setPassword(passwordEncoder.encode(user.getPassword()));
                    userRepository.save(updateUser);
                }
        );
        return "redirect:/admin";
    }
}
