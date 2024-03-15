public class Main {
    public static void main(String[] args)
    {
        System.out.println(fibo(10));
    }
    public static int fibo(int n)
    {
        if (n==0)
        {
            return 0;
        }
        if (n==1)
        {
            return 1;
        }
        else{
            return (fibo(n-1)+fibo(n-2));
        }
    }
}