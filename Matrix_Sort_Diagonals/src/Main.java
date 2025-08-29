import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MatrixSort {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;
        int[][] result = new int[n][n];

        for (int d = -(n - 1); d < n; d++) {
            List<Integer> values = new ArrayList<>();
            List<int[]> positions = new ArrayList<>();
            int start_i = Math.max(0, d);
            int end_i = Math.min(n - 1, n - 1 + d);

            for (int i = start_i; i <= end_i; i++) {
                int j = i - d;
                values.add(grid[i][j]);
                positions.add(new int[]{i, j});
            }

            // Sort values
            Integer[] valuesArray = values.toArray(new Integer[0]);
            if (d >= 0) {
                Arrays.sort(valuesArray, (a, b) -> b - a); // Reverse sort for d >= 0
            } else {
                Arrays.sort(valuesArray); // Normal sort for d < 0
            }

            // Place sorted values back
            for (int k = 0; k < positions.size(); k++) {
                int[] pos = positions.get(k);
                result[pos[0]][pos[1]] = valuesArray[k];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] inputMatrix = {
                {10, 2, 30, 4},
                {5, 6, 7, 8},
                {9, 1, 11, 12},
                {13, 14, 15, 3}
        };

        MatrixSort solution = new MatrixSort();
        int[][] sortedMatrix = solution.sortMatrix(inputMatrix);

        // Print results
        System.out.println("Original Matrix:");
        for (int[] row : inputMatrix) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("\nSorted Matrix (by diagonals):");
        for (int[] row : sortedMatrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}