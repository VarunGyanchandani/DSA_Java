import java.util.*;

class Solution {

    public List<Integer> queryResults(int limit, List<List<Integer>> queries) {
        // Dictionary to store ball to color mapping
        Map<Integer, Integer> ballColors = new HashMap<>();
        // Dictionary to keep count of each color
        Map<Integer, Integer> colorCount = new HashMap<>();
        // Keep track of distinct colors count
        int distinctColors = 0;
        // List to store results after each query
        List<Integer> result = new ArrayList<>();

        for (List<Integer> query : queries) {
            int ball = query.get(0);
            int newColor = query.get(1);

            // If ball was previously colored
            if (ballColors.containsKey(ball)) {
                int oldColor = ballColors.get(ball);
                // Decrease count of old color
                colorCount.put(oldColor, colorCount.get(oldColor) - 1);
                // If count becomes 0, decrease distinct colors
                if (colorCount.get(oldColor) == 0) {
                    distinctColors--;
                    colorCount.remove(oldColor);
                }
            }

            // Add new color
            ballColors.put(ball, newColor);
            colorCount.put(newColor, colorCount.getOrDefault(newColor, 0) + 1);

            // If this is the first occurrence of color, increase distinct colors
            if (colorCount.get(newColor) == 1) {
                distinctColors++;
            }

            result.add(distinctColors);
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example test
        int limit = 5;
        List<List<Integer>> queries = new ArrayList<>();
        queries.add(Arrays.asList(1, 3));
        queries.add(Arrays.asList(2, 4));
        queries.add(Arrays.asList(3, 3));
        queries.add(Arrays.asList(2, 5));
        queries.add(Arrays.asList(1, 3));

        List<Integer> result = solution.queryResults(limit, queries);
        System.out.println(result);  // Output: [1, 2, 2, 2, 2]
    }
}
