// LinkedDequeueTest.java

/**
 * PSET 5 #2 
 * LinkedDequeue Test 
 * 
 * @author Kuljit Takhar
 * @version November 30, 2023
 */

public class LinkedDequeueTest {
    public static void main(String[] args) {
        LinkedDequeue dequeue = new LinkedDequeue();

        try {
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
        } catch (LinkedDequeue.DequeueUnderFlowException e) {
            System.out.println("Caught an exception: " + e.getMessage());
        }
    }
}
