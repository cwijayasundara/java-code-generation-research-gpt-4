package com.cham.tradegpt4.service;

import static org.junit.jupiter.api.Assertions.*;

import com.cham.tradegpt4.repository.TradeRepository;
import com.cham.tradegpt4.domain.Trade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class TradeServiceTest {

    @InjectMocks
    private TradeService tradeService;

    @Mock
    private TradeRepository tradeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Trade trade = new Trade("1", 1000, LocalDate.now(), "trader1");
        when(tradeRepository.save(trade)).thenReturn(trade);
        Trade savedTrade = tradeService.save(trade);
        assertNotNull(savedTrade);
        assertEquals(trade, savedTrade);
        verify(tradeRepository).save(trade);
    }

    @Test
    void findById() {
        String id = "1";
        Trade trade = new Trade(id, 1000, LocalDate.now(), "trader1");
        when(tradeRepository.findById(id)).thenReturn(Optional.of(trade));
        Optional<Trade> foundTrade = tradeService.findById(id);
        assertTrue(foundTrade.isPresent());
        assertEquals(trade, foundTrade.get());
        verify(tradeRepository).findById(id);
    }

    @Test
    void findById_NotFound() {
        String id = "1";
        when(tradeRepository.findById(id)).thenReturn(Optional.empty());
        Optional<Trade> foundTrade = tradeService.findById(id);
        assertFalse(foundTrade.isPresent());
        verify(tradeRepository).findById(id);
    }

    @Test
    void findAll() {
        Iterable<Trade> trades = List.of(
                new Trade("1", 1000, LocalDate.now(), "trader1"),
                new Trade("2", 2000, LocalDate.now(), "trader2")
        );
        when(tradeRepository.findAll()).thenReturn(trades);
        Iterable<Trade> foundTrades = tradeService.findAll();
        assertNotNull(foundTrades);
        assertEquals(trades, foundTrades);
        verify(tradeRepository).findAll();
    }

    @Test
    void deleteById() {
        String id = "1";
        doNothing().when(tradeRepository).deleteById(id);
        tradeService.deleteById(id);
        verify(tradeRepository).deleteById(id);
    }
}

