// ActionEventsDemo.java demonstrates ActionEvents using the "Layout Challenge" problem
// from lecture 9.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ActionEventsDemoSetForeground {
    public static void main(String [] args) {
        ActionEventsDemoWindow aedWindow = new ActionEventsDemoWindow();
        aedWindow.setVisible(true);
    }
}



class ActionEventsDemoWindow extends JFrame {

    private JButton jButton1           = new JButton( "Button 1" ),
                    jButton2           = new JButton( "Button 2" ),
                    jButtonCenter      = new JButton( "Center" );

    // Collect JButtons in array to facilitate adding ActionListeners
    private JButton[] jButtons = { jButton1, jButton2, jButtonCenter };

    private JLabel  jLabelSouthwest    = new JLabel( "Southwest" ),
                    jLabelSoutheast    = new JLabel( "Southeast" );

    private JPanel  jPanelNorthRegion  = new JPanel( new FlowLayout() ),
                    jPanelSouthRegion  = new JPanel( new BorderLayout() );



// CONSTRUCTOR
    public ActionEventsDemoWindow() {
        layoutComponents();
        addListeners();
    }



// LAYOUT THE JFRAME'S COMPONENTS
    private void layoutComponents() {
        jButtonCenter.setForeground( Color.PINK );

        jPanelNorthRegion.add( jButton1 );
        jPanelNorthRegion.add( jButton2 );

        jPanelSouthRegion.add( jLabelSouthwest, BorderLayout.WEST );
        jPanelSouthRegion.add( jLabelSoutheast, BorderLayout.EAST );

        this.add( jPanelNorthRegion, BorderLayout.NORTH );
        this.add( jButtonCenter,     BorderLayout.CENTER );
        this.add( jPanelSouthRegion, BorderLayout.SOUTH );

        this.pack();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Layout Mystery Problem");
    }



// ADD LISTENERS
    private void addListeners() {
        for ( JButton jButton : jButtons ) {
            jButton.addActionListener( new ActionListener() {
                public void actionPerformed( ActionEvent ae ) {
                    btnClicked( ae );
                }
            } );
        }
    }



// BUSINESS LOGIC
    public void btnClicked( ActionEvent ae ) {
        System.out.println( ae.getActionCommand() );
    }
}

