import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// A panel that draws animated rectangles on its surface.
public class Anim extends JPanel 
{
    private Point p1;    // location of first rectangle
    private Point p2;    // location of second rectangle
    private int dx;      // amount by which to change position horizontally
    private int dy;      // amount by which to change position vertically

    private ImageIcon  im = new ImageIcon ("shark.jpg");
    private ImageIcon im2 = new ImageIcon ("shark2.jpg"); 

    public Anim () 
    {
        p1 = new Point (20, 40);
        p2 = new Point (60, 10);
        dx = 7;
        dy = 7;
        setBackground(Color.WHITE);
    }

    // draws 1  rectangle & 1 shark on this panel on the screen
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);  // call JPanel's original version
        g.setColor (Color.RED);
        g.fillRect ((int)p1.x, (int)p1.y, 70, 30);
        g.setColor (Color.BLUE);
        if (Math.random() > 0.66) 
        {
            g.drawImage (im.getImage(), (int)p2.x, (int)p2.y, this); 
        }
        else im2.paintIcon(this, g, (int)p2.x, (int)p2.y);
    }

    // adjusts the position of both objects on the screen 
    public void move() 
    {
        p2.x += (int) (Math.random()*60) - 25;
        p1.x += dx;
        p2.y += dy;
        if (p1.x <= 0 || p1.x + 70 >= getWidth()) 
        {
            dx = -dx;  // rectangle 1 has hit left/right edge
        }
        if (p2.x <= 0 ) p2.x = 3;
        else if (p2.x + im.getIconWidth() >= getWidth()) 
        {
            p2.x -= 10;  // shark has hit left/right edge
        }
        if (p2.y <= 0 || p2.y + 59 >= getHeight()) 
        {
            dy = -dy;  // shark has hit top/bottom edge
        }
    }
}
