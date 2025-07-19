import java.util.*;

class Solution {
    public List<String> removeSubfolders(List<String> folder) {
        // Sort the folder list to ensure parent folders come before subfolders
        Collections.sort(folder);
        // Initialize result list to store non-subfolder paths
        List<String> result = new ArrayList<>();

        // Iterate through each folder in the sorted list
        for (String f : folder) {
            // Add folder if result is empty or if it's not a subfolder of the last added folder
            if (result.isEmpty() || !f.startsWith(result.get(result.size() - 1) + "/")) {
                result.add(f);
            }
        }

        return result;
    }

    // For testing with examples
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example 1
        List<String> folder1 = Arrays.asList("/a", "/a/b", "/c/d", "/c/d/e", "/c/f");
        System.out.println("Example 1 Input: " + folder1);
        System.out.println("Output: " + solution.removeSubfolders(folder1));

        // Example 2
        List<String> folder2 = Arrays.asList("/a", "/a/b/c", "/a/b/d");
        System.out.println("Example 2 Input: " + folder2);
        System.out.println("Output: " + solution.removeSubfolders(folder2));

        // Example 3
        List<String> folder3 = Arrays.asList("/a/b/c", "/a/b/ca", "/a/b/d");
        System.out.println("Example 3 Input: " + folder3);
        System.out.println("Output: " + solution.removeSubfolders(folder3));
    }
}