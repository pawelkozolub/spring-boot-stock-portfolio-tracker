package com.example.stockportfoliotracker.controller;

import com.example.stockportfoliotracker.domain.portfolio.Balance;
import com.example.stockportfoliotracker.domain.portfolio.Portfolio;
import com.example.stockportfoliotracker.domain.portfolio.Transaction;
import com.example.stockportfoliotracker.domain.user.User;
import com.example.stockportfoliotracker.repository.BalanceRepository;
import com.example.stockportfoliotracker.repository.PortfolioRepository;
import com.example.stockportfoliotracker.repository.UserRepository;
import com.example.stockportfoliotracker.service.PortfolioService;
import com.example.stockportfoliotracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/portfolio")
@RequiredArgsConstructor
public class PortfolioController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PortfolioRepository portfolioRepository;
    private final BalanceRepository balanceRepository;
    private final PortfolioService portfolioService;

    @GetMapping
    public String home(Model model, Principal principal) {
        model.addAttribute("isAdmin", userService.checkIfAdmin(principal.getName()));
        model.addAttribute("username", principal.getName());
        User user = userRepository.findByUsername(principal.getName()).orElse(null);
        model.addAttribute("user", user);
        List<Portfolio> portfolioList = portfolioRepository.findAllByUser(user);
        model.addAttribute("portfolioList", portfolioList);
        Portfolio portfolio = new Portfolio();
        model.addAttribute("portfolio", portfolio);
        return "views/portfolio/home-list";
    }

    @PostMapping
    public String save(@Valid Portfolio portfolio, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "views/portfolio/home-list";
        }
        portfolio.setUser(userRepository.findByUsername(principal.getName()).orElse(null));
        portfolioRepository.save(portfolio);
        return "redirect:/portfolio";
    }

    @GetMapping("/delete")
    public String deleteView(@RequestParam Long id, Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        model.addAttribute("portfolio", portfolioRepository.findById(id).orElse(null));
        return "views/portfolio/delete";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        portfolioRepository.findById(id).ifPresent(portfolio -> portfolioRepository.delete(portfolio));
        return "redirect:/portfolio";
    }

    @GetMapping("/edit")
    public String editView(@RequestParam Long id, Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        model.addAttribute("portfolio", portfolioRepository.findById(id).orElse(null));
        return "views/portfolio/edit";
    }

    @PostMapping("edit")
    public String edit(@Valid Portfolio portfolio, BindingResult result) {
        if (result.hasErrors()) {
            return "views/portfolio/edit";
        }
        portfolioRepository.findById(portfolio.getId()).ifPresent(
                updatePortfolio -> {
                    updatePortfolio.setName(portfolio.getName());
                    updatePortfolio.setDescription(portfolio.getDescription());
                    portfolioRepository.save(updatePortfolio);
                }
        );
        return "redirect:/portfolio";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable(name = "id") Long id, Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        Portfolio portfolio = portfolioRepository.findById(id).orElse(null);
        List<Balance> balances = balanceRepository.findAllByPortfolioOrderByStock(portfolio);
        model.addAttribute("portfolio", portfolio);
        model.addAttribute("balanceList", balances);
        model.addAttribute("summary", portfolioService.getSummary(balances));
        return "views/portfolio/view";
    }

    @GetMapping("/{portfolioId}/buy")       // portfolioId to be used instead id > Spring confuses it with stock.id
    public String buyStockView(@PathVariable(name = "portfolioId") Long id, Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        Portfolio portfolio = portfolioRepository.findById(id).orElse(null);
        model.addAttribute("portfolio", portfolio);
        model.addAttribute("transaction", new Transaction());
        return "views/portfolio/stock-buy";
    }

    @PostMapping("/{portfolioId}/buy")       // portfolioId to be used instead id > Spring confuses it with stock.id
    public String buyStock(@PathVariable(name = "portfolioId") Long id, @Valid Transaction transaction, BindingResult result) {
        if (result.hasErrors()) {
            return "views/portfolio/stock-buy";
        }
        Portfolio portfolio = portfolioRepository.findById(id).orElse(null);
        portfolioService.makeBuyPortfolioTransaction(portfolio, transaction);
        return "redirect:/portfolio/" + id;
    }

    @GetMapping("/{portfolioId}/sell")      // portfolioId to be used instead id > Spring confuses it with stock.id
    public String sellStockView(@PathVariable(name = "portfolioId") Long id, Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        Portfolio portfolio = portfolioRepository.findById(id).orElse(null);
        List<Balance> balances = balanceRepository.findAllByPortfolioOrderByStock(portfolio);
        if (balances.isEmpty()) {
            return "redirect:/portfolio/" + id;
        }
        model.addAttribute("portfolio", portfolio);
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("balanceList", balances);
        return "views/portfolio/stock-sell";
    }

    @PostMapping("/{portfolioId}/sell")      // portfolioId to be used instead id > Spring confuses it with stock.id
    public String sellStock(@PathVariable(name = "portfolioId") Long id, @Valid Transaction transaction, BindingResult result) {
        if (result.hasErrors()) {
            return "views/portfolio/stock-sell";
        }
        Portfolio portfolio = portfolioRepository.findById(id).orElse(null);
        portfolioService.makeSellPortfolioTransaction(portfolio, transaction);
        return "redirect:/portfolio/" + id;
    }

 }
