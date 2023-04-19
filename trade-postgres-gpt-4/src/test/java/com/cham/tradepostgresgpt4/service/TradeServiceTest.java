package com.cham.tradepostgresgpt4.service;

import com.cham.tradepostgresgpt4.domain.Trade;
import com.cham.tradepostgresgpt4.repository.TradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TradeServiceTest {

    private TradeRepository tradeRepository;
    private TradeService tradeService;

    @BeforeEach
    public void setUp() {
        tradeRepository = Mockito.mock(TradeRepository.class);
        tradeService = new TradeService(tradeRepository);
    }

    @Test
    public void createTradeTest() {
        Trade trade = new Trade();
        trade.setId(1L);
        trade.setValue(1000.0);
        trade.setDate(LocalDate.now());
        trade.setTrader("John");
        when(tradeRepository.save(trade)).thenReturn(trade);
        Trade createdTrade = tradeService.createTrade(trade);
        assertEquals(trade, createdTrade);
    }

    // Add more tests for other methods in TradeService
}
