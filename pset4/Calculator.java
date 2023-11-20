// Calculator.java

/**
 * PSET4 - #7
 * 
 * Calculator GUI using Swing.  
 * 
 * @author Kuljit Takhar
 * @version November 10 2023
 * 
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    private JTextField display;
    private JPanel buttonPanel;
    private CalcBackend calcBackend;

    /**
     * Constructor for the Calculator class, initializes the GUI components.
     */
    public Calculator() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        calcBackend = new CalcBackend();

        display = new JTextField(10);
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4));

        // Adding buttons to the calculator
        addButton("C");   // CLEAR
        addButton("√");   // SQRT
        addButton("/");   // DIVISION
        addButton("*");   // MULTIPLCATION
        addButton("7");
        addButton("8");
        addButton("9");
        addButton("-");   // SUBTRACTION
        addButton("4");
        addButton("5");
        addButton("6");
        addButton("+");   // ADDITION
        addButton("1");
        addButton("2");
        addButton("3");
        addButton("=");   // EQUALS
        addButton("0");
        addButton(".");
        addButton("(");   // OPENPAREN
        addButton(")");   // CLOSEPAREN
        addButton("MC");  // MEMORYCLEAR
        addButton("MS");  // MEMORYSET
        addButton("MR");  // MEMORYRECALL

        // Adding components to the frame
        add(display, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    /**
     * Helper method to add a button with the given label to the button panel.
     *
     * @param label The label of the button to be added.
     */
    private void addButton(String label) {
        JButton button = new JButton(label);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleButtonClick(label);
            }
        });
        buttonPanel.add(button);
    }

    /**
     * Handles the click event of the buttons, updating the display accordingly.
     *
     * @param label The label of the clicked button.
     */
   private void handleButtonClick(String label) {
    if (label.equals("MC")) {
        // Handle memory clear
        calcBackend.feedChar('C');
    } else if (label.equals("MS")) {
        // Handle memory set
        calcBackend.feedChar('S');
    } else if (label.equals("MR")) {
        // Handle memory recall
        calcBackend.feedChar('R');
    } else {
        // For other buttons, pass the first character of the label
        calcBackend.feedChar(label.charAt(0));
    }
    display.setText(calcBackend.getDisplayVal());
}

    /**
     * Main method to launch the calculator application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Calculator().setVisible(true);
        });
    }
}
