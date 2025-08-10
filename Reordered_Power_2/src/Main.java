import java.util.*;

class Solution {

    // Helper method to sort digits of a number and return as a string
    private String sortDigits(int n) {
        char[] digits = String.valueOf(n).toCharArray();
        Arrays.sort(digits);
        return new String(digits);
    }

    public boolean reorderedPowerOf2(int n) {
        // Precompute digit patterns of all powers of 2 up to 2^30
        Set<String> power2Digits = new HashSet<>();
        for (int i = 0; i < 31; i++) {
            int power = 1 << i;
            power2Digits.add(sortDigits(power));
        }

        // Check if n's sorted digits match any power of 2's digits
        return power2Digits.contains(sortDigits(n));
    }

    public static void main(String[] args) {
        Solution solver = new Solution();
        int[] examples = {1, 10, 46, 128, 256, 821, 1024, 4096, 123};
        for (int num : examples) {
            boolean result = solver.reorderedPowerOf2(num);
            System.out.println("n = " + num + ", can be reordered to power of 2? " + result);
        }
    }
}
