import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class PongGame extends JPanel implements ActionListener {
    private int ballX, ballY; // Ball's position
    private int ballSpeedX = 2, ballSpeedY = 2; // Ball's movement speed
    private int playerY; // Player's paddle position
    private int opponentY; // Opponent's paddle position
    private int playerSpeed = 12; // Increased player's paddle speed
    private int opponentSpeed = 2; // Opponent's paddle speed
    private int playerScore = 0;
    private int opponentScore = 0;

    public PongGame() {
        Timer timer = new Timer(10, this); // Set up a timer to update the game
        timer.start();
        ballX = 300;
        ballY = 200;
        playerY = 150;
        opponentY = 150;

        // Increase the player's paddle speed
        playerSpeed = 12;  // Adjust this value as needed

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W && playerY > 0) {
                    playerY -= playerSpeed;
                }
                if (e.getKeyCode() == KeyEvent.VK_S && playerY + 100 < getHeight()) {
                    playerY += playerSpeed;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP && playerY > 0) {
                    playerY -= playerSpeed;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN && playerY + 100 < getHeight()) {
                    playerY += playerSpeed;
                }
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
    }

    private void drawGame(Graphics g) {
        // Draw background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw paddles
        g.setColor(Color.WHITE);
        g.fillRect(20, playerY, 10, 100); // Player's paddle
        g.fillRect(getWidth() - 30, opponentY, 10, 100); // Opponent's paddle

        // Draw the ball
        g.setColor(Color.WHITE);
        g.fillOval(ballX, ballY, 20, 20);

        // Draw scores
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.drawString("Player: " + playerScore, 50, 50);
        g.drawString("Opponent: " + opponentScore, getWidth() - 250, 50);
    }

    private void updateGame() {
        ballX += ballSpeedX;
        ballY += ballSpeedY;

        // Ball collision with top and bottom of the screen
        if (ballY <= 0 || ballY >= getHeight() - 20) {
            ballSpeedY = -ballSpeedY;
        }

        // Ball collision with paddles
        if (ballX <= 30 && ballY >= playerY && ballY <= playerY + 100) {
            ballSpeedX = -ballSpeedX;
            increaseBallSpeed();
        }

        if (ballX >= getWidth() - 50 && ballY >= opponentY && ballY <= opponentY + 100) {
            ballSpeedX = -ballSpeedX;
            increaseBallSpeed();
        }

        // Opponent AI (simple following of the ball)
        if (opponentY + 50 < ballY) {
            opponentY += opponentSpeed;
        } else {
            opponentY -= opponentSpeed;
        }

        // Ball out of bounds
        if (ballX <= 0 || ballX >= getWidth() - 20) {
            ballX = 300;
            ballY = 200;
            ballSpeedX = -ballSpeedX;
        }
    }

    private void increaseBallSpeed() {
        if (ballSpeedX > 0) {
            ballSpeedX += 0.5;
        } else {
            ballSpeedX -= 0.5;
        }

        if (ballSpeedY > 0) {
            ballSpeedY += 0.5;
        } else {
            ballSpeedY -= 0.5;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateGame();
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong Game");
        PongGame game = new PongGame();
        frame.add(game);
        frame.setSize(640, 480);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
