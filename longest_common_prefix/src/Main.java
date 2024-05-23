class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return ""; // Handle empty array case

        String prefix = strs[0]; // Initialize prefix with the first string
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) { // Check if prefix is present at the beginning of current string
                prefix = prefix.substring(0, prefix.length() - 1); // Reduce prefix length if mismatch found
                if (prefix.isEmpty()) return ""; // Return empty string if no common prefix exists
            }
        }
        return prefix;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution s= new Solution();
        String [] strs = {"flower","flow","flight"};
        System.out.println(s.longestCommonPrefix(strs));
        String [] strs2 = {"dog","racecar","car"};
        System.out.println(s.longestCommonPrefix(strs2));
    }
}