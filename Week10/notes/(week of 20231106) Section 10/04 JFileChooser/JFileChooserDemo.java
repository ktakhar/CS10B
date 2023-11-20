import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/**
 * Description of JFileChooserDemo class.
 *
 * @author  David Habermehl
 * @version Last modified 25_Feb_2017
 **/
public class JFileChooserDemo {
    public static void main(String [] args) {
        JFileChooserDemoWindow jfcdWindow = new JFileChooserDemoWindow( "Window Title" );
        jfcdWindow.setVisible( true );
    }
}



/**
 * Description of JFileChooserDemoWindow class.
 *
 * @author  David Habermehl
 * @version Last modified 25_Feb_2017
 **/
class JFileChooserDemoWindow extends JFrame {

    private JButton button;
    private JFileChooser chooser;



    /**
     * Description of JFileChooserDemoWindow constructor
     *
     * @param   someArg    Description of someArg.
     */
    public JFileChooserDemoWindow( String windowTitle ) {
        layoutComponents( windowTitle );
        addListeners();
    }



    /**
     * Description of layoutComponents method
     *
     * @param   someArg    Description of someArg.
     */
    private void layoutComponents( String windowTitle ) {
        this.button = new JButton( "Click Me" );
        this.chooser = new JFileChooser();
        this.add( button );
        this.pack();

        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setLocationRelativeTo( null );
        this.setTitle( windowTitle );
    }



    /**
     * Description of addListeners method
     *
     * @param   someArg    Description of someArg.
     */
    private void addListeners() {
        button.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent ae ) {
                btnClicked( ae );
            }
        } );
    }



    /**
     * Description of btnClicked method
     *
     * @param   someArg    Description of someArg.
     */
    public void btnClicked( ActionEvent ae ) {
        this.chooser.showOpenDialog( null );
        File file = this.chooser.getSelectedFile();
        if ( file == null ) {
            System.out.println( "You didn't click open with a selected file!" );
        }
        else {
            String filename = file.getAbsolutePath();
            System.out.printf( "You selected %s\n", filename );
        }
    }

}
