package com.cham.tradegpt4;

import com.cham.tradegpt4.domain.Trade;
import com.cham.tradegpt4.repository.TradeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class TradeGpt4Application implements CommandLineRunner {
	// added for testing the code
	@Autowired
	private TradeRepository tradeRepository;

	public static void main(String[] args) {
		SpringApplication.run(TradeGpt4Application.class, args);
	}

	// added for testing: reuse the generated Trades from the unit tests
	@Override
	public void run(String... args)  {
		Trade trade1 = new Trade("1", 1000, LocalDate.now(), "trader1");
		Trade trade2 = new Trade("2", 2000, LocalDate.now(), "trader2");
		tradeRepository.saveAll(Arrays.asList(trade1, trade2));
	}
}
