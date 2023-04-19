package com.cham.tradegpt4.repository;

import com.cham.tradegpt4.domain.Trade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends CrudRepository<Trade, String> {
}