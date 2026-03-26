package algorithms;

public class RabinKarp {
    // Result class to store the number of character comparisons and the execution time
    public static class Result {
        public int comparisons;  // counts how many character comparisons
        public long timeNs;// execution time in nanoseconds

        public Result(int comparisons, long timeNs) {
            this.comparisons = comparisons;
            this.timeNs = timeNs;
        }
    }
    // Main Rabin-Karp search function
    public static Result search(String text, String pattern) {
        long startTime = System.nanoTime(); // Start timer
        int comparisons = 0; //  comparison counter

        int n = text.length();  // Length of the text
        int m = pattern.length(); // Length of the pattern
        int prime = 101; // A prime number used for modulo in hashing
        int d = 256; // Number of possible characters (ASCII)

        // Compute h = pow(d, m-1) % prime
        // This will be used later to remove the leading character from hash

        int h = 1;
        for (int i = 0; i < m - 1; i++)
            h = (h * d) % prime;

        int p = 0; // hash for pattern
        int t = 0; // hash for text

        // Compute initial hash values for pattern and first text window
        for (int i = 0; i < m; i++) {
            p = (d * p + pattern.charAt(i)) % prime;
            t = (d * t + text.charAt(i)) % prime;
        }
// Slide the pattern over text one by one
        for (int i = 0; i <= n - m; i++) {

            comparisons++; // Count hash comparison
            if (p == t) {  // If hash matches, check characters one by one
                // Check character by character
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    comparisons++; // Count each character comparison
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false; // If mismatch found, break
                        break;
                    }
                }
                if (match) { //if match
                    long endTime = System.nanoTime();
                    return new Result(comparisons, endTime - startTime);
                }
            }

            // Compute hash for next window of text
            // Remove leading character and add trailing character
            if (i < n - m) {
                t = (d * (t - text.charAt(i) * h) + text.charAt(i + m)) % prime;
                if (t < 0) t = t + prime; // Make sure hash is positive
            }
        }

        // If pattern not found, return comparisons and total time
        long endTime = System.nanoTime();
        return new Result(comparisons, endTime - startTime);
    }
}