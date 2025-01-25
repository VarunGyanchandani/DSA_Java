import java.util.*;

public class Main {

    public static int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        // Create pairs of (number, original index)
        List<int[]> indexed = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            indexed.add(new int[]{nums[i], i});
        }

        // Sort by values
        indexed.sort(Comparator.comparingInt(a -> a[0]));

        // Group numbers that can be swapped with each other
        List<List<int[]>> groups = new ArrayList<>();
        List<int[]> currentGroup = new ArrayList<>();
        currentGroup.add(indexed.get(0));

        for (int i = 1; i < n; i++) {
            // If the difference between adjacent numbers <= limit, they belong to the same group
            if (indexed.get(i)[0] - indexed.get(i - 1)[0] <= limit) {
                currentGroup.add(indexed.get(i));
            } else {
                groups.add(currentGroup);
                currentGroup = new ArrayList<>();
                currentGroup.add(indexed.get(i));
            }
        }
        groups.add(currentGroup);

        // Prepare the result array
        int[] result = new int[n];

        for (List<int[]> group : groups) {
            List<Integer> values = new ArrayList<>();
            List<Integer> indices = new ArrayList<>();

            // Collect the values and indices from the group
            for (int[] pair : group) {
                values.add(pair[0]);
                indices.add(pair[1]);
            }

            // Sort the values and indices
            Collections.sort(values);
            Collections.sort(indices);

            // Assign sorted values to the corresponding indices
            for (int i = 0; i < values.size(); i++) {
                result[indices.get(i)] = values.get(i);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(lexicographicallySmallestArray(new int[]{1, 5, 3, 9, 8}, 2)));
        System.out.println(Arrays.toString(lexicographicallySmallestArray(new int[]{1, 7, 28, 19, 10}, 3)));
    }
}
