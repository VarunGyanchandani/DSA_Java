public class Main
{
    public static void check(int[] nums,int target)
    {
        int flag=0;
        for(int i=0;i<nums.length-1;i++)
        {
            for(int j=i+1;j<nums.length;j++)
            {
                if(nums[i]+nums[j]==target)
                {
                    System.out.printf("Pair found at (%d,%d)\n",nums[i],nums[j]);
                    flag=1;
                }
            }
            if(flag==0)
            {
                System.out.println("No record found");
            }

        }
    }
    public static void main(String[] args)
    {
        int[] nums={6,7,8,2,3,4,5};
        int target=10;
        check(nums,target);
    }
}