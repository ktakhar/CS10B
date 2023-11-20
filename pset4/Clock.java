// Clock.java 

/**
 * PSET4 - #10
 * 
 * @author Kuljit Takhar
 * @version November 11 2023
 * 
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Clock extends JFrame {
    private JTextField hoursTextField;
    private JTextField minutesTextField;
    private ClockPanel clockPanel;

    public Clock() {
        setTitle("Analog Clock");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Input components for setting the time
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel hoursLabel = new JLabel("Hours:");
        hoursTextField = new JTextField(2);
        JLabel minutesLabel = new JLabel("Minutes:");
        minutesTextField = new JTextField(2);

        JButton setButton = new JButton("Set Time");
        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
        });

        inputPanel.add(hoursLabel);
        inputPanel.add(hoursTextField);
        inputPanel.add(minutesLabel);
        inputPanel.add(minutesTextField);
        inputPanel.add(setButton);

        // Clock panel
        clockPanel = new ClockPanel();

        // Frame layout
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);

        // Add the clock panel to the center of the frame
        add(clockPanel, BorderLayout.CENTER);

        // Set the initial time to 1:43
        hoursTextField.setText("1");
        minutesTextField.setText("43");
        updateTime();
    }

    private void updateTime() {
        try {
            int hours = Integer.parseInt(hoursTextField.getText());
            int minutes = Integer.parseInt(minutesTextField.getText());

            if (hours >= 0 && hours < 24 && minutes >= 0 && minutes < 60) {
                clockPanel.setClockTime(hours, minutes);
                clockPanel.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid time input. Hours should be between 0 and 23, and minutes between 0 and 59.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers for hours and minutes.");
        }
    }

    private class ClockPanel extends JPanel {
        private int clockHours;
        private int clockMinutes;

        public ClockPanel() {
            // Initialize clock time to the current system time
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            clockHours = calendar.get(java.util.Calendar.HOUR_OF_DAY);
            clockMinutes = calendar.get(java.util.Calendar.MINUTE);
        }

        public void setClockTime(int hours, int minutes) {
            clockHours = hours;
            clockMinutes = minutes;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int radius = Math.min(getWidth(), getHeight()) / 2 - 20;

            // Draw clock face
            g.setColor(Color.WHITE);
            g.fillOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);

            // Calculate angles for clock hands
            double hourAngle = Math.toRadians(90 - ((clockHours % 12) * 30 + (clockMinutes / 60.0) * 30));
            double minuteAngle = Math.toRadians(90 - (clockMinutes * 6));

            // Draw clock hands
            drawClockHand(g, centerX, centerY, hourAngle, radius * 0.5, 8, Color.BLACK); // Hour hand
            drawClockHand(g, centerX, centerY, minuteAngle, radius * 0.8, 4, Color.BLACK); // Minute hand

            // Draw clock center
            g.setColor(Color.BLACK);
            g.fillOval(centerX - 5, centerY - 5, 10, 10);
        }

        private void drawClockHand(Graphics g, int centerX, int centerY, double angle, double length, int thickness, Color color) {
            int x = (int) (centerX + length * Math.cos(angle));
            int y = (int) (centerY - length * Math.sin(angle));

            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(color); // Set the color to black
            g2d.setStroke(new BasicStroke(thickness));
            g2d.drawLine(centerX, centerY, x, y);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Clock().setVisible(true);
        });
    }
}
