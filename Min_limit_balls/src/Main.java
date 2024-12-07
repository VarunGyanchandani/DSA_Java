class Solution {

    // Helper function to check if it's possible to achieve the maxSize with the given number of operations
    private boolean canAchieveMaxSize(int maxSize, int[] nums, int maxOperations) {
        int splitsNeeded = 0;
        for (int balls : nums) {
            if (balls > maxSize) {
                // Calculate the number of splits needed to ensure no bag has more than maxSize balls
                splitsNeeded += (balls - 1) / maxSize;
            }
            if (splitsNeeded > maxOperations) {
                return false;
            }
        }
        return true;
    }

    public int minPenalty(int[] nums, int maxOperations) {
        // Binary search for the smallest possible maximum bag size
        int left = 1, right = Integer.MIN_VALUE;

        // Find the maximum number of balls in any bag to set the upper bound for binary search
        for (int balls : nums) {
            right = Math.max(right, balls);
        }

        // Binary search
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canAchieveMaxSize(mid, nums, maxOperations)) {
                right = mid;  // Try for a smaller max size
            } else {
                left = mid + 1;  // Increase the max size
            }
        }

        return left;  // The minimum possible penalty
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example 1
        int[] nums1 = {9};
        int maxOperations1 = 2;
        System.out.println(solution.minPenalty(nums1, maxOperations1));  // Output: 3

        // Example 2
        int[] nums2 = {2, 4, 8, 2};
        int maxOperations2 = 4;
        System.out.println(solution.minPenalty(nums2, maxOperations2));  // Output: 2
    }
}