package com.cham.merchantgalaxygpt4.service;

import com.cham.merchantgalaxygpt4.domain.RomanNumerals;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GalaxyMerchant {
    private static final Pattern SYMBOL_DEFINITION_PATTERN = Pattern.compile("(\\w+) is ([IVXLCDM])");
    private static final Pattern METAL_DEFINITION_PATTERN = Pattern.compile("(\\w+)(?:\\s+\\w+)+\\s+(\\w+) is (\\d+) Credits");
    private static final Pattern HOW_MUCH_PATTERN = Pattern.compile("how much is ((?:\\w+\\s?)+)\\?");
    private static final Pattern HOW_MANY_CREDITS_PATTERN = Pattern.compile("how many Credits is ((?:\\w+\\s?)+)\\?");

    private final Map<String, Character> symbolMap;
    private final Map<String, Double> metalValueMap;
    private final RomanNumerals romanNumerals;

    public GalaxyMerchant() {
        symbolMap = new HashMap<>();
        metalValueMap = new HashMap<>();
        romanNumerals = new RomanNumerals();
    }

    public String processInput(String input) {
        Matcher symbolMatcher = SYMBOL_DEFINITION_PATTERN.matcher(input);
        Matcher metalMatcher = METAL_DEFINITION_PATTERN.matcher(input);
        Matcher howMuchMatcher = HOW_MUCH_PATTERN.matcher(input);
        Matcher howManyCreditsMatcher = HOW_MANY_CREDITS_PATTERN.matcher(input);

        if (symbolMatcher.matches()) {
            symbolMap.put(symbolMatcher.group(1), symbolMatcher.group(2).charAt(0));
        } else if (metalMatcher.matches()) {
            String metal = metalMatcher.group(2);
            int value = Integer.parseInt(metalMatcher.group(3));
            String[] tokens = metalMatcher.group(1).trim().split("\\s+");
            StringBuilder romanBuilder = new StringBuilder();
            for (String token : tokens) {
                Character symbol = symbolMap.get(token);
                if (symbol == null) {
                    return "I have no idea what you are talking about";
                }
                romanBuilder.append(symbol);
            }
            int quantity = romanNumerals.toInt(romanBuilder.toString());
            metalValueMap.put(metal, (double) value / quantity);
        } else if (howMuchMatcher.matches()) {
            String[] tokens = howMuchMatcher.group(1).trim().split("\\s+");
            StringBuilder romanBuilder = new StringBuilder();
            for (String token : tokens) {
                Character symbol = symbolMap.get(token);
                if (symbol == null) {
                    return "I have no idea what you are talking about";
                }
                romanBuilder.append(symbol);
            }
            int value = romanNumerals.toInt(romanBuilder.toString());
            return String.format("%s is %d", howMuchMatcher.group(1).trim(), value);
        } else if (howManyCreditsMatcher.matches()) {
            String[] tokens = howManyCreditsMatcher.group(1).trim().split("\\s+");
            String metal = tokens[tokens.length - 1];
            Double metalValue = metalValueMap.get(metal);
            if (metalValue == null) {
                return "I have no idea what you are talking about";
            }
            StringBuilder romanBuilder = new StringBuilder();
            for (int i = 0; i < tokens.length - 1; i++) {
                Character symbol = symbolMap.get(tokens[i]);
                if (symbol == null) {
                    return "I have no idea what you are talking about";
                }
                romanBuilder.append(symbol);
            }
            int quantity = romanNumerals.toInt(romanBuilder.toString());
            int credits = (int) (quantity * metalValue);
            return String.format("%s is %d Credits", howManyCreditsMatcher.group(1).trim(), credits);
        } else {
            return "I have no idea what you are talking about";
        }
        // need to fix
        return null;
    }
}
