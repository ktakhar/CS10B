// This demo shows how a program might listen for and respond to focus events.


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class FocusEventsDemo {

    public static void main(String [] args) {

        // Create the window
        FocusEventsDemoWindow fedWindow = new FocusEventsDemoWindow();

        // Make it visible
        fedWindow.setVisible(true);
    }
}



class FocusEventsDemoWindow extends JFrame {

    // Instance variables
    private    JTextField    txtFieldTop    = new JTextField();
    private    JTextField    txtFieldBottom = new JTextField();
    private    JPanel        panel          = new JPanel();
    private    boolean       firstTime      = true;



    // Constructor

    public FocusEventsDemoWindow() {

        layoutComponents();
        addListeners();
    }



    private void layoutComponents() {

        // Add the widgets to the JFrame, along with some other layout configuration.

        // There is NO business logic or plumbing here. So we can change the layout independently of
        // the business logic and plumbing.

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("FocusEventsDemoWindow");
        this.setLocationRelativeTo(null); // Centers the window

        txtFieldTop.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtFieldTop.setBackground(Color.WHITE);

        txtFieldBottom.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtFieldBottom.setBackground(Color.WHITE);

        panel.setLayout(new GridLayout(2,1)); // 2 rows, one column
        panel.add(txtFieldTop);
        panel.add(txtFieldBottom);

        this.add(panel);

        this.pack();    // Optimize JFrame's size
    }



    private void addListeners() {

        // FocusListener interface requires two methods: focusGained() and focusLost().
        txtFieldTop.addFocusListener(new FocusListener () {
            public void focusGained(FocusEvent e) {
                txtFieldTop_focusGained();
            }

            public void focusLost(FocusEvent e) {
                txtFieldTop_focusLost();
            }
        } );


        // FocusAdapter class conveniently provides default focusGained() and focusLost()
        // methods, so you only need to override the method(s) that you care about.

        txtFieldBottom.addFocusListener(new FocusAdapter () {
            public void focusGained(FocusEvent e) {
                txtFieldBottom_focusGained();
            }
        } );


        // Normally the JFrame's first widget (textField1) gets the focus when the program starts. That causes
        // txtFieldTop_focusGained() to run when we fire up the program. To present the appropriate interaction to
        // the user, I don't want that to happen. Instead, I use txtFieldBottom.requestFocusInWindow() to give txtFieldBottom
        // the focus when the program starts. That causes txtFieldBottom_focusGained() to run when we fire up the program.

        txtFieldBottom.requestFocusInWindow();
    }



    // ************************************************************************
    // ************************************************************************

    // THIS IS THE APPLICATION'S BUSINESS LOGIC!

    // Because addListeners() calls txtFieldBottom.requestFocusInWindow(), txtFieldBottom_focusGained() runs automatically
    // when we fire up the program. It also runs if txtFieldBottom later get the focus again, but in that case it doesn't
    // actually do anything to the text fields.

    public void txtFieldBottom_focusGained() {
        System.out.println("txtFieldBottom_focusGained");

        if (firstTime) {
            firstTime = false;

            // We want the text fields to look like this when we fire up the program.
            txtFieldTop.setText("Type here");
            txtFieldBottom.setText("Then click here");
        }
    }


    // txtFieldTop_focusGained() runs automatically whenever txtFieldTop gets the focus. That happens when the user
    // clicks in txtFieldTop to begin typing.

    public void txtFieldTop_focusGained() {
        System.out.println("txtFieldTop_focusGained");

        // blank out txtFieldTop in preparation for user's input.
        txtFieldTop.setText("");
        txtFieldBottom.setText("Then click here");
    }


    // txtFieldTop_focusLost() runs automatically whenever txtFieldTop loses the focus. That happens when the user
    // clicks in txtFieldBottom per the program's instructions.

    public void txtFieldTop_focusLost() {
        System.out.println("txtFieldTop_focusLost");

        // display user's input in txtFieldBottom show "Type here" in txtFieldTop
        txtFieldBottom.setText(String.format("You typed \"%s\"", txtFieldTop.getText()));
        txtFieldTop.setText("Type here");
    }

// ************************************************************************
// ************************************************************************
}
