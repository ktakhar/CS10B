import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Challenge
{
    public static void main (String [] args)
    {
        JFrame frame = new JFrame ("Layout Management");
        Font f = new Font ("SansSerif", Font.BOLD, 36);
        JButton b1 = new JButton ("Button 1");
        JButton b2 = new JButton ("Button 2");
        JButton b3 = new JButton ("Center");
        b3.setBackground (Color.PINK.darker());
        JLabel l1 = new JLabel ("Southwest");
        JLabel l2 = new JLabel ("SouthEast");

        b1.addActionListener (new Clicker());
        b2.addActionListener (new Clicker());

        b1.setFont (f);  b2.setFont (f);  b3.setFont (f);
        l1.setFont (f);  l2.setFont(f);

        JPanel northPanel = new JPanel (new FlowLayout());
        JPanel southPanel = new JPanel (new BorderLayout());
        northPanel.add (b1);
        northPanel.add (b2);

        southPanel.add (l1, BorderLayout.WEST);
        southPanel.add (l2, BorderLayout.EAST);

        frame.add (northPanel, BorderLayout.NORTH);
        frame.add (southPanel, BorderLayout.SOUTH);
        frame.add (b3, BorderLayout.CENTER);
        frame.setSize (500, 500);
        frame.setVisible (true);

    }
}

class Clicker implements ActionListener
{
    static int counter = 0;
    public void actionPerformed (ActionEvent e)
    {
        counter++;
        System.out.println ("Thanks for clicking! " + counter);
    }
}