class Circle  implements Shape
{
     private double radius;
    
     public Circle (double r)
     {
         radius = r;
     }

     public double getArea()
     {
          return Math.PI * radius * radius;
     }


     public double getPerimeter()
     {
          return 2.0 * Math.PI * radius;
     }
}
 
     
