import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * set up an display a frame with 3 buttons
 *
 * adapted from "Core Java", Chapter 8
 */

public class ButtonTest
{
    /**
     * creates a simple 3-button frame
     *
     * @param args not used here
     */

    public static void main (String [] args)
    {
         ButtonFrame aFrame = new ButtonFrame();
         aFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
         aFrame.setVisible (true);

    }
}

/**
 * a frame with a 3-button panel
 */

class ButtonFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 650;
    public static final int DEFAULT_HEIGHT = 200;

    /**
     * construct a button-panel frame instance
     */
    
    private JPanel buttonPanel; // challenge 
   

    public ButtonFrame()
    {
        this.setSize (DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setTitle ("ButtonTest");

        // create and add a button panel to my frame

        buttonPanel = new JPanel(); // challenge
        this.add(buttonPanel); // challenge

        ButtonPanel aButtonPanel = new ButtonPanel(); // challenge
        this.add(aButtonPanel); // challenge
    }
}

/**
 * a panel with 3 buttons
 */

class ButtonPanel extends JPanel
{
    /**
     * constructs a button panel instance
     */

    public ButtonPanel()
    {
        // create some buttons!
        Font f = new Font ("Serif", Font.BOLD, 50);
        JButton yellowButton = new JButton("Yellow");
        yellowButton.setFont (f);

        JButton blueButton = new JButton("Blue");
        blueButton.setFont (f);
        JButton redButton = new JButton("Red");
        redButton.setFont (f);

        // add these buttons to this new panel

        this.add (yellowButton);
        this.add (blueButton);
        this.add (redButton);

        // I want to associate listeners with these buttons --
        //    JButton has an addActionListener method,
        //    to add an action listener to that button.
        //

        // this listener must be an object of type
        //     ActionListener

        ColorAction yellowAction = new ColorAction (Color.YELLOW, this);
        yellowButton.addActionListener (yellowAction);

        ColorAction blueAction = new ColorAction(Color.BLUE, this);
        blueButton.addActionListener (blueAction);

        redButton.addActionListener(new ColorAction(Color.RED, this));
    }

    /**
     * this is an inner class!
     * An action listener that sets the containing
     *     panel's background color
     *
     * note: a class implementing ActionListener must
     *     implement a method with this signature:
     *
     *     public void actionPerformed(ActionEvent event)
     */

    // challenge

    private class ColorAction implements ActionListener
    {
        // implement this class

        private Color backgroundColor;
        private JPanel panelToColor;

        public ColorAction(Color color, JPanel panel) {
            backgroundColor = color;
            panelToColor = panel;
        }
        public void actionPerformed(ActionEvent event) {
            panelToColor.setBackground(backgroundColor);
        }
    }
}