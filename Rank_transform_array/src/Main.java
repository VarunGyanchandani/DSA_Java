import java.util.*;

public class Main {
    public int[] arrayRankTransform(int[] arr) {
        if (arr.length == 0) {
            return new int[0];
        }

        // Step 1: Create a sorted set of unique elements
        TreeSet<Integer> uniqueSorted = new TreeSet<>();
        for (int num : arr) {
            uniqueSorted.add(num);
        }

        // Step 2: Create a mapping from element to its rank
        Map<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;
        for (int num : uniqueSorted) {
            rankMap.put(num, rank++);
        }

        // Step 3: Replace each element in the original array with its rank
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = rankMap.get(arr[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        Main solution = new Main();
        int[] arr = {40, 10, 20, 30};
        int[] rankedArray = solution.arrayRankTransform(arr);
        System.out.println(Arrays.toString(rankedArray)); // Output: [4, 1, 2, 3]
    }
}
