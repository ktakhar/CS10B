import java.util.LinkedList;

class Prob3Ex3 {
    public static void main(String[] args) {
        LinkedList<String> roster = new LinkedList<>();

        roster.add("Mookie Betts");
        roster.add("Chris Taylor");
        roster.add("Joe Kelly");

        addOhtani(roster);
        playerList(roster);
    }
    public static void playerList(LinkedList<String> players) {
        for (String player : players) {
            System.out.println(player);
        }
    }
    public static void addOhtani(LinkedList<String> roster) {
        roster.add("Ohtani");
    } 
}