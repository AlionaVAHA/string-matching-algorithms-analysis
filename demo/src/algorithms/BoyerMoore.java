package algorithms;

public class BoyerMoore {

    public static class Result {
        public int comparisons;
        public long timeNs;

        public Result(int comparisons, long timeNs) {
            this.comparisons = comparisons;
            this.timeNs = timeNs;
        }
    }

    private static final int ALPHABET_SIZE = 256;

    // Preprocess for bad character heuristic
    private static int[] badCharTable(String pattern) {
        int[] table = new int[ALPHABET_SIZE];
        for (int i = 0; i < ALPHABET_SIZE; i++)
            table[i] = -1;

        for (int i = 0; i < pattern.length(); i++)
            table[(int) pattern.charAt(i)] = i;

        return table;
    }

    public static Result search(String text, String pattern) {
        long startTime = System.nanoTime();
        int comparisons = 0;

        int n = text.length();
        int m = pattern.length();

        int[] badChar = badCharTable(pattern);

        int s = 0; // shift of the pattern
        while (s <= (n - m)) {
            int j = m - 1;

            while (j >= 0 && pattern.charAt(j) == text.charAt(s + j)) {
                comparisons++;
                j--;
            }

            if (j < 0) {
                long endTime = System.nanoTime();
                return new Result(comparisons, endTime - startTime);
                // Shift pattern to next position
                // s += (s + m < n) ? m - badChar[text.charAt(s + m)] : 1;
            } else {
                comparisons++; // for the mismatch comparison
                int shift = Math.max(1, j - badChar[text.charAt(s + j)]);
                s += shift;
            }
        }

        long endTime = System.nanoTime();
        return new Result(comparisons, endTime - startTime);
    }
}