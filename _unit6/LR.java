// LinesRectsOvals.java
// Drawing lines, rectangles and ovals

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LR
{
 public static void main( String args[] )
 {
      JFrame f = new JFrame ("Drawing lines, rectangles & ovals!");
      f.setSize( 500, 400 );
      MyDrawingPanel m = new MyDrawingPanel();
      f.getContentPane().add(m);
      f.setVisible(true);
      f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
 }
}

class MyDrawingPanel extends JPanel
{
   public void paintComponent (Graphics g2)
   {
      super.paintComponent (g2);
      Graphics2D g = (Graphics2D) g2;
      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                         RenderingHints.VALUE_ANTIALIAS_ON);
      g.setColor (Color.RED);
      g.drawLine (5, 30, 350, 30);

      g.setColor (Color.BLUE);
      g.drawRect (5, 40, 90, 55);
      g.fillRect (100, 40, 90, 55);

      g.setColor (Color.CYAN);
      g.fillRoundRect (195, 40, 90, 55, 50, 50);
      g.drawRoundRect (290, 40, 90, 55, 20, 20);

      g.setColor (Color.YELLOW );
      g.draw3DRect (5, 100, 90, 55, true);
      g.fill3DRect (100, 100, 90, 55, false);

      g.setColor (Color.MAGENTA);
      g.drawOval (195, 100, 90, 55);
      g.fillOval (290, 100, 90, 55);
   }
}

