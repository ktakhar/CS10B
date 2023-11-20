import javax.swing.*;

class FrameTest2
{
    public static void main (String [] args)
    {
            JFrame frame = new JFrame ("Frame Demo #2");
            frame.setSize (400, 400);
            frame.setVisible (true);

        for (int i = 1; i <=15; i++)
        {
            frame.setLocation ( rint (30, 300), rint (40, 200) );
            try {Thread.sleep (900) ; }
            catch (Exception e) {};
        }
        frame.setSize (250, 275);
    }

    static int rint (int a, int b)
    {
        return a + (int) (Math.random() * (b-a+1) );
    }
}