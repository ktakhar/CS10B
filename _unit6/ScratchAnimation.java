// move a rectangular and cartoonish character around 
// on the screen an in infinite loop

import javax.swing.*;

class ScratchAnimation
{
   public static void main (String [] args)
   {
       Anim a = new Anim();
       JFrame jf = new JFrame ("The illusion of movement");
       jf.add (a);
       jf.setSize (500, 500);
       jf.setVisible(true);
       jf.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
       while (true) 
       {
         a.move(); 
         try {Thread.sleep(100); } catch (InterruptedException e) {};
         a.repaint(); 
       }
  }
}
