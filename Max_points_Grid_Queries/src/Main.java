import java.util.*;

class MaxPointsGrid {
    public static int[] maxPoints(int[][] grid, int[] queries) {
        int m = grid.length;
        int n = grid[0].length;

        // Function to process each query
        return Arrays.stream(queries)
                .map(query -> simulateQuery(grid, query))
                .toArray();
    }

    // Helper function to simulate query and calculate points
    private static int simulateQuery(int[][] grid, int query) {
        int m = grid.length;
        int n = grid[0].length;

        // Track visited cells
        Set<String> visited = new HashSet<>();
        visited.add("0,0");

        // Track points
        int points = 0;

        // Directions: right, down, left, up
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // Queue to track cells to explore
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});

        // Explore cells
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<int[]> currentCells = new ArrayList<>();

            // Process current level of cells
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int r = cell[0], c = cell[1];

                // Explore adjacent cells if value is less than query
                if (grid[r][c] < query) {
                    for (int[] dir : directions) {
                        int nr = r + dir[0];
                        int nc = c + dir[1];

                        // Check if new cell is valid and not visited
                        String newCellKey = nr + "," + nc;
                        if (nr >= 0 && nr < m && nc >= 0 && nc < n &&
                                !visited.contains(newCellKey) &&
                                grid[nr][nc] < query) {

                            // Mark as visited and add points
                            visited.add(newCellKey);
                            points++;
                            queue.offer(new int[]{nr, nc});
                        }
                    }
                }
            }
        }

        return points;
    }

    // Main method for testing
    public static void main(String[] args) {
        int[][] grid = {
                {1, 2, 3},
                {2, 5, 7},
                {3, 5, 1}
        };
        int[] queries = {5, 6, 2};

        // Calculate maximum points for each query
        int[] result = maxPoints(grid, queries);

        // Print results
        System.out.println("Final Result: " + Arrays.toString(result));

        // Detailed explanation
        System.out.println("\nDetailed Explanation:");
        for (int i = 0; i < queries.length; i++) {
            System.out.println("\nQuery " + queries[i] + ":");
            System.out.println("Maximum Points: " + result[i]);
        }
    }
}