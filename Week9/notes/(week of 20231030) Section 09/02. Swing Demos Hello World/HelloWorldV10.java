// HelloWorldV10: Layout code moved into a layoutComponents() method,
//                Plumbing code moved into a addListeners method.
//                Business logic moved into event handler methods.

//                Uses the "ActionListener is the JFrame class" strategy.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HelloWorldV10 {

    private static final String TITLE="Hello World 10";

    public static void main(String [] args) {

        // Construct the window
        MyWindow myWindow = new MyWindow(TITLE);

        // Make it visible
        myWindow.setVisible(true);

        // Nothing else for main() to do.
        System.out.println("Nothing else for main() to do");
    }
}



// "extends JFrame" says "I'm a window (well, actually, a JFrame)."
//
// "implements ActionListener" says "this class promises to meet whatever requirements need to be met
//  in order to satisfy the definition of the ActionListener interface." It turns out that there is
//  just one requirement: a class that claims to implement the ActionListener interface must provide
//  a public void method named actionPerformed that accepts one argument: an ActionEvent object.

class MyWindow extends JFrame implements ActionListener {

    private static final int WIDTH=300, HEIGHT=200;
    private JButton   buttonRed,
                      buttonYellow,
                      buttonGreen;



    // The actual creation of the window happens in the constructor
    public MyWindow() {}
    public MyWindow(String title) {

        layoutComponents(title);
        addListeners();
    }



    // 1. Set up the window's appearance
    private void layoutComponents(String title) {

        this.setTitle(title);

        // Attach a GridLayout layout manager.
        this.setLayout(new GridLayout(2, 3)); // two rows, three columns

        // Construct some button widgets
        buttonRed = new JButton("Red");
        buttonYellow = new JButton("Yellow");
        buttonGreen = new JButton("Green");

        // Add the buttons to the window
        this.add(buttonRed);
        this.add(buttonYellow);
        this.add(buttonGreen);

        // Add some empty JLabels to fill out the 2nd row of the grid
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());

        // Resize the window.
        this.setSize(WIDTH, HEIGHT);

        // Center the windows
        this.setLocationRelativeTo(null); // Centers the window

        // Tell the jvm to kill the program when the window closes.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    // 2. Set up the window's plumbing. By "plumbing" I mean that this is where we connect "listeners"
    // to the widgets that the user interacts with (e.g. the three JButton widgets).
    private void addListeners() {

        // Buttons fire "action events" which are handled by an ActionListener instance's
        // actionPerformed() method.
        //
        // addActionListener() associates an ActionListener instance with a specific widget.
        //
        // addActionListener() takes an argument: an instance of a class that implements
        // the ActionListener interface. Since we said that the MyWindow class implements
        // the ActionListener interface, let's pass the class instance that's currently
        // being constructed. Inside of the class itself, of course, we refer to the instance
        // that we're currently working on using the reserved word "this".
        buttonRed.addActionListener(this);
        buttonYellow.addActionListener(this);
        buttonGreen.addActionListener(this);
    }



    // An ActionListener instance's actionPerformed() method is invoked when widgets fire action events
    // (e.g., when they are clicked). The ActionListener is the instance of the ActionListener interface
    // that was registered with the widget with addActionListener().

    // The application's "business logic" originates in the actionPerformed() method,
    // but actionPerformed() doesn't do the heavy lefting. Instead it uses helper methods
    // to do the heavy lifting.

    // This version of the program implements the ActionListener in the JFrame class.
    public void actionPerformed(ActionEvent ae) {

        // getActionCommand() returns the command string associated with this action.
        // By default the command string is the string passed to the JButton's
        // constructor.
        if(ae.getActionCommand().equals("Red")) {
            btnClicked( ae, Color.RED );
        }
        else if(ae.getActionCommand().equals("Yellow")) {
            btnClicked( ae, Color.YELLOW );
        }
        else if(ae.getActionCommand().equals("Green")) {
            btnClicked( ae, Color.GREEN );
        }
        else {
            System.out.println("Error in button interface.");
        }
    }



    // 3. Here is our application's actual "business logic"
    private void btnClicked( ActionEvent ae, Color color ) {
        // In previous versions of HelloWorld, I said that technically, widgets are added to the JFrame's
        // content pane. But as a programming convenience, when the add method is invoked on a JFrame, the
        // method automatically adds the widget to the JFrame's content pane. In addition to the add() method,
        // the remove() and setLayout() methods are also "forwarded" to the contentPane as necessary.

        // But some other methods, like setBackground(), are not forwarded to the content pane. Those methods
        // must be invoked directly on the content pane.
        this.getContentPane().setBackground( color );
    }
}
