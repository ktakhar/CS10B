import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SqrtCalculator {
    public static void main( String [] args ) {
        MyWindow myWindow = new MyWindow();
        myWindow.setVisible( true );
    }
}



class MyWindow extends JFrame {

    // Widgets
    private JTextField txtNumber = new JTextField( 10 );
    private JButton btnSquareRoot = new JButton( ""+'\u221A' );


    // Constructor
    public MyWindow () {
        layoutComponents();
        addListeners();
    }



    // SET UP THE PROGRAM'S LAYOUT. There is NO listener logic or business logic here. So
    // we can change the layout independently of the listener logic and business logic.
    private void layoutComponents() {
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setTitle( "Square Root Calculator" );
        this.setLocationRelativeTo( null ); // Centers the window

        this.txtNumber.setHorizontalAlignment( JTextField.RIGHT );
        this.txtNumber.setBackground( Color.LIGHT_GRAY );
        this.txtNumber.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );

        JPanel panel = new JPanel();
        panel.add( btnSquareRoot ); // JPanel's FlowLayout centers button

        Box box = Box.createVerticalBox();
        box.add( this.txtNumber );
        box.add( panel );

        this.add( box, BorderLayout.NORTH );

        // Optimize JFrame's size
        this.setPreferredSize( new Dimension( 600, 200 ) );
        this.pack();
    }



    // SET UP THE PROGRAM'S LISTENERS. There is NO layout or business logic here.
    // So we can change the listeners independently of the layout and business logic.
    private void addListeners() {
        // Buttons fire action events, which are handled by action listeners.
        // Register an action listener for the button's action events.

        // The argument to addActionListener must be an instance of a class that
        // implements the ActionListener interface. We satisfy that requirement with
        // an anonymous inner class derived from the ActionListener interface.

        // This is a good strategy because 1. it avoids scope issues (inner classes'
        // code can see things that live in the outer class), 2. it avoids namespace
        // pollution (no need to invent names for ActionListener classes), and 3. you
        // can easily have a separate actionPerformed method for each widget instead
        // of a single actionPerformed method having to serve all widgets.
        this.btnSquareRoot.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent ae ) {
                btnSquareRootClicked( ae );
            }
        } );
    }



    // THIS IS THE PROGRAM'S BUSINESS LOGIC. There is NO layout or listener logic here.
    // So we can change the business logic independently of the layout and listener logic.

    // Click handler for the "sqrt" button. This click handler doesn't need access to
    // the ActionEvent object. I included it only to show you how it's done in case
    // your event handlers do need it.
    public void btnSquareRootClicked( ActionEvent ae ) {
        String number = this.txtNumber.getText().trim();
        String error = "Enter a number >= 0.0";
        if ( number.length() > 0 ) {
            double result;
            try {
                result = Double.parseDouble( number );
                if ( result >= 0 ) {
                    error = "";
                    result = Math.sqrt( result );
                    this.txtNumber.setText( String.format( "%.2f",result ) );
                }
            }
            catch( NumberFormatException e ) { } // error is already set
        }
        if ( !error.equals( "" ) ) { show_error_message( error ); }
    }

    private static void show_error_message( String error_message ) {
        JOptionPane.showMessageDialog( null, error_message, "Input Error",
                                       JOptionPane.ERROR_MESSAGE
                                     );
    }
}