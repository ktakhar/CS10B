

class BookedTest {
    public static void main(String[] args) {
        Booked Room1 = new Booked("Monday", 1, "Rose");
        Booked Room2 = new Booked("Tuesday", 2, "Jack");
    }

    public static void printBooked(Booked booked) {
        if (booked.isBooked()) {
            System.out.println("Booked: " + booked.getName() + " on " + booked.getDay() + " at " + booked.getTime());
        } else {
            System.out.println("Not booked: " + booked.getName() + " on " + booked.getDay() + " at " + booked.getTime());
        }

        System.out.println("Booked: " + booked.getName() + " on " + booked.getDay() + " at " + booked.getTime());
    }
}