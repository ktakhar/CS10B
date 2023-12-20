import java.util.*; 
class StackEx {
    public static void main(String [] args) {
        Stack<String> hello = new Stack<>();

        hello.push("Hello");
        hello.push("World");

        for (String str : hello) {
            System.out.println(str);
        }
    }

}