package com.example.stockportfoliotracker.service;

import com.example.stockportfoliotracker.domain.portfolio.Stock;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor
public class StockService {
    private final String URL = "https://www.biznesradar.pl/gielda/akcje_gpw";

    public List<Stock> updateStocks() {
        log.info("Retrieving stock data.");
        List<Stock> updatedStocks = new ArrayList<>();
        try {
            Document document = Jsoup.connect(URL).get();
            Elements elements = document.select(".qTableFull tr");
            for (Element element : elements) {
                if (!skipElement(element)) {
                    String symbol = element.select("a").text().substring(0, 3);
                    String name = element.select("a").text();
                    String time = element.select(".q_ch_date").attr("datetime");
                    String close = element.select(".q_ch_act").text();
                    String closePct = element.select(".q_ch_per").text();
                    updatedStocks.add(new Stock(symbol, name, time, close, closePct));
                }
            }
            log.info("Stock data updated.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return updatedStocks;
    }

    private boolean skipElement(Element element) {
        if (!element.select("th.d_col_shortName").isEmpty()) return true;
        if (!element.select(".ad").isEmpty()) return true;
        return false;
    }
}
