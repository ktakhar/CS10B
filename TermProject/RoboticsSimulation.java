import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

class Robot {
    private int x, y;

    public Robot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }
}

class RobotEnvironment extends JPanel {
    private ArrayList<Robot> robots;

    public RobotEnvironment() {
        robots = new ArrayList<>();
        initializeRobots();

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveRobots();
                repaint();
            }
        });
        timer.start();
    }

    private void initializeRobots() {
        // Create some initial robots at random positions
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            robots.add(new Robot(x, y));
        }
    }

    private void moveRobots() {
        // Implement robot movement and interaction logic here
        // For simplicity, let's just move the robots randomly
        Random random = new Random();
        for (Robot robot : robots) {
            int newX = robot.getX() + random.nextInt(3) - 1; // Move left, right, or stay
            int newY = robot.getY() + random.nextInt(3) - 1; // Move up, down, or stay

            // Ensure the robot stays within the grid boundaries (0-9)
            newX = Math.max(0, Math.min(9, newX));
            newY = Math.max(0, Math.min(9, newY));

            robot.move(newX, newY);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the robots on the grid
        for (Robot robot : robots) {
            int cellSize = getWidth() / 10;
            int robotX = robot.getX() * cellSize;
            int robotY = robot.getY() * cellSize;
            g.setColor(Color.BLUE);
            g.fillRect(robotX, robotY, cellSize, cellSize);
        }
    }
}

public class RoboticsSimulation extends JFrame {
    private RobotEnvironment environment;

    public RoboticsSimulation() {
        setTitle("Robotics Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        environment = new RobotEnvironment();
        add(environment);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RoboticsSimulation();
            }
        });
    }
}
