// TestSetsSwing.java

/**
 * PSET 5 #1 Part C
 * Create Swing GUI for TestSets.java
 * 
 * @author Kuljit Takhar
 * @version November 23 2024
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TestSetsSwing extends JFrame {
    private Set<Integer> setA = new HashSet<>(); // Initialize Set A
    private Set<Integer> setB = new HashSet<>(); // Initialize Set B

    private JTextArea outputTextArea; // TextArea to display output
    private JTextField setATextField; // TextField for input of Set A
    private JTextField setBTextField; // TextField for input of Set B
    private JComboBox<String> operationComboBox; // ComboBox for selecting operations
    private JButton executeButton; // Button to execute selected operation

    // Constructor for the TestSetsSwing class
    public TestSetsSwing() {
        // Set the title and initial size of the JFrame
        setTitle("TestSets Swing GUI");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the mainPanel and set its layout to BorderLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create the inputPanel to hold input components and set its layout to FlowLayout
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        // Create text fields, combo box, and execute button
        setATextField = new JTextField(20); // TextField for Set A input
        setBTextField = new JTextField(20); // TextField for Set B input
        operationComboBox = new JComboBox<>(new String[]{"Create SET A", "Create SET B", "Intersection (A * B)", "Union (A + B)", "Difference (A - B)", "Cardinality of SET A", "Cardinality of SET B", "Check if Subset"}); // ComboBox for operations
        executeButton = new JButton("Execute"); // Button to execute operations

        // Add labels and input components to inputPanel
        inputPanel.add(new JLabel("Set A: ")); // Label for Set A
        inputPanel.add(setATextField); // TextField for Set A input
        inputPanel.add(new JLabel("Set B: ")); // Label for Set B
        inputPanel.add(setBTextField); // TextField for Set B input
        inputPanel.add(operationComboBox); // ComboBox for operations
        inputPanel.add(executeButton); // Button to execute operations

        // Create the outputTextArea and make it non-editable, then add it to a scroll pane
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        // Add inputPanel to the top (north) and scrollPane to the center of mainPanel
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add mainPanel to the JFrame
        add(mainPanel);

        // Add an action listener to the executeButton
        executeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performOperation(); // Execute the selected operation
            }
        });
    }

    // Helper method to update the outputTextArea
    private void updateTextArea(String text) {
        outputTextArea.append(text + "\n"); // Append the new text to the existing text
    }

    // Helper method to save the input sets from text fields
    private void saveSets() {
        // Get the text input from setATextField and setBTextField
        String setAInput = setATextField.getText();
        String setBInput = setBTextField.getText();

        // Clear the existing sets
        setA.clear();
        setB.clear();

        // Split the input values by space and populate the sets with integers
        String[] valuesA = setAInput.split(" ");
        String[] valuesB = setBInput.split(" ");

        for (String value : valuesA) {
            try {
                int num = Integer.parseInt(value.trim());
                setA.add(num);
            } catch (NumberFormatException ex) {
                // Handle invalid input (non-integer values)
            }
        }

        for (String value : valuesB) {
            try {
                int num = Integer.parseInt(value.trim());
                setB.add(num);
            } catch (NumberFormatException ex) {
                // Handle invalid input (non-integer values)
            }
        }
    }

    // Helper method to perform the selected operation and update the outputTextArea
    private void performOperation() {
        saveSets(); // Save the input sets from text fields
        String selectedOperation = (String) operationComboBox.getSelectedItem(); // Get the selected operation from the combo box

        if (selectedOperation == null) {
            return;
        }

        switch (selectedOperation) {
            case "Create SET A":
                updateTextArea("Set A = " + setA); // Display Set A
                break;
            case "Create SET B":
                updateTextArea("Set B = " + setB); // Display Set B
                break;
            case "Intersection (A * B)":
                Set<Integer> intersection = new HashSet<>(setA);
                intersection.retainAll(setB); // Find the intersection of Set A and Set B
                updateTextArea("Intersection (A * B): " + intersection); // Display the intersection
                break;
            case "Union (A + B)":
                Set<Integer> union = new HashSet<>(setA);
                union.addAll(setB); // Find the union of Set A and Set B
                updateTextArea("Union (A + B): " + union); // Display the union
                break;
            case "Difference (A - B)":
                Set<Integer> difference = new HashSet<>(setA);
                difference.removeAll(setB); // Find the difference of Set A and Set B
                updateTextArea("Difference (A - B): " + difference); // Display the difference
                break;
            case "Cardinality of SET A":
                int cardinalityA = setA.size(); // Calculate the cardinality of Set A
                updateTextArea("Cardinality of SET A: " + cardinalityA); // Display the cardinality
                break;
            case "Cardinality of SET B":
                int cardinalityB = setB.size(); // Calculate the cardinality of Set B
                updateTextArea("Cardinality of SET B: " + cardinalityB); // Display the cardinality
                break;
            case "Check if Subset":
                boolean isSubsetAB = setB.containsAll(setA); // Check if Set A is a subset of Set B
                boolean isSubsetBA = setA.containsAll(setB); // Check if Set B is a subset of Set A
                if (isSubsetAB && isSubsetBA) {
                    updateTextArea("SET A is equal to SET B"); // Display equality
                } else if (isSubsetAB) {
                    updateTextArea("SET A is a subset of SET B"); // Display A as a subset of B
                } else if (isSubsetBA) {
                    updateTextArea("SET B is a subset of SET A"); // Display B as a subset of A
                } else {
                    updateTextArea("Neither SET A nor SET B is a subset of the other"); // Display no subset relationship
                }
                break;
            default:
                break;
        }
    }

    // Main method to start the Swing application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TestSetsSwing gui = new TestSetsSwing();
                gui.setVisible(true); // Make the GUI visible
            }
        });
    }
}