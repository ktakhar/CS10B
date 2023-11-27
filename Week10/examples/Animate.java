//Animate.java
/** Show how to display "gif" or "jpg" images
 * in rapid-fire succession to create the
 * illusion of animation
 * @author: Henry Leitner
 */

import java.awt.*;
import javax.swing.*;

public class Animate
{
   public static void main (String [] args)
   {
      JFrame j = new JFrame ("Simple Animation");
      j.setSize (700, 700);
      j.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

      JLabel jl = new JLabel ("  Simple Animation Using Icons");
      jl.setFont (new Font ("SansSerif", Font.BOLD, 36) );
      jl.setForeground (Color.RED.darker());
      JButton jb = new JButton();
      jb.setBackground (Color.GREEN);

      j.add (jb, BorderLayout.CENTER);
      j.add (jl, BorderLayout.NORTH);

      j.setVisible (true);

      for (int repetition = 0; repetition < 10; repetition++)
      {
        try
        {
           ImageIcon im;
           for (int i = 0; i <= 15; i++)
           {
	         im = new ImageIcon ("images/T" + (i+1)  + ".gif");
	         jb.setIcon (im);
	         if (i == 5 || i == 6 || i == 14) Thread.sleep (2500);
	         else Thread.sleep (300);
	       }
        }
        catch (InterruptedException e) { }
      }
    }
}

