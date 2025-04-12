import java.util.*;

class Solution {
    // Global result counter.
    long totalGood = 0;
    // Factorials up to n (n<=10).
    long[] fact;
    int nGlobal, kGlobal;

    public long countGoodIntegers(int n, int k) {
        nGlobal = n;
        kGlobal = k;
        totalGood = 0;

        // Precompute factorials up to n.
        fact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i;
        }

        // frequency vector for digits 0..9. We'll fill this using DFS.
        int[] freq = new int[10];
        dfs(0, n, freq);
        return totalGood;
    }

    // Depth-first search over digits to assign frequency counts.
    private void dfs(int digit, int remaining, int[] freq) {
        if (digit == 9) {
            // Only one possibility remains.
            freq[9] = remaining;
            // Skip all-zero frequency (i.e. if all digits are zero, it's not valid since it would have a leading zero).
            if (freq[0] == nGlobal) {
                return;
            }
            // Check if the current multiset can be rearranged into a palindrome.
            if (canFormPalindrome(freq)) {
                List<Integer> half = buildHalf(freq);
                Integer middle = null;  // if n is odd
                if (nGlobal % 2 == 1) {
                    // find the digit with an odd frequency.
                    for (int d = 0; d < 10; d++) {
                        if (freq[d] % 2 == 1) {
                            middle = d;
                            break;
                        }
                    }
                }
                // Check if there exists any palindromic arrangement (with no leading zero) that is divisible by k.
                if (existsValidPalindrome(half, middle)) {
                    totalGood += countNumbers(freq);
                }
            }
            return;
        }

        // For the current digit, assign counts from 0 to remaining.
        for (int cnt = 0; cnt <= remaining; cnt++) {
            freq[digit] = cnt;
            dfs(digit + 1, remaining - cnt, freq);
        }
    }

    // Check if frequency vector can form a palindrome based on n.
    private boolean canFormPalindrome(int[] freq) {
        if (nGlobal % 2 == 0) {
            // For even n, every digit must occur an even number of times.
            for (int cnt : freq) {
                if (cnt % 2 != 0) return false;
            }
            return true;
        } else {
            int oddCount = 0;
            for (int cnt : freq) {
                if (cnt % 2 != 0)
                    oddCount++;
            }
            return oddCount == 1;
        }
    }

    // Build the half list of digits (each digit repeated freq/2 times) to help construct the palindrome.
    private List<Integer> buildHalf(int[] freq) {
        List<Integer> half = new ArrayList<>();
        for (int d = 0; d < 10; d++) {
            for (int i = 0; i < freq[d] / 2; i++) {
                half.add(d);
            }
        }
        return half;
    }

    // Given a half list and an optional middle digit (for odd n),
    // check whether there exists an arrangement (no leading zero) which forms a palindrome divisible by k.
    private boolean existsValidPalindrome(List<Integer> half, Integer middle) {
        // When n == 1, half will be empty and the "middle" digit is the number.
        if (half.isEmpty() && middle != null) {
            int num = middle;
            return (middle != 0 && num % kGlobal == 0);
        }

        // Backtracking to generate all unique permutations of the half.
        Collections.sort(half);
        boolean[] used = new boolean[half.size()];
        List<Integer> current = new ArrayList<>();
        return backtrackPermutations(half, used, current, middle);
    }

    // Backtracking over unique permutations of the half list.
    private boolean backtrackPermutations(List<Integer> half, boolean[] used, List<Integer> current, Integer middle) {
        if (current.size() == half.size()) {
            // Check leading zero - the first digit of the half (which becomes the first digit of the palindrome)
            if (current.get(0) == 0) return false;

            // Build the complete number.
            long num = 0;
            // Append the half.
            for (int d : current) {
                num = num * 10 + d;
            }
            // Add the middle digit if exists.
            if (middle != null) {
                num = num * 10 + middle;
            }
            // Append the reverse of current.
            for (int i = current.size() - 1; i >= 0; i--) {
                num = num * 10 + current.get(i);
            }
            // Check divisibility.
            return num % kGlobal == 0;
        }

        // Try to add any number from half, skipping duplicates.
        for (int i = 0; i < half.size(); i++) {
            if (used[i]) continue;
            // Skip duplicate branches:
            if (i > 0 && half.get(i).equals(half.get(i - 1)) && !used[i - 1]) continue;
            used[i] = true;
            current.add(half.get(i));
            if (backtrackPermutations(half, used, current, middle)) {
                return true;
            }
            // Backtrack.
            current.remove(current.size() - 1);
            used[i] = false;
        }
        return false;
    }

    // Count the total number of arrangements for the frequency distribution that yield valid n-digit numbers.
    // This is computed as:
    //   total = n! / (f[0]! * f[1]! * ... * f[9]!)
    // and subtracting those with a leading zero.
    private long countNumbers(int[] freq) {
        long totalPerm = fact[nGlobal];
        for (int d = 0; d < 10; d++) {
            totalPerm /= fact[freq[d]];
        }
        long bad = 0;
        if (freq[0] > 0) {
            // Count arrangements with leading zero.
            bad = fact[nGlobal - 1];
            // For digit 0, reduce count by one.
            bad /= fact[freq[0] - 1];
            for (int d = 1; d < 10; d++) {
                bad /= fact[freq[d]];
            }
        }
        return totalPerm - bad;
    }

    // Main method to run provided examples.
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.countGoodIntegers(3, 5)); // Expected output: 27
        System.out.println(sol.countGoodIntegers(1, 4)); // Expected output: 2
        System.out.println(sol.countGoodIntegers(5, 6)); // Expected output: 2468
    }
}
