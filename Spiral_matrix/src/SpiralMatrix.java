class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class SpiralMatrix {

    public static ListNode createLinkedList(int[] values) {
        if (values.length == 0) {
            return null;
        }
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }
        return head;
    }

    public static int[][] generateMatrix(int m, int n, ListNode head) {
        // Initialize the matrix with -1
        int[][] matrix = new int[m][n];
        for (int[] row : matrix) {
            java.util.Arrays.fill(row, -1);
        }

        // Initialize boundaries
        int top = 0, bottom = m - 1;
        int left = 0, right = n - 1;

        // Traverse the linked list and fill the matrix in spiral order
        ListNode current = head;

        while (top <= bottom && left <= right) {
            // Fill top row
            for (int col = left; col <= right; col++) {
                if (current != null) {
                    matrix[top][col] = current.val;
                    current = current.next;
                }
            }
            top++;

            // Fill right column
            for (int row = top; row <= bottom; row++) {
                if (current != null) {
                    matrix[row][right] = current.val;
                    current = current.next;
                }
            }
            right--;

            // Fill bottom row
            for (int col = right; col >= left; col--) {
                if (current != null) {
                    matrix[bottom][col] = current.val;
                    current = current.next;
                }
            }
            bottom--;

            // Fill left column
            for (int row = bottom; row >= top; row--) {
                if (current != null) {
                    matrix[row][left] = current.val;
                    current = current.next;
                }
            }
            left++;
        }

        return matrix;
    }

    public static void main(String[] args) {
        // Example usage
        int[] values1 = {0, 1, 2};
        int[][] result1 = generateMatrix(1, 4, createLinkedList(values1));
        printMatrix(result1);

        int[] values2 = {3, 0, 2, 6, 8, 1, 7, 9, 4, 2, 5, 5, 0};
        int[][] result2 = generateMatrix(3, 5, createLinkedList(values2));
        printMatrix(result2);
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
