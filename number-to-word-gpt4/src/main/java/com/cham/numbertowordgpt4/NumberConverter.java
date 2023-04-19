package com.cham.numbertowordgpt4;

class NumberConverter {
    private static final String[] ONES = {
            "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
    };

    private static final String[] TEENS = {
            "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };

    private static final String[] TENS = {
            "", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    private static final String[] BIG = {
            "", "thousand", "million", "billion"
    };

    public static String toWords(long number) {
        if (number < 0 || number >= 1_000_000_000_000L) {
            throw new IllegalArgumentException("Number must be between 0 and 1,000,000,000,000");
        }

        if (number == 0) {
            return "zero";
        }

        StringBuilder words = new StringBuilder();

        for (int i = BIG.length - 1; i >= 0; i--) {
            long scale = (long) Math.pow(1000, i);
            long part = number / scale;
            number %= scale;

            if (part > 0) {
                words.append(convert(part));
                words.append(" ");
                words.append(BIG[i]);
                words.append(" ");
            }
        }

        return words.toString().trim();
    }

    private static String convert(long number) {
        StringBuilder words = new StringBuilder();

        if (number >= 100) {
            long hundreds = number / 100;
            number %= 100;
            words.append(ONES[(int) hundreds]);
            words.append(" hundred ");
        }

        if (number >= 20) {
            long tens = number / 10;
            number %= 10;
            words.append(TENS[(int) tens]);
            words.append(" ");
        } else if (number >= 10) {
            words.append(TEENS[(int) (number - 10)]);
            words.append(" ");
            number = 0;
        }

        words.append(ONES[(int) number]);

        return words.toString().trim();
    }
}
