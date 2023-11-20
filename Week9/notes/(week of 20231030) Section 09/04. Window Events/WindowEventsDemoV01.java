// WindowEventsDemoV01.java
// Uses inner class (MyWindowListener) that implements WindowListener

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WindowEventsDemoV01 {
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
        this.addWindowListener( new WindowListener() {
            public void windowOpened( WindowEvent we ) { // Window has been opened
                businessLogic( we );
            }
            public void windowClosing( WindowEvent we ) { // Window in process of being closed
                businessLogic( we );
            }
            public void windowClosed( WindowEvent we ) { // Window has been closed
                businessLogic( we );
            }
            public void windowIconified( WindowEvent we ) { // Window is iconified(minimized)
                businessLogic( we );
            }
            public void windowDeiconified( WindowEvent we ) { // Window is deiconified
                businessLogic( we );
            }
            public void windowActivated( WindowEvent we ) { // Window is activated(e.g., you click in window)
                businessLogic( we );
            }
            public void windowDeactivated( WindowEvent we ) { // Window is deactivated (e.g., another window is activated)
                businessLogic( we );
            }
            public void windowGainedFocus( WindowEvent we ) { // Window gains focus
                businessLogic( we );
            }
            public void windowLostFocus( WindowEvent we ) { // Window loses focus
                businessLogic( we );
            }
            public void windowStateChanged( WindowEvent we ) { // Window changes state
                businessLogic( we );
            }
        } );
    }




// BUSINESS LOGIC
    static int eventNumber = 0;
    public void businessLogic( WindowEvent we ) {
        String eventID;

        switch( we.getID() ) {
            case WindowEvent.WINDOW_OPENED:        eventID="WINDOW_OPENED";        break;
            case WindowEvent.WINDOW_CLOSING:       eventID="WINDOW_CLOSING";       break;
            case WindowEvent.WINDOW_CLOSED:        eventID="WINDOW_CLOSED";        break;
            case WindowEvent.WINDOW_ICONIFIED:     eventID="WINDOW_ICONIFIED";     break;
            case WindowEvent.WINDOW_DEICONIFIED:   eventID="WINDOW_DEICONIFIED";   break;
            case WindowEvent.WINDOW_ACTIVATED:     eventID="WINDOW_ACTIVATED";     break;
            case WindowEvent.WINDOW_DEACTIVATED:   eventID="WINDOW_DEACTIVATED";   break;
            case WindowEvent.WINDOW_GAINED_FOCUS:  eventID="WINDOW_GAINED_FOCUS";  break;
            case WindowEvent.WINDOW_LOST_FOCUS:    eventID="WINDOW_LOST_FOCUS";    break;
            case WindowEvent.WINDOW_STATE_CHANGED: eventID="WINDOW_STATE_CHANGED"; break;
            default:                               eventID="UNKNOWN";              break;
        }

        System.out.printf( "%d. WindowListener fired %s\n", ++eventNumber, eventID );
    }
}
