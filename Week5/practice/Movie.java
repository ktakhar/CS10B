public abstract class Movie {
    private String name;
    private int year;
    private String lead;
    private String logline;
    private double rating;

    public Movie(String name, int year, String lead, String logline, double rating) {
        this.name = name;
        this.year = year;
        this.lead = lead;
        this.logline = logline;
        this.rating = rating;
    }

    public String getName() {
        return name;
    } 

    public int getYear() {
        return year;
    }

    public String getLead() {
        return lead;
    }

    public String getLogline() {
        return logline;
    }

    public double getRating() {
        return rating;
    }

    public abstract String comment();
}