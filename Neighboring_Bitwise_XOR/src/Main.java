public class Main {
    public boolean canBeOriginal(int[] derived) {
        int n = derived.length;

        // Assume original[0] = 0 and calculate the rest of the array
        int[] original = new int[n];

        // Step 1: Compute the original array based on the derived values
        for (int i = 1; i < n; i++) {
            original[i] = original[i - 1] ^ derived[i - 1];
        }

        // Step 2: Check if the final condition holds for the last element
        return derived[n - 1] == (original[n - 1] ^ original[0]);
    }

    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1:
        int[] derived1 = {1, 1, 0};
        System.out.println(solution.canBeOriginal(derived1)); // Output: true

        // Example 2:
        int[] derived2 = {1, 1};
        System.out.println(solution.canBeOriginal(derived2)); // Output: true

        // Example 3:
        int[] derived3 = {1, 0};
        System.out.println(solution.canBeOriginal(derived3)); // Output: false
    }
}
