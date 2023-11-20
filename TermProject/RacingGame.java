import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.image.BufferedImage;

public class RacingGame extends JFrame implements ActionListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int CAR_WIDTH = 60;
    private static final int CAR_HEIGHT = 80;

    private int playerX = WIDTH / 2 - CAR_WIDTH / 2;
    private int playerY = HEIGHT - 100;
    private int playerSpeed = 5;

    private Timer timer;
    private BufferedImage buffer;

    public RacingGame() {
        setTitle("Racing Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        timer = new Timer(16, this); // 60 FPS
        timer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT && playerX > 0) {
                    playerX -= playerSpeed;
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && playerX < WIDTH - CAR_WIDTH) {
                    playerX += playerSpeed;
                }
            }
        });

        setFocusable(true);
        setResizable(false);
    }

    private void updateGame() {
        // Implement game logic here (e.g., move obstacles, check collisions)

        // For simplicity, let's simulate movement by scrolling the background
        playerY -= 5;
        if (playerY <= 0) {
            playerY = HEIGHT - 100;
        }
    }

    private void drawGame() {
        Graphics2D g = buffer.createGraphics();

        // Draw background
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw track
        g.setColor(Color.GRAY);
        g.fillRect(50, 0, WIDTH - 100, HEIGHT);

        // Draw player car
        g.setColor(Color.BLUE);
        g.fillRect(playerX, playerY, CAR_WIDTH, CAR_HEIGHT);

        // Add more drawing for obstacles, opponents, etc.

        Toolkit.getDefaultToolkit().sync(); // Ensure smooth drawing on all systems
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateGame();
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        drawGame();
        g.drawImage(buffer, 0, 0, this);
    }

    public static void main(String[] args) {
        RacingGame game = new RacingGame();
        game.setVisible(true);
    }
}
