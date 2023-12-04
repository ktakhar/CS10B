// LinkedDequeue.java

/**
 * PSET 5 #2
 * Double-ended queue (deque) data structure
 * Singly-linked list implementation
 * Methods: 
 * adding and removing elements from both ends 
 * checking its status
 * providing a string representation of its contents
 * handling exceptions for underflow conditions
 * 
 * @author Kuljit Takhar
 * @version Novemeber 30, 2023
 * 
 */

class QueueNode {
    private Object item;        // Data stored in the node
    private QueueNode link;     // Reference to the next node

    /**
     * Constructor to create a new QueueNode with the specified item.
     *
     * @param item The data to be stored in the node.
     */
    public QueueNode(Object item) {
        this.item = item;
        this.link = null;        // Initialize link to null since this is the last node in the beginning.
    }

    /**
     * Get the item (data) stored in this node.
     *
     * @return The item stored in the node.
     */
    public Object getItem() {
        return item;
    }

    /**
     * Get the reference to the next node in the list.
     *
     * @return The next node in the list.
     */
    public QueueNode getLink() {
        return link;
    }

    /**
     * Set the reference to the next node in the list.
     *
     * @param link The next node to be linked to this node.
     */
    public void setLink(QueueNode link) {
        this.link = link;
    }
}

public class LinkedDequeue {
    private QueueNode tail;     // Rear of the dequeue
    private QueueNode head;     // Front of the dequeue
    private int count;          // Number of elements in the dequeue

    /**
     * Constructor to create an empty LinkedDequeue.
     */
    public LinkedDequeue() {
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * Insert an item into the Dequeue at the head.
     *
     * @param item The item to be added to the head of the Dequeue.
     */
    public void headAdd(Object item) {
        QueueNode newNode = new QueueNode(item);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setLink(head);
            head = newNode;
        }
        count++;
    }

    /**
     * Peek at the head of the Dequeue.
     *
     * @return The item at the head of the Dequeue.
     * @throws DequeueUnderFlowException if the Dequeue is empty.
     */
    public Object headPeek() {
        if (!isEmpty()) {
            return head.getItem();
        }
        throw new DequeueUnderFlowException("Cannot peek at an empty Dequeue");
    }

    /**
     * Remove an item from the head of the Dequeue.
     *
     * @return The removed item from the head of the Dequeue.
     * @throws DequeueUnderFlowException if the Dequeue is empty.
     */
    public Object headRemove() {
        if (!isEmpty()) {
            Object removedItem = head.getItem();
            head = head.getLink();
            count--;
            if (isEmpty()) {
                tail = null; // If Dequeue becomes empty, update tail reference
            }
            return removedItem;
        }
        throw new DequeueUnderFlowException("Cannot remove from an empty Dequeue");
    }

    /**
     * Check if the Dequeue is empty.
     *
     * @return true if the Dequeue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Get the number of elements currently in the Dequeue.
     *
     * @return The number of elements in the Dequeue.
     */
    public int size() {
        return count;
    }

    /**
     * Insert an item into the Dequeue at the tail.
     *
     * @param item The item to be added to the tail of the Dequeue.
     */
    public void tailAdd(Object item) {
        QueueNode newNode = new QueueNode(item);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setLink(newNode);
            tail = newNode;
        }
        count++;
    }

    /**
     * Peek at the tail of the Dequeue.
     *
     * @return The item at the tail of the Dequeue.
     * @throws DequeueUnderFlowException if the Dequeue is empty.
     */
    public Object tailPeek() {
        if (!isEmpty()) {
            return tail.getItem();
        }
        throw new DequeueUnderFlowException("Cannot peek at an empty Dequeue");
    }

    /**
     * Remove an item from the tail of the Dequeue.
     *
     * @return The removed item from the tail of the Dequeue.
     * @throws DequeueUnderFlowException if the Dequeue is empty.
     */
    public Object tailRemove() {
        if (!isEmpty()) {
            if (count == 1) {
                Object removedItem = tail.getItem();
                head = null;
                tail = null;
                count = 0;
                return removedItem;
            } else {
                Object removedItem = tail.getItem();
                QueueNode current = head;
                while (current.getLink() != tail) {
                    current = current.getLink();
                }
                tail = current;
                tail.setLink(null);
                count--;
                return removedItem;
            }
        }
        throw new DequeueUnderFlowException("Cannot remove from an empty Dequeue");
    }

    /**
     * Generate a string representation of the Dequeue.
     *
     * @return A string containing all objects in the Dequeue, from head to tail.
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "Dequeue is empty.";
        }

        StringBuilder result = new StringBuilder();
        QueueNode current = head;
        while (current != null) {
            result.append(current.getItem()).append("\n");
            current = current.getLink();
        }
        return result.toString();
    }

    public static void main(String[] args) {
        LinkedDequeue dequeue = new LinkedDequeue();

        // Adding elements to both ends of the dequeue
        dequeue.headAdd("First");
        dequeue.tailAdd("Last");

        // Peeking at the head and tail
        System.out.println("Head Peek: " + dequeue.headPeek());
        System.out.println("Tail Peek: " + dequeue.tailPeek());

        // Removing elements from both ends
        System.out.println("Removed from head: " + dequeue.headRemove());
        System.out.println("Removed from tail: " + dequeue.tailRemove());

        // Checking size and emptiness
        System.out.println("Size: " + dequeue.size());
        System.out.println("Is Empty: " + dequeue.isEmpty());

        // Displaying the contents of the dequeue
        System.out.println("Contents:");
        System.out.println(dequeue);
    }
}

// Custom exception class for Dequeue underflow
class DequeueUnderFlowException extends RuntimeException {
    public DequeueUnderFlowException(String message) {
        super(message);
    }
}