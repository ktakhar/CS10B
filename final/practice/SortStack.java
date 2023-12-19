import java.util.Stack;

public class SortStack {
    public static Stack<Integer> sortStack(Stack<Integer> stack) {
        Stack<Integer> sortedStack = new Stack<>();

        while (!stack.isEmpty()) {
            // Pop the top element from the original stack
            int current = stack.pop();

            // Move elements from the sortedStack to the original stack
            // if they are greater than the current element
            while (!sortedStack.isEmpty() && sortedStack.peek() > current) {
                stack.push(sortedStack.pop());
            }

            // Push the current element onto the sortedStack
            sortedStack.push(current);
        }

        // Return the sorted stack, which now contains elements in non-decreasing order
        return sortedStack;
    }

    public static void main(String[] args) {
        // Create an initial stack of integers
        Stack<Integer> stack = new Stack<>();
        stack.push(5);
        stack.push(2);
        stack.push(9);
        stack.push(1);
        stack.push(7);

        // Call the sortStack method to sort the stack
        Stack<Integer> sortedStack = sortStack(stack);

        // Print the sorted elements from the sortedStack
        while (!sortedStack.isEmpty()) {
            System.out.println(sortedStack.pop());
        }
    }
}
