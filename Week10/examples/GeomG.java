/** GeomG.java
 *  Plays with the geometry of line segments, using arrays & classes.
 *  It attempts to show that the median line-segments of
 *  the 3 sides of any triangle all meet at a single point.
 *  Similar to Geom.java, but this one implements a GUI
 *  Uses classes Line and Point.
 *
 *      Dr. Henry H. Leitner
 */

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class GeomG
{
    static JLabel prompt = new JLabel();

    public static void main (String[] args)
    {
        JFrame jf =
         new JFrame("Click mouse to input the coordinates of the vertices of a triangle");
        MyPanel m = new MyPanel();

        jf.add (m, BorderLayout.CENTER);
        jf.add (prompt, BorderLayout.SOUTH);
        JOptionPane.showMessageDialog (null,
                "Click the mouse 3 times to input the vertices of a triangle!");
        jf.setSize (600, 800);
        jf.setVisible (true);
        jf.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    }
}

class MyPanel extends JPanel implements MouseListener, MouseMotionListener
{
    static Point [] vertices = new Point [3];
    static Point [] midpoints = new Point [3];
    static Point [] intersections = new Point [3];
    static Line [] medians = new Line [3];
    static Point currentLocation = new Point();
    static int clicks;

    MyPanel()
    {
        addMouseListener (this);
        addMouseMotionListener (this);
    }

    public void paintComponent (Graphics g)
    {
       super.paintComponent(g);
       Graphics2D g2d = (Graphics2D) g;
       g2d.setStroke (new BasicStroke (5, 1, 1));
       if (clicks == 1)
          g.drawLine((int)vertices[0].getX(), (int)vertices[0].getY(),
                     (int)currentLocation.getX(),(int)currentLocation.getY());
       else if (clicks == 2)
       {
          g2d.drawLine((int)vertices[0].getX(), (int)vertices[0].getY(),
                       (int)vertices[1].getX(), (int)vertices[1].getY());
          g2d.drawLine((int)vertices[1].getX(), (int)vertices[1].getY(),
                       (int)currentLocation.getX(),(int)currentLocation.getY());
       }
       else if (clicks >= 3)
       {		// draw the complete triangle
          g2d.drawLine((int)vertices[0].getX(), (int)vertices[0].getY(),
                       (int)vertices[1].getX(), (int)vertices[1].getY());
          g2d.drawLine((int)vertices[1].getX(), (int)vertices[1].getY(),
                       (int)vertices[2].getX(), (int)vertices[2].getY());
          g2d.drawLine((int)vertices[2].getX(), (int)vertices[2].getY(),
                       (int)vertices[0].getX(), (int)vertices[0].getY());
          g2d.setColor(Color.RED.darker());
          	// now draw the medians
          for (int i =0; i <=2 ; i++)
            g2d.drawLine( (int)midpoints[i].getX(), (int)midpoints[i].getY(),
                          (int)vertices[i].getX(), (int)vertices[i].getY());
          clicks++;

       }
    }

    public void mouseExited	(MouseEvent e) {  }
    public void mouseEntered	(MouseEvent e) {  }
    public void mousePressed	(MouseEvent e) {  }
    public void mouseReleased	(MouseEvent e) {  }
    public void mouseDragged	(MouseEvent e) {  }

    public void mouseMoved (MouseEvent e)
    {
        GeomG.prompt.setText ("("+e.getX()+", "+e.getY()+")");
        currentLocation.setX ( e.getX() );
        currentLocation.setY ( e.getY() );
        repaint ();
    }

    public void mouseClicked (MouseEvent e)
    {
       if (clicks < 3)
       {
          vertices[clicks]=new Point(e.getX(), e.getY());
          clicks++;
          repaint();
       }

       if (clicks == 3 )
       {
          System.out.print ("\nVERTICES\n");
          for (int i = 0; i < 3; i++)  System.out.println ("  " + vertices[i]);

          midpoints[2] = vertices[0].midpoint( vertices[1] );
          midpoints[1] = vertices[2].midpoint( vertices[0] );
          midpoints[0] = vertices[1].midpoint( vertices[2] );
          System.out.print ("MIDPOINTS:\n");
          for (int i = 0; i < 3; i++)
              System.out.println ("  " + midpoints[i]);

          System.out.print ("MEDIAN LINE EQUATIONS:\n");
          for (int i = 0; i < 3; i++)
          {
             medians[i] = new Line (midpoints[i], vertices[i]);
             System.out.println ("  " + medians[i]);
          }
          intersections[2] =  medians[0].intersect( medians[1] );
          intersections[0] =  medians[1].intersect( medians[2] );
          intersections[1] =  medians[2].intersect( medians[0] );

          System.out.print ("Points of intersection are:\n");
          for (int i = 0; i < 3; i++)  System.out.print ("  " + intersections[i]);
        System.out.println();
      }		// of if
   }		// of mouseClicked
}		    // of class MyPanel
