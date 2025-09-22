
class Solution {

    public int maxFrequencyElements(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] freq = new int[101];
        
        for (int num : nums) {
            freq[num]++;
        }

        int maxF = 0;
        for (int f : freq) {
            maxF = Math.max(maxF, f);
        }

        int count = 0;
        for (int f : freq) {
            if (f == maxF) {
                count++;
            }
        }

        return count * maxF;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums1 = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4};
        System.out.println(solution.maxFrequencyElements(nums1));

        int[] nums2 = {5};
        System.out.println(solution.maxFrequencyElements(nums2));

        int[] nums3 = {10, 20, 30, 40, 50};
        System.out.println(solution.maxFrequencyElements(nums3));

        int[] nums4 = {};
        System.out.println(solution.maxFrequencyElements(nums4));

        int[] nums5 = {2, 2, 3, 3, 3, 3, 5, 5};
        System.out.println(solution.maxFrequencyElements(nums5));
    }
}
