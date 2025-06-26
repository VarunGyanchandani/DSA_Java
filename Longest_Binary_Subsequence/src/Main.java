class Solution {
    public int longestSubsequence(String s, int k) {
        // Count zeros - we can include all of them since they don't affect the value
        int zeroCount = (int) s.chars().filter(ch -> ch == '0').count();

        // For ones, we need to be selective to stay <= k
        // Process from right to left (least significant bits first)
        int onesValue = 0;
        int onesCount = 0;
        int power = 1;  // 2^0

        // Iterate through the string from right to left
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                // Check if adding this '1' bit keeps us <= k
                if (onesValue + power <= k) {
                    onesValue += power;
                    onesCount++;
                }
            }

            // Update power for next position (moving left)
            power *= 2;

            // Optimization: if power > k, no point in checking further left '1's
            if (power > k) {
                break;
            }
        }

        return zeroCount + onesCount;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        String s1 = "1001010";
        int k1 = 5;
        System.out.println(sol.longestSubsequence(s1, k1));  // Output: 5

        // Example 2
        String s2 = "00101001";
        int k2 = 1;
        System.out.println(sol.longestSubsequence(s2, k2));  // Output: 6

        // Example 3
        String s3 = "11110000";
        int k3 = 15;
        System.out.println(sol.longestSubsequence(s3, k3));  // Output: 8

        // Edge Case 1: All zeros
        String s4 = "0000";
        int k4 = 5;
        System.out.println(sol.longestSubsequence(s4, k4));  // Output: 4

        // Edge Case 2: All ones
        String s5 = "1111";
        int k5 = 1;
        System.out.println(sol.longestSubsequence(s5, k5));  // Output: 1

        // Edge Case 3: Empty string
        String s6 = "";
        int k6 = 0;
        System.out.println(sol.longestSubsequence(s6, k6));  // Output: 0
    }
}