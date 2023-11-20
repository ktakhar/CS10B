// BullsEye.java

/**
 * PSET4 - #4
 * 
 * GUI using Swing to draw a bull's eye pattern
 * 
 * @author Kuljit Takhar
 * @version November 8 2023
 * 
 */

import javax.swing.*;
import java.awt.*;

// The BullsEye class represents the main frame of the Bull's Eye application.
public class BullsEye extends JFrame {

    // Constructor for the BullsEye class
    public BullsEye() {
        setTitle("Bull's Eye");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a BullsEyePanel and add it to the frame
        BullsEyePanel bullsEyePanel = new BullsEyePanel();
        add(bullsEyePanel, BorderLayout.CENTER);
    }

    // Inner class representing the panel where the Bull's Eye is displayed
    private class BullsEyePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Center coordinates of the panel
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;

            // Diameter values for each circle of the Bull's Eye
            int outermostDiameter = 200;
            int outerDiameter = 160;
            int middleDiameter = 120;
            int innerDiameter = 80;
            int bullseyeDiameter = 40;

            // Draw the Bull's Eye circles with alternating black and white colors
            g.setColor(Color.BLACK);
            g.fillOval(centerX - outermostDiameter / 2, centerY - outermostDiameter / 2, outermostDiameter, outermostDiameter);

            g.setColor(Color.WHITE);
            g.fillOval(centerX - outerDiameter / 2, centerY - outerDiameter / 2, outerDiameter, outerDiameter);

            g.setColor(Color.BLACK);
            g.fillOval(centerX - middleDiameter / 2, centerY - middleDiameter / 2, middleDiameter, middleDiameter);

            g.setColor(Color.WHITE);
            g.fillOval(centerX - innerDiameter / 2, centerY - innerDiameter / 2, innerDiameter, innerDiameter);

            g.setColor(Color.BLACK);
            g.fillOval(centerX - bullseyeDiameter / 2, centerY - bullseyeDiameter / 2, bullseyeDiameter, bullseyeDiameter);
        }
    }

    // Main method to create and display the Bull's Eye
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BullsEye().setVisible(true);
        });
    }
}
