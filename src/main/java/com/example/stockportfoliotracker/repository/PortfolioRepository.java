package com.example.stockportfoliotracker.repository;

import com.example.stockportfoliotracker.domain.portfolio.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
}
