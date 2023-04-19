package com.cham.tradepostgresgpt4.service;

import com.cham.tradepostgresgpt4.domain.Trade;
import com.cham.tradepostgresgpt4.repository.TradeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TradeService {
    private final TradeRepository tradeRepository;

    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public Trade createTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

    public List<Trade> findAllTrades() {
        return tradeRepository.findAll();
    }

    public Trade findTradeById(Long id) {
        return tradeRepository.findById(id).orElseThrow(() -> new RuntimeException("Trade not found"));
    }

    public Trade updateTrade(Long id, Trade updatedTrade) {
        Trade trade = findTradeById(id);
        trade.setValue(updatedTrade.getValue());
        trade.setDate(updatedTrade.getDate());
        trade.setTrader(updatedTrade.getTrader());
        return tradeRepository.save(trade);
    }

    public void deleteTrade(Long id) {
        tradeRepository.deleteById(id);
    }
}
