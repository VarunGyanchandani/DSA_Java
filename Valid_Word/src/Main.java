class Solution {

    public boolean isValid(String word) {

        if (word.length() < 3) {
            return false;
        }

        boolean hasVowel = false;
        boolean hasConsonant = false;
        String vowels = "aeiouAEIOU";

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);


            if (!Character.isLetterOrDigit(c)) {
                return false;
            }


            if (Character.isAlphabetic(c)) {
                // Rule 3: Check for a vowel
                if (vowels.indexOf(c) != -1) {
                    hasVowel = true;
                }

                else {
                    hasConsonant = true;
                }
            }
        }

        return hasVowel && hasConsonant;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        String word1 = "234Adas";
        System.out.println("Is the word '" + word1 + "' valid? 👉 " + solver.isValid(word1));

        String word2 = "b3";
        System.out.println("Is the word '" + word2 + "' valid? 👉 " + solver.isValid(word2));

        String word3 = "a3$e";
        System.out.println("Is the word '" + word3 + "' valid? 👉 " + solver.isValid(word3));
    }
}
