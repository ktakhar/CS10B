import javax.swing.*;
import javax.swing.event.*;

class Slider
{
     static JSlider s = new JSlider (SwingConstants.HORIZONTAL, 5, 200, 50);

     public static void main (String [] args)
     {
         JFrame jf = new JFrame ("Sliding around ...");
         s.addChangeListener ( new ChangeListener()
                               {
                                    public void stateChanged (ChangeEvent e)
                                    {
                                      System.out.println ("New value = " + s.getValue());
                                    }
                               }
                             );
         s.setMajorTickSpacing (20);
         s.setPaintTicks (true);

         jf.add (s);
         jf.setSize (600,600);
         jf.setVisible(true);

     }
}