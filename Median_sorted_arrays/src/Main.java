class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int m = nums1.length;
        int n = nums2.length;
        int imin = 0, imax = m;
        int halfLen = (m + n + 1) / 2;  // Half length to find median

        while (imin <= imax) {
            // Partition nums1 at index i, and nums2 at index j
            int i = (imin + imax) / 2;
            int j = halfLen - i;

            // Check if the partition is valid
            if (i < m && nums2[j - 1] > nums1[i]) {
                // Move i to the right
                imin = i + 1;
            } else if (i > 0 && nums1[i - 1] > nums2[j]) {
                // Move i to the left
                imax = i - 1;
            } else {
                // Correct partition is found
                int maxLeft;
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }

                // If total length is odd, return the max of the left partition
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight;
                if (i == m) {
                    minRight = nums2[j];
                } else if (j == n) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums1[i], nums2[j]);
                }

                // If total length is even, return the average of maxLeft and minRight
                return (maxLeft + minRight) / 2.0;
            }
        }

        return 0.0;  // This fallback should never be reached
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test 1
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println(solution.findMedianSortedArrays(nums1, nums2));  // Output: 2.0

    }
}
