import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawingDemo {

    public static void main( String [] args ) {
        DrawingDemoWindow drawingDemo = new DrawingDemoWindow();
        drawingDemo.setVisible( true );
    }
}



class DrawingDemoWindow extends JFrame {

    final int CIRCLEDIAMETER = 200,
              CIRCLEOFFSET   = 15,
              PREFERREDSIZE  = CIRCLEDIAMETER + CIRCLEOFFSET + 1;

    private Color fillColor, lineColor;

    private MyDrawingPanel drawingPanel = new MyDrawingPanel( CIRCLEDIAMETER, CIRCLEOFFSET, PREFERREDSIZE );

    private JRadioButton lineWhiteButton = new JRadioButton( "White" );
    private JRadioButton lineBlackButton = new JRadioButton( "Black" );
    private JRadioButton lineRedButton   = new JRadioButton( "Red" );
    private JRadioButton lineGreenButton = new JRadioButton( "Green" );
    private JRadioButton lineBlueButton  = new JRadioButton( "Blue" );

    private JRadioButton fillWhiteButton = new JRadioButton( "White" );
    private JRadioButton fillBlackButton = new JRadioButton( "Black" );
    private JRadioButton fillRedButton   = new JRadioButton( "Red" );
    private JRadioButton fillGreenButton = new JRadioButton( "Green" );
    private JRadioButton fillBlueButton  = new JRadioButton( "Blue" );



    // Constructor

    public DrawingDemoWindow() {
        layoutComponents();
        addListeners();

        // Initially display red line, green fill.
        this.lineRedButton.doClick();
        this.fillGreenButton.doClick();
    }



    private void layoutComponents() {

        // Group the radio buttons. These are *logical* groupings, whose
        // purpose is to tell the JRE "make it so that at most one of the
        // group's buttons is selected at any time."
        ButtonGroup lineColorGroup = new ButtonGroup();
        lineColorGroup.add( this.lineWhiteButton );
        lineColorGroup.add( this.lineBlackButton );
        lineColorGroup.add( this.lineRedButton );
        lineColorGroup.add( this.lineGreenButton );
        lineColorGroup.add( this.lineBlueButton );

        ButtonGroup fillColorGroup = new ButtonGroup();
        fillColorGroup.add( this.fillWhiteButton );
        fillColorGroup.add( this.fillBlackButton );
        fillColorGroup.add( this.fillRedButton );
        fillColorGroup.add( this.fillGreenButton );
        fillColorGroup.add( this.fillBlueButton );

        // Group the radio buttons. These are *physical* groupings, whose
        // purpose is to put all related buttons into a container, to make
        // it easy to deal with them as a collection.
        JPanel colorPanel = new JPanel( new GridLayout( 2,6 ));

        colorPanel.add( new JLabel( "Line Color" ) );
        colorPanel.add( this.lineWhiteButton );
        colorPanel.add( this.lineBlackButton );
        colorPanel.add( this.lineRedButton );
        colorPanel.add( this.lineGreenButton );
        colorPanel.add( this.lineBlueButton );

        colorPanel.add( new JLabel( "Fill Color" ) );
        colorPanel.add( this.fillWhiteButton );
        colorPanel.add( this.fillBlackButton );
        colorPanel.add( this.fillRedButton );
        colorPanel.add( this.fillGreenButton );
        colorPanel.add( this.fillBlueButton );

        this.setLayout( new BorderLayout() );

        this.add( this.drawingPanel, BorderLayout.CENTER );
        this.add( colorPanel,        BorderLayout.SOUTH );

        this.pack();
        this.setTitle( "Graphics Demo" );
        this.setLocationRelativeTo( null ); // Centers the window
        this.setDefaultCloseOperation( EXIT_ON_CLOSE );
    }



    private void addListeners() {
        this.lineWhiteButton.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent ae ) {
                setLineColor( Color.WHITE );
            }
        } );

        this.lineBlackButton.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent ae ) {
                setLineColor( Color.BLACK );
            }
        } );

        this.lineRedButton.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent ae ) {
                setLineColor( Color.RED );
            }
        } );

        this.lineGreenButton.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent ae ) {
                setLineColor( Color.GREEN );
            }
        } );

        this.lineBlueButton.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent ae ) {
                setLineColor( Color.BLUE );
            }
        } );

        this.fillWhiteButton.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent ae ) {
                setFillColor( Color.WHITE );
            }
        } );

        this.fillBlackButton.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent ae ) {
                setFillColor( Color.BLACK );
            }
        } );

        this.fillRedButton.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent ae ) {
                setFillColor( Color.RED );
            }
        } );

        this.fillGreenButton.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent ae ) {
                setFillColor( Color.GREEN );
            }
        } );

        this.fillBlueButton.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent ae ) {
                setFillColor( Color.BLUE );
            }
        } );

    }



    // Helper method updates this.lineColor and repaints image.
    private void setLineColor( Color lineColor ) {
        this.lineColor = lineColor;
        this.drawingPanel.repaint();
    }



    // Helper method updates this.fillColor and repaints image.
    private void setFillColor( Color fillColor ) {
        this.fillColor = fillColor;
        this.drawingPanel.repaint();
    }



    private class MyDrawingPanel extends JPanel {

        // Instance variables
        private int circleDiameter, circleOffset;

        // Constructors
        public MyDrawingPanel( int circleDiameter, int circleOffset, int preferredSize ) {
            this.circleDiameter = circleDiameter;
            this.circleOffset   = circleOffset;
            this.setPreferredSize( new Dimension( preferredSize, preferredSize ) );
        }
        public MyDrawingPanel() {}

        // paint image with current line color and fill color.
        // paintComponent is NEVER called directly.
        public void paintComponent ( Graphics g ) {

            super.paintComponent( g );

            // x, y, width, height define a rectangle that bounds the oval.
            g.setColor( lineColor );
            g.drawOval( this.circleOffset, this.circleOffset, this.circleDiameter, this.circleDiameter ); // x,  y,  w,   h

            // Fill an oval that's inside the 1-pixel-wide oval outline drawn above.
            g.setColor( fillColor );
            g.fillOval( this.circleOffset+1, this.circleOffset+1, this.circleDiameter-2, this.circleDiameter-2 );
        }
    }
}
