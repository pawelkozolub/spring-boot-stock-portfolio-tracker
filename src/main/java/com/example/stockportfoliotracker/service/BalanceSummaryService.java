package com.example.stockportfoliotracker.service;

import com.example.stockportfoliotracker.domain.portfolio.Balance;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class BalanceSummaryService {
    private BigDecimal invested;
    private BigDecimal withdrawn;
    private BigDecimal realizedProfit;
    
    public BalanceSummaryService(List<Balance> balances) {
        this.invested = invested(balances);
        this.withdrawn = withdrawn(balances);
        this.realizedProfit = realizedProfit(balances);
    }

    private BigDecimal invested(List<Balance> balances) {
        BigDecimal invested = BigDecimal.valueOf(0);
        for (Balance balance : balances) {
            if (balance.getInvested() != null) {
                invested = invested.add(balance.getInvested());
            }
        }
        return invested;
    }

    private BigDecimal withdrawn(List<Balance> balances) {
        BigDecimal withdrawn = BigDecimal.valueOf(0);
        for (Balance balance : balances) {
            if (balance.getInvested() != null) {
                withdrawn = withdrawn.add(balance.getWithdrawn());
            }
        }
        return withdrawn;
    }

    private BigDecimal realizedProfit(List<Balance> balances) {
        BigDecimal realizedProfit = BigDecimal.valueOf(0);
        for (Balance balance : balances) {
            if (balance.getInvested() != null) {
                realizedProfit = realizedProfit.add(balance.getRealizedProfit());
            }
        }
        return realizedProfit;
    }
}
