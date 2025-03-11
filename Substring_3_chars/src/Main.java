class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int result = 0;

        // Initialize counts of a, b, c in current window
        int[] count = new int[3]; // count[0] for 'a', count[1] for 'b', count[2] for 'c'

        // Initialize left pointer of sliding window
        int left = 0;

        // Iterate with right pointer
        for (int right = 0; right < n; right++) {
            // Include current character in window
            count[s.charAt(right) - 'a']++;

            // Shrink window from left until we don't have all three characters
            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                // For each valid window ending at 'right', all possible extensions to the right
                // will also be valid. There are (n - right) such extensions.
                result += n - right;

                // Remove leftmost character and move left pointer
                count[s.charAt(left) - 'a']--;
                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example usage
        System.out.println(solution.numberOfSubstrings("abcabc"));
    }
}
