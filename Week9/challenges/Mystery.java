import java.awt.*;
import javax.swing.*;

public class Mystery {
    public static void main(String[] args) {
        // Create a JFrame (window) with the title "Layout Management"
        JFrame frame = new JFrame("Layout Management");

        // Create a Font object with SansSerif font, bold, and size 36
        Font f = new Font("SansSerif", Font.BOLD, 36);

        // Create a JPanel for the top section with a default FlowLayout
        JPanel topPanel = new JPanel(); // Use the default FlowLayout

        // Create two JButtons named "Button 1" and "Button 2"
        JButton b1 = new JButton("Button 1");
        JButton b2 = new JButton("Button 2");

        // Add the two buttons to the topPanel
        topPanel.add(b1);
        topPanel.add(b2);

        // Create a JButton named "Center"
        JButton b3 = new JButton("Center");

        // Set the background color of the "Center" button to a darker shade of PINK
        b3.setBackground(Color.PINK.darker());

        // Create a JPanel for the bottom section with a GridLayout
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));

        // Create two JLabels with text "Southwest" and "Southeast"
        JLabel l1 = new JLabel("Southwest");
        JLabel l2 = new JLabel("Southeast");

        // Create FlowLayout for l1 with left alignment
        FlowLayout leftFlowLayout = new FlowLayout(FlowLayout.LEFT);
        JPanel leftPanel = new JPanel(leftFlowLayout);
        leftPanel.add(l1);

        // Create FlowLayout for l2 with right alignment
        FlowLayout rightFlowLayout = new FlowLayout(FlowLayout.RIGHT);
        JPanel rightPanel = new JPanel(rightFlowLayout);
        rightPanel.add(l2);

        // Add the left and right panels to the bottomPanel
        bottomPanel.add(leftPanel);
        bottomPanel.add(rightPanel);

        // Set the layout manager for the frame to BorderLayout
        frame.setLayout(new BorderLayout());

        // Add components to the frame with specific layout constraints
        frame.add(topPanel, BorderLayout.NORTH);      // Add topPanel to the North region
        frame.add(b3, BorderLayout.CENTER);           // Add b3 to the Center region
        frame.add(bottomPanel, BorderLayout.SOUTH);   // Add bottomPanel to the South region

        // Set the size of the frame to 400x400 pixels
        frame.setSize(400, 400);

        // Make the frame visible
        frame.setVisible(true);

        // Close the application when the frame is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
