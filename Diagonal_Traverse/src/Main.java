import java.util.*;

class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return new int[0];
        }

        int m = mat.length;
        int n = mat[0].length;
        int[] res = new int[m * n];
        int idx = 0;

        // Traverse all diagonals
        for (int k = 0; k < m + n - 1; k++) {
            int start = Math.max(0, k - (n - 1));
            int end = Math.min(m - 1, k);

            List<Integer> diag = new ArrayList<>();
            for (int i = start; i <= end; i++) {
                int j = k - i;
                diag.add(mat[i][j]);
            }

            // Reverse every alternate diagonal
            if (k % 2 == 0) {
                Collections.reverse(diag);
            }

            for (int val : diag) {
                res[idx++] = val;
            }
        }

        return res;
    }


    public static void main(String[] args) {
        Solution sol = new Solution();

        int[][] mat1 = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println("Matrix 1:");
        printMatrix(mat1);
        System.out.println("Diagonal Order: " + Arrays.toString(sol.findDiagonalOrder(mat1)));
        // Expected Output: [1, 2, 4, 7, 5, 3, 6, 8, 9]

        System.out.println("\n-----------------------------------\n");

        int[][] mat2 = {{1,2},{3,4}};
        System.out.println("Matrix 2:");
        printMatrix(mat2);
        System.out.println("Diagonal Order: " + Arrays.toString(sol.findDiagonalOrder(mat2)));
        // Expected Output: [1, 2, 3, 4]

        System.out.println("\n-----------------------------------\n");

        // Larger matrix
        int[][] mat3 = new int[4][5];
        int val = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                mat3[i][j] = val++;
            }
        }

        System.out.println("Matrix 3:");
        printMatrix(mat3);
        System.out.println("Diagonal Order: " + Arrays.toString(sol.findDiagonalOrder(mat3)));
    }

    private static void printMatrix(int[][] mat) {
        for (int[] row : mat) {
            System.out.println(Arrays.toString(row));
        }
    }
}
