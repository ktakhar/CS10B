public class Queue {
    private int front;
    private int rear;
    private int count;
    private int capacity;
    private final static int CAPACITY_INCREMENT = 5;
    private Object[] entry;
    private static final int DEFAULT_SIZE = 10;

    public Queue() {
        count = 0;
        front = 0;
        rear = -1;
        capacity = DEFAULT_SIZE;
        entry = new Object[capacity];
    }

    public Queue(int size) {
        count = 0;
        front = 0;
        rear = -1;
        capacity = size;
        entry = new Object[capacity];
    }

    public Object delete() {
        if (empty()) return null;
        Object temp = entry[front];
        front = (front + 1) % capacity;
        count--;
        return temp;
    }

    public void add(Object x) {
        if (count == capacity) {
            // Queue is full, need to resize
            capacity += CAPACITY_INCREMENT;
            Object[] tempArray = new Object[capacity];
            for (int i = 0; i < count; i++) {
                tempArray[i] = entry[(front + i) % capacity];
            }
            front = 0;
            rear = count - 1;
            entry = tempArray;
        }
        rear = (rear + 1) % capacity;
        entry[rear] = x;
        count++;
    }

    public boolean empty() {
        return count == 0;
    }

    public int size() {
        return count;
    }
}
