package com.example.stockportfoliotracker.repository;

import com.example.stockportfoliotracker.domain.portfolio.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
