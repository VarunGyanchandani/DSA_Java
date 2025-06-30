import java.util.*;

class Solution {

    // Function to find the longest harmonious subsequence (LHS)
    public int findLHS(int[] nums) {
        // Create a frequency map of the elements in the array
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        int maxLength = 0;

        // For each unique number, check if num+1 exists
        for (int num : freq.keySet()) {
            if (freq.containsKey(num + 1)) {
                // Harmonious subsequence length is count of num + count of num+1
                int currentLength = freq.get(num) + freq.get(num + 1);
                maxLength = Math.max(maxLength, currentLength);
            }
        }

        return maxLength;
    }

    // Function to find the longest harmonious subsequence (LHS) with additional details
    public Result findLHSWithDetails(int[] nums) {
        // Create a frequency map of the elements in the array
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        int maxLength = 0;
        List<PairInfo> pairsFound = new ArrayList<>();
        PairInfo bestPair = null;

        // Iterate over sorted unique keys
        List<Integer> keys = new ArrayList<>(freq.keySet());
        Collections.sort(keys);

        for (int num : keys) {
            if (freq.containsKey(num + 1)) {
                int currentLength = freq.get(num) + freq.get(num + 1);
                PairInfo pairInfo = new PairInfo(num, num + 1, freq.get(num), freq.get(num + 1), currentLength);
                pairsFound.add(pairInfo);

                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    bestPair = pairInfo;
                }
            }
        }

        return new Result(maxLength, pairsFound, bestPair);
    }

    // Result class to hold the results of the findLHSWithDetails method
    public static class Result {
        int maxLength;
        List<PairInfo> pairsFound;
        PairInfo bestPair;

        public Result(int maxLength, List<PairInfo> pairsFound, PairInfo bestPair) {
            this.maxLength = maxLength;
            this.pairsFound = pairsFound;
            this.bestPair = bestPair;
        }

        @Override
        public String toString() {
            return "Max Length: " + maxLength + ", Best Pair: " + bestPair;
        }
    }

    // PairInfo class to hold information about each pair (num, num+1)
    public static class PairInfo {
        int num;
        int numPlusOne;
        int countNum;
        int countNumPlusOne;
        int totalLength;

        public PairInfo(int num, int numPlusOne, int countNum, int countNumPlusOne, int totalLength) {
            this.num = num;
            this.numPlusOne = numPlusOne;
            this.countNum = countNum;
            this.countNumPlusOne = countNumPlusOne;
            this.totalLength = totalLength;
        }

        @Override
        public String toString() {
            return "Values: (" + num + ", " + numPlusOne + "), Counts: (" + countNum + ", " + countNumPlusOne + "), Total Length: " + totalLength;
        }
    }

    // Method to demonstrate basic examples
    public static void demonstrateBasicExamples() {
        Solution solution = new Solution();

        System.out.println("=== Basic Examples ===");

        // Example 1
        int[] nums1 = {1, 3, 2, 2, 5, 2, 3, 7};
        int result1 = solution.findLHS(nums1);
        System.out.println("Input: " + Arrays.toString(nums1));
        System.out.println("Output: " + result1);
        System.out.println("Explanation: Harmonious subsequence [3, 2, 2, 2, 3] has length " + result1);
        System.out.println();

        // Example 2
        int[] nums2 = {1, 2, 3, 4};
        int result2 = solution.findLHS(nums2);
        System.out.println("Input: " + Arrays.toString(nums2));
        System.out.println("Output: " + result2);
        System.out.println("Explanation: Multiple harmonious subsequences of length " + result2);
        System.out.println();

        // Example 3
        int[] nums3 = {1, 1, 1, 1};
        int result3 = solution.findLHS(nums3);
        System.out.println("Input: " + Arrays.toString(nums3));
        System.out.println("Output: " + result3);
        System.out.println("Explanation: No harmonious subsequence exists");
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Longest Harmonious Subsequence - Complete Implementation\n");

        // Run all demonstrations
        demonstrateBasicExamples();
    }
}
