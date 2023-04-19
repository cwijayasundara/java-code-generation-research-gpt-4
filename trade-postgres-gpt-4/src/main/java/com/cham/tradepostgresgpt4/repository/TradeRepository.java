package com.cham.tradepostgresgpt4.repository;

import com.cham.tradepostgresgpt4.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {
}

