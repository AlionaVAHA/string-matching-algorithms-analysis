package algorithms;

public class KMP {
    public static int search(String text, String pattern) {

        int comparisons = 0;

        int[] lps = computeLPSArray(pattern);

        int i = 0;
        int j = 0;

        while (i < text.length()) {

            comparisons++;

            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == pattern.length()) {
                return comparisons;
            }

            else if (i < text.length() && pattern.charAt(j) != text.charAt(i)) {

                if (j != 0)
                    j = lps[j - 1];
                else
                    i++;
            }
        }

        return comparisons;
    }

    private static int[] computeLPSArray(String pattern) {

        int length = 0;
        int i = 1;

        int[] lps = new int[pattern.length()];

        while (i < pattern.length()) {

            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            }

            else {

                if (length != 0)
                    length = lps[length - 1];
                else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }
}
