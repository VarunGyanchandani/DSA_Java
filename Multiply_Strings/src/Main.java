class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int m = num1.length();
        int n = num2.length();
        int[] result = new int[m + n];

        // Process from right to left (most significant to least significant)
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j;
                int p2 = i + j + 1;
                int total = mul + result[p2];

                result[p2] = total % 10;
                result[p1] += total / 10;
            }
        }

        // Convert to string, skipping leading zeros
        StringBuilder sb = new StringBuilder();
        int start = 0;
        while (start < result.length && result[start] == 0) {
            start++;
        }

        // If all zeros, return "0"
        if (start == result.length) {
            return "0";
        }

        for (int i = start; i < result.length; i++) {
            sb.append(result[i]);
        }

        return sb.toString();
    }

    // Example usage
    public static void main(String[] args) {
        Solution solution = new Solution();

        String num1 = "123";
        String num2 = "456";
        String result = solution.multiply(num1, num2);
        System.out.println("Multiplication of " + num1 + " and " + num2 + " is: " + result);
    }
}