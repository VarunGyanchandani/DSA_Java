import java.util.PriorityQueue;
import java.util.Collections;

public class Main {

    // Function to calculate the remaining gifts after k seconds
    public static int remainingGifts(int[] gifts, int k) {
        // Use a max-heap (PriorityQueue in Java by default is a min-heap, so we reverse the order)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Add all the gift piles into the max-heap
        for (int gift : gifts) {
            maxHeap.add(gift);
        }

        // Simulate the process for k seconds
        for (int i = 0; i < k; i++) {
            // Get the pile with the maximum number of gifts (pop from max-heap)
            int maxGift = maxHeap.poll();

            // Compute the remaining gifts (floor of the square root)
            int remainingGift = (int) Math.sqrt(maxGift);

            // Push the remaining gifts back into the max-heap
            maxHeap.add(remainingGift);
        }

        // Calculate the total remaining gifts
        int totalRemaining = 0;
        while (!maxHeap.isEmpty()) {
            totalRemaining += maxHeap.poll();
        }

        return totalRemaining;
    }

    // Main function to run some test cases
    public static void main(String[] args) {
        // Example 1
        int[] gifts1 = {25, 64, 9, 4, 100};
        int k1 = 4;
        System.out.println("Remaining gifts (Example 1): " + remainingGifts(gifts1, k1)); // Output: 29

        // Example 2
        int[] gifts2 = {1, 1, 1, 1};
        int k2 = 4;
        System.out.println("Remaining gifts (Example 2): " + remainingGifts(gifts2, k2)); // Output: 4
    }
}
