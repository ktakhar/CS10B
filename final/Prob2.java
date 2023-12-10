// Prob2.java

/**  Part A : Prob 2
 * Describe output
*/

import java.awt.*;
import javax.swing.*;

public class Prob2 {
    public static void main(String[] args) {
        
        JPanel center = new JPanel(new GridLayout(2, 2));
        center.add(new JButton("Button4"));
        center.add(new JButton("Button6"));
        center.add(new JButton("Button5"));
        center.add(new JButton("Button7"));
        
        JPanel north = new JPanel(new GridLayout(1, 3));
        north.add(new JButton("Button1"));
        north.add(new JButton("Button2"));
        north.add(new JButton("Button3"));

        JPanel south = new JPanel();
        south.add(new JLabel("Type stuff:"));
        south.add(new JTextField(10));

        JFrame frame = new JFrame("Good thing I studied!");
        frame.setSize(285, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(north, BorderLayout.NORTH);
        frame.add(center, BorderLayout.CENTER);
        frame.add(south, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}

// Frame: 285 x 400 "Good thing I studied"
// North: 1 row of 3 buttons
// Center: 2 rows of 2 buttons - center
// South: "Type stuff:"" text field that displays 10 chars