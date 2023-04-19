package com.cham.tradepostgresgpt4;

import com.cham.tradepostgresgpt4.domain.Trade;
import com.cham.tradepostgresgpt4.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class TradePostgresGpt4Application implements CommandLineRunner {
	@Autowired
	private TradeRepository tradeRepository;

	public static void main(String[] args) {
		SpringApplication.run(TradePostgresGpt4Application.class, args);
	}

	@Override
	public void run(String... args)  {
		Trade trade1 = new Trade(1L, 2000.00, LocalDate.now(), "John Doe");
		Trade trade2 = new Trade(2L, 3000.00, LocalDate.now(), "Jane Doe");
		tradeRepository.saveAll(Arrays.asList(trade1, trade2));
	}
}
