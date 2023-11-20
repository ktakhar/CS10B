// Currency.java

/**
 * PSET4 - #5
 * 
 * Simple currency converter
 * GUI using Grid Layout
 * Ttext fields for entering Euro and Dollar amounts
 * Buttons to convert between the two currencies using a fixed conversion rate
 * Appropriate error handling for invalid inputs
 * 
 * @author Kuljit Takhar
 * @version November 5 2023
 * 
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 * Currency Converter GUI Application.
 * Allows users to convert currency between Euros and Dollars using a fixed conversion rate.
 */
public class Currency extends JFrame {
    // Components for the GUI
    private JTextField euroField, dollarField;
    private JButton euroToDollarButton, dollarToEuroButton;
    private double conversionRate = 1.13; // Fixed conversion rate

    /**
     * Constructor to set up the Currency Converter GUI.
     * Initializes the frame, components, layout, and event listeners.
     */
    public Currency() {
        setTitle("Currency Converter");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 2));

        euroField = new JTextField(10);
        dollarField = new JTextField(10);
        euroToDollarButton = new JButton(">");
        dollarToEuroButton = new JButton("<");

        // Label the text fields with currency symbols
        euroField.setBorder(BorderFactory.createTitledBorder("€ Euro"));
        dollarField.setBorder(BorderFactory.createTitledBorder("$ Dollar"));

        add(euroField);
        add(dollarField);
        add(euroToDollarButton);
        add(dollarToEuroButton);

        // Add action listeners to the buttons
        euroToDollarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertEuroToDollar();
            }
        });

        dollarToEuroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertDollarToEuro();
            }
        });
    }

    /**
     * Converts Euro to Dollar based on the entered amount and the conversion rate.
     * Displays the result in the Dollar field.
     * Handles NumberFormatException and displays an error message if input is invalid.
     */
    private void convertEuroToDollar() {
        try {
            double euroAmount = Double.parseDouble(euroField.getText());
            double dollarAmount = euroAmount * conversionRate;
            dollarField.setText(new DecimalFormat("0.00").format(dollarAmount));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number in the Euro field.");
        }
    }

    /**
     * Converts Dollar to Euro based on the entered amount and the conversion rate.
     * Displays the result in the Euro field.
     * Handles NumberFormatException and displays an error message if input is invalid.
     */
    private void convertDollarToEuro() {
        try {
            double dollarAmount = Double.parseDouble(dollarField.getText());
            double euroAmount = dollarAmount / conversionRate;
            euroField.setText(new DecimalFormat("0.00").format(euroAmount));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number in the Dollar field.");
        }
    }

    /**
     * Main method to launch the Currency Converter GUI.
     * Uses SwingUtilities.invokeLater to ensure proper GUI thread execution.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Currency().setVisible(true);
        });
    }
}
