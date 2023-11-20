// InstanceVariableCanBeAnArray is a program class that manages a CD collection.
// It uses several template classes:
//     1. The CDCollection template class models a CD collection.
//        It has an array of CD objects as an instance variable.
//     2. The CD class models a compact disc.
//        It has an array of Song objects as an instance variable.
//     3. The Song template class models a song.

public class InstanceVariableCanBeAnArray {

    public static void main ( String [] args ) {
        demoInstanceVariableCanBeAnArray();
    }



    public static void demoInstanceVariableCanBeAnArray () {

        // Create a CD named "Compilation #1" and add some songs to it.
        CD compilation1 = new CD ( "Compilation #1", 2 );
        compilation1.addSong ( new Song( "Sweet Virginia", "Rolling Stones", "4:27" ) );
        compilation1.addSong ( new Song( "Imagine",        "John Lennon",    "3:01" ) );

        // Create another CD named "Meet The Beatles" and add some songs to it.
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
        myCDCollection.addCD ( compilation1 );
        myCDCollection.addCD ( meetTheBeatles );

        // Display the CD collection.
        System.out.printf ( "%s\n", myCDCollection );
    }
}



// Template class that models a CD collection.
class CDCollection {

    // Instance variables
    private String name;
    private CD[] cds;
    private int nextEntryIndex;

    // Constructors
    public CDCollection () { this ( "", 0 ); }
    public CDCollection ( String name, int numberOfCDs ) {
        this.nextEntryIndex = 0;
        this.name              = name;
        this.cds               = new CD[numberOfCDs];
    }

    // Adds a CD to the CDCollection.
    public void addCD ( CD cd ) {
        if ( this.nextEntryIndex < this.cds.length ) { this.cds[this.nextEntryIndex++] = cd; }
        else {
            System.out.printf ( "Error adding CD  %s to CD collection%s\n", cd.getTitle(), this.name );
            System.exit ( -1 );
        }
    }

    // Return String representation of a CDCollection object.
    public String toString() {
        String cdCollectionString = String.format ( "\n%s", this.name );
        CD cd;
        // Append each CD to cdCollectionString
        for ( int i=0; i<this.cds.length; i++ ) {
            cd = this.cds[i];
            if ( cd == null ) { break; }
            else {
                cdCollectionString += String.format ( "\n   %s", cd );
            }
        }
        return cdCollectionString;
    }
}



// Template class that models a CD as an array of Song objects
class CD {

    // Instance variables
    private String title;
    private Song[] songs;
    private int nextEntryIndex;

    // Constructors
    public CD () { this ( "", 0 ); }
    public CD ( String title, int numberOfSongs ) {
        this.nextEntryIndex = 0;
        this.title   = title;
        this.songs   = new Song[numberOfSongs];
    }

    // Getter for CD's title
    public String getTitle () { return this.title; }

    // Adds a Song to the CD.
    public void addSong ( Song song ) {
        if ( this.nextEntryIndex < this.songs.length ) { this.songs[this.nextEntryIndex++] = song; }
        else {
            System.out.printf ( "Error adding song %s to CD \"%s\" (no more room)\n", song.getTitle (), this.title );
            System.exit ( -1 );
        }
    }

    // Return String representation of a CD object.
    public String toString() {
        String cdString = String.format ( "\n    %s", this.title );
        Song song;
        // Append each Song to cdString
        for ( int i=0; i<this.songs.length; i++ ) {
            song = this.songs[i];
            if ( song == null ) { break; }
            else {
                cdString += String.format ( "\n       %3d. %s", i+1, song );
            }
        }
        return cdString;
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

    // Getter for Song's title
    public String getTitle ()  { return this.title; }

    // Return String representation of a Song object.
    public String toString () {
        return String.format ( "%-30s    %-15s    %4s", this.title, this.artist, this.length );
    }
}
