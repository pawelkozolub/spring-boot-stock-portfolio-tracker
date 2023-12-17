package com.example.stockportfoliotracker.controller;

import com.example.stockportfoliotracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping("/")
    public String start() {
        return "start";
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        model.addAttribute("isAdmin", userService.checkIfAdmin(principal.getName()));
        model.addAttribute("portfolioList", new ArrayList<Integer>());  // temporary
        return "views/home";
    }

}
