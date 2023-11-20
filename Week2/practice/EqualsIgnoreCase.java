
class EqualsIgnoreCase
    {
        public static void main(String[] args)
        {
            String str1 = "Hello";
            String str2 = "hello";

            System.out.println("str = " + str1 + " and str2 = " + str2);
            System.out.println("str1.equals(str2) = " + str1.equals(str2));
            System.out.println("str1.equalsIgnoreCase(str2) = " + str1.equalsIgnoreCase(str2));
            System.out.println("str1.length() = " + str1.length());
            
        }
    }