class Solution {
    public int compareVersion(String version1, String version2) {
        // Split the version strings by the dot (.) and convert them to integers
        String[] v1Parts = version1.split("\\.");
        String[] v2Parts = version2.split("\\.");

        int maxLen = Math.max(v1Parts.length, v2Parts.length);


        for (int i = 0; i < maxLen; i++) {
            int v1 = (i < v1Parts.length) ? Integer.parseInt(v1Parts[i]) : 0;
            int v2 = (i < v2Parts.length) ? Integer.parseInt(v2Parts[i]) : 0;

            if (v1 < v2) {
                return -1;
            } else if (v1 > v2) {
                return 1;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Comparing two versions
        String version1 = "1.2.0";
        String version2 = "1.2.1";

        int result = solution.compareVersion(version1, version2);

        if (result == -1) {
            System.out.println("Version " + version1 + " is older than version " + version2 + ".");
        } else if (result == 1) {
            System.out.println("Version " + version1 + " is newer than version " + version2 + ".");
        } else {
            System.out.println("Version " + version1 + " and version " + version2 + " are the same.");
        }
    }
}
