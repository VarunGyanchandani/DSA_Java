class Solution {

    public String countAndSay(int n) {
        // Constraint check (optional, based on problem statement)
        if (n <= 0) {
            // Or throw an IllegalArgumentException based on requirements
            return "";
        }

        // Base case
        if (n == 1) {
            return "1";
        }

        // Start with the first term
        String currentSequence = "1";

        // Iterate n-1 times to reach the nth term
        // (We already have the 1st term, need n-1 more steps)
        for (int k = 1; k < n; k++) {
            // Use StringBuilder for efficient string construction
            StringBuilder nextSequenceBuilder = new StringBuilder();
            int i = 0;
            int len = currentSequence.length();

            // Iterate through the current sequence
            while (i < len) {
                // Get the character to count
                char currentChar = currentSequence.charAt(i);
                int count = 0;

                // Count consecutive occurrences of currentChar
                int j = i; // Use a second pointer j to count
                while (j < len && currentSequence.charAt(j) == currentChar) {
                    count++;
                    j++;
                }

                // Append the count and the character to the builder
                nextSequenceBuilder.append(count); // Appends integer as string
                nextSequenceBuilder.append(currentChar);

                // Move the main pointer 'i' past the group we just counted
                i = j; // 'j' is already at the start of the next group or end of string
            }
            // Update the current sequence for the next iteration
            currentSequence = nextSequenceBuilder.toString();
        }

        return currentSequence;
    }

    // --- Optional: Main method for testing ---
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1:
        int n1 = 4;
        String output1 = sol.countAndSay(n1);
        System.out.println("Input: n = " + n1);          // Output: Input: n = 4
        System.out.println("Output: \"" + output1 + "\"");  // Output: Output: "1211"

        // Example 2:
        int n2 = 1;
        String output2 = sol.countAndSay(n2);
        System.out.println("Input: n = " + n2);          // Output: Input: n = 1
        System.out.println("Output: \"" + output2 + "\"");  // Output: Output: "1"

        // Another example
        int n3 = 5;
        String output3 = sol.countAndSay(n3);
        System.out.println("Input: n = " + n3);          // Output: Input: n = 5
        System.out.println("Output: \"" + output3 + "\"");  // Output: Output: "111221"
    }
}