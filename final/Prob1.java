import java.util.ArrayList;

public class Prob1 {
    private ArrayList<Integer> list;

    public Prob1() {
        list = new ArrayList<>();
    }
    public static void prob1(ArrayList<Integer> list) {
        for (int i = list.size() - 1; i>=0; i--) { // i = 2 (last index)
            if (i % 2 == 0) { // i is even
                list.add(list.get(i)); // add to end
            }
            else {
                list.add(0, list.get(i)); // add to [0]
            }
        } System.out.println(list);
    }
    public static void main(String[] args) {
        Prob1 prob1 = new Prob1();
        ArrayList<Integer> list = new ArrayList<Integer>();

        list.add(10);
        list.add(20);
        list.add(30);

        prob1(list);
    }
}

    // i = 2 -> 2 % 2 == 0 (True) list[2] = 30 (add to end) list = { 10, 20, 30, 30 }
    // i = 1 -> 1 % 2 == 0 (False) list[1] = 20 (add to beginning) list = { 20, 10, 20, 30, 30 }
    // i = 0 -> 0 % 2 == 0 (True) (new) list[0] = 20 (add to end) list = { 20, 10, 20, 30, 30 20,}

    // list = { 20, 10, 20, 30, 30 20,}
    