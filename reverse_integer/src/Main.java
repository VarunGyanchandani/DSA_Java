class Solution {
    public int reverse(int x) {
        long reversed = 0;
        int pop;
        while (x != 0) {
            pop = x % 10;
            reversed = reversed * 10 + pop;
            // Check for overflow
            if (reversed > Integer.MAX_VALUE || reversed < Integer.MIN_VALUE) {
                return 0;
            }
            x /= 10;
        }
        return (int) reversed;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s= new Solution();
        System.out.println(s.reverse(345));
    }
}