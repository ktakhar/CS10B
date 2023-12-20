import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class IsPalindromeQueue {
    public static void main(String[] args) {
        Queue<Integer> queue1 = new LinkedList<>();
        queue1.add(3);
        queue1.add(8);
        queue1.add(17);
        queue1.add(9);
        queue1.add(17);
        queue1.add(8);
        queue1.add(3);

        System.out.println(isPalindrome(queue1)); // Should print true

        Queue<Integer> queue2 = new LinkedList<>();
        queue2.add(3);
        queue2.add(8);
        queue2.add(17);
        queue2.add(9);
        queue2.add(4);
        queue2.add(17);
        queue2.add(8);
        queue2.add(3);

        System.out.println(isPalindrome(queue2)); // Should print false
    }

    public static boolean isPalindrome(Queue<Integer> queue) {
        Stack<Integer> stack = new Stack<>();
        int size = queue.size();

        // Push the first half of the elements into the stack
        for (int i = 0; i < size / 2; i++) {
            stack.push(queue.remove());
        }

        // If the size is odd, skip the middle element
        if (size % 2 != 0) {
            queue.remove();
        }

        // Compare the elements from the stack with the remaining elements in the queue
        while (!stack.isEmpty()) {
            int front = queue.remove();
            int top = stack.pop();

            if (front != top) {
                return false;
            }
        }

        return true;
    }
}
