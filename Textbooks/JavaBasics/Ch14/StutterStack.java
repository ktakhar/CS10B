import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

public class StutterStack {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(7);
        stack.push(1);
        stack.push(14);
        stack.push(9);

        stutter(stack);

        // Print the updated stack
        System.out.println("bottom " + stack + " top");
    }

    public static void stutter(Stack<Integer> stack) {
        Queue<Integer> queue = new LinkedList<>();

        // Pop elements from the stack and add them to the queue twice each
        while (!stack.isEmpty()) {
            int element = stack.pop();
            queue.offer(element);
            queue.offer(element);
        }

        // Pop elements from the queue and push them back onto the stack
        while (!queue.isEmpty()) {
            int element = queue.poll();
            stack.push(element);
        }
    }
}
