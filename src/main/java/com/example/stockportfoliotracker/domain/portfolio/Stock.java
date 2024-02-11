package com.example.stockportfoliotracker.domain.portfolio;

import lombok.Getter;

@Getter
public class Stock {
    private String symbol;
    private String name;
    private String time;
    private String close;
    private String closePct;

    public Stock(String symbol, String name, String time, String close, String closePct) {
        this.symbol = symbol;
        this.name = name;
        this.time = time;
        this.close = close;
        this.closePct = closePct;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", close='" + close + '\'' +
                ", closePct='" + closePct + '\'' +
                '}';
    }
}
