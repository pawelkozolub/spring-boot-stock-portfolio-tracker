package com.example.stockportfoliotracker.controller;

import com.example.stockportfoliotracker.domain.portfolio.Portfolio;
import com.example.stockportfoliotracker.domain.user.User;
import com.example.stockportfoliotracker.repository.PortfolioRepository;
import com.example.stockportfoliotracker.repository.UserRepository;
import com.example.stockportfoliotracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PortfolioController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PortfolioRepository portfolioRepository;

    @GetMapping("/")
    public String start() {
        return "start";
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        model.addAttribute("isAdmin", userService.checkIfAdmin(principal.getName()));
        model.addAttribute("username", principal.getName());
        User user = userRepository.findByUsername(principal.getName()).orElse(null);
        model.addAttribute("user", user);
        List<Portfolio> portfolioList = portfolioRepository.findAll();
        model.addAttribute("portfolioList", portfolioList);
        Portfolio portfolio = new Portfolio();
        model.addAttribute("portfolio", portfolio);
        return "views/portfolio/home";
    }

    @PostMapping("/home")
    public String save(@Valid Portfolio portfolio, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "views/portfolio/home";
        }
        portfolio.setUser(userRepository.findByUsername(principal.getName()).orElse(null));
        portfolioRepository.save(portfolio);
        return "redirect:/home";
    }

}
