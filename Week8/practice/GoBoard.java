import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

public class GoBoard {
    private static final int BOARD_SIZE = 9;
    private JFrame frame;
    private JPanel boardPanel;
    private StoneButton[][] boardButtons;

    public GoBoard() {
        frame = new JFrame("Go Board");
        boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        boardButtons = new StoneButton[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                boardButtons[i][j] = new StoneButton();
                boardButtons[i][j].setPreferredSize(new Dimension(50, 50));
                boardPanel.add(boardButtons[i][j]);
            }
        }

        frame.add(boardPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // Initialize the board with initial stones
        initializeBoard();

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                final int row = i;
                final int col = j;
                boardButtons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Handle button click here
                        // Implement stone placement logic
                        // Implement capturing and game rules
                    }
                });
            }
        }
    }

    private void initializeBoard() {
        // Add initial stones to both sides
        placeStone(3, 3, Color.BLACK);
        placeStone(4, 4, Color.BLACK);
        placeStone(3, 4, Color.WHITE);
        placeStone(4, 3, Color.WHITE);

        // You can add more initial stones here as needed
    }

    private void placeStone(int row, int col, Color color) {
        boardButtons[row][col].setStoneColor(color);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GoBoard();
        });
    }

    class StoneButton extends JButton {
        private Color stoneColor;

        public StoneButton() {
            setContentAreaFilled(false);
            setBorderPainted(false);
        }

        public void setStoneColor(Color stoneColor) {
            this.stoneColor = stoneColor;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (stoneColor != null) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setPaint(stoneColor);
                g2d.fill(new Ellipse2D.Double(5, 5, getWidth() - 10, getHeight() - 10));
                g2d.dispose();
            }
        }
    }
}
