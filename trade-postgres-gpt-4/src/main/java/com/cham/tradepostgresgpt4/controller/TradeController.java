package com.cham.tradepostgresgpt4.controller;

import com.cham.tradepostgresgpt4.domain.Trade;
import com.cham.tradepostgresgpt4.service.TradeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/trades")
public class TradeController {
    private final TradeService tradeService;
    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @PostMapping
    public Trade createTrade(@RequestBody Trade trade) {
        return tradeService.createTrade(trade);
    }

    @GetMapping
    public List<Trade> findAllTrades() {
        return tradeService.findAllTrades();
    }

    @GetMapping("/{id}")
    public Trade findTradeById(@PathVariable Long id) {
        return tradeService.findTradeById(id);
    }

    @PutMapping("/{id}")
    public Trade updateTrade(@PathVariable Long id, @RequestBody Trade updatedTrade) {
        return tradeService.updateTrade(id, updatedTrade);
    }
    @DeleteMapping("/{id}")
    public void deleteTrade(@PathVariable Long id) {
        tradeService.deleteTrade(id);
    }
}

