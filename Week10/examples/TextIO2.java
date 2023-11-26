/*
 * File: TextIO.java
 * Adapted from the book "Java, Java, Java"
 * Description: This program illustrates techniques that
 *  can be used to read and write text files. It sets up
 *  a simple user interface that lets the user type in
 *  a file name and then read or write the designated file.
 *  @version Last modified on March 2, 2019
 */

import javax.swing.*;                // Swing components
import java.awt.*;                   // Colors, Fonts, etc.
import java.io.*;                    // File I/O
import java.awt.event.*;             // ActionListener, etc.
import java.util.*;                  // Scanner class

public class TextIO2 extends JFrame implements ActionListener
{
    private JTextArea display =
         new JTextArea("Type text here to write into a file");
    private JLabel prompt =
         new JLabel ("FIRST, type a filename here:", JLabel.RIGHT);
    private JButton  readButton =
         new JButton ("NEXT, click here to READ a file");
    private JButton writeButton =
         new JButton ("OR, click here to WRITE a file");
    private JTextField nameField = new JTextField (20);

    private JPanel commands = new JPanel();

    /**
     * TextIO() constructor sets up the application's interface.
     */
    public TextIO2 ()
    {                                                       // Constructor
        super ("TextIO Demo");                              // Set window title
        readButton.setIcon ( new ImageIcon ("happyFace.gif"));
        readButton.addActionListener (this);
        writeButton.addActionListener (this);
        commands.setLayout (new GridLayout (2, 2, 1, 1));   // Control panel
        readButton.setForeground (Color.BLUE);
        writeButton.setForeground (Color.GREEN.darker().darker());
        Font f = new Font ("Serif", Font.BOLD, 30);
        readButton.setFont (f);
        writeButton.setFont (f);
        prompt.setFont (f);
        nameField.setToolTipText ("Type the name of a file that you want to READ or Write!");
        nameField.setFont (f);
        display.setFont (f);

        commands.add (prompt);
        commands.add (nameField);
        commands.add (readButton);
        commands.add (writeButton);
        display.setLineWrap (true);
        setLayout (new BorderLayout ());
        add (commands, BorderLayout.NORTH);
        //instead of ...  add (display, BorderLayout.CENTER);
        add (new JScrollPane (display), BorderLayout.CENTER);
    } // TextIO

    /**
     * readTextFile() reads the named file and displays its text.
     *  Note that when the file is empty, the nextLine() method
     *  will return with an empty string. This is how end-of-file is detected.
     * @param fileName -- a String giving the file's name
     * @param display -- a JTextArea where the text is displayed
     */
    private void readTextFile (JTextArea display, String fileName)
    {
         try
         {
            File file = new File(fileName);
            System.out.println("Absolute Path: " + file.getAbsolutePath());
	         // Create and open the stream
            Scanner input = new Scanner (new File (fileName));



	         // While more text, display a line
            while (input.hasNextLine())
            {
                String line = input.nextLine ();   	 // Reads next line
                display.append (line + "\n" );
            }

            input.close();                        // Close the file

         }

         catch (FileNotFoundException e)
         {
            display.setText ("Sorry, File NOT Found: " + fileName + "\n");
            e.printStackTrace ();
         }


    } // readTextFile

    /**
     * writeTextFile() writes the contents of display to a named file
     * @param fileName -- a String giving the file's name
     * @param display -- a JTextArea where the text is stored
     */
    private void writeTextFile (JTextArea display, String fileName)
    {
        try
          {
            PrintWriter outStream =  new PrintWriter (new File (fileName));
            outStream.print (display.getText());
            outStream.close ();
            JOptionPane.showMessageDialog (null, "File " + "was successfully created!");
          }
          catch (IOException e)
          {
               display.setText("IOERROR: " + e.getMessage() + "\n");
               e.printStackTrace();
          }
    } // writeTextFile()

    /**
     *  The method actionPerformed() handles clicks on the read or write buttons
     */
    public void actionPerformed (ActionEvent evt)
    {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog (null);
        String fileName = chooser.getSelectedFile().getName();
        nameField.setText  (fileName);

        if (evt.getSource() == readButton)
        {
            readTextFile (display, fileName);  // better to use a JFileChooser
        }
        else  writeTextFile (display, fileName);
    }                          // end of method actionPerformed()

    /**
     *  main() creates an instance of this class.
     */
    public static void main (String args[])
    {
        TextIO2 tio = new TextIO2 ();

        tio.setSize (1000, 500);
        tio.setVisible (true);
        tio.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE) ;
    }
}   // of TextIO class
