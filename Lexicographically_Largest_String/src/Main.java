import java.util.*;

class Solution {
    private String word;
    private String maxWord;
    private int len;
    private Map<String, List<List<String>>> memo;

    public String answerString(String word, int numFriends) {
        this.word = word;
        this.maxWord = "";
        this.len = word.length();
        this.memo = new HashMap<>();

        dfs(0, numFriends);
        return maxWord;
    }

    private List<List<String>> dfs(int start, int partsLeft) {
        String key = start + "," + partsLeft;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        List<List<String>> result = new ArrayList<>();

        if (partsLeft == 1) {
            String part = word.substring(start);
            if (part.compareTo(maxWord) > 0) {
                maxWord = part;
            }
            List<String> list = new ArrayList<>();
            list.add(part);
            result.add(list);
            memo.put(key, result);
            return result;
        }

        for (int i = start + 1; i <= len - partsLeft + 1; i++) {
            String left = word.substring(start, i);
            if (left.compareTo(maxWord) > 0) {
                maxWord = left;
            }
            List<List<String>> rest = dfs(i, partsLeft - 1);
            for (List<String> r : rest) {
                List<String> newList = new ArrayList<>();
                newList.add(left);
                newList.addAll(r);
                result.add(newList);
            }
        }

        memo.put(key, result);
        return result;
    }

    // Example usage
    public static void main(String[] args) {
        Solution solution = new Solution();

        String word1 = "dbca";
        int numFriends1 = 2;
        System.out.println("Input: '" + word1 + "', numFriends = " + numFriends1);
        System.out.println("Output: '" + solution.answerString(word1, numFriends1) + "'");

        String word2 = "gggg";
        int numFriends2 = 4;
        System.out.println("Input: '" + word2 + "', numFriends = " + numFriends2);
        System.out.println("Output: '" + solution.answerString(word2, numFriends2) + "'");

        String word3 = "zxyabc";
        int numFriends3 = 3;
        System.out.println("Input: '" + word3 + "', numFriends = " + numFriends3);
        System.out.println("Output: '" + solution.answerString(word3, numFriends3) + "'");
    }
}
