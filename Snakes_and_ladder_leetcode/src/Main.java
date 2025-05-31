import java.util.*;

class Solution {
    public int snakesAndLadders(int[][] board) {
        SnakesAndLaddersGame game = new SnakesAndLaddersGame(board);
        return game.solve();
    }
}

class SnakesAndLaddersGame {
    private int[][] board;
    private int n;
    private int target;

    public SnakesAndLaddersGame(int[][] board) {
        this.board = board;
        this.n = board.length;
        this.target = n * n;
    }

    /**
     * Convert square number to (row, col) coordinates
     */
    public int[] getCoordinates(int square) {
        square--; // Convert to 0-based indexing
        int row = n - 1 - square / n;
        int col = square % n;

        // For odd rows (from bottom), reverse the column
        if ((n - 1 - row) % 2 == 1) {
            col = n - 1 - col;
        }

        return new int[]{row, col};
    }

    /**
     * Convert (row, col) coordinates to square number
     */
    public int getSquareNumber(int row, int col) {
        int rowFromBottom = n - 1 - row;
        int adjustedCol;

        if (rowFromBottom % 2 == 1) { // Odd rows go right to left
            adjustedCol = n - 1 - col;
        } else { // Even rows go left to right
            adjustedCol = col;
        }

        return rowFromBottom * n + adjustedCol + 1;
    }

    /**
     * Find minimum dice rolls to reach the target
     */
    public int solve() {
        Queue<int[]> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.offer(new int[]{1, 0}); // {current_square, moves}
        visited.add(1);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currSquare = current[0];
            int moves = current[1];

            if (currSquare == target) {
                return moves;
            }

            // Try all dice rolls (1-6)
            for (int dice = 1; dice <= 6; dice++) {
                int nextSquare = currSquare + dice;

                if (nextSquare > target) {
                    break;
                }

                // Get coordinates and check for snake/ladder
                int[] coords = getCoordinates(nextSquare);
                int row = coords[0], col = coords[1];
                int finalSquare = nextSquare;

                if (board[row][col] != -1) {
                    finalSquare = board[row][col];
                }

                if (!visited.contains(finalSquare)) {
                    visited.add(finalSquare);
                    queue.offer(new int[]{finalSquare, moves + 1});
                }
            }
        }

        return -1;
    }

    /**
     * Find minimum dice rolls and return the path taken
     */
    public GameResult solveWithPath() {
        Queue<PathState> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        List<Integer> initialPath = new ArrayList<>();
        initialPath.add(1);
        queue.offer(new PathState(1, 0, initialPath));
        visited.add(1);

        while (!queue.isEmpty()) {
            PathState current = queue.poll();
            int currSquare = current.square;
            int moves = current.moves;
            List<Integer> path = current.path;

            if (currSquare == target) {
                return new GameResult(moves, path);
            }

            for (int dice = 1; dice <= 6; dice++) {
                int nextSquare = currSquare + dice;

                if (nextSquare > target) {
                    break;
                }

                int[] coords = getCoordinates(nextSquare);
                int row = coords[0], col = coords[1];
                int finalSquare = nextSquare;

                if (board[row][col] != -1) {
                    finalSquare = board[row][col];
                }

                if (!visited.contains(finalSquare)) {
                    visited.add(finalSquare);
                    List<Integer> newPath = new ArrayList<>(path);
                    newPath.add(nextSquare);

                    if (finalSquare != nextSquare) {
                        newPath.add(finalSquare);
                    }

                    queue.offer(new PathState(finalSquare, moves + 1, newPath));
                }
            }
        }

        return new GameResult(-1, new ArrayList<>());
    }

    /**
     * Print the board with square numbers and snakes/ladders
     */
    public void printBoard() {
        System.out.println("\n" + n + "x" + n + " Snakes and Ladders Board:");
        System.out.println("=".repeat(n * 8));

        for (int i = 0; i < n; i++) {
            // Print square numbers
            StringBuilder rowNums = new StringBuilder();
            for (int j = 0; j < n; j++) {
                int squareNum = getSquareNumber(i, j);
                rowNums.append(String.format("%2d", squareNum));
                if (j < n - 1) rowNums.append(" | ");
            }
            System.out.println(rowNums.toString());

            // Print snake/ladder info
            StringBuilder rowInfo = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (board[i][j] != -1) {
                    rowInfo.append(String.format("→%2d", board[i][j]));
                } else {
                    rowInfo.append("   ");
                }
                if (j < n - 1) rowInfo.append(" | ");
            }
            System.out.println(rowInfo.toString());

            if (i < n - 1) {
                System.out.println("-".repeat(n * 6 - 1));
            }
        }

        System.out.println("=".repeat(n * 8));
    }

    // Helper classes
    private static class PathState {
        int square;
        int moves;
        List<Integer> path;

        PathState(int square, int moves, List<Integer> path) {
            this.square = square;
            this.moves = moves;
            this.path = path;
        }
    }
}

