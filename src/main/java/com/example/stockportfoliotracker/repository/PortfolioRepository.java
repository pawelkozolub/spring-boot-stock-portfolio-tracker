package com.example.stockportfoliotracker.repository;

import com.example.stockportfoliotracker.domain.portfolio.Portfolio;
import com.example.stockportfoliotracker.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    List<Portfolio> findAllByUser(User user);
}
