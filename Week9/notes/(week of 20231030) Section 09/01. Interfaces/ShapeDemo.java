class ShapeDemo {
    public static void main (String [] args) {
        Shape [] myShapes = {
                              new Circle(1),
                              new Rectangle(1, 2),
                              new Triangle(3, 4, 5)
                            };

        for (Shape s : myShapes) {
            System.out.printf("%s\n\tperimeter: %.2f\n\tarea: %.2f\n\n", s, s.getPerimeter(), s.getArea());
        }
    }
}