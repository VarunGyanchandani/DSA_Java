import java.util.ArrayList;

public class Main {
    public static void main(String[] args)
    {
        ArrayList<String> al = new ArrayList<>();
        numberphone(0,"23","",al);
    }
    public static void numberphone(int idx, String digits, String path, ArrayList<String> al)
    {
        if (idx==digits.length())
        {
            System.out.println(path);
            return;
        }
        char ch = digits.charAt(idx);
        String str = Stringresptochar(ch);
        for (int i=0;i<str.length();i++)
        {
            numberphone(idx+1,digits,path+str.charAt(i),al);
        }
    }
    public static String Stringresptochar(char ch)
    {
        String[] arr = {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        return arr[ch-'2'];
    }
}