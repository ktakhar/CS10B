import java.util.*;

public class Medals {
    public static void main(String[] args) {
        final int COUNTRIES = 7;
        final int MEDALS = 3;
        
        // Arrays for countries and medals
        String[] countries = 
            {
                "Canada", 
                "China", 
                "Germany", 
                "Korea", 
                "Japan", 
                "Russia", 
                "USA"
            };

        int[][] counts = {
            {1, 0, 1},
            {1, 1, 0},
            {0, 0, 1},
            {1, 0, 0},
            {0, 1, 1},
            {0, 2, 1},
            {2, 1, 0}
        };

        // Print header
        System.out.println("        Country    Gold  Silver  Bronze   Total");

        // Print medal data
        for (int i = 0; i < COUNTRIES; i++) 
        {
            // Process the ith row
            System.out.printf("%12s", countries[i]);

            int total = 0; // Initialize total to zero for each country
            
            // Print each row element and update the row total
            for (int j = 0; j < MEDALS; j++) 
            {
                System.out.printf("%8d", counts[i][j]);
                total += counts[i][j]; // Add the current medal count to the total
            }
            System.out.printf("%8d\n", total); // Print the total
            System.out.println();
        }
    }
}

