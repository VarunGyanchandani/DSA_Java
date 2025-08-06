import java.util.*;

class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        // Step 1: Create a sorted set of all values in fruits and baskets
        Set<Integer> allValsSet = new TreeSet<>();
        for (int fruit : fruits) {
            allValsSet.add(fruit);
        }
        for (int basket : baskets) {
            allValsSet.add(basket);
        }

        List<Integer> allVals = new ArrayList<>(allValsSet);
        int size = allVals.size();

        // Step 2: Map each unique value to an index
        Map<Integer, Integer> mapping = new HashMap<>();
        for (int i = 0; i < size; i++) {
            mapping.put(allVals.get(i), i);
        }

        // Step 3: Create a heap for each basket capacity
        Map<Integer, PriorityQueue<Integer>> capHeap = new HashMap<>();
        Map<Integer, Set<Integer>> capDeleted = new HashMap<>();
        for (int basket : baskets) {
            capHeap.putIfAbsent(basket, new PriorityQueue<>());
        }

        int n = baskets.length;
        for (int i = 0; i < n; i++) {
            capHeap.get(baskets[i]).offer(i);
        }

        // Step 4: Initialize the array to track the next available basket for each capacity
        long INF = (long) 1e18;
        long[] arr = new long[size];
        Arrays.fill(arr, INF);

        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : capHeap.entrySet()) {
            int cap = entry.getKey();
            PriorityQueue<Integer> heapList = entry.getValue();
            while (!heapList.isEmpty() && capDeleted.getOrDefault(cap, new HashSet<>()).contains(heapList.peek())) {
                heapList.poll();
            }
            if (!heapList.isEmpty()) {
                arr[mapping.get(cap)] = heapList.peek();
            } else {
                arr[mapping.get(cap)] = INF;
            }
        }

        // Step 5: Segment Tree to manage the smallest available basket indices
        long[] tree = new long[4 * size];
        Arrays.fill(tree, INF);

        // Build the segment tree
        buildTree(tree, arr, 0, 0, size - 1);

        // Step 6: Handle tree updates
        long[] updateTree = tree.clone();

        // Step 7: Process each fruit and attempt to place it
        int unplaced = 0;
        for (int x : fruits) {
            int L = binarySearch(allVals, x);
            if (L >= size) {
                unplaced++;
                continue;
            }
            long minIndex = queryTree(tree, 0, 0, size - 1, L, size - 1);
            if (minIndex >= INF) {
                unplaced++;
            } else {
                int cap = baskets[(int) minIndex];
                capDeleted.putIfAbsent(cap, new HashSet<>());
                capDeleted.get(cap).add((int) minIndex);

                PriorityQueue<Integer> heapList = capHeap.get(cap);
                while (!heapList.isEmpty() && capDeleted.get(cap).contains(heapList.peek())) {
                    heapList.poll();
                }
                long newSmallest = heapList.isEmpty() ? INF : heapList.peek();
                int compIdxCap = mapping.get(cap);
                updateTree(updateTree, compIdxCap, newSmallest, 0, 0, size - 1);
            }
        }

        return unplaced;
    }

    private void buildTree(long[] tree, long[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            buildTree(tree, arr, 2 * node + 1, start, mid);
            buildTree(tree, arr, 2 * node + 2, mid + 1, end);
            tree[node] = Math.min(tree[2 * node + 1], tree[2 * node + 2]);
        }
    }

    private void updateTree(long[] tree, int idx, long val, int node, int start, int end) {
        if (start == end) {
            tree[node] = val;
        } else {
            int mid = (start + end) / 2;
            if (idx <= mid) {
                updateTree(tree, idx, val, 2 * node + 1, start, mid);
            } else {
                updateTree(tree, idx, val, 2 * node + 2, mid + 1, end);
            }
            tree[node] = Math.min(tree[2 * node + 1], tree[2 * node + 2]);
        }
    }

    private long queryTree(long[] tree, int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return (long) 1e18;
        }
        if (l <= start && end <= r) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        long leftVal = queryTree(tree, 2 * node + 1, start, mid, l, r);
        long rightVal = queryTree(tree, 2 * node + 2, mid + 1, end, l, r);
        return Math.min(leftVal, rightVal);
    }

    private int binarySearch(List<Integer> allVals, int target) {
        int left = 0, right = allVals.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (allVals.get(mid) == target) {
                return mid;
            } else if (allVals.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example 1
        int[] fruits1 = {3, 1, 4, 1, 2};
        int[] baskets1 = {1, 3, 5, 2};

        int unplaced1 = solution.numOfUnplacedFruits(fruits1, baskets1);
        System.out.println("Unplaced Fruits (Example 1): " + unplaced1);

        // Example 2
        int[] fruits2 = {1, 2, 3, 4, 5};
        int[] baskets2 = {3, 4, 5};

        int unplaced2 = solution.numOfUnplacedFruits(fruits2, baskets2);
        System.out.println("Unplaced Fruits (Example 2): " + unplaced2);

        // Example 3
        int[] fruits3 = {10, 20, 30};
        int[] baskets3 = {5, 5, 5, 10, 20};

        int unplaced3 = solution.numOfUnplacedFruits(fruits3, baskets3);
        System.out.println("Unplaced Fruits (Example 3): " + unplaced3);
    }
}
