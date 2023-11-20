//  ColorDemo.java
/** Rapidly change the content pane's
  * background color (using all RGB combinations)
  * @author: Henry Leitner
  * @version: Last modified on February 6, 2019
  */

import javax.swing.*;
import java.awt.*;

public class ColorDemo
{
  public static void main (String[] args)
  {
     JFrame jf = new JFrame ("Lotsa colors!");
     jf.setSize (800, 800);
     jf.setVisible (true);
     jf.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

     Container c = jf.getContentPane();

     for (int red = 0; red <= 255; red+=20)
     {
        for (int green = 0; green <= 255; green+=10)
        {
             for (int blue = 0; blue <= 255; blue+=90 )
	           {
		            Color color = new Color (red, green, blue);
		            try {Thread.sleep(40); }
		            catch (Exception e) {};
		            c.setBackground (color);
	           }
        }
     }
  }
}

