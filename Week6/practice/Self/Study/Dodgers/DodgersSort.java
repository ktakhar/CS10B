public class DodgersSort {
    public static void main (String[] args) {
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

        for (int i = 0; i < DodgerPlayer.length; i++) {
            for (int j = i + 1; j < DodgerPlayer.length; j++) {
                if (DodgerPlayer[i].getBattingAverage() < DodgerPlayer[j].getBattingAverage()) {
                    Player temp = DodgerPlayer[i];
                    DodgerPlayer[i] = DodgerPlayer[j];
                    DodgerPlayer[j] = temp;
                }
            }
        }

        for (Player player : DodgerPlayer ) {
            System.out.println();
            System.out.println(player.getName());
            System.out.println(player.getBattingAverage());
            System.out.println();
        }
    }
}
