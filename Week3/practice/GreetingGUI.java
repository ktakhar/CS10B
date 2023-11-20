import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GreetingGUI {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Greeting Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        // Create a panel
        JPanel panel = new JPanel();
        frame.add(panel);

        // Create a label
        JLabel nameLabel = new JLabel("Enter your name:");
        panel.add(nameLabel);

        // Create a text field
        JTextField nameField = new JTextField(20);
        panel.add(nameField);

        // Create a button
        JButton greetButton = new JButton("Greet");
        panel.add(greetButton);

        // Add an action listener to the button
        greetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                JOptionPane.showMessageDialog(null, "Hello " + name + "!");
            }
        });

        // Set the frame to be visible
        frame.setVisible(true);
    }
}
