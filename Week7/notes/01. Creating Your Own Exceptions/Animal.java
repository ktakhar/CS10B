// Animal template class models animals.
class Animal {

    private String species;
    private String name;
    private int age;

    // Three-arg Animal constructor.
    //public Animal(String species, String name, int age) {
    public Animal(String species, String name, int age) throws IllegalAnimalAgeException { // Acknowledge checked exception
        if (age < 0) {
            throw new IllegalAnimalAgeException( String.format( "age: %d", age ) );
        }
        this.species = species;
        this.name = name;
        this.age = age;
    }

    // Zero-arg constructor defaults species to "Tiger", name to "Fred", and age to 0.
    //public Animal() {
    public Animal() throws IllegalAnimalAgeException { // Acknowledge checked exception{
        this("Tiger", "Fred", 0);
    }

    // toString returns a String representation of an Animal.
    public String toString() {
        return String.format( "Species: %s\nName:%s\nAge:%d", this.species, this.name, this.age );
    }
}

