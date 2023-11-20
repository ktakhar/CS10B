// File LayoutDemo.java
// CSCI E-10b, Unit 6
// Illustrate several types of Layout Managers (as well as some Borders)

import javax.swing.*;
import java.awt.*;

class LayoutManagerDemo
{
   public static void main (String args[])
   {
      // System.out.println (UIManager.getLookAndFeel());
 	  JButton [] jB = new JButton [16];
 	  for (int i = 0; i < 16; i++ )
 	  {
 	      jB[i] = new JButton ("Button #"+(i+1));
 	      jB[i].setFont (new Font ("SansSerif", Font.BOLD, 24));
 	  }
 	  for (int i = 0; i < 16; i+=4)
 	  {
 	      jB[i].setBackground (Color.RED);
 	      jB[i].setBorder (BorderFactory.createTitledBorder("Foobar"));
 	      jB[i+1].setBackground (Color.YELLOW);
 	      jB[i+1].setBorder (BorderFactory.createLoweredBevelBorder());
 	      jB[i+2].setBackground (Color.BLUE);
 	      jB[i+3].setBackground (Color.GREEN);
 	   }

      JFrame jf1 = new JFrame ("JFrame with FlowLayout");
      jf1.setSize (100, 100);
      jf1.setLocation (500, 500);
      jf1.setLayout (new FlowLayout() );
      for (int i=0; i < 4; i++)  jf1.add (jB[i] );
      jf1.setVisible (true);

      JFrame jf2 = new JFrame ("JFrame with BorderLayout");
      jf2.setSize (100, 100);
      jf2.setLayout (new BorderLayout() ); // although BorderLayout is the default!
      jf2.add (jB[4], BorderLayout.WEST);
      jf2.add (jB[5], BorderLayout.SOUTH);
      jf2.add (jB[6], BorderLayout.CENTER);
      jf2.add (jB[7], BorderLayout.NORTH);
      jf2.setVisible (true);

      JFrame jf3 = new JFrame ("JFrame with GridLayout");
      jf3.setSize (400 , 400);
      jf3.setLayout (new GridLayout (2, 0, 4, 7) );  //rows, cols, hgap, vgap
      for (int i = 8; i < 12; i++) jf3.add (jB[i] );
      jf3.setVisible (true);

      JFrame jf4 = new JFrame ("JFrame with BoxLayout");
      jf4.setSize (100, 100);
      jf4.setLayout (new BoxLayout (jf4.getContentPane(), BoxLayout.X_AXIS) );
      for (int i = 12; i < 16; i++)  jf4.add ( jB[i] );
      jf4.setVisible (true);
   }
}
