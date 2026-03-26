package experiment;
import utils.DataGenerator;

import algorithms.KMP;
import algorithms.RabinKarp;
import algorithms.BoyerMoore;

public class Benchmark {

    public static void main(String[] args) {

        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";

        long start = System.nanoTime();

        KMP.Result kmpResult = KMP.search(text, pattern);
        int comparisons = kmpResult.comparisons;
        long time = kmpResult.timeNs;

        long end = System.nanoTime();

        System.out.println("Comparisons: " + comparisons);
        System.out.println("Execution time: " + (end - start) + " ns"+ "\n");

        RabinKarp.Result result = RabinKarp.search(text, pattern);
        System.out.println("Rabin-Karp Comparisons: " + result.comparisons);
        System.out.println("Rabin-Karp Time(ns): " + result.timeNs+ "\n");

        BoyerMoore.Result bmResult = BoyerMoore.search(text, pattern);
        System.out.println("Boyer-Moore Comparisons: " + bmResult.comparisons);
        System.out.println("Boyer-Moore Time(ns): " + bmResult.timeNs + "\n");


        String englishText = DataGenerator.generateEnglishText(1000);
        String dnaText = DataGenerator.generateDNASequence(1000);

        String englishPattern = DataGenerator.generatePatternFromText(englishText, 20);
        String dnaPattern = DataGenerator.generatePatternFromText(dnaText, 20);

        System.out.println("English Text: " + englishText.substring(0,50) + "...");
        System.out.println("English Pattern: " + englishPattern);

        System.out.println("DNA Text: " + dnaText.substring(0,50) + "...");
        System.out.println("DNA Pattern: " + dnaPattern);

    }

}
