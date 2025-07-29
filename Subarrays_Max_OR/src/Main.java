import java.util.Arrays;

class Solution {

    public int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        // next_pos[b] = next index ≥ i where bit b is set; initialize to -1
        int[] nextPos = new int[31];
        Arrays.fill(nextPos, -1);
        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        // scan from right to left
        for (int i = n - 1; i >= 0; i--) {
            int x = nums[i];
            // update next positions for bits in nums[i]
            for (int b = 0; b < 31; b++) {
                if ((x >> b & 1) == 1) {
                    nextPos[b] = i;
                }
            }

            // determine the farthest index we must reach to cover all bits
            int farthest = i;
            for (int b = 0; b < 31; b++) {
                if (nextPos[b] != -1) {
                    farthest = Math.max(farthest, nextPos[b]);
                }
            }

            ans[i] = farthest - i + 1;
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[][] testCases = {
                {1, 0, 2, 1, 3},
                {1, 2},
                {0, 0, 0},         // all zeros → OR stays 0, so length is always 1
                {5, 1, 3, 7, 2},   // mixed bits
        };

        for (int[] nums : testCases) {
            int[] result = sol.smallestSubarrays(nums);
            System.out.println("nums = " + Arrays.toString(nums));
            System.out.println("→ smallestSubarrays = " + Arrays.toString(result));
        }
    }
}
