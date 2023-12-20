import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public static void StackNegsPos(Stack<Integer> s) {
    Queue<Integer> negQueue = new LinkedList<>(); // Use LinkedList as a Queue
    Stack<Integer> tempStack = new Stack<>(); // Temporary stack to hold positive numbers

    // Separate negative numbers and push positive numbers to the temporary stack
    while (!s.isEmpty()) {
        int num = s.pop();
        if (num <= 0) {
            negQueue.add(num);
        } else {
            tempStack.push(num);
        }
    }

    // Push negative numbers back to the original stack
    while (!negQueue.isEmpty()) {
        s.push(negQueue.poll());
    }

    // Push positive numbers back to the original stack
    while (!tempStack.isEmpty()) {
        s.push(tempStack.pop());
    }
}