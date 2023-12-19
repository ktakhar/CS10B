import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class Prob4 {

    public static boolean isSorted(Stack<Integer> stack) {
        if (stack.size() < 2) {
            // A stack with 0 or 1 elements is always considered sorted
            return true;
        }

        Queue<Integer> queue = new LinkedList<>();
        int prev = stack.pop();
        boolean sorted = true;

        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (current < prev) {
                sorted = false;
            }
            queue.add(prev);
            prev = current;
        }

        // Rebuild the original stack from the queue
        q2s(queue, stack);

        return sorted;
    }

    public static void s2q(Stack<Integer> s, Queue<Integer> q) {
        while (!s.isEmpty()) {
            q.add(s.pop());
        }
    }

    public static void q2s(Queue<Integer> q, Stack<Integer> s) {
        while (!q.isEmpty()) {
            s.push(q.remove());
        }
    }

    public static void main(String[] args) {
        // You can test your code here if needed
    }
}
