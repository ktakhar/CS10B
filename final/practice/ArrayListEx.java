import java.util.*;

public class ArrayListEx{
    public static void main(String[] args) {
        ArrayList<String> newList = new ArrayList<>();

        System.out.println(aList(newList));
    }
    public static ArrayList<String> aList(ArrayList<String> list) {
        list.add("Pink Floyd");
        list.add("Violent Femmes");
        list.add("Blondie");

        list.remove(0);
        System.out.println(list.size());
        System.out.println(list.get(1));

        return list;
    }
}