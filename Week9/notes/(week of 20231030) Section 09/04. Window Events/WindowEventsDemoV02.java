// WindowEventsDemoV02.java
// Uses anonymous inner classes that extend WindowAdapter

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WindowEventsDemoV02 {
    public static void main(String [] args) {
        WindowEventsDemoWindow wedWindow = new WindowEventsDemoWindow();
        wedWindow.setVisible(true);
    }
}



class WindowEventsDemoWindow extends JFrame {

    private JLabel  jLabelSouthwest    = new JLabel( "Southwest" ),
                    jLabelSoutheast    = new JLabel( "Southeast" );

    private JPanel  jPanelSouthRegion  = new JPanel( new BorderLayout() );



// CONSTRUCTOR
    public WindowEventsDemoWindow() {
        layoutComponents();
        addListeners();
    }



// LAYOUT THE JFRAME'S COMPONENTS
    private void layoutComponents() {
        jPanelSouthRegion.add( jLabelSouthwest, BorderLayout.WEST );
        jPanelSouthRegion.add( jLabelSoutheast, BorderLayout.EAST );

        this.add( jPanelSouthRegion, BorderLayout.SOUTH );

        this.pack();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Layout Mystery Problem");
    }



// ADD LISTENERS
    private void addListeners() {
        this.addWindowListener( new WindowAdapter() {
            public void windowIconified( WindowEvent we ) {
                businessLogic( we );
            }
            public void windowDeiconified( WindowEvent we ) {
                businessLogic( we );
            }
        } );
    }




// BUSINESS LOGIC
    static int eventNumber = 0;
    public void businessLogic( WindowEvent we ) {
        String eventID;

        switch( we.getID() ) {
            case WindowEvent.WINDOW_DEICONIFIED: eventID="WINDOW_DEICONIFIED"; break;
            case WindowEvent.WINDOW_ICONIFIED:   eventID="WINDOW_ICONIFIED";   break;
            default:                             eventID="UNKNOWN";            break;
        }

        System.out.printf( "%d. WindowListener fired %s\n", ++eventNumber, eventID );
    }
}
