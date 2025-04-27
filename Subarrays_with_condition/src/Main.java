import java.util.Arrays;

class Solution {
    public int countSubarrays(int[] nums) {
        int count = 0;
        int n = nums.length;

        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("Checking all subarrays of length 3...");

        for (int i = 0; i < n - 2; i++) {
            int first = nums[i];
            int middle = nums[i + 1];
            int third = nums[i + 2];

            int[] subarray = {first, middle, third};

            System.out.println("Subarray " + i + ": " + Arrays.toString(subarray));
            System.out.println("  Checking if " + first + " + " + third + " = " + middle + "/2");

            // Note: In Java, we need to be careful with integer division
            // Using double to handle the division properly
            if (first + third == (double) middle / 2) {
                System.out.println("  ✓ Condition met: " + first + " + " + third + " = " + ((double) middle / 2));
                count++;
            } else {
                System.out.println("  ✗ Condition not met: " + first + " + " + third + " ≠ " + ((double) middle / 2));
            }
        }

        System.out.println("Total count of valid subarrays: " + count);
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example 1
        int[] nums1 = {1, 2, 1, 4, 1};
        System.out.println("\nExample 1:");
        int result1 = solution.countSubarrays(nums1);
        System.out.println("\nFinal result: " + result1);

        // Example 2
        int[] nums2 = {1, 1, 1};
        System.out.println("\nExample 2:");
        int result2 = solution.countSubarrays(nums2);
        System.out.println("\nFinal result: " + result2);

        // Additional Example
        int[] nums3 = {2, 6, 4, 8, 10, 2};
        System.out.println("\nAdditional Example:");
        int result3 = solution.countSubarrays(nums3);
        System.out.println("\nFinal result: " + result3);
    }
}