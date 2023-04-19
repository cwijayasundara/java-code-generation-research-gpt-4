package com.cham.tradegpt4.controller;

import com.cham.tradegpt4.domain.Trade;
import com.cham.tradegpt4.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/trades")
@RequiredArgsConstructor
public class TradeController {

    private final TradeService tradeService;

    @PostMapping
    public ResponseEntity<Trade> save(@RequestBody Trade trade) {
        return ResponseEntity.ok(tradeService.save(trade));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trade> findById(@PathVariable String id) {
        Optional<Trade> trade = tradeService.findById(id);
        return trade.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterable<Trade>> findAll() {
        return ResponseEntity.ok(tradeService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        tradeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

