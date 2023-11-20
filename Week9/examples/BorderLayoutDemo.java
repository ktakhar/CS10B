import javax.swing.*;
import java.awt.*;

class BorderLayoutDemo
{
    public static void main (String [] args)
    {
	   JFrame jf = new JFrame ("JFrame with BorderLayout");
	   jf.setSize (500, 500);
	   jf.setLayout (new BorderLayout());   // though this is the
                                            // default for JFrames
	   JButton [] jButtonArray = { new JButton ("West Side"),
				                   new JButton ("East Side"),
				                   new JButton ("Center"),
				                   new JButton ("North Side"),
				                   new JButton ("South Side")
	                             };

       jButtonArray [4].setBackground (Color.RED);
	   Font f = new Font ("SansSerif", Font.BOLD, 50);
	   for ( JButton j : jButtonArray ) j.setFont (f);
	   jf.add (jButtonArray[0], BorderLayout.WEST );
	   jf.add (jButtonArray[1], BorderLayout.EAST );
	   jf.add (jButtonArray[2], BorderLayout.CENTER );
	   jf.add (jButtonArray[3], BorderLayout.NORTH);
	   jf.add (jButtonArray[4], BorderLayout.SOUTH);

	   jf.setVisible(true);
    }
}