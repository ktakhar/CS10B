public class ListNodeTest {
    public static void main(String[] args) {
        // Create a series of ListNode objects to store data
        ListNode head = new ListNode();    // Create the first node, 'head'
        ListNode l2 = new ListNode();      // Create the second node, 'l2'
        ListNode l3 = new ListNode();      // Create the third node, 'l3'
        ListNode l4 = new ListNode();      // Create the fourth node, 'l4'
        ListNode l5 = new ListNode();      // Create the fifth node, 'l5'

        // Assign data to each node
        head.data = "Abbot";
        l2.data = "Bud";
        l3.data = "Costello";
        l4.data = "Foobar";
        l5.data = "Ziggy";

        // Connecting the nodes to form a linked list
        head.link = l2;   // Link 'head' to 'l2'
        l2.link = l3;     // Link 'l2' to 'l3'
        l3.link = l4;     // Link 'l3' to 'l4'
        l4.link = l5;     // Link 'l4' to 'l5'
        l5.link = null;   // Mark the end of the linked list by setting 'l5.link' to null

        // Print the nodes of the linked list
        ListNode current = head;  // Start at the beginning of the list with 'head'
        while (current != null) {
            System.out.println(current.data);  // Print the data of the current node
            current = current.link;           // Move to the next node in the list
        }

        // Print specific data
        System.out.println(head.data);
        System.out.println(l2.data);
        System.out.println(head.link);
        System.out.println(l2);
    }
}

class ListNode {
    String data;     // Data stored in the node
    ListNode link;  // Reference to the next node in the list

    public ListNode() {
        // Constructor for creating a ListNode with default values
        this.data = null;    // Initialize data to null (can be customized)
        this.link = null;    // Initialize link to null (no next node initially)
    }
}
