import java.util.ArrayList;

public class Dodgers {
    private ArrayList<Player> roster;

    public Dodgers() {
        this.roster = new ArrayList<>();
    }

    public void addPlayer(String name, double avg, int hr, int rbi, double ops) {
        Player player = new Player(name, avg, hr, rbi, ops);
        roster.add(player);
    }

    public void getRoster() {
        // Display column headers
        System.out.printf("%-20s%-10s%-10s%-10s%-10s%n", "Name", "Avg", "HR", "RBI", "OPS");
        
        // Display player data
        for (Player player : roster) {
            System.out.printf("%-20s%-10.3f%-10d%-10d%-10.3f%n",
                    player.getName(), player.getAvg(), player.getHr(), player.getRbi(), player.getOps());
        }
    }

    public static void main(String[] args) {
        Dodgers dodgers = new Dodgers();

        dodgers.addPlayer("Shohei Ohtani", .304, 44, 95, 1.066);
        dodgers.addPlayer("Mookie Betts", .307, 29, 107, .808);
        dodgers.addPlayer("Freddie Freeman", .331, 29, 102, .977);
        dodgers.addPlayer("Max Muncy", .212, 36, 105, .808);
        dodgers.addPlayer("J.D. Martinez", .271, 33, 103, .893);
        dodgers.addPlayer("James Outman", .248, 23, 70, .790);
        dodgers.addPlayer("Chris Taylor", .237, 15, 56, .746);
        dodgers.addPlayer("Jayson Heyward", .269, 15, 40, .813);
        dodgers.addPlayer("Enrique Hernandez", .262, 5, 30, .731);
        dodgers.addPlayer("David Peralta", .259, 7, 55, .675);
        dodgers.addPlayer("Miguel Vargas", .195, 7, 32, .672);
        dodgers.addPlayer("Miguel Rojas", .236, 5, 31, .612);
        dodgers.addPlayer("Will Smith", .261, 19, 76, .797);
        dodgers.addPlayer("Austin Barnes", .180, 2, 11, .498);

        dodgers.getRoster();
    }
}

class Player {
    private String name;
    private double avg;
    private int hr;
    private int rbi;
    private double ops;

    public Player(String name, double avg, int hr, int rbi, double ops) {
        this.name = name;
        this.avg = avg;
        this.hr = hr;
        this.rbi = rbi;
        this.ops = ops;
    }

    public String getName() {
        return name;
    }

    public double getAvg() {
        return avg;
    }

    public int getHr() {
        return hr;
    }

    public int getRbi() {
        return rbi;
    }

    public double getOps() {
        return ops;
    }
}
