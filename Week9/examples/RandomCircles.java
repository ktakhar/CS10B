// RandomCircles.java
// Draw some circles of random size, color & location
// Author: Henry H. Leitner
// Date: March 1, 2019

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class RandomCircles extends JFrame
{
   static JButton circleButton;
   static MyCirclePanel m = new MyCirclePanel();

   public static void main (String args[])
   {
	  RandomCircles fr = new RandomCircles();
	  fr.setSize (550,550);
	  fr.setTitle ("Random Circles!");
	  circleButton = new JButton ("Click Here to Draw a Random Circle");
	  circleButton.setFont (new Font("Helvetica", Font.BOLD, 24));
	  circleButton.setBackground (Color.YELLOW);
	  circleButton.setForeground (Color.RED);
	  fr.getContentPane().add (circleButton, BorderLayout.NORTH);
	  circleButton.addActionListener (m);
	  fr.add (m, BorderLayout.CENTER);
	  fr.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
  	  fr.setVisible (true);
   }
}


class MyCirclePanel extends JPanel implements ActionListener
{
   public void paintComponent (Graphics g)
   {
      super.paintComponent(g);           // include this line or not?!??
      int size = getSize().width / 2;

      // Generate a random center for the circle.
      int x = (int) (size * Math.random());
      int y = (int) (size * Math.random());
      Color randomColor = new Color ( (int)(255*Math.random()),
		                                (int)(255*Math.random()),
		                                (int)(255*Math.random()) );
      // now randomly reduce the diameter
      int diameter = (int) (size * Math.random());
      int radius = (int)(diameter/2);
      g.setColor (randomColor);
      g.fillOval (x-radius, y-radius, diameter, diameter);
  }


  public void actionPerformed (ActionEvent event)
  {
      repaint();
  }
}
