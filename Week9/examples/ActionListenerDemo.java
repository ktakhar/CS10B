import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ActionListenerDemo {
    public static void main(String [] args) {
        JFrame jf = new JFrame ("My event-ful Demo");
        jf.setSize (500, 500);
        JButton jb1 = new JButton ("Click me");
        JButton jb2 = new JButton ("No, click me.");

        jb2.setForeground (Color.RED);
        jb1.addActionListener (new Clicker());
        jb2.addActionListener (new Clicker());

        jf.add (jb1, BorderLayout.NORTH);
        jf.add (jb2, BorderLayout.SOUTH);
        jf.setVisible(true);

    }
}

class Clicker implements ActionListener {
    static int counter = 0;
    public void actionPerformed (ActionEvent e) {
        counter++;
        System.out.println("Another click! Total = " + counter);
        JButton jb = (JButton) e.getSource();
        jb.setText ("Thank you for clicking me.");
    }
}