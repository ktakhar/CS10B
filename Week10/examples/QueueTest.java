class QueueTest {
    public static void main(String[] args) {
        Queue q = new Queue(13);

        q.add("now");
        q.add("is");
        q.add("the");
        q.add("time");
        q.add("for all good csci e10b students");

        int size = q.size();
        for (int i = 0; i <= size; i++) {
            System.out.println(q.delete());
        }
    }
}
