package com.cham.numbertowordgpt4;

import java.util.Scanner;

public class NumberToWordConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number between 0 and 1,000,000,000,000: ");
        long number = scanner.nextLong();
        scanner.close();

        try {
            String words = NumberConverter.toWords(number);
            System.out.println(words);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
