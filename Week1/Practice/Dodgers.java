import java.util.*;

public class Dodgers {
    public static void main(String[] args) {
        final int PITCHERS = 13;
        final int STATS = 3;

        String[] pitchers = {
            "Caleb Ferguson",
            "Victor Gonzalez",
            "Brusadar Graterol",
            "Clayton Kershaw",
            "Lance Lynn",
            "Shelby Miller",
            "Bobby Miller",
            "Ryan Pepiot",
            "Evan Phillips",
            "Emmett Sheehan",
            "Gus Varland",
            "Alex Vesia",
            "Ryan Yarbrough",
        };

        double[][] stats = {
            {7, 3, 2.65},
            {2, 3, 4.40},
            {4, 2, 1.38},
            {12, 4, 2.61},
            {4, 2, 4.95},
            {1, 0, 2.18},
            {9, 3, 3.80},
            {1, 0, 1.29},
            {1, 3, 2.26},
            {3, 1, 5.31},
            {1, 0, 3.27},
            {0, 5, 5.18},
            {3, 1, 2.82},
        };

        String[] players = {
            "Mookie Betts",
            "Max Muncy",
            "JD Martinez",
            "Freddie Freeman",
            "James Outman",
            "Will Smith",
            "Chris Taylor",
            "Justin Heyward", 
            "David Peralta",
            "Kike Hernandez",
            "Miguel Rojas", 
        };

        double[][] playerStats = {
            {.313, 99, 38},
            {.202, 91, 33},
            {.256, 78, 26},
            {.333, 90, 25},
            {.255, 62, 18},
            {.271, 68, 17},
            {.225, 41, 15},
            {.269, 36, 14},
            {.273, 49, 7},
            {.245, 16, 3},
            {.228, 25, 3},
        };

       System.out.println("        Pitcher    Wins  Losses  ERA");

        for (int i = 0; i < PITCHERS; i++) {
            System.out.printf("%12s", pitchers[i]);

            for (int j = 0; j < STATS; j++) {
                System.out.printf("%8.2f", stats[i][j]);
        }
        
        System.out.println();
        
        }

        System.out.println("\n   Player      AVG      RBI     HR");

        for (int i = 0; i < players.length; i++) {
            System.out.printf("%12s", players[i]); // Use %s to print player names as strings

            for (int j = 0; j < STATS; j++) {
                System.out.printf("%8.3f", playerStats[i][j]); // Use %8.2f for floating-point numbers
            }
        System.out.println();
        }

    }
}
