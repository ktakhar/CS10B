class Rectangle  implements Shape
{
    private double height, width;

    public Rectangle (double h, double w)
    {
        height = h;
        width = w;
    }

    public double getArea ()
    {
         return height*width;
    }

    public double getPerimeter()
    {
         return 2.0 * (height + width);
    }
}
