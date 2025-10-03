import java.util.PriorityQueue;

class Solution {

    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
            return 0;
        }

        int m = heightMap.length, n = heightMap[0].length;
        if (m < 3 || n < 3) {
            return 0;
        }

        // Min heap: (height, row, col)
        PriorityQueue<Cell> minHeap = new PriorityQueue<>((a, b) -> a.height - b.height);
        boolean[][] visited = new boolean[m][n];

        // Add all border cells to heap
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    minHeap.offer(new Cell(heightMap[i][j], i, j));
                    visited[i][j] = true;
                }
            }
        }

        int water = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // Directions for right, down, left, up

        // Process cells from lowest to highest
        while (!minHeap.isEmpty()) {
            Cell cell = minHeap.poll();
            int height = cell.height;
            int row = cell.row;
            int col = cell.col;

            // Check all 4 neighbors
            for (int[] dir : directions) {
                int nr = row + dir[0], nc = col + dir[1];

                // Skip if out of bounds or already visited
                if (nr < 0 || nr >= m || nc < 0 || nc >= n || visited[nr][nc]) {
                    continue;
                }

                visited[nr][nc] = true;

                // Water level at this cell is at least 'height'
                // If neighbor is lower, it can trap water
                water += Math.max(0, height - heightMap[nr][nc]);

                // Push neighbor with the effective height (max of its height and current water level)
                minHeap.offer(new Cell(Math.max(height, heightMap[nr][nc]), nr, nc));
            }
        }

        return water;
    }

    // Helper class to represent a cell in the grid
    private static class Cell {
        int height, row, col;

        Cell(int height, int row, int col) {
            this.height = height;
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] heightMap = {
                {1, 4, 3, 1, 3, 2},
                {3, 2, 1, 3, 2, 4},
                {2, 3, 3, 2, 3, 1},
                {3, 2, 1, 3, 4, 2},
                {1, 3, 2, 1, 2, 1}
        };
        int result = solution.trapRainWater(heightMap);
        System.out.println("Trapped rainwater: " + result + " units");
    }
}
