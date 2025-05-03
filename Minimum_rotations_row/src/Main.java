import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int n = tops.length;

        // Try making all tops equal to tops[0]
        int rotationsTop = checkRotations(tops[0], tops, bottoms, n);

        // Try making all tops equal to bottoms[0]
        int rotationsBottom = checkRotations(bottoms[0], tops, bottoms, n);

        // Try making all bottoms equal to tops[0]
        int rotationsTopForBottom = checkRotations(tops[0], bottoms, tops, n);

        // Try making all bottoms equal to bottoms[0]
        int rotationsBottomForBottom = checkRotations(bottoms[0], bottoms, tops, n);

        // Find the minimum valid rotation count
        int minRotations = Integer.MAX_VALUE;
        for (int rotation : new int[]{rotationsTop, rotationsBottom, rotationsTopForBottom, rotationsBottomForBottom}) {
            if (rotation != -1) {
                minRotations = Math.min(minRotations, rotation);
            }
        }

        return minRotations == Integer.MAX_VALUE ? -1 : minRotations;
    }

    private int checkRotations(int target, int[] primary, int[] secondary, int length) {
        /**
         * Check how many rotations are needed to make all values in primary equal to target.
         * Returns -1 if it's not possible.
         */
        int rotations = 0;
        for (int i = 0; i < length; i++) {
            if (primary[i] == target) {
                // Already matches, no rotation needed
                continue;
            } else if (secondary[i] == target) {
                // Can be rotated to match
                rotations++;
            } else {
                // Can't make this position match the target
                return -1;
            }
        }

        return rotations;
    }
}

class DominoVisualizer {
    public static void visualizeDominoes(int[] tops, int[] bottoms, List<Integer> rotatedIndices) {
        /**
         * Visualize the dominoes in ASCII art, marking rotated ones
         */
        int n = tops.length;
        String[] topLine = new String[n];
        String[] bottomLine = new String[n];
        String[] markers = new String[n];

        for (int i = 0; i < n; i++) {
            if (rotatedIndices != null && rotatedIndices.contains(i)) {
                topLine[i] = String.valueOf(bottoms[i]);
                bottomLine[i] = String.valueOf(tops[i]);
                markers[i] = "R";  // Rotated
            } else {
                topLine[i] = String.valueOf(tops[i]);
                bottomLine[i] = String.valueOf(bottoms[i]);
                markers[i] = " ";  // Not rotated
            }
        }

        System.out.println("┌─┐ ".repeat(n));
        System.out.print("│");
        System.out.print(String.join("│ │", topLine));
        System.out.println("│");
        System.out.println("├─┤ ".repeat(n));
        System.out.print("│");
        System.out.print(String.join("│ │", bottomLine));
        System.out.println("│");
        System.out.println("└─┘ ".repeat(n));
        System.out.print(" ");
        System.out.println(String.join("  ", markers));
        System.out.println("R = Rotated domino");
    }

    public static void displaySolution(int[] tops, int[] bottoms) {
        /**
         * Solve the domino rotation problem and display the solution
         */
        System.out.println("Original dominoes:");
        visualizeDominoes(tops, bottoms, null);

        Solution solution = new Solution();
        int minRotations = solution.minDominoRotations(tops, bottoms);

        if (minRotations == -1) {
            System.out.println("\nIt's not possible to make all tops or all bottoms the same.");
            return;
        }

        // Find which configuration gives the minimum rotations
        List<Integer> rotatedIndices = new ArrayList<>();
        int targetValue = -1;
        boolean isTopRow = true;

        // Check if making tops all equal to tops[0] is optimal
        int rotation = checkRotations(tops[0], tops, bottoms, tops.length);
        if (rotation != -1 && rotation == minRotations) {
            targetValue = tops[0];
            isTopRow = true;
            for (int i = 0; i < tops.length; i++) {
                if (tops[i] != targetValue && bottoms[i] == targetValue) {
                    rotatedIndices.add(i);
                }
            }
        }
        // Check if making tops all equal to bottoms[0] is optimal
        else if (checkRotations(bottoms[0], tops, bottoms, tops.length) == minRotations) {
            targetValue = bottoms[0];
            isTopRow = true;
            for (int i = 0; i < tops.length; i++) {
                if (tops[i] != targetValue && bottoms[i] == targetValue) {
                    rotatedIndices.add(i);
                }
            }
        }
        // Check if making bottoms all equal to tops[0] is optimal
        else if (checkRotations(tops[0], bottoms, tops, tops.length) == minRotations) {
            targetValue = tops[0];
            isTopRow = false;
            for (int i = 0; i < tops.length; i++) {
                if (bottoms[i] != targetValue && tops[i] == targetValue) {
                    rotatedIndices.add(i);
                }
            }
        }
        // Check if making bottoms all equal to bottoms[0] is optimal
        else {
            targetValue = bottoms[0];
            isTopRow = false;
            for (int i = 0; i < tops.length; i++) {
                if (bottoms[i] != targetValue && tops[i] == targetValue) {
                    rotatedIndices.add(i);
                }
            }
        }

        System.out.println("\nOptimal solution: " + minRotations + " rotation(s)");
        System.out.println("Making all " + (isTopRow ? "tops" : "bottoms") + " equal to " + targetValue);
        System.out.println("Rotating dominoes at positions: " + rotatedIndices);

        // Display the solution
        int[] rotatedTops = Arrays.copyOf(tops, tops.length);
        int[] rotatedBottoms = Arrays.copyOf(bottoms, bottoms.length);
        for (int i : rotatedIndices) {
            int temp = rotatedTops[i];
            rotatedTops[i] = rotatedBottoms[i];
            rotatedBottoms[i] = temp;
        }

        System.out.println("\nAfter rotations:");
        visualizeDominoes(rotatedTops, rotatedBottoms, rotatedIndices);
    }

    private static int checkRotations(int target, int[] primary, int[] secondary, int length) {
        /**
         * Check how many rotations are needed to make all values in primary equal to target.
         * Returns -1 if it's not possible.
         */
        int rotations = 0;
        for (int i = 0; i < length; i++) {
            if (primary[i] == target) {
                // Already matches, no rotation needed
                continue;
            } else if (secondary[i] == target) {
                // Can be rotated to match
                rotations++;
            } else {
                // Can't make this position match the target
                return -1;
            }
        }

        return rotations;
    }
}

public class Main {
    public static void main(String[] args) {
        // Example 1
        System.out.println("Example 1:");
        int[] tops1 = {2, 1, 2, 4, 2, 2};
        int[] bottoms1 = {5, 2, 6, 2, 3, 2};
        DominoVisualizer.displaySolution(tops1, bottoms1);

        System.out.println("\n" + "-".repeat(50) + "\n");

        // Example 2
        System.out.println("Example 2:");
        int[] tops2 = {3, 5, 1, 2, 3};
        int[] bottoms2 = {3, 6, 3, 3, 4};
        DominoVisualizer.displaySolution(tops2, bottoms2);

        System.out.println("\n" + "-".repeat(50) + "\n");

        // Additional example
        System.out.println("Additional example:");
        int[] tops3 = {1, 2, 1, 1, 1, 1};
        int[] bottoms3 = {1, 6, 2, 2, 2, 2};
        DominoVisualizer.displaySolution(tops3, bottoms3);
    }
}