package com.cham.tradegpt4.service;

import com.cham.tradegpt4.repository.TradeRepository;
import com.cham.tradegpt4.domain.Trade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TradeService {

    private final TradeRepository tradeRepository;

    public Trade save(Trade trade) {
        return tradeRepository.save(trade);
    }

    public Optional<Trade> findById(String id) {
        return tradeRepository.findById(id);
    }

    public Iterable<Trade> findAll() {
        return tradeRepository.findAll();
    }

    public void deleteById(String id) {
        tradeRepository.deleteById(id);
    }
}

