class Solution {
    public int maxDiff(int num) {
        String s = String.valueOf(num);

        // Step 1: Maximize by replacing first non-9 digit with 9
        String maxStr = s;
        for (char c : s.toCharArray()) {
            if (c != '9') {
                maxStr = s.replace(c, '9');
                break;
            }
        }

        // Step 2: Minimize
        String minStr = s;
        if (s.charAt(0) != '1') {
            minStr = s.replace(s.charAt(0), '1');
        } else {
            for (int i = 1; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c != '0' && c != '1') {
                    minStr = s.replace(c, '0');
                    break;
                }
            }
        }

        return Integer.parseInt(maxStr) - Integer.parseInt(minStr);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example input
        int num = 9288;
        int difference = sol.maxDiff(num);
        System.out.println("The maximum difference for " + num + " is: " + difference);

        // Additional test cases
        System.out.println(sol.maxDiff(555));      // Output: 888
        System.out.println(sol.maxDiff(123456));   // Output: 820000
        System.out.println(sol.maxDiff(10000));    // Output: 80000
    }
}
