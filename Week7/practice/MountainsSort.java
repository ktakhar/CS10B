public class MountainsSort {
    public static void main(String[] args) {
        Mountains[] mountains = new Mountains[6];

           try {
            mountains[0] = new Mountains("Palisades", 29029, "North Lake Tahoe");
            mountains[1] = new Mountains("Mammoth", 28251, "Mammoth");
            mountains[2] = new Mountains("Kirkwood", 28169, "South Lake Tahoe");
            mountains[3] = new Mountains("Alta", 27940, "Utah");
            mountains[4] = new Mountains("Banff", 27838, "BC");
            mountains[5] = new Mountains("Jackson", 26864, "Wyoming");
        } catch (IllegalElevationException e) {
            System.err.println("Invalid elevation: " + e.getMessage());
        }
        
        // Sort the mountains by elevation iteratively using Bubble Sort from highest to lowest using Bubble Sort

        for (int i = 0; i < mountains.length - 1; i++) {
            for (int j = 0; j < mountains.length - 1 - i; j++) {
                if (mountains[j].getElevation() < mountains[j + 1].getElevation()) { // highest to lowest < mountains
                    // Swap the mountains
                    Mountains temp = mountains[j];
                    mountains[j] = mountains[j + 1];
                    mountains[j + 1] = temp;
                }
            }
        }

        System.out.println();

        // Print the sorted mountains
        for (Mountains mountain : mountains) {
            System.out.println(mountain.getName() + " " + mountain.getElevation() + "ft " + mountain.getLocation());
        }
    }
}
