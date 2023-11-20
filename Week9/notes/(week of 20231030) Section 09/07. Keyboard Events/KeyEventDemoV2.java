import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KeyEventDemoV2 {
    public static void main(String[] args) {
        KeyEventDemoV2Window kedWindow = new KeyEventDemoV2Window();
        kedWindow.setVisible(true);
    }
}

class KeyEventDemoV2Window extends JFrame {

    JTextArea displayArea = new JTextArea("Type in the above field.\n");
    JTextField typingArea = new JTextField(20);
    JButton btnClear = new JButton("Clear");
    JScrollPane scrollPane = new JScrollPane(displayArea);
    static final String newline = "\n";

    public KeyEventDemoV2Window() {
        layoutComponents();
        addListeners();
    }

    private void layoutComponents() {
        this.setLocationRelativeTo(null); // Centers the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.displayArea.setEditable(false);
        this.scrollPane.setPreferredSize(new Dimension(375, 125));

        this.add(typingArea, BorderLayout.PAGE_START);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(btnClear, BorderLayout.PAGE_END);
        this.pack();
    }

    private void addListeners() {
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnClear_clicked();
            }
        } );

        typingArea.addKeyListener(new KeyAdapter () {
            public void keyTyped(KeyEvent e) {
                keyTyped_Handler(e);
            }
            public void keyPressed(KeyEvent e) {
                keyPressed_Handler(e);
            }
        } );

        //Uncomment this if you wish to turn off focus
        //traversal.  Doing so will allow the KeyListener
        //to consume tab and shift tab.
        typingArea.setFocusTraversalKeysEnabled(false);
    }

    /** Handle the button click. */
    public void btnClear_clicked() {
        //Clear the text components.
        displayArea.setText("");
        typingArea.setText("");

        //Return the focus to the typing area.
        typingArea.requestFocusInWindow();
    }

    private void keyTyped_Handler(KeyEvent e) {
        char c = e.getKeyChar();
        int keyCode = e.getKeyCode();
        int extendedKeyCode = e.getExtendedKeyCode();
        String keyString = "key character = '" + c + "'";
        if (Character.isISOControl(c)) keyString = "key character = '\\" + (int) c + "'";

        // Modifiers are shift and ctrl
        int modifiersEx = e.getModifiersEx();
        String modString = "extended modifiers = " + modifiersEx;
        String tmpString = KeyEvent.getModifiersExText(modifiersEx);
        if (tmpString.length() > 0) {
            modString += " (" + tmpString + ")";
        } else {
            modString += " (no extended modifiers)";
        }

        String locationString = "key location: ";

        switch( e.getKeyLocation() ) {
            case KeyEvent.KEY_LOCATION_STANDARD: locationString += "standard"; break;
            case KeyEvent.KEY_LOCATION_LEFT:     locationString += "left";     break;
            case KeyEvent.KEY_LOCATION_RIGHT:    locationString += "right";    break;
            case KeyEvent.KEY_LOCATION_NUMPAD:   locationString += "numpad";   break;
            case KeyEvent.KEY_LOCATION_UNKNOWN:  locationString += "unknown";  break;
            default:                             locationString += String.format( "error (e.getKeyLocation()=\"%s\")", e.getKeyLocation() );
        }

        displayArea.append(keyString + "\n    " +
                           modString + "\n    " +
                           locationString + "\n");
        displayArea.setCaretPosition(displayArea.getDocument().getLength());
    }

    private void keyPressed_Handler(KeyEvent e) {

        int keyCode = e.getKeyCode(),
            extendedKeyCode = e.getExtendedKeyCode();
        String arrowKey = "Not an arrow key";
        switch( keyCode ) {
            case KeyEvent.VK_UP:
                arrowKey = "Up";
                break;
            case KeyEvent.VK_DOWN:
                arrowKey = "Down";
                break;
            case KeyEvent.VK_LEFT:
                arrowKey = "Left";
                break;
            case KeyEvent.VK_RIGHT:
                arrowKey = "Right";
                break;
        }
        System.out.printf( "keyCode = %d\nextendedKeyCode = %d\narrowKey = %s\n", keyCode, extendedKeyCode, arrowKey );
    }
}
