import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean canConstruct(String s, int k) {
        // Initialize a map to store the character count
        Map<Character, Integer> charCount = new HashMap<>();

        // Count the frequency of each character in the string
        for (char ch : s.toCharArray()) {
            charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);
        }

        // Count how many characters have odd frequencies
        int oddCount = 0;
        for (int count : charCount.values()) {
            if (count % 2 != 0) {
                oddCount++;
            }
        }

        // If the number of odd frequency characters is greater than k, return false
        if (oddCount > k) {
            return false;
        }

        // If k is greater than the length of the string, return false
        return k <= s.length();

        // Otherwise, return true

    }
}
public class Main {
    public static void main(String[] args) {
        // Create an instance of Solution class
        Solution solution = new Solution();

        // Example 1
        String s1 = "aabbcc";
        int k1 = 3;
        System.out.println("Example 1: " + solution.canConstruct(s1, k1));  // Output: true

        // Example 2
        String s2 = "abc";
        int k2 = 2;
        System.out.println("Example 2: " + solution.canConstruct(s2, k2));  // Output: false

        // Example 3
        String s3 = "aabb";
        int k3 = 3;
        System.out.println("Example 3: " + solution.canConstruct(s3, k3));  // Output: true

        // Example 4
        String s4 = "aaaaa";
        int k4 = 1;
        System.out.println("Example 4: " + solution.canConstruct(s4, k4));  // Output: true
    }
}

