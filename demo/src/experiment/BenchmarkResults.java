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

        // Define test parameters
        int[] patternSizes = {5, 20, 50, 100, 500}; // different pattern lengths
        int[] textSizes = {10000, 100000, 1000000}; // small, medium, large texts
        int repetitions = 3; // repeat each test 3 times for consistency
        String[] dataTypes = {"English", "DNA"}; // types of text data

        // File names for CSV output
        String csvAll = "results.csv";              // all algorithms together
        String csvKMP = "results_KMP.csv";         // only KMP results
        String csvRK = "results_RabinKarp.csv";    // only Rabin-Karp results
        String csvBM = "results_BoyerMoore.csv";   // only Boyer-Moore results

        //  automatically closes writers
        try (
                BufferedWriter writerAll = new BufferedWriter(new FileWriter(csvAll));
                BufferedWriter writerKMP = new BufferedWriter(new FileWriter(csvKMP));
                BufferedWriter writerRK = new BufferedWriter(new FileWriter(csvRK));
                BufferedWriter writerBM = new BufferedWriter(new FileWriter(csvBM));
        ) {
            //  Write headers to all CSV files
            String header = "Algorithm,TextSize,PatternSize,DataType,Comparisons,Time(ns)";
            writerAll.write(header); writerAll.newLine();
            writerKMP.write(header); writerKMP.newLine();
            writerRK.write(header); writerRK.newLine();
            writerBM.write(header); writerBM.newLine();

            // Loop through all combinations of data type, text size, pattern size
            for (String dataType : dataTypes) {
                for (int textSize : textSizes) {

                    // Generate the text based on data type
                    String text;
                    if (dataType.equals("English")) {
                        text = DataGenerator.generateEnglishText(textSize);
                    } else {
                        text = DataGenerator.generateDNASequence(textSize);
                    }
                    //  Loop through different pattern sizes
                    for (int patternSize : patternSizes) {
                        for (int rep = 0; rep < repetitions; rep++) {

                            //  Generate a pattern from the text
                            String pattern = DataGenerator.generatePatternFromText(text, patternSize);

                            //  KMP Algorithm
                            KMP.Result kmpResult = KMP.search(text, pattern);
                            String lineKMP = "KMP," + textSize + "," + patternSize + "," + dataType + ","
                                    + kmpResult.comparisons + "," + kmpResult.timeNs;
                            writerAll.write(lineKMP); writerAll.newLine(); // unified CSV
                            writerKMP.write(lineKMP); writerKMP.newLine(); // KMP-only CSV

                            //  Rabin-Karp Algorithm
                            RabinKarp.Result rkResult = RabinKarp.search(text, pattern);
                            String lineRK = "RabinKarp," + textSize + "," + patternSize + "," + dataType + ","
                                    + rkResult.comparisons + "," + rkResult.timeNs;
                            writerAll.write(lineRK); writerAll.newLine(); // unified CSV
                            writerRK.write(lineRK); writerRK.newLine();   // Rabin-Karp-only CSV

                            //  Boyer-Moore Algorithm
                            BoyerMoore.Result bmResult = BoyerMoore.search(text, pattern);
                            String lineBM = "BoyerMoore," + textSize + "," + patternSize + "," + dataType + ","
                                    + bmResult.comparisons + "," + bmResult.timeNs;
                            writerAll.write(lineBM); writerAll.newLine(); // unified CSV
                            writerBM.write(lineBM); writerBM.newLine();   // Boyer-Moore-only CSV
                        }
                    }
                }
            }

            System.out.println("Benchmark complete. Results saved in all CSV files.");

        } catch (IOException e) {
            // Catch exceptions if file cannot be written
            e.printStackTrace();
        }
    }
}