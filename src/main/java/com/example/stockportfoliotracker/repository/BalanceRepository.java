package com.example.stockportfoliotracker.repository;

import com.example.stockportfoliotracker.domain.portfolio.Balance;
import com.example.stockportfoliotracker.domain.portfolio.Portfolio;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Optional<Balance> findFirstByPortfolioAndStock(Portfolio portfolio, @Size(min = 3, max = 3) String stock);

    List<Balance> findAllByPortfolioOrderByStock(Portfolio portfolio);
}
