import java.util.Scanner;

class Solution {
    public int findNumbers(int[] nums) {
        int count = 0;
        for (int num : nums) {
            // Convert the number to a string and check if its length is even
            if (String.valueOf(num).length() % 2 == 0) {
                count++;
            }
        }
        return count;
    }
}

class EvenDigitsCounter {
    public static void main(String[] args) {
        // Create instance of Solution class
        Solution solution = new Solution();

        // Example 1
        int[] example1 = {12, 345, 2, 6, 7896};
        int result1 = solution.findNumbers(example1);
        System.out.println("Example 1:");
        System.out.print("Input: nums = [");
        printArray(example1);
        System.out.println("]");
        System.out.println("Output: " + result1);
        System.out.println("Explanation:");
        for (int num : example1) {
            int digits = String.valueOf(num).length();
            String evenOrOdd = (digits % 2 == 0) ? "even" : "odd";
            System.out.println(num + " contains " + digits + " digits (" + evenOrOdd + " number of digits).");
        }

        System.out.println("\n" + "-".repeat(50) + "\n");

        // Example 2
        int[] example2 = {555, 901, 482, 1771};
        int result2 = solution.findNumbers(example2);
        System.out.println("Example 2:");
        System.out.print("Input: nums = [");
        printArray(example2);
        System.out.println("]");
        System.out.println("Output: " + result2);
        System.out.println("Explanation:");
        for (int num : example2) {
            int digits = String.valueOf(num).length();
            String evenOrOdd = (digits % 2 == 0) ? "even" : "odd";
            System.out.println(num + " contains " + digits + " digits (" + evenOrOdd + " number of digits).");
        }

        System.out.println("\n" + "-".repeat(50) + "\n");

        // Custom example with user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Try your own array of numbers:");
        System.out.print("Enter the number of elements: ");

        try {
            int n = scanner.nextInt();
            int[] userNums = new int[n];

            System.out.println("Enter " + n + " integers:");
            for (int i = 0; i < n; i++) {
                userNums[i] = scanner.nextInt();
            }

            int userResult = solution.findNumbers(userNums);
            System.out.print("Input: nums = [");
            printArray(userNums);
            System.out.println("]");
            System.out.println("Output: " + userResult);
            System.out.println("Explanation:");
            for (int num : userNums) {
                int digits = String.valueOf(num).length();
                String evenOrOdd = (digits % 2 == 0) ? "even" : "odd";
                System.out.println(num + " contains " + digits + " digits (" + evenOrOdd + " number of digits).");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter valid integers.");
        } finally {
            scanner.close();
        }
    }

    // Helper method to print arrays
    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
    }
}