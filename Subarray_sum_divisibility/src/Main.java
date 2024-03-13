public class Main {
    public int countSubarrays(int[] nums, int k) {
        int[] count = new int[k];
        count[0] = 1;
        int sum = 0;
        int result = 0;

        for (int num : nums) {
            sum = (sum + num) % k;
            if (sum < 0) sum += k;
            result += count[sum];
            count[sum]++;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 0, -2, -3, 1};
        int[] nums1 = {5, 5, 5, 5, 5};
        int k = 5;
        Main solution = new Main();
        System.out.println("Output: " + solution.countSubarrays(nums, k));
        System.out.println("Output: " + solution.countSubarrays(nums1, k));
    }
}
