// LinkedDequeue.java

/**
 * @author Kuljit Takhar
 * @version Novemeber 30, 2023
 * 
 */

import java.util.LinkedList;

// Custom exception class for Dequeue underflow
class DequeueUnderFlowException extends RuntimeException {
    public DequeueUnderFlowException(String message) {
        super(message);
    }
}

class LinkedDequeue<E> {
    // Data members (private)
    private LinkedList<E> deque; // LinkedList to store elements of the Dequeue

        // Main Method
    public static void main(String[] args) {
        LinkedDequeue<String> mustangsDeque = new LinkedDequeue<>();

        // Add elements to the head and tail of the Dequeue
        mustangsDeque.headAdd("Shelby");
        mustangsDeque.tailAdd("Cobra");
        mustangsDeque.tailAdd("Bullit");
        mustangsDeque.tailAdd("Boss");

        // Peek at the head and tail of the Dequeue
        String headPeekResult = mustangsDeque.headPeek();
        String tailPeekResult = mustangsDeque.tailPeek();

        System.out.println("Head Peek: " + headPeekResult);
        System.out.println("Tail Peek: " + tailPeekResult);

        // Remove elements from the head and tail of the Dequeue
        String removedFromHead = mustangsDeque.headRemove();
        String removedFromTail = mustangsDeque.tailRemove();

        System.out.println("Removed from head: " + removedFromHead);
        System.out.println("Removed from tail: " + removedFromTail);

        // Check the size and whether the Dequeue is empty
        int size = mustangsDeque.size();
        boolean isEmpty = mustangsDeque.isEmpty();

        System.out.println("Size: " + size);
        System.out.println("Is Empty: " + isEmpty);

        // Display the updated contents of the Dequeue
        System.out.println("Updated Dequeue contents:");
        System.out.println(mustangsDeque);
    }

    // Constructor
    public LinkedDequeue() {
        deque = new LinkedList<>(); // Initialize the LinkedList in the constructor
    }

    // Add an element to the head of the Dequeue
    void headAdd(E item) {
        deque.addFirst(item);
    }

    // Peek at the element at the head of the Dequeue (without removing it)
    E headPeek() {
        if (!isEmpty()) { // Check if the Dequeue is not empty
            return deque.getFirst(); // Return the first element
        }
        throw new DequeueUnderFlowException("Cannot peek at an empty Dequeue");
    }

    // Remove and return the element from the head of the Dequeue
    E headRemove() {
        if (!isEmpty()) { // Check if the Dequeue is not empty
            return deque.removeFirst(); // Remove and return the first element
        }
        throw new DequeueUnderFlowException("Cannot remove from an empty Dequeue");
    }

    // Check if the Dequeue is empty
    boolean isEmpty() {
        return deque.isEmpty();
    }

    // Get the number of elements currently in the Dequeue
    int size() {
        return deque.size();
    }

    // Add an element to the tail of the Dequeue
    void tailAdd(E item) {
        deque.addLast(item);
    }

    // Peek at the element at the tail of the Dequeue (without removing it)
    E tailPeek() {
        if (!isEmpty()) { // Check if the Dequeue is not empty
            return deque.getLast(); // Return the last element
        }
        throw new DequeueUnderFlowException("Cannot peek at an empty Dequeue");
    }

    // Remove and return the element from the tail of the Dequeue
    E tailRemove() {
        if (!isEmpty()) { // Check if the Dequeue is not empty
            return deque.removeLast(); // Remove and return the last element
        }
        throw new DequeueUnderFlowException("Cannot remove from an empty Dequeue");
    }
    
    // Generate a string representation of the Dequeue
    @Override
    public String toString() {
        if (isEmpty()) {
            return "Deque is empty."; // Return a message if the Dequeue is empty
        }

        StringBuilder result = new StringBuilder();
        for (E item : deque) {
            result.append(item).append("\n"); // Append each element to the result on a separate line
        }
        return result.toString(); // Return the concatenated string
    }
}