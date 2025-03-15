class Solution {
    public int minCapability(int[] nums, int k) {
        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;

        for (int num : nums) {
            left = Math.min(left, num);
            right = Math.max(right, num);
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canSteal(nums, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean canSteal(int[] nums, int k, int capability) {
        int count = 0;
        boolean prevStolen = false;
        for (int num : nums) {
            if (num <= capability && !prevStolen) {
                count++;
                prevStolen = true;
            } else {
                prevStolen = false;
            }
        }
        return count >= k;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums1 = {2, 3, 5, 9};
        int k1 = 2;
        int result1 = solution.minCapability(nums1, k1);
        System.out.println("Example 1: nums = " + java.util.Arrays.toString(nums1) + ", k = " + k1 + ", Minimum Capability = " + result1);

        int[] nums2 = {2, 7, 9, 3, 1};
        int k2 = 2;
        int result2 = solution.minCapability(nums2, k2);
        System.out.println("Example 2: nums = " + java.util.Arrays.toString(nums2) + ", k = " + k2 + ", Minimum Capability = " + result2);

        int[] nums3 = {2, 10, 5, 20, 1};
        int k3 = 3;
        int result3 = solution.minCapability(nums3, k3);
        System.out.println("Example 3: nums = " + java.util.Arrays.toString(nums3) + ", k = " + k3 + ", Minimum Capability = " + result3);

        int[] nums4 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int k4 = 5;
        int result4 = solution.minCapability(nums4, k4);
        System.out.println("Example 4: nums = " + java.util.Arrays.toString(nums4) + ", k = " + k4 + ", Minimum Capability = " + result4);
    }
}