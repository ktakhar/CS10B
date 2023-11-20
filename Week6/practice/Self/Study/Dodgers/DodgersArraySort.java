import java.util.Arrays;
import java.util.Comparator;

public class DodgersArraySort {
    public static void main(String[] args) {
        Player[] DodgerPlayer = new Player[9];

        DodgerPlayer[0] = new Player("Mookie Betts", 0.307);
        DodgerPlayer[1] = new Player("Freddie Freeman", 0.331);
        DodgerPlayer[2] = new Player("Will Smith", 0.261);
        DodgerPlayer[3] = new Player("Max Muncy", 0.212);
        DodgerPlayer[4] = new Player("J.D. Martinez", 0.271);
        DodgerPlayer[5] = new Player("Jason Heyward", 0.269);
        DodgerPlayer[6] = new Player("David Peralta", 0.259);
        DodgerPlayer[7] = new Player("James Outman", 0.248);
        DodgerPlayer[8] = new Player("Miguel Rojas", 0.236);

        // Sort the array based on batting averages in descending order
        Arrays.sort(DodgerPlayer, new Comparator<Player>() {
            @Override
            public int compare(Player player1, Player player2) {
                // Compare in descending order
                return Double.compare(player2.getBattingAverage(), player1.getBattingAverage());
            }
        });

        for (Player player : DodgerPlayer) {
            System.out.println();
            System.out.println(player.getName());
            System.out.println(player.getBattingAverage());
            System.out.println();
        }
    }
}
