import javax.swing.*;
import java.awt.*;

public class SwingBasic {
    public static void main(String[] args) {
        JFrame jf = new JFrame("Flow Layout Example");
        jf.setSize(500, 500);
        jf.setLayout(new FlowLayout());
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("Title:");
        jf.add(titleLabel);

        JTextField titleTextField = new JTextField(20);
        jf.add(titleTextField);

        JLabel descriptionLabel = new JLabel("Description:");
        jf.add(descriptionLabel);

        JTextArea descriptionTextArea = new JTextArea(5, 20);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(descriptionTextArea);
        jf.add(scrollPane);

        JButton submitButton = new JButton("Submit");
        jf.add(submitButton);

        jf.setVisible(true);
    }
}
