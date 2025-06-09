// Main.java

public class Main {

    public static void main(String[] args) {
        Solution solver = new Solution();

        int[][] examples = {
                {13, 2},   // Expected output: 10
                {1, 1},    // Expected output: 1
                {100, 10}, // Additional case: should give 17
                {1000, 100} // Large range
        };

        for (int[] example : examples) {
            int n = example[0];
            int k = example[1];
            int result = solver.findKthNumber(n, k);
            System.out.println("n = " + n + ", k = " + k + " => kth smallest: " + result);
        }
    }
}


class Solution {
    public int findKthNumber(int n, int k) {
        int current = 1;
        k -= 1;

        while (k > 0) {
            int count = 0;
            long first = current;
            long last = current + 1;

            while (first <= n) {
                count += Math.min((long)n + 1, last) - first;
                first *= 10;
                last *= 10;
            }

            if (count <= k) {
                current += 1;
                k -= count;
            } else {
                current *= 10;
                k -= 1;
            }
        }

        return current;
    }
}
