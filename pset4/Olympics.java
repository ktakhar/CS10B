// Olympics.java

/**
 * PSET4 - #9
 * 
 * Displays olympic rings using Swing
 * 
 * @author Kuljit Takhar
 * @version November 8 2023
 */

import javax.swing.*;
import java.awt.*;

public class Olympics extends JFrame {
    /**
     * Constructor for the Olympics class.
     * Sets up the main window for displaying Olympic rings.
     */
    public Olympics() {
        setTitle("Olympic Rings");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        RingsPanel ringsPanel = new RingsPanel();
        add(ringsPanel);

        setVisible(true);
    }

    /**
     * The main method to launch the Olympics application.
     * Creates an instance of the Olympics class and displays the Olympic rings.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Olympics();
        });
    }

    /**
     * Inner class representing the panel where the Olympic rings are drawn.
     */
    class RingsPanel extends JPanel {
        /**
         * Overrides the paintComponent method to draw the Olympic rings.
         *
         * @param g The graphics context to paint on.
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Draw the Olympic rings using the drawRing method
            drawRing(g2d, 50, 100, Color.BLUE);
            drawRing(g2d, 110, 100, Color.BLACK);
            drawRing(g2d, 170, 100, Color.RED);
            drawRing(g2d, 80, 150, Color.YELLOW);
            drawRing(g2d, 140, 150, Color.GREEN);
        }

        /**
         * Draws an Olympic ring at the specified position and with the given color.
         *
         * @param g2d    The Graphics2D context to draw on.
         * @param x      The x-coordinate of the ring's top-left corner.
         * @param y      The y-coordinate of the ring's top-left corner.
         * @param color  The color of the ring.
         */
        private void drawRing(Graphics2D g2d, int x, int y, Color color) {
            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(10));
            g2d.drawOval(x, y, 60, 60);
        }
    }
}
