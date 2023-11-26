import javax.swing.*;                // Swing components
import java.awt.*;                   // Colors, Fonts, etc.
import java.io.*;                    // File I/O
import java.awt.event.*;             // ActionListener, etc.
import java.util.*;                  // Scanner class


public class TextIO extends JFrame implements ActionListener {

        // instances 

        private JTextArea display = new JTextArea("Type text here to write into a field");
        private JLabel prompt = new JLabel("Enter filename");
        private JButton readButton = new JButton("READ");
        private JButton writeButton = new JButton("WRITE");
        private JTextField nameField = new JTextField(20);

        private JPanel commands = new JPanel();

    /**
     * Text IO() constructor ets up the application interface
     */

    public TextIO () { // constructor 
        super ("TextIO Demo"); // window title

        commands.setLayout (new GridLayout (2, 2, 5, 5)); // set layout for commands JPanel Layout
        Font f = new Font("Serif", Font.BOLD, 30); // Set font
        
        readButton.addActionListener(this); // add listener to read button
        writeButton.addActionListener(this); // add listener to write button

        display.setFont(f); // set font
        prompt.setFont(f);
        readButton.setFont(f);
        writeButton.setFont(f);
        nameField.setFont(f);

        commands.add(prompt);
        commands.add(nameField);
        commands.add(readButton);
        commands.add(writeButton);

        display.setLineWrap(true); // wrap text
        setLayout (new BorderLayout()); // set layout for main container
        add (commands, BorderLayout.NORTH); // set position of commands inside main container
        add (new JScrollPane (display), BorderLayout.CENTER); //  add scrollable display component to center of main container
    }

    /**
     * readTextFile() reads named file and displays text
     * @param fileName -- a String giving the files name
     * @param display -- a JTextArea where the text is displayed
     */

    private void readTextFile(JTextArea display, String fileName) {
        try {
            Scanner input = new Scanner(new File (fileName)); // create scanner
            while (input.hasNextLine()) { // loop while input has next line 
                String line = input.nextLine(); // reads next line
                display.append(line + "\n"); // adds line to display
            }
            input.close();
        }
        catch (FileNotFoundException e) {
            display.setText("File not found: " + fileName + "\n");
            e.printStackTrace();
        }
    }

    /**
     * writeTextFile() writes the contents of display to a named file
     * @param fileName -- A String giving file's name
     * @param display -- A JTextArea where the text is stored
     */

    private void writeTextFile(JTextArea display, String fileName) {
        try {
            PrintWriter outStream = new PrintWriter(new File(fileName));
            outStream.print(display.getText());
            outStream.close();
            JOptionPane.showMessageDialog(null, "File was successfully created!");
        } catch (IOException e) {
            display.setText("IOERROR: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    /**
     * The actionPerformed() method handles clicks on the read or write button
     */

    public void actionPerformed(ActionEvent evt) {
        
        String fileName = nameField.getText();
        
        if (evt.getSource() == readButton) {
            display.setText("");
            readTextFile(display, fileName);
        } else 
            writeTextFile(display, fileName);
    } 

    /**
     * main() creates an instance of this class
     */

    public static void main(String[] args) {

        TextIO tio = new TextIO();

        tio.setSize(1000,500);
        tio.setVisible(true);
        tio.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    }
}