//  ColorDemo.java
/** Rapidly change the content pane's
  * background color (using all RGB combinations)
  * @author: Henry Leitner
  * @version: Last modified on March 6, 2017
  */

import javax.swing.*;
import java.awt.*;

public class Cd
{
  public static void main (String[] args)
  {
     JFrame jf = new JFrame("Lotsa colors!");
     jf.setSize (800, 800);
     jf.setVisible (true);

     Container c = jf.getContentPane();


   for (int i = 1; i < 100; i++)
   {
		            Color color = new Color ((float)Math.random(), (float)Math.random(), (float)Math.random());
		            c.setBackground (color);
		            try {Thread.sleep(200);} catch (Exception e){};
	           }
        }

}


