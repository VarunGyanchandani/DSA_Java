import java.util.Stack;

public class Main
{
    public static void main(String[] args)
    {
        Stack<Integer> st =new Stack<>();
        int[] nums = {3,1,4,2};
        System.out.println(pattern132(nums, st));
    }
    public static boolean pattern132(int[] nums, Stack<Integer> st)
    {
        int k= Integer.MIN_VALUE;
        for(int i= nums.length-1;i>=0;i--)
        {
            if(nums[i]<k)
            {
                return true;
            }
            while (!st.isEmpty() && st.peek() < nums[i])
            {
                k = st.pop();
            }
            st.add(nums[i]);

        }
        return false;
    }
}