// MailLayout.java

/**
 * PSET4 - #2
 * 
 * Mail layout GUI with labeled input fields 
 * Carbon copy (Cc) blind carbon copy (Bcc), subject, and sender's email address. 
 * The "Send" button allows the user to compose and send a message, and the program appends the message details to a text file named "outbox.txt" for record-keeping.
 * 
 * @author Kuljit Takhar
 * @version November 4 2023
 * 
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class MailLayout {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Mail Layout");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel with a BoxLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        // Create a panel for the "Send" button using FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton sendButton = new JButton("Send");
        sendButton.setForeground(Color.BLUE); // Set the button color to blue

        // Create components
        JPanel toPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Align components to the left
        JLabel toLabel = new JLabel("To:");
        JTextField toField = new JTextField(20);
        toPanel.add(toLabel);
        toPanel.add(toField);

        JPanel ccPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Align components to the left
        JLabel ccLabel = new JLabel("Cc:");
        JTextField ccField = new JTextField(20);
        ccPanel.add(ccLabel);
        ccPanel.add(ccField);

        JPanel bccPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Align components to the left
        JLabel bccLabel = new JLabel("Bcc:");
        JTextField bccField = new JTextField(20);
        bccPanel.add(bccLabel);
        bccPanel.add(bccField);

        JPanel subjectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Align components to the left
        JLabel subjectLabel = new JLabel("Subject:");
        JTextField subjectField = new JTextField(20);
        subjectPanel.add(subjectLabel);
        subjectPanel.add(subjectField);

        JPanel fromPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Align components to the left
        JLabel fromLabel = new JLabel("From:");
        String[] fromOptions = {"Beyonce@gmail.com", "BeyonceWork@bmail.com", "NotBeyonce@gmail.com"};
        JComboBox<String> fromComboBox = new JComboBox<>(fromOptions);
        fromPanel.add(fromLabel);
        fromPanel.add(fromComboBox);

        JTextArea messageArea = new JTextArea(10, 20);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String subject = subjectField.getText().trim();
                String title = (subject.isEmpty()) ? "New Message" : subject;
                frame.setTitle(title); // Set the JFrame title

                try (FileWriter writer = new FileWriter("outbox.txt", true)) {
                    writer.write("Subject: " + subject + "\n");
                    writer.write("To: " + toField.getText() + "\n");
                    writer.write("Cc: " + ccField.getText() + "\n");
                    writer.write("Bcc: " + bccField.getText() + "\n");
                    writer.write("From: " + fromComboBox.getSelectedItem() + "\n");
                    writer.write("Message:\n" + messageArea.getText() + "\n\n");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                // Clear fields
                toField.setText("");
                ccField.setText("");
                bccField.setText("");
                subjectField.setText("");
                messageArea.setText("");
            }
        });

        // Add components to the panel
        buttonPanel.add(sendButton); // Add the "Send" button to the top-left of the panel
        panel.add(buttonPanel);
        panel.add(toPanel);
        panel.add(ccPanel);
        panel.add(bccPanel);
        panel.add(subjectPanel);
        panel.add(fromPanel);
        panel.add(new JScrollPane(messageArea));

        // Add the panel to the frame
        frame.add(panel);

        frame.setVisible(true);
    }
}
