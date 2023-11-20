public abstract class Pet {
    private String name;
    private int year;

    public Pet(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public abstract String speak();
}