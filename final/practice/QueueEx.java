import java.util.*;

class QueueEx {
    public static void main(String[] args) {
        String[] data = {"to", "be", "or", "not", "to", "be"};
        Queue<String> q = new LinkedList<>();

        for (String str : data) {
            q.add(str);
        }

        System.out.println("Queue: " + q);           // Queue: [to, be, or, not, to, be]
        System.out.println("Size: " + q.size());     // Size: 6
        System.out.println("Peek: " + q.peek());     // Peek: to
        System.out.println("Remove: " + q.remove()); // Remove: to         

        while (!q.isEmpty()) {
            System.out.print(q.remove() + " "); // be or not to be (removes all items and adds them back with a space)
        }
        
        System.out.println();
    }
}

// add(value) = add to back
// remove() remove and return value at front 
// isEmpty() = false
// peek() = front of queue 
// size() = number of values 