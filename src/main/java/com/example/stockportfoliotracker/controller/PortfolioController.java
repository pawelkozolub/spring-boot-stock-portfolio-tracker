package com.example.stockportfoliotracker.controller;

import com.example.stockportfoliotracker.domain.portfolio.Portfolio;
import com.example.stockportfoliotracker.repository.PortfolioRepository;
import com.example.stockportfoliotracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PortfolioController {

    private final UserService userService;
    private final PortfolioRepository portfolioRepository;

    @GetMapping("/")
    public String start() {
        return "start";
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        model.addAttribute("isAdmin", userService.checkIfAdmin(principal.getName()));
        List<Portfolio> portfolioList = portfolioRepository.findAll();
        model.addAttribute("portfolioList", portfolioList);  // temporary
        return "views/portfolio/home";
    }

}
