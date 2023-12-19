import java.util.*;

class DodgerList {
    public static void main(String[] args) {
        ArrayList<String> teamRoster = new ArrayList<>(
            List.of("Mookie", "Freddie", "Shohei", "Chris Taylor"));

        System.out.println(teamRoster);

    }
}

class DodgerListStack {
    public static void main(String[] args) {
        String [] roster = {"Mookie", "Freddie", "Shohei", "Chris Taylor", "Joe Kelly"};
        Stack<String> rosterStack = new Stack<>();

        for (String name : roster) {
            rosterStack.push(name);
        }
        System.out.println(rosterStack);
    }
}