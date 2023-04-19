package com.cham.tradegpt4.controller;

import com.cham.tradegpt4.domain.Trade;
import com.cham.tradegpt4.service.TradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TradeControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TradeService tradeService;

    private Trade trade1;
    private Trade trade2;

    @BeforeEach
    void setUp() {
        trade1 = new Trade("1", 1000, LocalDate.now(), "trader1");
        trade2 = new Trade("2", 2000, LocalDate.now(), "trader2");
    }

    @AfterEach
    void tearDown() {
        tradeService.deleteById(trade1.getId());
        tradeService.deleteById(trade2.getId());
    }

    @Test
    void save() {
        ResponseEntity<Trade> response = restTemplate.postForEntity("/trades", trade1, Trade.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(trade1, response.getBody());
    }

    @Test
    void findById() {
        tradeService.save(trade1);
        ResponseEntity<Trade> response = restTemplate.getForEntity("/trades/" + trade1.getId(), Trade.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(trade1, response.getBody());
    }

    @Test
    void findById_NotFound() {
        ResponseEntity<Trade> response = restTemplate.getForEntity("/trades/non-existent-id", Trade.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void findAll() {
        tradeService.save(trade1);
        tradeService.save(trade2);
        ResponseEntity<List> response = restTemplate.getForEntity("/trades", List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void deleteById() {
        tradeService.save(trade1);
        restTemplate.delete("/trades/" + trade1.getId());
        ResponseEntity<Trade> response = restTemplate.getForEntity("/trades/" + trade1.getId(), Trade.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}

