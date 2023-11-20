import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Aircraft {
    private double x, y;  // Aircraft position
    private double altitude;
    private double speed;
    private double heading;  // Aircraft heading in degrees

    private static final double GRAVITY = 9.8;  // Acceleration due to gravity
    private static final double MAX_SPEED = 300;
    private static final double MIN_SPEED = 50;

    public Aircraft(double x, double y) {
        this.x = x;
        this.y = y;
        this.altitude = 5000;  // Initial altitude
        this.speed = 200;     // Initial speed
        this.heading = 0;     // Initial heading (east)
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAltitude() {
        return altitude;
    }

    public double getSpeed() {
        return speed;
    }

    public double getHeading() {
        return heading;
    }

    public void updatePosition() {
        // Update aircraft position based on speed, heading, etc.
        // This is a simplified model and can be improved for realism
        double newX = x + speed * Math.cos(Math.toRadians(heading));
        double newY = y + speed * Math.sin(Math.toRadians(heading));

        // Check for boundaries
        if (newX >= 0 && newX <= 800 && newY >= 0 && newY <= 600) {
            x = newX;
            y = newY;
        }

        // Simulate descent due to gravity
        altitude -= 0.5 * GRAVITY;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public void setSpeed(double speed) {
        this.speed = Math.max(MIN_SPEED, Math.min(MAX_SPEED, speed));
    }

    public void setHeading(double heading) {
        this.heading = heading;
    }
}

class FlightEnvironment extends JPanel implements ActionListener, KeyListener {
    private Aircraft aircraft;

    public FlightEnvironment() {
        aircraft = new Aircraft(100, 100);  // Initial position

        Timer timer = new Timer(50, this);  // Update every 50 milliseconds
        timer.start();

        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the sky
        g.setColor(new Color(135, 206, 250));  // Light blue
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw the ground
        g.setColor(new Color(34, 139, 34));  // Green
        g.fillRect(0, getHeight() / 2, getWidth(), getHeight() / 2);

        // Draw the aircraft
        int aircraftSize = 20;
        int aircraftX = (int) aircraft.getX();
        int aircraftY = (int) aircraft.getY();
        g.setColor(Color.RED);
        g.fillRect(aircraftX, aircraftY, aircraftSize, aircraftSize);

        // Display altitude and speed
        g.setColor(Color.BLACK);
        g.drawString("Altitude: " + (int) aircraft.getAltitude() + " ft", 10, 20);
        g.drawString("Speed: " + (int) aircraft.getSpeed() + " knots", 10, 40);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        aircraft.updatePosition();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used in this example
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Handle key presses for user control
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                // Increase altitude
                aircraft.setAltitude(aircraft.getAltitude() + 100);
                break;
            case KeyEvent.VK_DOWN:
                // Decrease altitude
                aircraft.setAltitude(aircraft.getAltitude() - 100);
                break;
            case KeyEvent.VK_LEFT:
                // Turn left
                aircraft.setHeading(aircraft.getHeading() - 5);
                break;
            case KeyEvent.VK_RIGHT:
                // Turn right
                aircraft.setHeading(aircraft.getHeading() + 5);
                break;
            case KeyEvent.VK_W:
                // Increase speed
                aircraft.setSpeed(aircraft.getSpeed() + 10);
                break;
            case KeyEvent.VK_S:
                // Decrease speed
                aircraft.setSpeed(aircraft.getSpeed() - 10);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used in this example
    }
}

public class ImprovedFlightSimulator extends JFrame {
    public ImprovedFlightSimulator() {
        setTitle("Improved Flight Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        FlightEnvironment environment = new FlightEnvironment();
        add(environment);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ImprovedFlightSimulator());
    }
}
