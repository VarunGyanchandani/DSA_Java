import java.util.Arrays;

class Solution {

    public boolean isArraySpecial(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] % 2 == nums[i + 1] % 2) { // Check if parity is the same
                return false;
            }
        }
        return true; // If the loop completes without returning false, it's special
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums1 = {1};
        System.out.println("nums1: " + Arrays.toString(nums1) + ", isSpecial: " + solution.isArraySpecial(nums1)); // Output: true

        int[] nums2 = {2, 1, 4};
        System.out.println("nums2: " + Arrays.toString(nums2) + ", isSpecial: " + solution.isArraySpecial(nums2)); // Output: true

        int[] nums3 = {4, 3, 1, 6};
        System.out.println("nums3: " + Arrays.toString(nums3) + ", isSpecial: " + solution.isArraySpecial(nums3)); // Output: false

        int[] nums4 = {1, 2, 3, 4, 5};
        System.out.println("nums4: " + Arrays.toString(nums4) + ", isSpecial: " + solution.isArraySpecial(nums4)); // Output: true

        int[] nums5 = {2, 4, 6, 8};
        System.out.println("nums5: " + Arrays.toString(nums5) + ", isSpecial: " + solution.isArraySpecial(nums5)); // Output: false

        int[] nums6 = {1, 3, 5, 7};
        System.out.println("nums6: " + Arrays.toString(nums6) + ", isSpecial: " + solution.isArraySpecial(nums6)); // Output: false

        int[] nums7 = {2, 4, 1, 3, 5};
        System.out.println("nums7: " + Arrays.toString(nums7) + ", isSpecial: " + solution.isArraySpecial(nums7)); // Output: true

        int[] nums8 = {1, 2, 3, 4, 5, 6};
        System.out.println("nums8: " + Arrays.toString(nums8) + ", isSpecial: " + solution.isArraySpecial(nums8)); // Output: false

    }
}