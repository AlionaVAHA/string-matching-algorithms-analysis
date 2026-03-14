package experiment;

import algorithms.KMP;

public class Benchmark {

    public static void main(String[] args) {

        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";

        long start = System.nanoTime();

        int comparisons = KMP.search(text, pattern);

        long end = System.nanoTime();

        System.out.println("Comparisons: " + comparisons);
        System.out.println("Execution time: " + (end - start) + " ns");
    }
}
