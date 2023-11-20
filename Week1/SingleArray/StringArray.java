import java.util.*;

class StringArray
{
    public static void main (String [] args)
    {
        String [] strArr = new String [5];
        for (int k = 0; k < strArr.length; k++)
        {
            strArr[k] = "Hello" + k + 1;
        }
        System.out.println(Arrays.toString(strArr));
    }
}