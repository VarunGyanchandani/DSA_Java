import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    public int[] findEvenNumbers(int[] digits) {

        int[] digitCounts = new int[10]; // Indices 0-9 for digits 0-9
        for (int digit : digits) {
            // Increment the count for the encountered digit.
            digitCounts[digit]++;
        }

        List<Integer> resultList = new ArrayList<>();

        for (int num = 100; num < 1000; num++) { // Loop includes 100 to 999


            if (num % 2 != 0) {
                continue; // Skip to the next number if 'num' is odd.
            }

            int d1 = num / 100;        // Hundreds digit
            int d2 = (num / 10) % 10;  // Tens digit
            int d3 = num % 10;         // Units digit

            int[] tempCounts = Arrays.copyOf(digitCounts, 10);

            tempCounts[d1]--;
            tempCounts[d2]--;
            tempCounts[d3]--;


            if (tempCounts[d1] >= 0 && tempCounts[d2] >= 0 && tempCounts[d3] >= 0) {

                resultList.add(num);
            }

        }

        int[] resultArray = new int[resultList.size()];
        // Copy elements from the List to the array.
        for (int i = 0; i < resultList.size(); i++) {
            resultArray[i] = resultList.get(i); // .get(i) retrieves the element
        }



        return resultArray; // Return the final sorted array.
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solver = new Solution();

        int[] digits1 = {2, 1, 3, 0};
        int[] output1 = solver.findEvenNumbers(digits1);

        System.out.println("Input: " + Arrays.toString(digits1));
        System.out.println("Output: " + Arrays.toString(output1));

        System.out.println();

        int[] digits2 = {2, 2, 8, 8, 2};
        int[] output2 = solver.findEvenNumbers(digits2);
        System.out.println("Input: " + Arrays.toString(digits2));
        System.out.println("Output: " + Arrays.toString(output2));

        System.out.println();

        int[] digits3 = {3, 7, 5};
        int[] output3 = solver.findEvenNumbers(digits3);
        System.out.println("Input: " + Arrays.toString(digits3));
        System.out.println("Output: " + Arrays.toString(output3));
    }
}