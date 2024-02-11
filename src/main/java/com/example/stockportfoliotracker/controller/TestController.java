package com.example.stockportfoliotracker.controller;

import com.example.stockportfoliotracker.domain.portfolio.Stock;
import com.example.stockportfoliotracker.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        StockService stockService = new StockService();
        List<Stock> stocks = stockService.updateStocks();
        return stocks.toString();
    }
}
