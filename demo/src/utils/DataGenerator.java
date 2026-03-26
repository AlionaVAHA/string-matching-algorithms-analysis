package utils;

import java.util.Random;

public class DataGenerator {

    private static final String ENGLISH_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ";
    private static final String DNA_CHARACTERS = "ACGT";

    private static final Random random = new Random();

    // Generate random English text
    public static String generateEnglishText(int length) {
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ENGLISH_CHARACTERS.length());
            sb.append(ENGLISH_CHARACTERS.charAt(index));
        }

        return sb.toString();
    }

    // Generate random DNA sequence
    public static String generateDNASequence(int length) {
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(DNA_CHARACTERS.length());
            sb.append(DNA_CHARACTERS.charAt(index));
        }

        return sb.toString();
    }

    // Optional: generate pattern from text (for testing guaranteed match)
    public static String generatePatternFromText(String text, int patternSize) {
        if (text.length() < patternSize) {
            throw new IllegalArgumentException("Text is smaller than pattern size");
        }

        int startIndex = random.nextInt(text.length() - patternSize + 1);
        return text.substring(startIndex, startIndex + patternSize);
    }
}