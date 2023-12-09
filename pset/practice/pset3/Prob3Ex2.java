import java.util.ArrayList;

class Prob3Ex2 {
    public static void main(String[] args) {
        ArrayList<String> roster = new ArrayList<>();

        roster.add("Mookie Betts");
        roster.add("Chris Taylor");
        roster.add("Joe Kelly");

        playerList(roster);
        addOhtani(roster);
        playerList(roster);
    }
    public static void playerList(ArrayList<String> players) {
        for (String player : players) {
            System.out.println(player); 
        }
    }
    public static void addOhtani(ArrayList<String> roster) {
        roster.add("Ohtani");
    }
}