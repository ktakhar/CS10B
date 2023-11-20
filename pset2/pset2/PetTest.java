// PetTest.java

/**
 * PSET2 #3
 * Test class to demonstrate different types of pets
 * 
 * @author Kuljit Takhar
 * @version September 29, 2023
 * 
 */

public class PetTest {
    public static void main(String[] args) {

        // Create an array of Pet objects
        Pet[] myPets = new Pet[3];

        // Initialize array with Dog and Cat objects
        myPets[0] = new Dog("Boo-Boo", 2008);
        myPets[1] = new Cat("Tigi", 2016);
        myPets[2] = new Cat("Sheldon", 2011);

        // Iterate through the array and display each pet's name and sound they make
        for (Pet p : myPets) {
            System.out.println(p.getName() + " says " + p.speak());
        }
    }
}

/**
 * A subclass of Pet representing a dog
 */

class Dog extends Pet {

    // Constructor for creating Dog object with a name and year of birth
    public Dog(String name, int year) {
        super(name, year);
    }

    // Implementation of speak() method for Dog
    public String speak() {
        return "Woof!";
    }
}

/**
 * A subclass of Pet representing a cat
 */

class Cat extends Pet {

    // Constructor for creating Cat object with a name and year of birth
    public Cat(String name, int year) {       
        super(name, year);
    }
    
    // Implementation of speak() method for Cat
    public String speak() {
        return "Meooowww!";
    }
}
