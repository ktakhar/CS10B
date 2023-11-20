/**
 * The PrimitiveTypewriterBackend class manages the typewriter's state. This class is completely
 * independent of the GUI's layout and its ActionListener classes. The ONLY communication between
 * the GUI and the backend is via the feedChar and getDisplayVal methods.
 *
 * @author  David Habermehl
 * @version Last modified 29_Mar_2020
 **/
public class PrimitiveTypewriterBackend {

    // The display String contains what should be displayed on the typewriter's paper JTextArea
    private String display;

    // 0-arg constructor.
    public PrimitiveTypewriterBackend() { this.display = ""; }

    // The GUI passes each clicked key's character to the backend via the feedChar method.
    // feedchar merely appends that character to the display String.
    public void feedChar( char ch ) { this.display += ch; }

    // The GUI calls getDisplayVal after passing each clicked key's character to the backend
    // via the feedChar method. getDisplayVal returns the display String that represents the
    // current contents to be displayed in the paper JTextArea. The GUI updates the JTextArea
    // accordingly.
    public String getDisplayVal() { return this.display; }

}
