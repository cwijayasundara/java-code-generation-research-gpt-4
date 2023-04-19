package com.cham.merchantgalaxygpt4.domain;

import java.util.Map;

public class RomanNumerals {
    private static final Map<Character, Integer> SYMBOL_VALUES = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000
    );
    public int toInt(String roman) {
        int total = 0;
        int prevValue = 0;
        for (int i = roman.length() - 1; i >= 0; i--) {
            int value = SYMBOL_VALUES.get(roman.charAt(i));
            if (value < prevValue) {
                total -= value;
            } else {
                total += value;
            }
            prevValue = value;
        }
        return total;
    }
}
