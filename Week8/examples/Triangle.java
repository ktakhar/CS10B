class Triangle implements Shape {
    private double base, height, side;
    public Triangle(double b, double h, double s) {
        base = b;
        height = h;
        side = s;
    }
    public double getArea() {
        return (b * h) / side
    }
    public double getPerimeter() {
        return b + h + s;
    }
 }