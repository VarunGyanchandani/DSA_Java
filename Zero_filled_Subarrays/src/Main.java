import java.util.Arrays;

class Solution {
    public int zeroFilledSubarray(int[] nums) {
        int totalCount = 0;
        int i = 0;

        while (i < nums.length) {
            if (nums[i] == 0) {
                int consecutiveZeros = 0;
                while (i < nums.length && nums[i] == 0) {
                    consecutiveZeros++;
                    i++;
                }
                totalCount += consecutiveZeros * (consecutiveZeros + 1) / 2;
            } else {
                i++;
            }
        }

        return totalCount;
    }
}

class SolutionConcise {
    public int zeroFilledSubarray(int[] nums) {
        int result = 0;
        int currentZeros = 0;

        for (int num : nums) {
            if (num == 0) {
                currentZeros++;
                result += currentZeros;
            } else {
                currentZeros = 0;
            }
        }

        return result;
    }
}

// Test class
class TestSolutions {
    public static void main(String[] args) {
        Solution sol = new Solution();
        SolutionConcise solConcise = new SolutionConcise();

        runTest(new int[]{1, 3, 0, 0, 2, 0, 0, 4}, 6, sol, solConcise, 1);
        runTest(new int[]{0, 0, 0, 2, 0, 0}, 9, sol, solConcise, 2);
        runTest(new int[]{2, 10, 2019}, 0, sol, solConcise, 3);
        runTest(new int[]{0}, 1, sol, solConcise, 4);
        runTest(new int[]{0, 0, 0, 0}, 10, sol, solConcise, 5);

        System.out.println("All tests passed!");
    }

    private static void runTest(int[] nums, int expected, Solution sol, SolutionConcise solConcise, int testCaseNum) {
        int result1 = sol.zeroFilledSubarray(nums);
        int result2 = solConcise.zeroFilledSubarray(nums);

        if (result1 != expected || result2 != expected) {
            System.out.println("Test " + testCaseNum + " failed: Expected " + expected + ", got " + result1 + " and " + result2);
        } else {
            System.out.println("Test " + testCaseNum + " passed: " + Arrays.toString(nums) + " -> " + expected);
        }
    }
}
