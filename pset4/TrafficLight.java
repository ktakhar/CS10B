// TrafficLight.java

/**
 * PSET4 - #3
 * Swing application representing a traffic light
 * Selecting light by mouse click enhances brightness of color
 *
 * @author Kuljit Takhar
 * @version November 10 2023
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// The TrafficLight class represents the main frame of the traffic light application.
public class TrafficLight extends JFrame {

    // Brightness values for each light (red, yellow, and green)
    private int redBrightness = 100;
    private int yellowBrightness = 100;
    private int greenBrightness = 255;

    // Constructor for the TrafficLight class
    public TrafficLight() {
        setTitle("Traffic Light");
        setSize(600, 700); // Set the initial frame size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a TrafficLightPanel and add it to the frame
        TrafficLightPanel lightPanel = new TrafficLightPanel();
        add(lightPanel, BorderLayout.CENTER);

        // Add a mouse listener to the panel to handle mouse clicks
        lightPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle mouse click to change the brightness of each light
                if (e.getY() < lightPanel.getHeight() / 3) {
                    // If the top third of the panel is clicked, brighten the red light
                    redBrightness = 255;
                    yellowBrightness = 150;
                    greenBrightness = 150;
                } else if (e.getY() < (2 * lightPanel.getHeight()) / 3) {
                    // If the middle third is clicked, brighten the yellow light
                    yellowBrightness = 255;
                    redBrightness = 150;
                    greenBrightness = 150;
                } else {
                    // If the bottom third is clicked, brighten the green light
                    greenBrightness = 255;
                    redBrightness = 150;
                    yellowBrightness = 150;
                }
                lightPanel.repaint(); // Repaint the panel to reflect the changes
            }
        });
    }

    // Inner class representing the panel where the traffic light is displayed
    private class TrafficLightPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int width = getWidth();
            int height = getHeight();
            int circleDiameter = Math.min(width, height) / 3;
            int centerX = width / 2 - circleDiameter / 2;

            g.setColor(Color.LIGHT_GRAY); // Background color
            g.fillRect(0, 0, width, height);

            // Draw the three colored circles representing the traffic light
            g.setColor(new Color(255, 0, 0, redBrightness)); // Red light
            g.fillOval(centerX, 10, circleDiameter, circleDiameter);

            g.setColor(new Color(255, 255, 0, yellowBrightness)); // Yellow light
            g.fillOval(centerX, circleDiameter + 10, circleDiameter, circleDiameter);

            g.setColor(new Color(0, 255, 0, greenBrightness)); // Green light
            g.fillOval(centerX, 2 * circleDiameter + 10, circleDiameter, circleDiameter);
        }
    }

    // Main method to create and display the traffic light
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TrafficLight().setVisible(true); // Create and display the traffic light
        });
    }
}
