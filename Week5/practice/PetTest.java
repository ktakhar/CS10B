public class PetTest {
    public static void main (String[] args) {
        Pet[] myPets = new Pet[3];

        myPets[0] = new Dog("Huxley", 2016);
        myPets[1] = new Cat("Binx", 2022);
        myPets[2] = new Horse("Jet", 2023);

        for (Pet p : myPets) {
            System.out.println(p.getName() + " says " + p.speak());
        }

    }
}

class Dog extends Pet {
    public Dog(String name, int year) {
        super(name, year);
    }

    public String speak() {
        return "Hellowoof!";
    }
}

class Cat extends Pet {
    public Cat(String name, int year) {
        super(name, year);
    }

    public String speak() {
        return "Meeooow";
    }
}

class Horse extends Pet {
    public Horse(String name, int year) {
        super(name, year);
    }

    public String speak() {
        return "Neigh-Neigh";
    }
}