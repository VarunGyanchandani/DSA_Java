import java.util.Arrays;

class Solution {

    public int[] getNoZeroIntegers(int n) {
        for (int a = 1; a < n; a++) {
            int b = n - a;
            if (!containsZero(a) && !containsZero(b)) {
                return new int[]{a, b};
            }
        }
        return new int[]{-1, -1}; // fallback, though for valid inputs, this won't be reached
    }

    private boolean containsZero(int num) {
        while (num > 0) {
            if (num % 10 == 0) {
                return true;
            }
            num /= 10;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int n1 = 11;
        System.out.println("No-zero integers that sum to " + n1 + ": " + Arrays.toString(solution.getNoZeroIntegers(n1)));

        int n2 = 101;
        System.out.println("No-zero integers that sum to " + n2 + ": " + Arrays.toString(solution.getNoZeroIntegers(n2)));

        int n3 = 10000;
        System.out.println("No-zero integers that sum to " + n3 + ": " + Arrays.toString(solution.getNoZeroIntegers(n3)));
    }
}
