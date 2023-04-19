package com.cham.tradegpt4.controller;

import com.cham.tradegpt4.domain.Trade;
import com.cham.tradegpt4.service.TradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TradeControllerTest {

    @InjectMocks
    private TradeController tradeController;

    @Mock
    private TradeService tradeService;

    private Trade trade1;
    private Trade trade2;

    @BeforeEach
    void setUp() {
        trade1 = new Trade("1", 1000, LocalDate.now(), "trader1");
        trade2 = new Trade("2", 2000, LocalDate.now(), "trader2");
    }

    @Test
    void save() {
        when(tradeService.save(trade1)).thenReturn(trade1);
        ResponseEntity<Trade> response = tradeController.save(trade1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(trade1, response.getBody());
        verify(tradeService).save(trade1);
    }

    @Test
    void findById() {
        when(tradeService.findById("1")).thenReturn(Optional.of(trade1));
        ResponseEntity<Trade> response = tradeController.findById("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(trade1, response.getBody());
        verify(tradeService).findById("1");
    }

    @Test
    void findById_NotFound() {
        when(tradeService.findById("1")).thenReturn(Optional.empty());
        ResponseEntity<Trade> response = tradeController.findById("1");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(tradeService).findById("1");
    }

    @Test
    void findAll() {
        when(tradeService.findAll()).thenReturn(List.of(trade1, trade2));
        ResponseEntity<Iterable<Trade>> response = tradeController.findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(trade1, trade2), response.getBody());
        verify(tradeService).findAll();
    }

    @Test
    void deleteById() {
        doNothing().when(tradeService).deleteById("1");
        ResponseEntity<Void> response = tradeController.deleteById("1");
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(tradeService).deleteById("1");
    }
}
