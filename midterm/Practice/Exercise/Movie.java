class MovieTest {
    public static void main(String[] args) {
        Movie[] MovieList = {
            new Drama("Flowers", 8),
            new Comedy("Barbie", 9 ),
        };
        for (Movie movie : MovieList) {
            System.out.println(movie);
        }
    }
}

public abstract class Movie {
    protected String name;
    protected int rating;

    public Movie(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

    public String toString() {
        return "Title: " + name + " Rating: " + rating;
    }
}

class Drama extends Movie {
    public Drama(String name, int rating) {
        super(name, rating);
    }

    public String toString() {
        return "Category: Drama " + super.toString();
    }
}

class Comedy extends Movie {
    public Comedy(String name, int rating) {
        super(name, rating);
    }

    public String toString() {
        return "Category: Comedy " + super.toString();
    }
}