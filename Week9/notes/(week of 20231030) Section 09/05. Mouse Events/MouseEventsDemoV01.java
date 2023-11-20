// MouseEventsDemoV01.java
// MyMouseListener implements MouseListener

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseEventsDemoV01 {
    public static void main(String [] args) {
        MouseEventsDemoWindow medWindow = new MouseEventsDemoWindow();
        medWindow.setVisible(true);
    }
}



class MouseEventsDemoWindow extends JFrame {

    private JLabel  jLabelNorth    = new JLabel( "Click me (north)" ),
                    jLabelSouth    = new JLabel( "Click me (south)" );

    // Collect JLabels in array to facilitate adding ActionListeners
    private JLabel[] jLabels = { jLabelNorth, jLabelSouth };



// CONSTRUCTOR
    public MouseEventsDemoWindow() {
        layoutComponents();
        addListeners();
    }



// LAYOUT THE JFRAME'S COMPONENTS
    private void layoutComponents() {
        this.add( jLabelNorth, BorderLayout.NORTH );
        this.add( jLabelSouth, BorderLayout.SOUTH );

        this.pack();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Layout Mystery Problem");
    }



// ADD LISTENERS
    private void addListeners() {
        for ( JLabel jLabel : jLabels ) {
            jLabel.addMouseListener( new MouseListener() {
                //Invoked when the mouse button has been clicked (pressed and released) on a component.
                public void mouseClicked( MouseEvent me ) {
                    businessLogic( me );
                }
                // Invoked when the mouse enters a component.
                public void mouseEntered( MouseEvent me ) {
                    businessLogic( me );
                }
                // Invoked when the mouse exits a component.
                public void mouseExited( MouseEvent me ) {
                    businessLogic( me );
                }
                // Invoked when a mouse button has been pressed on a component.
                public void mousePressed( MouseEvent me ) {
                    businessLogic( me );
                }
                // Invoked when a mouse button has been released on a component.
                public void mouseReleased( MouseEvent me ) {
                    businessLogic( me );
                }
            } );
        }
    }




// BUSINESS LOGIC
    static int eventNumber = 0;
    public void businessLogic( MouseEvent me ) {
        String eventID, jLabelText;

        switch( me.getID() ) {
            case MouseEvent.MOUSE_CLICKED:  eventID="MOUSE_CLICKED";  break;
            case MouseEvent.MOUSE_ENTERED:  eventID="MOUSE_ENTERED";  break;
            case MouseEvent.MOUSE_EXITED:   eventID="MOUSE_EXITED";   break;
            case MouseEvent.MOUSE_PRESSED:  eventID="MOUSE_PRESSED";  break;
            case MouseEvent.MOUSE_RELEASED: eventID="MOUSE_RELEASED"; break;
            default:                        eventID="UNKNOWN";        break;
        }

        JLabel jLabel = (JLabel)me.getSource();
        jLabelText = jLabel.getText();
        System.out.printf( "\n%d. %s's MouseListener fired %s\n", ++eventNumber, jLabelText, eventID );
    }
}
