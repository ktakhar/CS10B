class Node {
    int data;
    Node next;

    // Constructor for the Node class
    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {

    private Node head;

    // Main class to demonstrate the functionality of LinkedList
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.insertAtEnd(1);
        list.insertAtEnd(2);
        list.insertAtStart(3);
        list.insertAt(1, 4);
        list.display(); // Expected output: 3 -> 4 -> 1 -> 2 -> NULL
        list.deleteAtEnd();
        list.deleteAtStart();
        list.deleteAt(1);

        list.display(); // Expected output: 4 -> NULL
        System.out.println("Size of the list: " + list.getSize()); // Expected output: 1
    }


    // Insert a new node at the end of the list
    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Insert a new node at the start of the list
    public void insertAtStart(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    // Insert a new node at a specified index
    public void insertAt(int index, int data) {
        if (index == 0) {
            insertAtStart(data);
            return;
        }
        Node newNode = new Node(data);
        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            if (current.next == null) {
                throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
            }
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
    }

    // Delete the last node of the list
    public void deleteAtEnd() {
        if (head == null) {
            System.out.println("List is empty. No nodes to delete.");
            return;
        }

        if (head.next == null) {
            head = null;
            return;
        }
        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        current.next = null;
    }

    // Delete the first node of the list
    public void deleteAtStart() {
        if (head != null) {
            head = head.next;
        } else {
            System.out.println("List is empty. No nodes to delete.");
        }
    }

    // Delete a node at a specified index
    public void deleteAt(int index) {
        if (head == null) {
            System.out.println("List is empty. No nodes to delete.");
            return;
        }

        if (index == 0) {
            deleteAtStart();
            return;
        }
        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            if (current.next == null) {
                throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
            }
            current = current.next;
        }
        current.next = current.next.next;
    }

    // Display the elements of the list
    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("NULL");
    }

    // Get the size of the linked list
    public int getSize() {
        int size = 0;
        Node current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }
}
