public class Main {
    public int punishmentNumber(int n) {
        int punishmentSum = 0;

        for (int i = 1; i <= n; i++) {
            if (canPartition(i, i * i, 0, String.valueOf(i * i))) {
                punishmentSum += i * i;
            }
        }

        return punishmentSum;
    }

    private boolean canPartition(int target, int square, int index, String str) {
        if (index == str.length()) {
            return target == 0;
        }

        int val = 0;
        for (int i = index; i < str.length(); i++) {
            val = val * 10 + (str.charAt(i) - '0');
            if (val > target) break;
            if (canPartition(target - val, square, i + 1, str)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Main sol = new Main();
        int n = 10;
        int result = sol.punishmentNumber(n);
        System.out.println("Punishment Number of " + n + " is: " + result);
    }
}
