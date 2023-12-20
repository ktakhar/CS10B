import java.util.*;

class QueueEx {
    public static void main(String[] args) {
        String[] data = {"to", "be", "or", "not", "to", "be"};
        Queue<String> dataList = new LinkedList<>();

        for (String str : data) {
            dataList.add(str);
        }

        System.out.println("Queue: " + dataList);           // Queue: [to, be, or, not, to, be]
        System.out.println("Size: " + dataList.size());     // Size: 6
        System.out.println("Peek: " + dataList.peek());     // Peek: to
        System.out.println("Remove: " + dataList.remove()); // Remove: to         

        while (!dataList.isEmpty()) {
            System.out.print(dataList.remove() + " "); // be or not to be (removes all items and adds them back with a space)
        }
        
        System.out.println();
    }
}

// add(value) = add to back
// remove() remove and return value at front 
// isEmpty() = false
// peek() = front of queue 
// size() = number of values 