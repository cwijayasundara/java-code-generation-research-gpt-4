package com.cham.tradegpt4.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Trade")
public class Trade {
    @Id
    private String id;
    private double value;
    private LocalDate date;
    private String trader;
}

