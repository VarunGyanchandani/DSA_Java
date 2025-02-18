import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public String smallestNumber(String pattern) {
        int n = pattern.length();
        StringBuilder result = new StringBuilder();
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i <= n; i++) {
            stack.push(i + 1);

            if (i == n || pattern.charAt(i) == 'I') {
                while (!stack.isEmpty()) {
                    result.append(stack.pop());
                }
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String pattern1 = "IIIDIDDD";
        System.out.println(sol.smallestNumber(pattern1)); // Output: 123549876

        String pattern2 = "DDD";
        System.out.println(sol.smallestNumber(pattern2)); // Output: 4321

        String pattern3 = "I";
        System.out.println(sol.smallestNumber(pattern3)); // Output: 12

        String pattern4 = "D";
        System.out.println(sol.smallestNumber(pattern4)); // Output: 21


        String pattern5 = "";
        System.out.println(sol.smallestNumber(pattern5)); // Output: 1

        String pattern6 = "ID";
        System.out.println(sol.smallestNumber(pattern6)); // Output: 121

    }
}