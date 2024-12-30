class Sol {
    public int countGoodStrings(int low, int high, int zero, int one) {
        final int MOD = 1_000_000_007;

        // dp[i] represents the number of valid strings of length i
        int[] dp = new int[high + 1];

        // Base case - empty string
        dp[0] = 1;

        // For each length i, calculate number of valid strings
        for (int i = 0; i < high; i++) {
            // Add zeros
            if (i + zero <= high) {
                dp[i + zero] = (dp[i + zero] + dp[i]) % MOD;
            }

            // Add ones
            if (i + one <= high) {
                dp[i + one] = (dp[i + one] + dp[i]) % MOD;
            }
        }

        // Sum up all valid lengths between low and high
        int result = 0;
        for (int i = low; i <= high; i++) {
            result = (result + dp[i]) % MOD;
        }

        return result;
    }
}
public class Main extends Sol{
    public static void main(String [] args)
    {
        Sol s1= new Sol();
        System.out.println(s1.countGoodStrings(3,3,1,1));
    }
}