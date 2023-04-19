package com.cham.tradepostgresgpt4.controller;

import com.cham.tradepostgresgpt4.domain.Trade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TradeControllerIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private Trade trade;

    @BeforeEach
    public void setUp() {
        trade = new Trade();
        trade.setValue(1000.0);
        trade.setDate(LocalDate.now());
        trade.setTrader("John");
    }

    @Test
    public void createTradeTest() throws Exception {
        mockMvc.perform(post("/api/trades")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(trade)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(trade.getValue()))
                .andExpect(jsonPath("$.date").value(trade.getDate().toString()))
                .andExpect(jsonPath("$.trader").value(trade.getTrader()));
    }

    // Add more tests for other endpoints in TradeController
}
