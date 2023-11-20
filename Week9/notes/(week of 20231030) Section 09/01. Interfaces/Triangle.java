class Triangle implements Shape {

    double side1, side2, side3;

    public Triangle () { this( 0, 0, 0 ); }
    public Triangle( double side1, double side2, double side3 ) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public double getArea() { // uses Heron's formula
        double s = (this.side1 + this.side2 + this.side3) / 2;
        return Math.sqrt( s * (s - this.side1) * (s - this.side2) * (s - this.side3)) ;
    }

    public double getPerimeter() {
        return this.side1 + this.side2 + this.side3;
    }

    public String toString() {
        return String.format("Triangle (side1=%.2f, side2=%.2f, side3=%.2f)", this.side1, this.side2, this.side3);
    }
}
