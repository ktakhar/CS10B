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
    private Set<Integer> setA = new HashSet<>();
    private Set<Integer> setB = new HashSet<>();

    private JTextArea outputTextArea;
    private JTextField setATextField;
    private JTextField setBTextField;
    private JComboBox<String> operationComboBox;
    private JButton executeButton;

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
        setATextField = new JTextField(20);
        setBTextField = new JTextField(20);
        operationComboBox = new JComboBox<>(new String[]{"Create SET A", "Create SET B", "Intersection (A * B)", "Union (A + B)", "Difference (A - B)", "Cardinality of SET A", "Cardinality of SET B", "Check if Subset"});
        executeButton = new JButton("Execute");

        // Add labels and input components to inputPanel
        inputPanel.add(new JLabel("Set A: "));
        inputPanel.add(setATextField);
        inputPanel.add(new JLabel("Set B: "));
        inputPanel.add(setBTextField);
        inputPanel.add(operationComboBox);
        inputPanel.add(executeButton);

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
                performOperation();
            }
        });
    }

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

    private void performOperation() {
        // Save sets before performing any operation
        saveSets();

        // Get the selected operation from the combo box
        String selectedOperation = (String) operationComboBox.getSelectedItem();

        if (selectedOperation == null) {
            return;
        }

        // Perform the selected operation and update the outputTextArea accordingly
        switch (selectedOperation) {
            case "Create SET A":
                outputTextArea.setText("Set A = " + setA + "\n");
                break;
            case "Create SET B":
                outputTextArea.setText("Set B = " + setB + "\n");
                break;
            case "Intersection (A * B)":
                Set<Integer> intersection = new HashSet<>(setA);
                intersection.retainAll(setB);
                outputTextArea.setText("Intersection (A * B): " + intersection + "\n");
                break;
            case "Union (A + B)":
                Set<Integer> union = new HashSet<>(setA);
                union.addAll(setB);
                outputTextArea.setText("Union (A + B): " + union + "\n");
                break;
            case "Difference (A - B)":
                Set<Integer> difference = new HashSet<>(setA);
                difference.removeAll(setB);
                outputTextArea.setText("Difference (A - B): " + difference + "\n");
                break;
            case "Cardinality of SET A":
                int cardinalityA = setA.size();
                outputTextArea.setText("Cardinality of SET A: " + cardinalityA + "\n");
                break;
            case "Cardinality of SET B":
                int cardinalityB = setB.size();
                outputTextArea.setText("Cardinality of SET B: " + cardinalityB + "\n");
                break;
            case "Check if Subset":
                boolean isSubsetAB = setB.containsAll(setA);
                boolean isSubsetBA = setA.containsAll(setB);
                if (isSubsetAB && isSubsetBA) {
                    outputTextArea.setText("SET A is equal to SET B\n");
                } else if (isSubsetAB) {
                    outputTextArea.setText("SET A is a subset of SET B\n");
                } else if (isSubsetBA) {
                    outputTextArea.setText("SET B is a subset of SET A\n");
                } else {
                    outputTextArea.setText("Neither SET A nor SET B is a subset of the other\n");
                }
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TestSetsSwing gui = new TestSetsSwing();
                gui.setVisible(true);
            }
        });
    }
}