import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The PrimitiveTypewriter class contains the program's GUI. It implements a primitive typewriter whose
 * keyboard has the 26 lower-case letters, an enter key, and a space bar. This program provides a sample
 * implementation of the architecture that you are required to use for pset 4's Calculator problem.
 *
 * @author  David Habermehl
 * @version Last modified 28_Mar_2020
 **/
public class PrimitiveTypewriter {

    // main() instaniates an instance of a class that extends JFrame, and then makes it visible.
    public static void main(String [] args) {
        PrimitiveTypewriterWindow primitiveTypewriter = new PrimitiveTypewriterWindow( "Primitive Typewriter" );
        primitiveTypewriter.setVisible( true );
    }
}



/**
 * ThePrimitiveTypewriterWindow class extends JFrame. It contains the program's layout code, the code that
 * attaches ActionListener classes to the typewriter's keys, and the program's "business logic" that updates
 * the display in response to the clicked keys.
 *
 * @author  David Habermehl
 * @version Last modified 25_Feb_2017
 **/
class PrimitiveTypewriterWindow extends JFrame {

    // The GUI interacts with a "backend" class that does the actual work of updating the program's
    // state in response to clicked keys.
    private PrimitiveTypewriterBackend primitiveTypewriterBackend;

    // This character's appearance connotes "enter"
    private final static char ENTERKEY = '\u25BC';

    // The paper JTextArea is where the content of the typewriter's "paper" is displayed.
    private final static int PAPERROWS=5, PAPERCOLUMNS=25;
    private JTextArea paper = new JTextArea( PAPERROWS, PAPERCOLUMNS );

    // All of the typewriter's JButtons live in a single "keyboard" array.
    // That makes it easy to set up each key's ActionListener.
    private final static int NUMBEROFKEYS = 'z'+1-'a'+2; // 'a' thru 'z' + enter key + spacebar
    private JButton[] keyboard = new JButton[NUMBEROFKEYS];


    /**
     * The PrimitiveTypewriterWindow constructor
     *     1. Creates the PrimitiveTypewriterBackend object with which we'll communicate
     *        with the typewriter's backend that manages the typewriter's state.
     *     2. Lays out the GUI's widgets.
     *     3. Attached ActionListener classes to each button.
     *
     * @param   someArg    Description of someArg.
     */
    public PrimitiveTypewriterWindow( String windowTitle ) {
        this.primitiveTypewriterBackend = new PrimitiveTypewriterBackend();
        layoutComponents( windowTitle );
        addListeners();
    }



    /**
     * The layoutComponents method lays out the GUI's components.
     *
     * @param   someArg    Description of someArg.
     */
    private void layoutComponents( String windowTitle ) {

        // lettersPanel is a 3-row grid that contains most of the typewriter's keys.
        JPanel lettersPanel = new JPanel( new GridLayout( 3, 0 ) );

        // Stick buttons into the keyboard array and into lettersPanel.
        for ( int i=0; i<NUMBEROFKEYS-2; i++ ) {
            this.keyboard[i] = new JButton( "" + (char)('a'+i) );
            lettersPanel.add( this.keyboard[i] );
        }

        // Stick the enter key into the keyboard array and into lettersPanel.
        this.keyboard[NUMBEROFKEYS-2] = new JButton( "" + ENTERKEY );
        this.keyboard[NUMBEROFKEYS-2].setToolTipText("This is the Enter key");
        lettersPanel.add( this.keyboard[NUMBEROFKEYS-2] );

        // Stick the spacebar into the keyboard array. doesn't live in lettersPanel because its
        // appearance is different from the letter keys (it's the full width of the keyboard).
        this.keyboard[NUMBEROFKEYS-1] = new JButton( "Space" );

        // keyboardPanel contains lettersPanel and the spacebar.
        JPanel keyboardPanel = new JPanel( new BorderLayout() );
        keyboardPanel.add( lettersPanel, BorderLayout.NORTH );
        keyboardPanel.add( this.keyboard[NUMBEROFKEYS-1], BorderLayout.SOUTH );

        // The paper JTextArea can't be edited by the user typing.
        this.paper.setEditable( false );

        // Put the paper JTextArea in the JFrame's NORTH region.
        // Put keyboardPanel in the JFrame's SOUTH region.
        this.setLayout( new BorderLayout() );
        this.add( this.paper, BorderLayout.NORTH );
        this.add( keyboardPanel, BorderLayout.SOUTH );

        // Finish with some boilerplate stuff.
        this.pack();
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setLocationRelativeTo( null );
        this.setTitle( windowTitle );
    }



    /**
     * The addListeners method associates ActionListener classes with each key (JButton).
     * An ActionLister class must have an actionPerformed method. That method runs whenever
     * a JButton is clicked. I prefer to use "anonymous inner classes" for this.
     */
    private void addListeners() {
        for ( JButton key : keyboard ) {
            key.addActionListener( new ActionListener() {
                public void actionPerformed( ActionEvent ae ) {
                    keyClicked( ae );
                }
            } );
        }
        // Because feedChar's argument is a single char, and because the space bar's action command
        // defaults to its JButton's text ("Space"), change the space bar's action command to " "
        this.keyboard[NUMBEROFKEYS-1].setActionCommand( " " );

        // To simplify feedChar, we'll make the Enter key's action command a newline character
        this.keyboard[NUMBEROFKEYS-2].setActionCommand( "\n" );
    }



    /**
     * This is the entirety of the GUI's business logic! All that it does is feed clicked characters
     * to the backend's feedchar method, and replace the content of the paper JTextArea with whatever
     * it receives from the backend's getDisplayVal method.
     *
     * @param   someArg    Description of someArg.
     */
    public void keyClicked( ActionEvent ae ) {
        this.primitiveTypewriterBackend.feedChar( ae.getActionCommand().charAt( 0 ) );
        String newDisplayVal = this.primitiveTypewriterBackend.getDisplayVal();

        this.paper.selectAll();
        this.paper.replaceSelection( newDisplayVal );
    }

}
