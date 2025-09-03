import java.util.Arrays;

class Solution {
    public int[] plusOne(int[] digits) {
        int i = digits.length - 1;
        while (i >= 0) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
            i--;
        }
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }

    // Example usage:
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.plusOne(new int[]{1,2,3}))); // [1,2,4]
        System.out.println(Arrays.toString(solution.plusOne(new int[]{4,3,2,1}))); // [4,3,2,2]
        System.out.println(Arrays.toString(solution.plusOne(new int[]{9,9,9}))); // [1,0,0,0]
    }
}
