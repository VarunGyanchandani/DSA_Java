import java.util.Arrays;

class Solution {
    public int largestPerimeter(int[] nums) {
        // Sort array in descending order
        Arrays.sort(nums);
        // Reverse the sorted array to get descending order
        for (int i = 0; i < nums.length / 2; i++) {
            int temp = nums[i];
            nums[i] = nums[nums.length - 1 - i];
            nums[nums.length - 1 - i] = temp;
        }

        // Check for valid triangle
        for (int i = 0; i < nums.length - 2; i++) {
            int a = nums[i];
            int b = nums[i + 1];
            int c = nums[i + 2];
            if (b + c > a) {
                return a + b + c;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1
        int[] nums1 = {2, 1, 2};
        int result1 = solution.largestPerimeter(nums1);
        System.out.println("The list is " + Arrays.toString(nums1));
        System.out.println("The largest perimeter is: " + result1);

        // Test case 2
        int[] nums2 = {1, 2, 10};
        int result2 = solution.largestPerimeter(nums2);
        System.out.println("\nThe list is " + Arrays.toString(nums2));
        System.out.println("The largest perimeter is: " + result2);
    }
}