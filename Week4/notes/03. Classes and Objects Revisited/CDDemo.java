// The CDDemo program class is an application to manage a CD Collection.
// It leverages several template classes:
//     1. The Song template class models a song.
//     2. The CD template class models a compact disc.
//     3. The CDCollection template class models a CD collection.



public class CDDemo {
    public static void main ( String [] args ) {

        // Create a CD and add some songs to it.
        CD compilation1 = new CD ( "Mix \"Tape\"", 2 );
        compilation1.addSong ( new Song( "Sweet Virginia", "Rolling Stones", "4:27" ) );
        compilation1.addSong ( new Song( "Imagine",        "John Lennon",    "3:01" ) );

        // Create another CD and add some songs to it.
        CD meetTheBeatles = new CD ( "Meet The Beatles", 12 );
        meetTheBeatles.addSong ( new Song( "I Want to Hold Your Hand", "Beatles", "2:24" ) );
        meetTheBeatles.addSong ( new Song( "I Saw Her Standing There", "Beatles", "2:50" ) );
        meetTheBeatles.addSong ( new Song( "This Boy",                 "Beatles", "2:11" ) );
        meetTheBeatles.addSong ( new Song( "It Won't Be Long",         "Beatles", "2:11" ) );
        meetTheBeatles.addSong ( new Song( "All I've Got to Do",       "Beatles", "2:05" ) );
        meetTheBeatles.addSong ( new Song( "All My Loving",            "Beatles", "2:04" ) );
        meetTheBeatles.addSong ( new Song( "Don't Bother Me",          "Beatles", "2:28" ) );
        meetTheBeatles.addSong ( new Song( "Little Child",             "Beatles", "1:46" ) );
        meetTheBeatles.addSong ( new Song( "Till There Was You",       "Beatles", "2:12" ) );
        meetTheBeatles.addSong ( new Song( "Hold Me Tight",            "Beatles", "2:30" ) );
        meetTheBeatles.addSong ( new Song( "I Wanna Be Your Man",      "Beatles", "1:59" ) );
        meetTheBeatles.addSong ( new Song( "Not a Second Time",        "Beatles", "2:03" ) );

        // Create a CDCollection and add the CDs to it.
        CDCollection myCDCollection = new CDCollection ( "My CD Collection", 100 );
        myCDCollection .addCD ( compilation1 );
        myCDCollection .addCD ( meetTheBeatles );

        // Display the CD collection.
        System.out.printf ( "%s\n", myCDCollection );
    }
}



// Template class that models a song
class Song {

    // Instance variables
    private String title;
    private String artist;
    private String length; // "m:ss"

    // Constructors
    public Song () { this ( "", "", ""); }
    public Song ( String title, String artist, String length ) {
        this.title  = title;
        this.artist = artist;
        this.length = length;
    }

    public String getTitle ()  { return this.title; }

    public String toString () {
        return String.format ( "%-30s    %-15s    %4s", this.title, this.artist, this.length );
    }
}



// Template class that models a CD as an array of Song objects
class CD {

    // Instance variables
    private String title;
    private Song songs [];

    // Class-level static variable used to manage the songs array.
    private static int nextEntry;

    // Constructors
    public CD () { this ( "", 0 ); }
    public CD ( String title, int numberOfSongs ) {
        CD.nextEntry = 0;
        this.title   = title;
        this.songs   = new Song[numberOfSongs];
    }

    public String getTitle () { return this.title; }

    // Adds a Song to the CD.
    public void addSong ( Song song ) {
        if ( nextEntry < songs.length ) { this.songs[nextEntry++] = song; }
        else {
            System.out.printf ( "Error adding song %s to CD \"%s\" (no more room)\n", song.getTitle (), this.title );
            System.exit ( -1 );
        }
    }

    public String toString() {
        String cdString = String.format ( "\n    %s", this.title );
        // Append each Song to cdString
        int trackNumber = 1;
        for ( Song song : this.songs ) {
            cdString += song!=null ? String.format ( "\n       %3d. %s", trackNumber++, song ) : "";
        }
        return cdString;
    }
}


// Template class that models a CD collection.
class CDCollection {

    // Instance variables
    private String name;
    private CD cds [];

    // Class-level static variable used to manage the cds array.
    private static int nextEntry;

    // Constructors
    public CDCollection () { this ( "", 0 ); }
    public CDCollection ( String name, int numberOfCDs ) {
        CDCollection.nextEntry = 0;
        this.name              = name;
        this.cds               = new CD[numberOfCDs];
    }

    // Adds a CD to the CDCollection.
    public void addCD ( CD cd ) {
        if ( nextEntry < cds.length ) { this.cds[nextEntry++] = cd; }
        else {
            System.out.printf ( "Error adding CD  %s to CD collection%s\n", cd.getTitle(), this.name );
            System.exit ( -1 );
        }
    }

    public String toString() {
        String cdCollectionString = String.format ( "\n%s", this.name );
        // Append each CD to cdCollectionString
        for ( CD cd : this.cds ) {
            cdCollectionString += cd!=null ? String.format( "\n   %s", cd ) : "";
        }
        return cdCollectionString;
    }
}
