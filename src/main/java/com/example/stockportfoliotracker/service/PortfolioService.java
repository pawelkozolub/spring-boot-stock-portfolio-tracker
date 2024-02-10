package com.example.stockportfoliotracker.service;

import com.example.stockportfoliotracker.domain.portfolio.Balance;
import com.example.stockportfoliotracker.domain.portfolio.Portfolio;
import com.example.stockportfoliotracker.domain.portfolio.Transaction;
import com.example.stockportfoliotracker.domain.portfolio.TransactionType;
import com.example.stockportfoliotracker.repository.BalanceRepository;
import com.example.stockportfoliotracker.repository.PortfolioRepository;
import com.example.stockportfoliotracker.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final TransactionRepository transactionRepository;
    private final BalanceRepository balanceRepository;

    public BalanceSummaryService getSummary(List<Balance> balances) {
        return new BalanceSummaryService(balances);
    }

    public void makeBuyPortfolioTransaction(Portfolio portfolio, Transaction transaction) {
        if (portfolio != null) {
            transaction.setCreated(String.valueOf(LocalDateTime.now()));
            transaction.setType(TransactionType.BUY.name());
            transactionRepository.save(transaction);        // first persist Transaction entity
            portfolio.getTransactions().add(transaction);   // then add Transaction entity to list
            portfolioRepository.save(portfolio);            // finally update Portfolio entity

            Balance balance = balanceRepository
                    .findFirstByPortfolioAndStock(portfolio, transaction.getStock())
                    .orElse(null);
            if (balance == null) {
                balance = new Balance(transaction.getStock());
                balance.setPortfolio(portfolio);
            }
            balance.updateByBuyTransaction(transaction);
            balanceRepository.save(balance);
        }
    }

    public void makeSellPortfolioTransaction(Portfolio portfolio, Transaction transaction) {
        if (portfolio != null) {
            Balance balance = balanceRepository
                    .findFirstByPortfolioAndStock(portfolio, transaction.getStock())
                    .orElseThrow(IllegalAccessError::new);
            if (balance.getQuantity() > 0) {
                if (transaction.getQuantity() > balance.getQuantity()) {
                    transaction.setQuantity(balance.getQuantity());
                }
                transaction.setCreated(String.valueOf(LocalDateTime.now()));
                transaction.setType(TransactionType.SELL.name());
                transactionRepository.save(transaction);
                portfolio.getTransactions().add(transaction);
                portfolioRepository.save(portfolio);
                balance.updateBySellTransaction(transaction);
                balanceRepository.save(balance);
            }
        }
    }
}
