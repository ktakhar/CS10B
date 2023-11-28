import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TestSetsGUI extends JFrame {
    private Set<Integer> setA = new HashSet<>();
    private Set<Integer> setB = new HashSet<>();

    private JTextArea outputTextArea;
    private JTextField setATextField;
    private JTextField setBTextField;
    private JComboBox<String> operationComboBox;
    private JButton executeButton;

    public TestSetsGUI() {
        setTitle("TestSets GUI");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        setATextField = new JTextField(20);
        setBTextField = new JTextField(20);
        operationComboBox = new JComboBox<>(new String[]{"Create SET A", "Create SET B", "Intersection (A * B)", "Union (A + B)", "Difference (A - B)", "Cardinality of SET A", "Cardinality of SET B", "Check if Subset"});
        executeButton = new JButton("Execute");

        inputPanel.add(new JLabel("Set A: "));
        inputPanel.add(setATextField);
        inputPanel.add(new JLabel("Set B: "));
        inputPanel.add(setBTextField);
        inputPanel.add(operationComboBox);
        inputPanel.add(executeButton);

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);

        executeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performOperation();
            }
        });
    }

    private void saveSets() {
        String setAInput = setATextField.getText();
        String setBInput = setBTextField.getText();
        if (setAInput != null && !setAInput.isEmpty()) {
            setA.clear();
            String[] values = setAInput.split(" ");
            for (String value : values) {
                try {
                    int num = Integer.parseInt(value.trim());
                    setA.add(num);
                } catch (NumberFormatException ex) {
                    // Handle invalid input
                }
            }
        }
        if (setBInput != null && !setBInput.isEmpty()) {
            setB.clear();
            String[] values = setBInput.split(" ");
            for (String value : values) {
                try {
                    int num = Integer.parseInt(value.trim());
                    setB.add(num);
                } catch (NumberFormatException ex) {
                    // Handle invalid input
                }
            }
        }
    }

    private void performOperation() {
        saveSets(); // Save sets before performing any operation

        String selectedOperation = (String) operationComboBox.getSelectedItem();
        if (selectedOperation == null) {
            return;
        }

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
                TestSetsGUI gui = new TestSetsGUI();
                gui.setVisible(true);
            }
        });
    }
}
