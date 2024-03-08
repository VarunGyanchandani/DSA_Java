import java.util.Arrays;
import java.util.Scanner;

class B_S
{
    static int itr=0;
    static void search(int arr[],int n,int key)
    {
        itr+=1;
        int mid=n/2;
        if(arr.length==1)
        {
            if(arr[0]==key)
            {
                System.out.println("Key found.");
                System.out.println("Iterations:"+itr);
            }
            else
            {
                System.out.println("Key not found.");
                System.out.println("Iterations:"+itr);
            }
        }
        else if (arr[mid]==key)
        {
            System.out.println("Key found.");
            System.out.println("Iterations:"+itr);
        }
        else if (arr[mid]>key)
        {
            search(Arrays.copyOfRange(arr,0,mid),mid,key);
        }
        else
        {
            search(Arrays.copyOfRange(arr,mid+1,n),n-mid,key);
        }
    }
}
public class Main
{
    public static void main(String[] args)
    {
        int n;
        Scanner s=new Scanner(System.in);
        System.out.println("Enter array length:");
        n=s.nextInt();
        int [] arr=new int[n];
        for (int i=0;i<n;i++)
        {
            arr[i]=s.nextInt();
        }
        for(int i=0;i<n;i++)
        {
            int ind=i;
            for(int j=i+1;j<n;j++)
            {
                if(arr[ind]>arr[j])
                {
                    ind=j;
                }
            }
            int temp=arr[ind];
            arr[ind]=arr[i];
            arr[i]=temp;
        }
        B_S b=new B_S();
        System.out.println("Enter key:");
        int key=s.nextInt();
        b.search(arr,n,key);
    }
}