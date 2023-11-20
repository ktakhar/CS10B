import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

public class JTextPaneRightAlignment {

    private static final String TITLE="JTextAreaRightAlignment";

    public static void main(String [] args) {
        MyWindow myWindow = new MyWindow(TITLE);
        myWindow.setVisible(true);
    }
}



class MyWindow extends JFrame {

    private static final int WIDTH=300, HEIGHT=200;

    // A MyRightAlignedJTextPane object knows how to append right-aligned text
    private MyRightAlignedJTextPane output = new MyRightAlignedJTextPane();

    public MyWindow(String title) {
        this.setTitle(title);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null); // Centers the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(output);

        String initialText =  "This is a JTextPane widget" +
                              "\nIt shouldn\'t be this complicated to right-justify text," +
                              "\nbut apparently it is :)";
        String appendedText = "\nThis is additional text, appended to the initial text.\n";

        // See how easy it is to append right-aligned text?
        output.append(initialText);
        output.append(appendedText);
    }
}



class MyRightAlignedJTextPane extends JTextPane {

    // The text that's displayed in this MyRightAlignedJTextPane object
    private String myJTextPaneText;

    // Zero-arg constructor
    MyRightAlignedJTextPane() {
        // Make it so that text appended to the JTextPane is right-aligned.
        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs , StyleConstants.ALIGN_RIGHT);
        this.setParagraphAttributes(attribs,true);

        // Initialize the text that's displayed in this MyRightAlignedJTextPane object
        this.myJTextPaneText = "";
    }

    // Appends appendedText to the text currently displayed in this MyRightAlignedJTextPane object
    void append(String appendedText) {
        this.setCaretPosition(0);
        this.moveCaretPosition(myJTextPaneText.length());
        this.myJTextPaneText += appendedText;
        this.replaceSelection(myJTextPaneText);
    }
}