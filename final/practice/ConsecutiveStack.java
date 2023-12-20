import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class ConsecutiveStack {
    public static boolean isConsecutive(Stack<Integer> stack) {
        if (stack.size() < 2) {
            // A stack with fewer than two values is considered consecutive
            return true;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        
        int prev = stack.pop();
        queue.add(prev);
        
        // Traverse the stack from the bottom to the top
        while (!stack.isEmpty()) {
            int current = stack.pop();
            
            // Check if the current integer is not consecutive to the previous one
            if (current != prev - 1) {
                // Restore the stack and queue
                stack.push(current);
                while (!queue.isEmpty()) {
                    stack.push(queue.poll());
                }
                return false;
            }
            
            queue.add(current);
            prev = current;
        }
        
        // Restore the stack
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        Stack<Integer> stack1 = new Stack<>();
        stack1.push(3);
        stack1.push(4);
        stack1.push(5);
        stack1.push(6);
        stack1.push(7);
        stack1.push(8);
        stack1.push(9);
        stack1.push(10);
        
        System.out.println("isConsecutive(stack1): " + isConsecutive(stack1)); // Should print true
        
        Stack<Integer> stack2 = new Stack<>();
        stack2.push(3);
        stack2.push(4);
        stack2.push(5);
        stack2.push(6);
        stack2.push(7);
        stack2.push(8);
        stack2.push(9);
        stack2.push(10);
        stack2.push(12);
        
        System.out.println("isConsecutive(stack2): " + isConsecutive(stack2)); // Should print false
        
        Stack<Integer> stack3 = new Stack<>();
        stack3.push(3);
        stack3.push(2);
        stack3.push(1);
        
        System.out.println("isConsecutive(stack3): " + isConsecutive(stack3)); // Should print false
    }
}