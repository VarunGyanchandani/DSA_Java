public class Main {
    public static void main(String[] args)
    {
        int[] arr={1,2,3};
        int idx=0;
        combination_code(5,arr,idx,"");
    }
    public static void combination_code(int n, int[] arr, int idx, String str)
    {
        if (n==0)
        {
            System.out.println(str);
        }
        if (n<0)
        {
            return;
        }
        for(int i=idx; i<arr.length; i++)
        {
            combination_code(n-arr[i], arr, i, str+arr[i]);
        }
    }
}