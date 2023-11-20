public class MovieTest {
    public static void main (String[] args) {
        Movie[] myMovies = new Movie[2];

        myMovies[0] = new Drama("A THOUSAND AND ONE", 2023, "Teyana Taylor", "Young mother kidnaps her 6-year old son from the foster care system to give them a chance at a new life.", 5.0);
        myMovies[1] = new Comedy("BARBIE", 2023, "Margot Robbie", "Barbie suffers a crisis that leads her to question her world and her existence.", 5.0);
   
        for (Movie movie : myMovies ) {
            System.out.println();
            System.out.println("Title: " + movie.getName());
            System.out.println("Starring: " + movie.getLead());
            System.out.println("Logline: " + movie.getLogline());
            System.out.println("Rating: " + movie.getRating());
            System.out.println(movie.comment());
            System.out.println();
        }
    }
}

class Drama extends Movie {
    public Drama(String name, int year, String lead, String logline, double rating) {
        super(name, year, lead, logline, rating);
    }

    public String comment() {
        return("Sundance winner");
    }
}

class Comedy extends Movie {
    public Comedy(String name, int year, String lead, String logline, double rating) {
        super(name, year, lead, logline, rating);
    }

    public String comment() {
        return "Women are funnier.";
    }
}