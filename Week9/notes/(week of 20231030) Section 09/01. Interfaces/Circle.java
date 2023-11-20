class Circle  implements Shape {
    private double radius;

    public Circle() { this(0.0); }
    public Circle(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI * this.radius * this.radius;
    }


    public double getPerimeter() {
        double diameter = this.radius * 2;
        return Math.PI * diameter;
    }

    public String toString() {
        return String.format("Circle (radius=%.2f)", this.radius);
    }
}
