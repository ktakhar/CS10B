public abstract class Films {
    private String name;
    private double rating;

    public Films(String name, double rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

}

class Drama extends Films {
    public Drama(String name, double rating) {
        super(name, rating);
    }
}

class Comedy extends Films {
    public Comedy(String name, double rating) {
        super(name, rating);
    }
}

class MovieTest {
    public static void main(String [] args) {
        Films[] movie = new Films[10];

        movie[0] = new Drama("The Shawshank Redemption", 9.3);
        movie[1] = new Drama("The Godfather", 9.2);
        movie[2] = new Drama("The Dark Knight", 9.0);
        movie[3] = new Drama("The Godfather: Part II", 9.0);
        movie[4] = new Drama("The Lord of the Rings: The Return of the King", 8.9);
        movie[5] = new Comedy("The Hangover", 7.7);
        movie[6] = new Comedy("The Hangover Part II", 6.4);
        movie[7] = new Comedy("The Hangover Part III", 5.8);
        movie[8] = new Comedy("The Dictator", 6.4);
        movie[9] = new Comedy("The Interview", 6.5);
        

        for (int i = 0; i < movie.length; i++) {
            for (int j = i + 1; j < movie.length; j++) {
                if (movie[i].getRating() < movie[j].getRating()) {
                    Films temp = movie[i];
                    movie[i] = movie[j];
                    movie[j] = temp;
                }
            }
        }

        for (Films films : movie) {
            System.out.println();
            System.out.println(films.getName());
            System.out.println(films.getRating());
            System.out.println();
        }
    }
}