class GameResult {
    public int moves;
    public List<Integer> path;

    public GameResult(int moves, List<Integer> path) {
        this.moves = moves;
        this.path = path;
    }
}

class SnakesAndLaddersDemo {

    /**
     * Create example boards for testing
     */
    public static List<BoardExample> createExampleBoards() {
        List<BoardExample> examples = new ArrayList<>();

        // Example 1: 6x6 board from the problem
        int[][] board1 = {
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 35, -1, -1, 13, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 15, -1, -1, -1, -1}
        };

        // Example 2: 2x2 board
        int[][] board2 = {
                {-1, -1},
                {-1, 3}
        };

        // Example 3: 4x4 board with multiple snakes and ladders
        int[][] board3 = {
                {-1, -1, -1, -1},
                {-1, -1, 9, -1},
                {-1, 14, -1, -1},
                {-1, -1, -1, -1}
        };

        examples.add(new BoardExample("6x6 Board (Example 1)", board1));
        examples.add(new BoardExample("2x2 Board (Example 2)", board2));
        examples.add(new BoardExample("4x4 Board (Custom)", board3));

        return examples;
    }

    /**
     * Demonstrate the solution with examples
     */
    public static void demonstrateSolution() {
        System.out.println("🐍🪜 SNAKES AND LADDERS SOLVER 🪜🐍");
        System.out.println("=".repeat(50));

        List<BoardExample> examples = createExampleBoards();

        for (BoardExample example : examples) {
            System.out.println("\n📋 " + example.name);
            SnakesAndLaddersGame game = new SnakesAndLaddersGame(example.board);
            game.printBoard();

            // Solve and show path
            GameResult result = game.solveWithPath();

            if (result.moves == -1) {
                System.out.println("\n❌ No solution found!");
            } else {
                System.out.println("\n✅ Minimum moves required: " + result.moves);
                System.out.print("🎯 Path taken: ");
                for (int i = 0; i < result.path.size(); i++) {
                    System.out.print(result.path.get(i));
                    if (i < result.path.size() - 1) System.out.print(" → ");
                }
                System.out.println();

                // Explain the path
                System.out.println("\n📝 Step-by-step explanation:");
                for (int i = 0; i < result.path.size() - 1; i++) {
                    int curr = result.path.get(i);
                    int next = result.path.get(i + 1);

                    // Check if this was a snake/ladder move
                    if (i < result.path.size() - 2 && Math.abs(next - curr) > 6) {
                        System.out.println("   Step " + (i + 1) + ": Square " + curr +
                                " → Square " + next + " (Snake/Ladder)");
                    } else {
                        int diceRoll = next - curr;
                        if (diceRoll <= 6) {
                            System.out.println("   Step " + (i + 1) + ": Square " + curr +
                                    " → Square " + next + " (Dice roll: " + diceRoll + ")");
                        }
                    }
                }
            }

            System.out.println("\n" + "-".repeat(50));
        }
    }

    /**
     * Test coordinate conversion functions
     */
    public static void testCoordinateConversion() {
        System.out.println("\n🧪 TESTING COORDINATE CONVERSION");
        System.out.println("=".repeat(40));

        // Test with 6x6 board
        int[][] testBoard = new int[6][6];
        for (int i = 0; i < 6; i++) {
            Arrays.fill(testBoard[i], -1);
        }

        SnakesAndLaddersGame game = new SnakesAndLaddersGame(testBoard);
        int[] testSquares = {1, 6, 7, 12, 13, 18, 19, 24, 25, 30, 31, 36};

        for (int square : testSquares) {
            int[] coords = game.getCoordinates(square);
            int backToSquare = game.getSquareNumber(coords[0], coords[1]);
            System.out.println(String.format("Square %2d → (%d, %d) → Square %2d ✓",
                    square, coords[0], coords[1], backToSquare));
        }
    }

    /**
     * Benchmark the algorithm performance
     */
    public static void benchmarkPerformance() {
        System.out.println("\n⚡ PERFORMANCE BENCHMARK");
        System.out.println("=".repeat(30));

        int[] sizes = {6, 10, 15, 20};

        for (int size : sizes) {
            // Create a board with some random snakes and ladders
            int[][] board = new int[size][size];
            for (int i = 0; i < size; i++) {
                Arrays.fill(board[i], -1);
            }

            // Add a few snakes and ladders
            if (size >= 6) {
                board[size-2][1] = size * size - 5; // Ladder near start
                board[1][size-2] = 5; // Snake near end
            }

            SnakesAndLaddersGame game = new SnakesAndLaddersGame(board);

            long startTime = System.nanoTime();
            int result = game.solve();
            long endTime = System.nanoTime();

            double timeMs = (endTime - startTime) / 1_000_000.0;
            System.out.println(String.format("Board size %2dx%2d: %2d moves, Time: %.2fms",
                    size, size, result, timeMs));
        }
    }

    /**
     * Quick example for direct usage
     */
    public static void quickExample() {
        System.out.println("\n🚀 QUICK EXAMPLE");
        System.out.println("=".repeat(20));

        int[][] board = {
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 35, -1, -1, 13, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 15, -1, -1, -1, -1}
        };

        SnakesAndLaddersGame game = new SnakesAndLaddersGame(board);
        int minMoves = game.solve();
        System.out.println("Minimum moves needed: " + minMoves);

        // Get detailed path
        GameResult result = game.solveWithPath();
        System.out.print("Path: " + result.path);
        System.out.println();
    }

    /**
     * Main method to run all demonstrations
     */
    public static void main(String[] args) {
        demonstrateSolution();
        testCoordinateConversion();
        benchmarkPerformance();
        quickExample();

        System.out.println("\n🎉 Demo completed! Try creating your own boards!");
    }

    // Helper class for board examples
    private static class BoardExample {
        String name;
        int[][] board;

        BoardExample(String name, int[][] board) {
            this.name = name;
            this.board = board;
        }
    }
}

// LeetCode compatible class
class LeetCodeSolution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int target = n * n;

        Queue<int[]> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.offer(new int[]{1, 0}); // {current_square, moves}
        visited.add(1);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currSquare = current[0];
            int moves = current[1];

            if (currSquare == target) {
                return moves;
            }

            // Try all dice rolls (1-6)
            for (int dice = 1; dice <= 6; dice++) {
                int nextSquare = currSquare + dice;

                if (nextSquare > target) {
                    break;
                }

                // Get coordinates
                int[] coords = getCoordinates(nextSquare, n);
                int row = coords[0], col = coords[1];
                int finalSquare = nextSquare;

                if (board[row][col] != -1) {
                    finalSquare = board[row][col];
                }

                if (!visited.contains(finalSquare)) {
                    visited.add(finalSquare);
                    queue.offer(new int[]{finalSquare, moves + 1});
                }
            }
        }

        return -1;
    }

    private int[] getCoordinates(int square, int n) {
        square--; // Convert to 0-based indexing
        int row = n - 1 - square / n;
        int col = square % n;

        // For odd rows (from bottom), reverse the column
        if ((n - 1 - row) % 2 == 1) {
            col = n - 1 - col;
        }

        return new int[]{row, col};
    }
}