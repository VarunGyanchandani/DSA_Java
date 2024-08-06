public class Main {
    public static int minSwaps(int[] nums) {
        int n = nums.length;
        int totalOnes = 0;

        // Count the total number of 1's in the array
        for (int num : nums) {
            if (num == 1) {
                totalOnes++;
            }
        }

        // Handle edge cases
        if (totalOnes <= 1) {
            return 0;
        }

        // Extend the array
        int[] extendedNums = new int[2 * n];
        for (int i = 0; i < n; i++) {
            extendedNums[i] = nums[i];
            extendedNums[i + n] = nums[i];
        }

        int currentWindowCount = 0;
        for (int i = 0; i < totalOnes; i++) {
            if (extendedNums[i] == 1) {
                currentWindowCount++;
            }
        }

        int maxOnesInWindow = currentWindowCount;

        for (int i = 1; i <= n; i++) {
            if (extendedNums[i - 1] == 1) {
                currentWindowCount--;
            }
            if (extendedNums[i + totalOnes - 1] == 1) {
                currentWindowCount++;
            }
            maxOnesInWindow = Math.max(maxOnesInWindow, currentWindowCount);
        }

        // Calculate the minimum swaps needed
        return totalOnes - maxOnesInWindow;
    }

    public static void main(String[] args) {
        int[] nums1 = {0, 1, 0, 1, 1, 0, 0};
        int[] nums2 = {0, 1, 1, 1, 0, 0, 1, 1, 0};
        int[] nums3 = {1, 1, 0, 0, 1};

        System.out.println(minSwaps(nums1));  // Output: 1
        System.out.println(minSwaps(nums2));  // Output: 2
        System.out.println(minSwaps(nums3));  // Output: 0
    }
}
