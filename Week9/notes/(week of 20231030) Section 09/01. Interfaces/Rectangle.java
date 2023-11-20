class Rectangle  implements Shape {
    private double height, width;

    public Rectangle() {this(0, 0); }
    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

    public double getArea() {
         return this.height * this.width;
    }

    public double getPerimeter() {
         return (this.height + this.width) * 2;
    }

    public String toString() {
        return String.format("Rectangle (height=%.2f, width=%.2f)", this.height, this.width);
    }
}
