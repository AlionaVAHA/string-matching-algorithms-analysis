package algorithms;

public class KMP {
    // Result class to store output
    public static class Result {
        public int comparisons;// number of character comparisons
        public long timeNs;// number of character comparisons

        // Constructor to initialize result values
        public Result(int comparisons, long timeNs) {
            this.comparisons = comparisons;
            this.timeNs = timeNs;
        }
    }
    // Main KMP search method
    public static Result search(String text, String pattern) {
        long startTime = System.nanoTime(); // Start time measurement
        int comparisons = 0; // counter for comparisons

        // Preprocess pattern to get LPS array [Longest Prefix Suffix]
        int[] lps = computeLPSArray(pattern);

        int i = 0;// index for text
        int j = 0;// index for pattern

        while (i < text.length()) { // Loop for the text

            comparisons++; // count each comparison

            if (pattern.charAt(j) == text.charAt(i)) {
                // If characters match, move both pointers
                i++;
                j++;
            }
            // If full pattern is matched
            if (j == pattern.length()) {
                long endTime = System.nanoTime();
                // Return comparisons and execution time
                return new Result(comparisons, endTime - startTime);
            }
            // If mismatch occurs
            else if (i < text.length() && pattern.charAt(j) != text.charAt(i)) {
                // Use LPS to avoid re-checking characters
                if (j != 0)
                    j = lps[j - 1]; // jump back in pattern jump using LPS
                else
                    i++;// Use LPS to avoid re-checking characters
            }
        }

        long endTime = System.nanoTime();
        return new Result(comparisons, endTime - startTime);
    }
    // method to compute LPS (Longest Prefix Suffix) array
    private static int[] computeLPSArray(String pattern) {
        int length = 0; // length of previous longest prefix suffix
        int i = 1;// start from second character

        int[] lps = new int[pattern.length()]; // LPS array

        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++; // increase prefix length
                lps[i] = length;  // store it in array
                i++;
            } else {
                if (length != 0)
                    length = lps[length - 1];// fallback using LPS
                else {
                    lps[i] = 0;// no prefix match
                    i++;
                }
            }
        }

        return lps; // return computed LPS array
    }
}