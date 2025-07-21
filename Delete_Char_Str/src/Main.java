class Solution {

    public String makeFancyString(String s) {
        if (s.length() < 3) {
            return s;
        }

        StringBuilder resultChars = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);


            if (resultChars.length() >= 2 && resultChars.charAt(resultChars.length() - 1) == currentChar
                    && resultChars.charAt(resultChars.length() - 2) == currentChar) {
                continue;
            }

            resultChars.append(currentChar);
        }

        return resultChars.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        String[] examples = {
                "heyyy thereeee!!!",
                "woooow",
                "noooooooo",
                "yessssirrrr",
                "lookkk attt thattt"
        };

        for (String original : examples) {
            String cleaned = sol.makeFancyString(original);
            System.out.println("Original: " + original + "  ->  Cleaned: " + cleaned);
        }
    }
}
