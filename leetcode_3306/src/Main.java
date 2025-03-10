class Solution {
    public int countOfSubstrings(String word, int k) {
        int n = word.length();
        int count = 0;
        String vowels = "aeiou";

        // Sliding window approach
        for (int left = 0; left < n; left++) {
            if (left + k + 5 > n) { // Ensures the window can fit
                break;
            }

            // Initialize vowel counts and consonant count
            int[] vowelCount = new int[5]; // For 'a', 'e', 'i', 'o', 'u'
            int consonantCount = 0;

            for (int right = left; right < n; right++) {
                char ch = word.charAt(right);

                // Update vowel counts or increment consonant count
                int vowelIndex = vowels.indexOf(ch);
                if (vowelIndex != -1) {
                    vowelCount[vowelIndex]++;
                } else {
                    consonantCount++;
                }

                // If too many consonants, break
                if (consonantCount > k) {
                    break;
                }

                // Check if there are exactly k consonants and all vowels present
                if (consonantCount == k && allVowelsPresent(vowelCount)) {
                    count++;
                }
            }
        }

        return count;
    }

    // Check if all vowels ('a', 'e', 'i', 'o', 'u') are present at least once
    private boolean allVowelsPresent(int[] vowelCount) {
        for (int count : vowelCount) {
            if (count == 0) {
                return false;
            }
        }
        return true;
    }

    // Main method for testing
    public static void main(String[] args) {
        Solution solution = new Solution();
        String word = "aeioubcdfg";
        int k = 2;
        int result = solution.countOfSubstrings(word, k);
        System.out.println("Number of valid substrings: " + result);
    }
}
