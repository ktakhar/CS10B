import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;


// Recursive Koch Snowflake (Doesn't work)
// Solution: 2^n - 1 moves
public class KochSnowflake extends JFrame {

    public KochSnowflake() {
        setTitle("Koch Snowflake");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KochSnowflake snowflake = new KochSnowflake();
            snowflake.setVisible(true);
        });

        // Ask the user for the level of the snowflake
        int depth = getSnowflakeLevel();
        if (depth >= 0) {
            SwingUtilities.invokeLater(() -> {
                KochSnowflake snowflake = new KochSnowflake(depth);
                snowflake.setVisible(true);
            });
        }
    }

    private static int getSnowflakeLevel() {
        try {
            String input = JOptionPane.showInputDialog("Enter the level of the Koch Snowflake:");
            if (input != null) {
                return Integer.parseInt(input);
            }
        } catch (NumberFormatException e) {
            // Handle invalid input
        }
        return -1; // Default value for invalid input or cancel
    }

    private KochSnowflake(int depth) {
        add(new SnowflakePanel(depth));
    }

    private class SnowflakePanel extends JPanel {
        private final int depth;

        public SnowflakePanel(int depth) {
            this.depth = depth;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int sideLength = 600;
            int startX = 100;
            int startY = 500;

            drawKochSnowflake((Graphics2D) g, depth, sideLength, startX, startY);
        }

        private void drawKochSnowflake(Graphics2D g2d, int depth, double length, double x, double y) {
            // ... (rest of the drawKochSnowflake method remains the same)
        }
    }
}
