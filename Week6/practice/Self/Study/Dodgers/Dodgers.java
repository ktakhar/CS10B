public abstract class Dodgers {
    private String name;
    private double battingAverage;

    public Dodgers(String name, double battingAverage) {
        this.name = name;
        this.battingAverage = battingAverage;
    }

    public String getName() {
        return name;
    }

    public double getBattingAverage() {
        return battingAverage;
    }

}

class Player extends Dodgers {
    public Player(String name, double battingAverage) {
        super(name, battingAverage);
    }
}