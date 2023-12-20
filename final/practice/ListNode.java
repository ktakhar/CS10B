public class ListNode {
    public int data;       // data stored in this node
    public ListNode next;  // a link to the next node in the list

    public ListNode() {
        // Default constructor
    }

    public ListNode(int data) {
        this.data = data;
        this.next = null;  // By default, the next node is set to null
    }

    public ListNode(int data, ListNode next) {
        this.data = data;
        this.next = next;
    }
}

public class Main {
    public static void main(String[] args) {
        // Create the original list: list -> [1] -> [2]
        ListNode list = new ListNode(1);
        list.next = new ListNode(2);

        // Create a new node with value 3
        ListNode newNode = new ListNode(3);

        // Link the new node to the end of the original list
        ListNode current = list;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;

        // Print the updated list: list -> [1] -> [2] -> [3]
        current = list;
        while (current != null) {
            System.out.print("[" + current.data + "] -> ");
            current = current.next;
        }
        System.out.println("/");
    }
}
