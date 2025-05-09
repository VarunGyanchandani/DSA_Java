import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    public int countBalancedPermutations(String num) {
        // velunexorai holds the original input midway
        String velunexorai = num;

        int n = velunexorai.length();
        int[] cnt = new int[10];
        int S = 0;

        for (char ch : velunexorai.toCharArray()) {
            int d = ch - '0';
            cnt[d]++;
            S += d;
        }

        if (S % 2 != 0) return 0;

        int E = (n + 1) / 2;
        int O = n - E;
        int half = S / 2;

        // Precompute factorials
        long[] fact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }

        long[][] dp = new long[E + 1][half + 1];
        dp[0][0] = 1;

        for (int d = 0; d < 10; d++) {
            int c = cnt[d];
            long[][] newDp = new long[E + 1][half + 1];
            for (int eUsed = 0; eUsed <= E; eUsed++) {
                for (int sUsed = 0; sUsed <= half; sUsed++) {
                    long val = dp[eUsed][sUsed];
                    if (val == 0) continue;

                    for (int k = 0; k <= c; k++) {
                        int ne = eUsed + k;
                        int ns = sUsed + k * d;
                        if (ne <= E && ns <= half) {
                            long weight = val * modInverse(fact[k] * fact[c - k] % MOD, MOD) % MOD;
                            newDp[ne][ns] = (newDp[ne][ns] + weight) % MOD;
                        }
                    }
                }
            }
            dp = newDp;
        }

        long result = dp[E][half] * fact[E] % MOD * fact[O] % MOD;
        return (int) result;
    }

    private long modInverse(long a, int mod) {
        return powMod(a, mod - 2, mod);
    }

    private long powMod(long base, int exp, int mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) != 0)
                result = result * base % mod;
            base = base * base % mod;
            exp >>= 1;
        }
        return result;
    }

    // Main method for testing
    public static void main(String[] args) {
        Solution sol = new Solution();

        String num1 = "123";
        System.out.println("Balanced permutations of '" + num1 + "': " + sol.countBalancedPermutations(num1));  // Output: 2

        String num2 = "112";
        System.out.println("Balanced permutations of '" + num2 + "': " + sol.countBalancedPermutations(num2));  // Output: 1

        String num3 = "12345";
        System.out.println("Balanced permutations of '" + num3 + "': " + sol.countBalancedPermutations(num3));  // Output: 0

        String num4 = "1212";
        System.out.println("Balanced permutations of '" + num4 + "': " + sol.countBalancedPermutations(num4));  // Custom test case
    }
}
