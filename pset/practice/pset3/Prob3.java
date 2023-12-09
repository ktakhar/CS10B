import java.util.ArrayList;

class Prob3 {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Now");
        arrayList.add("Later");
        arrayList.add("Ever");

        printArrayList(arrayList);
    }
    public static void printArrayList(ArrayList<String> list) {
        for (String item : list) {
            System.out.print("NOT, " + item + ", ");
        }
    }
}