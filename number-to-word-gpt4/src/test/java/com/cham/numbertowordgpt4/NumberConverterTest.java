package com.cham.numbertowordgpt4;

class NumberConverterTest {
    public static void main(String[] args) {
        testNumberToWords();
    }

    private static void testNumberToWords() {
        assertEquals("zero", NumberConverter.toWords(0));
        assertEquals("one", NumberConverter.toWords(1));
        assertEquals("twenty", NumberConverter.toWords(20));
        assertEquals("thirty-five", NumberConverter.toWords(35));
        assertEquals("one hundred", NumberConverter.toWords(100));
        assertEquals("one hundred one", NumberConverter.toWords(101));
        assertEquals("one thousand", NumberConverter.toWords(1000));
        assertEquals("one thousand one", NumberConverter.toWords(1001));
        assertEquals("eleven thousand one", NumberConverter.toWords(11001));
        assertEquals("one hundred thousand", NumberConverter.toWords(100000));
        assertEquals("one million", NumberConverter.toWords(1_000_000));
        assertEquals("one billion", NumberConverter.toWords(1_000_000_000));
        assertEquals("nine hundred ninety-nine billion nine hundred ninety-nine million nine hundred ninety-nine thousand nine hundred ninety-nine", NumberConverter.toWords(999_999_999_999L));

        try {
            NumberConverter.toWords(-1);
            System.out.println("Failed: testNumberToWords - IllegalArgumentException not thrown for negative number");
        } catch (IllegalArgumentException e) {
            // Expected
        }

        try {
            NumberConverter.toWords(1_000_000_000_000L);
            System.out.println("Failed: testNumberToWords - IllegalArgumentException not thrown for number greater than one trillion");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    private static void assertEquals(String expected, String actual) {
        if (!expected.equals(actual)) {
            System.out.println("Failed: testNumberToWords - expected \"" + expected + "\", but got \"" + actual + "\"");
        }
    }
}

