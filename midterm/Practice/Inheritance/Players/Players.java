class Baseball {
    public static void main (String[] args) {
        Players[] Roster = {
            new Dodger("Mookie Betts", 300),
            new Dodger("Freddie Freeman", 290),
            new Dodger("Chris Taylor", 220),
        };

        for (Players player : Roster) {
            System.out.println(player + " team ranking: " + player.ranking());
        };
    };
};

public abstract class Players {
    protected String name;
    protected double battingAverage;

    public Players(String name, double avg ) {
        this.name = name;
        this.battingAverage = avg;
    };

    public abstract int ranking();

    public String toString() {
        return name + " " + battingAverage;
    };
};

class Dodger extends Players {
    public Dodger(String name, int avg) {
        super(name, avg); 
    };
    public int ranking() {
        return 1;
    };
    public String toString() {
        return "Dodger " + super.toString();
    };
}