
class Inheritance {
    public static void main (String[] args) {
        Animals[] AnimalList = {
            new Mammal ("Monkey", 12),
            new Mammal ("Giraffe", 7),
            new Bird ("Eagle", 19),
            new Bird ("Poof", 6),
        };

        for (Animals animal : AnimalList) {
            System.out.println(animal + "has a speed of " + animal.speed());
        }
    }
}

public abstract class Animals {
    
    protected String name;
    protected int age;

    public Animals(String n, int a) {
        this.name = n;
        this.age = a;
    }

    public abstract double speed();

    public String toString() {
       return name + " " + age;
    }
}

class Mammal extends Animals {
    public Mammal (String name, int age) {
        super(name, age);
    }

    public double speed() {
        return 0.0;
    }

    public String toString() {
        return "Mammal " + super.toString();
    }
}

class Bird extends Animals {
    public Bird (String name, int age) {
        super(name, age);
    }

    public double speed() {
        return 0.0;
    }

    public String toString() {
        return "Bird " + super.toString();
    }
}

