package experiment;

import algorithms.KMP;
import algorithms.RabinKarp;
import algorithms.BoyerMoore;
import utils.DataGenerator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BenchmarkResults {

    public static void main(String[] args) {

        int[] patternSizes = {5, 20, 50, 100, 500};
        int[] textSizes = {10000, 100000, 1000000}; // small, medium, large
        int repetitions = 3; // repeat each test

        String[] dataTypes = {"English", "DNA"};

        String csvFile = "results.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {

            // Header
            writer.write("Algorithm,TextSize,PatternSize,DataType,Comparisons,Time(ns)");
            writer.newLine();

            for (String dataType : dataTypes) {
                for (int textSize : textSizes) {
                    // Generate text
                    String text;
                    if (dataType.equals("English")) {
                        text = DataGenerator.generateEnglishText(textSize);
                    } else {
                        text = DataGenerator.generateDNASequence(textSize);
                    }

                    for (int patternSize : patternSizes) {
                        for (int rep = 0; rep < repetitions; rep++) {
                            String pattern = DataGenerator.generatePatternFromText(text, patternSize);

                            // KMP
                            KMP.Result kmpResult = KMP.search(text, pattern);
                            writer.write("KMP," + textSize + "," + patternSize + "," + dataType + ","
                                    + kmpResult.comparisons + "," + kmpResult.timeNs);
                            writer.newLine();

                            // Rabin-Karp
                            RabinKarp.Result rkResult = RabinKarp.search(text, pattern);
                            writer.write("RabinKarp," + textSize + "," + patternSize + "," + dataType + ","
                                    + rkResult.comparisons + "," + rkResult.timeNs);
                            writer.newLine();

                            // Boyer-Moore
                            BoyerMoore.Result bmResult = BoyerMoore.search(text, pattern);
                            writer.write("BoyerMoore," + textSize + "," + patternSize + "," + dataType + ","
                                    + bmResult.comparisons + "," + bmResult.timeNs);
                            writer.newLine();
                        }
                    }
                }
            }

            System.out.println("Benchmark complete. Results saved in " + csvFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}