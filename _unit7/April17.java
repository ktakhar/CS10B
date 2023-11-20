import javax.swing.*;
import java.net.*;


class April17
{
   public static void main (String [] args) throws MalformedURLException
   {
       JFrame jf = new JFrame ("URL example");

       URL u = new URL ("https://sites.fas.harvard.edu/~leitner/hhl.jpg");

       JButton jb = new JButton();
       jb.setIcon ( new ImageIcon (u));

       jf.add (jb);
       jf.setSize (500, 600);
       jf.setVisible (true);
   }
}
       