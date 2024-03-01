
import java.util.*;

class ArrayListExample {
    public static void main(String[] args)
    {
        int n = 10;

        ArrayList<Integer> arr1 = new ArrayList<>(n);

        ArrayList<Integer> arr2 = new ArrayList<>();

        // Printing the ArrayList
        System.out.println("Array 1:" + arr1);
        System.out.println("Array 2:" + arr2);

        for (int i = 1; i <= n; i++) {
            arr1.add(i);
            arr2.add(i*2);
        }

        // Printing the ArrayList
        System.out.println("Array 1:" + arr1);
        System.out.println("Array 2:" + arr2);
    }
}
