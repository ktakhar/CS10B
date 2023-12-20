import java.util.*;

class SortArrayList {
    public static void main(String [] args) {
        int [] numbers = {50, 15, 30, 20, 10};
        ArrayList<Integer> numList = new ArrayList<>();

        for (int num : numbers) {
            numList.add(num);
        }
        sortThis(numList);
        System.out.println(numList);

        // output: [10, 20, 15, 30, 50] (< = ascending)
        // output: [50, 30, 15, 20, 10] (> = descending)
    }
    public static ArrayList<Integer> sortThis(ArrayList<Integer> list) {
        for (int i = list.size() - 1; i > 0; i--) {
            if (list.get(i) < list.get(i - 1)) { // < = ascending order > = descending order
                int element = list.get(i);
                list.remove(i);
                list.add(0, element);
            }
        }   return list;
    }
}

class sortArrayEx2 {
    public static void main(String [] args) {
        int [] numbers = {50, 15, 30, 20, 10};
        ArrayList<Integer> numList = new ArrayList<>();

        for (int num : numbers) {
            numList.add(num);
        }

        Collections.sort(numList);
        System.out.println(numList);
    } 
}