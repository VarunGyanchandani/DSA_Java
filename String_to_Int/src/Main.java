class Solution {

    public int myAtoi(String s) {
        // Constants for 32-bit signed integer range
        int INT_MAX = (int) Math.pow(2, 31) - 1;
        int INT_MIN = (int) Math.pow(-2, 31);

        int i = 0;
        int sign = 1;
        int res = 0;
        int n = s.length();

        // 1. Ignore leading whitespace
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // 2. Determine the sign
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            if (s.charAt(i) == '-') {
                sign = -1;
            }
            i++;
        }

        // 3. Convert the number and handle overflow
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0'; // Convert char to int

            // Check for overflow before appending the new digit
            if (res > INT_MAX / 10 || (res == INT_MAX / 10 && digit > INT_MAX % 10)) {
                return sign == 1 ? INT_MAX : INT_MIN;
            }

            res = res * 10 + digit;
            i++;
        }

        // 4. Return the final result with the correct sign
        return sign * res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Cases
        String[] testCases = {
                "42",                             // Simple positive number
                "   -42",                         // Negative number with leading spaces
                "4193 with words",                // Valid number followed by non-digit characters
                "words and 987",                  // Invalid number at the start, should return 0
                "+123",                           // Positive number with a plus sign
                "-2147483649",                    // Out of range negative number (below INT_MIN)
                "21474836460",                    // Out of range positive number (above INT_MAX)
                "0000000000012345678",             // Leading zeros, valid number
                "",                               // Empty string
                "  +00123abc456"                  // Number with leading zeros and extra characters
        };

        int[] expectedResults = {
                42,                               // Expected result for "42"
                -42,                              // Expected result for "   -42"
                4193,                             // Expected result for "4193 with words"
                0,                                // Expected result for "words and 987"
                123,                              // Expected result for "+123"
                Integer.MIN_VALUE,                // Expected result for "-2147483649"
                Integer.MAX_VALUE,                // Expected result for "21474836460"
                12345678,                         // Expected result for "0000000000012345678"
                0,                                // Expected result for ""
                123                               // Expected result for "  +00123abc456"
        };

        // Running the test cases
        for (int i = 0; i < testCases.length; i++) {
            String inputStr = testCases[i];
            int expectedOutput = expectedResults[i];
            int result = solution.myAtoi(inputStr);

            System.out.println("Test case " + (i + 1) + ":");
            System.out.println("Input: '" + inputStr + "'");
            System.out.println("Expected: " + expectedOutput + ", Got: " + result + "\n");
        }
    }
}
