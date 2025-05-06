import java.util.Arrays;

class Solution {

    public int[] buildArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0]; // Or throw an IllegalArgumentException
        }
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            // valBComponent is original_nums[nums[i]] which is retrieved by (nums[nums[i]] % n)
            // nums[i] (on the right, first term) is original_nums[i]
            int valBComponent = nums[nums[i]] % n;
            nums[i] = nums[i] + n * valBComponent;
            // Now, nums[i] holds: original_value_of_nums_i + n * (original_value_of_nums_at_index_[original_value_of_nums_i])
        }

        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] / n; // Integer division
        }

        return nums;
    }


    public int[] buildArrayExtraSpace(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] ans = new int[n]; // Create a new array of the same size

        for (int i = 0; i < n; i++) {
            // For each index i, calculate nums[nums[i]]
            // and store it in the corresponding position in ans.
            ans[i] = nums[nums[i]];
        }

        return ans;
    }
}

class PermutationBuilderDemo {

    public static void main(String[] args) {
        // Create an instance of the Solution class
        Solution solver = new Solution();

        // --- Example 1 from the problem description ---
        int[] nums1 = {0, 2, 1, 5, 3, 4};
        System.out.println("Original nums1: " + Arrays.toString(nums1));

        // Using the O(1) space solution
        // Important: This method modifies the array in-place.
        // If you need to preserve the original nums1, make a copy.
        int[] nums1CopyForInPlace = Arrays.copyOf(nums1, nums1.length);
        int[] ans1InPlace = solver.buildArray(nums1CopyForInPlace);
        System.out.println("Result from buildArray (O(1) space) for nums1: " + Arrays.toString(ans1InPlace));
        // Expected output: [0, 1, 2, 4, 5, 3]

        // Using the O(n) space solution (original nums1 is preserved if you didn't pass it to the in-place method directly)
        int[] ans1ExtraSpace = solver.buildArrayExtraSpace(nums1); // Using original nums1
        System.out.println("Result from buildArrayExtraSpace (O(n) space) for nums1: " + Arrays.toString(ans1ExtraSpace));
        System.out.println("--------------------------------------------------");


        // --- Example 2 from the problem description ---
        int[] nums2 = {5, 0, 1, 2, 3, 4};
        System.out.println("Original nums2: " + Arrays.toString(nums2));

        // Using the O(1) space solution
        int[] nums2CopyForInPlace = nums2.clone(); // Another way to copy
        int[] ans2InPlace = solver.buildArray(nums2CopyForInPlace);
        System.out.println("Result from buildArray (O(1) space) for nums2: " + Arrays.toString(ans2InPlace));
        // Expected output: [4, 5, 0, 1, 2, 3]

        int[] ans2ExtraSpace = solver.buildArrayExtraSpace(nums2);
        System.out.println("Result from buildArrayExtraSpace (O(n) space) for nums2: " + Arrays.toString(ans2ExtraSpace));
        System.out.println("--------------------------------------------------");

        // --- Edge case: single element ---
        int[] nums3 = {0};
        System.out.println("Original nums3: " + Arrays.toString(nums3));

        int[] nums3CopyForInPlace = Arrays.copyOf(nums3, nums3.length);
        int[] ans3InPlace = solver.buildArray(nums3CopyForInPlace);
        System.out.println("Result from buildArray (O(1) space) for nums3: " + Arrays.toString(ans3InPlace));
        // Expected output: [0] (nums[nums[0]] = nums[0] = 0)

        int[] ans3ExtraSpace = solver.buildArrayExtraSpace(nums3);
        System.out.println("Result from buildArrayExtraSpace (O(n) space) for nums3: " + Arrays.toString(ans3ExtraSpace));
        System.out.println("--------------------------------------------------");

        // --- Edge case: empty array ---
        int[] nums4 = {};
        System.out.println("Original nums4: " + Arrays.toString(nums4));

        int[] ans4InPlace = solver.buildArray(Arrays.copyOf(nums4, nums4.length)); // Handles empty
        System.out.println("Result from buildArray (O(1) space) for nums4: " + Arrays.toString(ans4InPlace));

        int[] ans4ExtraSpace = solver.buildArrayExtraSpace(nums4); // Handles empty
        System.out.println("Result from buildArrayExtraSpace (O(n) space) for nums4: " + Arrays.toString(ans4ExtraSpace));
        System.out.println("--------------------------------------------------");
    }
}