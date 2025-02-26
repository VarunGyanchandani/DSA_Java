public class Main {

    // Method to calculate the maximum absolute sum of any subarray
    public int maxAbsoluteSum(int[] nums) {
        int maxSum = 0;
        int minSum = 0;
        int currentMax = 0;
        int currentMin = 0;

        for (int num : nums) {
            currentMax = Math.max(0, currentMax + num);
            currentMin = Math.min(0, currentMin + num);
            maxSum = Math.max(maxSum, currentMax);
            minSum = Math.min(minSum, currentMin);
        }

        return Math.max(maxSum, Math.abs(minSum));
    }

    // Main method for testing
    public static void main(String[] args) {
        Main solution = new Main();

        // Example arrays to test
        int[] nums1 = {1, -3, 2, 3, -4};
        int[] nums2 = {2, -5, 1, -4, 3, -2};

        // Calculating and printing results
        System.out.println("Maximum absolute sum of subarray for nums1: " + solution.maxAbsoluteSum(nums1)); // Output: 5
        System.out.println("Maximum absolute sum of subarray for nums2: " + solution.maxAbsoluteSum(nums2)); // Output: 8
    }
}
