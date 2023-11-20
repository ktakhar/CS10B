import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

// Demonstration of how to handle mouse events
class Mousing extends JFrame
{
    private JLabel status;

    public Mousing ()
    {
        setTitle ("Mousing Around ... ");
        setSize (500, 500);
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setVisible (true);

        status = new JLabel ("starting up ...");
        Font f = new Font ("Serif", Font.BOLD, 50);
        status.setFont (f);

        add (status, BorderLayout.SOUTH);
        addMouseListener (new MouseTracker());
        addMouseMotionListener (new MouseTracker());
    }

    public static void main (String [] args)
    {
        Mousing object = new Mousing ();
    }

    class MouseTracker extends MouseAdapter implements MouseMotionListener
    {
        public void mouseClicked (MouseEvent e)
        {
            status.setText ("Clicked at [" + e.getX() + ", " + e.getY() + "]");
        }

        public void mouseMoved (MouseEvent e)
        {
            status.setText ("Mouse MOVED at [" + e.getX() + ", " + e.getY() + "]");
        }

        public void mouseDragged (MouseEvent e)
        {
            status.setText ("Mouse DRAGGED at [" + e.getX() + ", " + e.getY() + "]");
        }
    }
}