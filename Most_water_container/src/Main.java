// MaxWaterContainer.java
import java.util.*;

class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int w = right - left;
            maxArea = Math.max(maxArea, h * w);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example 1
        int[] height1 = {1,8,6,2,5,4,8,3,7};
        System.out.println("Max water container area (Example 1): " + solution.maxArea(height1)); // Expected: 49

        // Example 2
        int[] height2 = {1,1};
        System.out.println("Max water container area (Example 2): " + solution.maxArea(height2)); // Expected: 1

        // Edge Case: Increasing heights
        int[] height3 = {1,2,3,4,5};
        System.out.println("Max water container area (Increasing): " + solution.maxArea(height3)); // Expected: 6

        // Edge Case: All heights are zero
        int[] height4 = {0,0,0,0};
        System.out.println("Max water container area (All zero): " + solution.maxArea(height4)); // Expected: 0
    }
}
