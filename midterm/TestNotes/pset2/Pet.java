// Pet.java

/**
 * PSET2 #3
 * Abstract class representing a pet
 * 
 * @author Kuljit Takhar
 * @version September 29, 2023
 * 
 */

public abstract class Pet {

    // initiate private instance variables representing name of pet and year of birth
    private String name;
    private int year;

    // constructor for creating a pet object with a name and year of birth
    public Pet(String name, int year) {
        this.name = name;
        this.year = year;
    }

    // getter method to retrieve name of pet
    public String getName() {
        return name;
    }

    // abstract method representing pet speaking
    public abstract String speak();
}
