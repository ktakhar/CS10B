import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.image.BufferedImage;

public class MarioGame extends JFrame implements ActionListener {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private int playerX = 50;
    private int playerY = 400;
    private int playerVelocityX = 0;
    private int playerVelocityY = 0;
    private boolean isJumping = false;

    private int obstacleX = WIDTH;
    private int obstacleY = 430;
    private int obstacleSpeed = 5;

    private int portalX = 600;
    private int portalY = 370;

    private boolean isGameOver = false;

    private BufferedImage buffer; // Double buffering

    public MarioGame() {
        setTitle("Super Mario Bros. Clone");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        Timer timer = new Timer(16, this); // 60 FPS
        timer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    playerVelocityX = -5;
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    playerVelocityX = 5;
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE && !isJumping && !isGameOver) {
                    jump();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    playerVelocityX = 0;
                }
            }
        });

        setFocusable(true);
        setResizable(false);
    }

    private void jump() {
        if (!isJumping) {
            isJumping = true;
            playerVelocityY = -15;
        }
    }

    private void updateGame() {
        // Update player position
        playerX += playerVelocityX;
        playerY += playerVelocityY;

        // Simulate gravity
        if (playerY < 400) {
            playerVelocityY += 1; // Gravity
        } else {
            playerY = 400;
            playerVelocityY = 0;
            isJumping = false;
        }

        // Update obstacle position
        obstacleX -= obstacleSpeed;

        // Check for collisions with the obstacle
        if (playerX < obstacleX + 30 &&
            playerX + 30 > obstacleX &&
            playerY < obstacleY + 30 &&
            playerY + 30 > obstacleY) {
            isGameOver = true;
        }

        // Reset obstacle if it goes off-screen
        if (obstacleX + 30 < 0) {
            obstacleX = WIDTH;
            isGameOver = false;
        }

        // Boundaries check
        if (playerX < 0) {
            playerX = 0;
        }
        if (playerX > WIDTH - 30) {
            playerX = WIDTH - 30;
        }
    }

    private void drawGame() {
        Graphics2D g = buffer.createGraphics();
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw ground platform
        g.setColor(Color.GREEN);
        g.fillRect(0, 450, WIDTH, 150);

        // Draw green blocks (platforms)
        g.fillRect(100, 350, 100, 20);
        g.fillRect(300, 300, 100, 20);
        g.fillRect(500, 250, 100, 20);

        // Draw green portal pipe
        g.setColor(Color.GREEN);
        g.fillRect(portalX, portalY, 30, 80);

        // Draw player
        g.setColor(Color.RED);
        g.fillRect(playerX, playerY, 30, 30);

        // Draw obstacle
        g.setColor(Color.BLUE);
        g.fillRect(obstacleX, obstacleY, 30, 30);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));

        if (isGameOver) {
            g.drawString("Game Over!", WIDTH / 2 - 100, HEIGHT / 2);
        }

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
        MarioGame game = new MarioGame();
        game.setVisible(true);
    }
}
