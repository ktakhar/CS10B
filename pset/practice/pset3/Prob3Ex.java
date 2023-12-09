import java.util.ArrayList;

class Prob3Ex {
    public static void main(String[] args) {
        ArrayList<String> dreamCars = new ArrayList<>();

        dreamCars.add("Porsche");
        dreamCars.add("Range Rover");
        dreamCars.add("Rolls Royce");
        dreamCars.add("Bently");
        
        System.out.println("Dream Cars:");
        
        carList(dreamCars);
    }

    public static void carList(ArrayList<String> list) {
        for (String car : list) {
            System.out.println(car);
        }
        
    }
}