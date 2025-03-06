import java.util.List;
import java.util.ArrayList;

class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        int[] count = new int[n * n + 1];

        // Count occurrences of each number in the grid
        for (int[] row : grid) {
            for (int num : row) {
                count[num]++;
            }
        }

        int repeated = -1;
        int missing = -1;

        // Find the repeated and missing values
        for (int num = 1; num <= n * n; num++) {
            if (count[num] == 2) {
                repeated = num;
            } else if (count[num] == 0) {
                missing = num;
            }

            if (repeated != -1 && missing != -1) {
                break;
            }
        }

        return new int[]{repeated, missing};
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] grid1 = {{1, 3}, {2, 2}};
        int[] result1 = solution.findMissingAndRepeatedValues(grid1);
        System.out.println("[" + result1[0] + ", " + result1[1] + "]"); // Output: [2, 4]

        int[][] grid2 = {{9, 1, 7}, {8, 9, 2}, {3, 4, 6}};
        int[] result2 = solution.findMissingAndRepeatedValues(grid2);
        System.out.println("[" + result2[0] + ", " + result2[1] + "]"); // Output: [9, 5]
    }
}