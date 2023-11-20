class MountainsTest {
    public static void main(String[] args) {
        Mountains[] mountains = new Mountains[7];
        HasSnow[] snowStatus = new HasSnow[7];

        // Initialize the mountains array with try-catch for elevation exceptions
        try {
            mountains[0] = new Mountains("Palisades", 29029, "North Lake Tahoe");
            mountains[1] = new Mountains("Mammoth", 28251, "Mammoth");
            mountains[2] = new Mountains("Kirkwood", 28169, "South Lake Tahoe");
            mountains[3] = new Mountains("Alta", 27940, "Utah");
            mountains[4] = new Mountains("Banff", 27838, "BC");
            mountains[5] = new Mountains("Jackson", 26864, "Wyoming");
            mountains[6] = new Mountains("Grand Canyon", -4000, "Arizona"); // Example of illegal elevation
        } catch (IllegalElevationException e) {
            System.out.println();
            System.err.println("Invalid elevation: " + e.getMessage());
        }

        // Initialize the snowStatus array with try-catch for HasSnow constructor calls
        try {
            snowStatus[0] = new HasSnow("Palisades", 29029, "North Lake Tahoe", false);
            snowStatus[1] = new HasSnow("Mammoth", 28251, "Mammoth", false);
            snowStatus[2] = new HasSnow("Kirkwood", 28169, "South Lake Tahoe", false);
            snowStatus[3] = new HasSnow("Alta", 27940, "Utah", true);
            snowStatus[4] = new HasSnow("Banff", 27838, "BC", false);
            snowStatus[5] = new HasSnow("Jackson", 26864, "Wyoming", false);
            snowStatus[6] = new HasSnow("Grand Canyon", -4000, "Arizona", false); // Example of illegal elevation
        } catch (IllegalElevationException e) {
            System.err.println("Invalid elevation: " + e.getMessage());
        }

        // Display all mountains with elevation and location
        for (Mountains mountain : mountains) {
            if (mountain != null) {
                System.out.println(mountain.getName() + " " + mountain.getElevation() + " " + mountain.getLocation());
            }
        }

        System.out.println();

        // Display only the names of the mountains and if they have snow
        for (HasSnow snow : snowStatus) {
            if (snow != null) {
                System.out.println(snow.getName() + " has snow: " + snow.hasSnow);
            }
        }
    }
}
