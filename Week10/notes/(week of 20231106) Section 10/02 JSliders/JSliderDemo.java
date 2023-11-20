import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

/**
 * JSlider class demos use of JSlider controls.
 *
 * @author  David Habermehl
 * @version Last modified 25_Feb_2017
 **/
public class JSliderDemo {
    public static void main(String [] args) {
        JSliderDemoWindow jsdWindow = new JSliderDemoWindow( "JSlider Demo" );
        jsdWindow.setVisible( true );
    }
}



/**
 * Description of MyWindow class.
 *
 * @author  David Habermehl
 * @version Last modified 25_Feb_2017
 **/
class JSliderDemoWindow extends JFrame {
    private final int RED_SLIDERINITIALVALUE   =  50,
                      GREEN_SLIDERINITIALVALUE =  75,
                      BLUE_SLIDERINITIALVALUE  = 100;

    private JSlider redSlider   = new JSlider(0, 100, RED_SLIDERINITIALVALUE),
                    greenSlider = new JSlider(0, 100, GREEN_SLIDERINITIALVALUE),
                    blueSlider  = new JSlider(0, 100, BLUE_SLIDERINITIALVALUE);

    private JPanel  colorPanel  = new JPanel();


    /**
     * JSliderDemoWindow constructor
     *
     * @param   windowTitle    Window's title.
     */
    public JSliderDemoWindow( String windowTitle ) {
        layoutComponents( windowTitle );
        addListeners();

        // Re-color colorPanel per sliders' inital values.
        stateChangedHandler();
    }



    /**
     * Description of layoutComponents method
     *
     * @param   someArg    Description of someArg.
     */
    private void layoutComponents( String windowTitle ) {

        JLabel  redSliderLabel   = new JLabel ("Red", SwingConstants.RIGHT),
                greenSliderLabel = new JLabel ("Green", SwingConstants.RIGHT),
                blueSliderLabel  = new JLabel ("Blue", SwingConstants.RIGHT);
        JPanel  sliderPanel      = new JPanel();

        sliderPanel.setLayout (new GridLayout(3, 2));
        sliderPanel.add( redSliderLabel );
        sliderPanel.add( this.redSlider );
        sliderPanel.add( greenSliderLabel );
        sliderPanel.add( this.greenSlider );
        sliderPanel.add( blueSliderLabel );
        sliderPanel.add( this.blueSlider );

        this.add ( this.colorPanel, BorderLayout.CENTER );
        this.add ( sliderPanel, BorderLayout.SOUTH );

        this.setSize ( 600, 600 );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setLocationRelativeTo( null );
        this.setTitle( windowTitle );
    }



    /**
     * addListeners method adds ChangeListener to JSlider controls
     */
    private void addListeners() {
        // Create a ChangeListener object.
        ChangeListener colorChangeListener = new ChangeListener() {
            public void stateChanged ( ChangeEvent ce ) {
                stateChangedHandler();
            }
        };

        // Use that object as the change listener for each slider.
        this.redSlider.addChangeListener   ( colorChangeListener );
        this.greenSlider.addChangeListener ( colorChangeListener );
        this.blueSlider.addChangeListener  ( colorChangeListener );
    }



   /**
      Event handler for JSlider events.
   */
    private void stateChangedHandler() {
        this.colorPanel.setBackground ( this.getColorFromSliders() );
    }



   /**
      Reads the slider values and returns the Color value that they produce.
   */
    private Color getColorFromSliders() {
        float red   = 0.01F * this.redSlider.getValue();
        float green = 0.01F * this.greenSlider.getValue();
        float blue  = 0.01F * this.blueSlider.getValue();
        return new Color( red, green, blue );
    }
}
