import java.util.Stack;

public class CopyStack {
    public static void main(String[] args) {
        Stack<Integer> newStackCity = new Stack<>();
        newStackCity.push(1);
        newStackCity.push(3);
        newStackCity.push(5);

        Stack<Integer> copiedStack = copyStack(newStackCity);

        for (int num : copiedStack) {
            System.out.println(num);
        }
    }

    public static Stack<Integer> copyStack(Stack<Integer> s) {
        Stack<Integer> newStack = new Stack<>();

        for (int num : s) {
            newStack.push(num);
        }
        return newStack;
    }
}
