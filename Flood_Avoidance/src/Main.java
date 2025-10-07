import java.util.*;

class Solution {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        TreeSet<Integer> dryDays = new TreeSet<>();
        HashMap<Integer, Integer> lakeLastRain = new HashMap<>();

        for (int i = 0; i < n; i++) {
            if (rains[i] == 0) {
                dryDays.add(i);
                ans[i] = 1;  // Placeholder, will be updated if needed
            } else {
                int lake = rains[i];
                if (lakeLastRain.containsKey(lake)) {
                    int lastRainDay = lakeLastRain.get(lake);
                    Integer dryDay = dryDays.higher(lastRainDay);

                    if (dryDay == null || dryDay >= i) {
                        return new int[0]; // Cannot prevent flood
                    }

                    ans[dryDay] = lake;
                    dryDays.remove(dryDay);
                }
                lakeLastRain.put(lake, i);
            }
        }

        return ans;
    }

    // Test harness
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] testCases = {
                {1, 2, 0, 0, 2, 1},
                {1, 2, 0, 2, 1},
                {1, 0, 2, 0, 2, 1, 0, 3, 0, 1}
        };

        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test case " + (i + 1) + ": " + Arrays.toString(testCases[i]));
            int[] result = solution.avoidFlood(testCases[i]);
            System.out.println("Output: " + Arrays.toString(result) + "\n");
        }
    }
}
