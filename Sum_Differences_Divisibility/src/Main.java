// Solution.java
class Solution {

    public int differenceOfSums(int n, int m) {
        int num1 = 0; // Sum of numbers not divisible by m
        int num2 = 0; // Sum of numbers divisible by m

        // Iterate from 1 to n (inclusive)
        for (int i = 1; i <= n; i++) {
            if (i % m == 0) {
                // If i is divisible by m, add it to num2
                num2 += i;
            } else {
                // If i is not divisible by m, add it to num1
                num1 += i;
            }
        }

        // Return the difference between num1 and num2
        return num1 - num2;
    }

    // Example usage (optional, can be in a main method or a test class)
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        int n1 = 10;
        int m1 = 3;
        int result1 = sol.differenceOfSums(n1, m1);
        System.out.println("For n = " + n1 + ", m = " + m1 + ", difference is: " + result1); // Output: 19

        // Example 2
        int n2 = 5;
        int m2 = 1;
        int result2 = sol.differenceOfSums(n2, m2);
        System.out.println("For n = " + n2 + ", m = " + m2 + ", difference is: " + result2); // Output: -15

        // Example 3
        int n3 = 20;
        int m3 = 5;
        int result3 = sol.differenceOfSums(n3, m3);
        System.out.println("For n = " + n3 + ", m = " + m3 + ", difference is: " + result3); // Output: 110
    }
}
