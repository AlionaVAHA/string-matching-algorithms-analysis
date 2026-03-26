package algorithms;

public class RabinKarp {

    public static class Result {
        public int comparisons;
        public long timeNs;

        public Result(int comparisons, long timeNs) {
            this.comparisons = comparisons;
            this.timeNs = timeNs;
        }
    }

    public static Result search(String text, String pattern) {
        long startTime = System.nanoTime();
        int comparisons = 0;

        int n = text.length();
        int m = pattern.length();
        int prime = 101; // A prime number for hashing
        int d = 256; // Number of characters in input alphabet

        int h = 1;
        for (int i = 0; i < m - 1; i++)
            h = (h * d) % prime;

        int p = 0; // hash for pattern
        int t = 0; // hash for text

        // Initial hash computation
        for (int i = 0; i < m; i++) {
            p = (d * p + pattern.charAt(i)) % prime;
            t = (d * t + text.charAt(i)) % prime;
        }

        for (int i = 0; i <= n - m; i++) {

            comparisons++;
            if (p == t) {
                // Check character by character
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    comparisons++;
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    long endTime = System.nanoTime();
                    return new Result(comparisons, endTime - startTime);
                }
            }

            // Compute hash for next window
            if (i < n - m) {
                t = (d * (t - text.charAt(i) * h) + text.charAt(i + m)) % prime;
                if (t < 0) t = t + prime;
            }
        }

        long endTime = System.nanoTime();
        return new Result(comparisons, endTime - startTime);
    }
}