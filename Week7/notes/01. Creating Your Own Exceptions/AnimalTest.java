// AnimalTest program class demonstrates custom exceptions via Animal template class
class AnimalTest {
    public static void main(String [] args) {

        // Create three Animal instances.
        Animal fred   = getAnimal("Dog",   "Fred",   7);
        Animal ted    = getAnimal("Tiger", "Ted",   -13);
        Animal louise = getAnimal("Cat",   "Louise", 9);

        // Print the three instances.
        System.out.printf("\n%s\n",   fred);
        System.out.printf("\n%s\n",   ted);
        System.out.printf("\n%s\n\n", louise);
    }

    // Tries to construct an Animal. On IllegalAnimalAgeException returns Animal with age = 0.
    public static Animal getAnimal(String species, String name, int age) {
        Animal animal=null, knownGoodAnimal=null;
        try {
            knownGoodAnimal = new Animal(species, name, 0); // Just in case IllegalAnimalAgeException is thrown
            System.out.printf( "\nConstructing new Animal( %s, %s, %d )\n", species, name, age );
            animal = new Animal(species, name, age);
        }
        catch ( IllegalAnimalAgeException iae ) { // Acknowledge checked exception e) {
            System.out.printf( "new Animal( %s, %s, %d ) threw IllegalAnimalAgeException\n", species, name, age );
            iae.printStackTrace();
            System.out.printf( "Constructing new Animal( %s, %s, %d )\n", species, name, 0 );
            animal = knownGoodAnimal;
        }
        return animal;
    }
}
