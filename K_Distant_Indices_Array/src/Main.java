import java.util.*;

class Solution {
    /**
     * Find all k-distant indices in the array.
     *
     * @param nums Array of integers
     * @param key The target value to find
     * @param k Maximum distance allowed
     * @return List of k-distant indices sorted in increasing order
     */
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        // Find all indices where nums[j] == key
        List<Integer> keyIndices = new ArrayList<>();
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] == key) {
                keyIndices.add(j);
            }
        }

        List<Integer> result = new ArrayList<>();

        // For each index i, check if it's k-distant
        for (int i = 0; i < nums.length; i++) {
            // Check if there exists at least one j such that |i - j| <= k and nums[j] == key
            for (int j : keyIndices) {
                if (Math.abs(i - j) <= k) {
                    result.add(i);
                    break; // Found one valid j, no need to check others
                }
            }
        }

        return result;
    }
}

// Example Usage and Testing Class
class KDistantIndicesDemo {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println("=== K-Distant Indices Java Examples ===\\n");

        // Example 1: Basic case
        System.out.println("Example 1:");
        int[] nums1 = {3, 4, 9, 1, 3, 9, 5};
        int key1 = 9, k1 = 1;
        List<Integer> result1 = solution.findKDistantIndices(nums1, key1, k1);
        System.out.printf("Input: nums = %s, key = %d, k = %d%n",
                Arrays.toString(nums1), key1, k1);
        System.out.println("Output: " + result1);
        System.out.println("Expected: [1, 2, 3, 4, 5, 6]\\n");

        // Example 2: All elements are the key
        System.out.println("Example 2:");
        int[] nums2 = {2, 2, 2, 2, 2};
        int key2 = 2, k2 = 2;
        List<Integer> result2 = solution.findKDistantIndices(nums2, key2, k2);
        System.out.printf("Input: nums = %s, key = %d, k = %d%n",
                Arrays.toString(nums2), key2, k2);
        System.out.println("Output: " + result2);
        System.out.println("Expected: [0, 1, 2, 3, 4]\\n");

        // Example 3: Single occurrence
        System.out.println("Example 3:");
        int[] nums3 = {1, 2, 3, 4, 5};
        int key3 = 3, k3 = 2;
        List<Integer> result3 = solution.findKDistantIndices(nums3, key3, k3);
        System.out.printf("Input: nums = %s, key = %d, k = %d%n",
                Arrays.toString(nums3), key3, k3);
        System.out.println("Output: " + result3);
        System.out.println("Expected: [0, 1, 2, 3, 4]\\n");

        // Example 4: Distance 0
        System.out.println("Example 4:");
        int[] nums4 = {1, 2, 3, 4, 5};
        int key4 = 3, k4 = 0;
        List<Integer> result4 = solution.findKDistantIndices(nums4, key4, k4);
        System.out.printf("Input: nums = %s, key = %d, k = %d%n",
                Arrays.toString(nums4), key4, k4);
        System.out.println("Output: " + result4);
        System.out.println("Expected: [2]\\n");

        // Run comprehensive tests
        runTests(solution);
    }

    // Comprehensive testing method
    public static void runTests(Solution solution) {
        System.out.println("=== Running Comprehensive Tests ===\\n");

        TestCase[] testCases = {
                new TestCase(new int[]{3, 4, 9, 1, 3, 9, 5}, 9, 1, Arrays.asList(1, 2, 3, 4, 5, 6)),
                new TestCase(new int[]{2, 2, 2, 2, 2}, 2, 2, Arrays.asList(0, 1, 2, 3, 4)),
                new TestCase(new int[]{1, 2, 3, 4, 5}, 3, 2, Arrays.asList(0, 1, 2, 3, 4)),
                new TestCase(new int[]{1, 2, 3, 4, 5}, 3, 0, Arrays.asList(2)),
                new TestCase(new int[]{1}, 1, 0, Arrays.asList(0)),
                new TestCase(new int[]{7, 1, 7, 3, 7, 2, 7}, 7, 1, Arrays.asList(0, 1, 2, 3, 4, 5, 6))
        };

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            List<Integer> result = solution.findKDistantIndices(tc.nums, tc.key, tc.k);
            boolean passed = result.equals(tc.expected);

            System.out.printf("Test Case %d: %s%n", i + 1, passed ? "✓ PASS" : "✗ FAIL");
            System.out.printf("  Input: nums=%s, key=%d, k=%d%n",
                    Arrays.toString(tc.nums), tc.key, tc.k);
            System.out.printf("  Expected: %s%n", tc.expected);
            System.out.printf("  Got: %s%n", result);
            System.out.println();
        }
    }

    // Helper class for test cases
    static class TestCase {
        int[] nums;
        int key;
        int k;
        List<Integer> expected;

        TestCase(int[] nums, int key, int k, List<Integer> expected) {
            this.nums = nums;
            this.key = key;
            this.k = k;
            this.expected = expected;
        }
    }

    // Utility method to analyze a specific case
    public static void analyzeCase(int[] nums, int key, int k) {
        Solution solution = new Solution();
        List<Integer> result = solution.findKDistantIndices(nums, key, k);

        System.out.println("=== Case Analysis ===");
        System.out.printf("Input: nums=%s, key=%d, k=%d%n", Arrays.toString(nums), key, k);
        System.out.println("Result: " + result);

        // Find key positions
        List<Integer> keyPositions = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == key) {
                keyPositions.add(i);
            }
        }
        System.out.println("Key positions: " + keyPositions);

        // Detailed analysis
        System.out.println("\\nDetailed analysis:");
        for (int i = 0; i < nums.length; i++) {
            int minDistance = Integer.MAX_VALUE;
            for (int pos : keyPositions) {
                minDistance = Math.min(minDistance, Math.abs(i - pos));
            }
            boolean isKDistant = minDistance <= k;
            System.out.printf("  Index %d: min distance = %d, k-distant = %s%n",
                    i, minDistance == Integer.MAX_VALUE ? -1 : minDistance, isKDistant);
        }
    }
}
