// FifteenPuzzle.java

/**
 * PSET4 - #6
 * 
 * 15-puzzle game with a 4x4 grid of numbered tiles
 * Shuffle button to randomize the tile positions
 * Exit button to close the game
 * Congratulatory message label that appears when the puzzle is solved
 * 
 * @author Kuljit Takhar
 * @version November 11 2023
 * 
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class FifteenPuzzle extends JFrame {
    private static final int ROWS = 4;
    private static final int COLUMNS = 4;
    private static final int TILE_COUNT = ROWS * COLUMNS;

    private JButton[] tiles; // An array to hold the buttons representing the puzzle tiles
    private JButton shuffleButton; // Button to shuffle the tiles
    private JButton exitButton; // Button to exit the game
    private JLabel messageLabel; // Label for displaying congratulatory message

    public FifteenPuzzle() {
        setTitle("15-Puzzle Game");
        setSize(400, 500); // Increased height to accommodate the new buttons
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tiles = new JButton[TILE_COUNT]; // Initialize the array to hold tiles

        // Create a panel for the puzzle grid
        JPanel puzzlePanel = new JPanel(new GridLayout(ROWS, COLUMNS));
        initTiles(); // Initialize the puzzle tiles
        for (JButton tile : tiles) {
            puzzlePanel.add(tile);
        }

        add(puzzlePanel, BorderLayout.CENTER); // Add puzzle panel to the CENTER

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());

        shuffleButton = new JButton("Shuffle"); // Create a "Shuffle" button
        shuffleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shuffleTiles(); // Shuffle the tiles when the button is clicked
                updateUI(); // Update the UI to reflect the shuffled tiles
                messageLabel.setText(""); // Clear the congratulatory message
            }
        });
        buttonPanel.add(shuffleButton); // Add the "Shuffle" button to the panel

        exitButton = new JButton("Exit"); // Create an "Exit" button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the game when the button is clicked
            }
        });
        buttonPanel.add(exitButton); // Add the "Exit" button to the panel

        add(buttonPanel, BorderLayout.SOUTH); // Add button panel to the SOUTH

        messageLabel = new JLabel("", JLabel.CENTER); // Create a label for displaying messages
        add(messageLabel, BorderLayout.NORTH); // Add the message label to the NORTH

        shuffleTiles(); // Shuffle the tiles initially
        updateUI(); // Update the UI to display the shuffled tiles

        for (JButton tile : tiles) {
            tile.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    moveTile(tile); // Handle tile clicks and move them
                    if (isPuzzleSolved()) {
                        messageLabel.setText("Congratulations! You've solved the puzzle.");
                    }
                }
            });
        }
    }

    // Initialize the puzzle tiles with numbers and an empty tile
    private void initTiles() {
        for (int i = 0; i < TILE_COUNT - 1; i++) {
            tiles[i] = new JButton(String.valueOf(i + 1));
        }
        tiles[TILE_COUNT - 1] = new JButton(""); // Empty tile
    }

    // Shuffle the tiles randomly
    private void shuffleTiles() {
        Random random = new Random();
        for (int i = TILE_COUNT - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            JButton temp = tiles[index];
            tiles[index] = tiles[i];
            tiles[i] = temp;
        }
    }

    // Move a tile if it's adjacent to the empty tile
    private void moveTile(JButton tile) {
        int emptyTileIndex = findEmptyTileIndex();
        int tileIndex = findTileIndex(tile);

        if (isAdjacent(emptyTileIndex, tileIndex)) {
            swapTiles(emptyTileIndex, tileIndex);
            updateUI();
        }
    }

    // Find the index of the empty tile
    private int findEmptyTileIndex() {
        for (int i = 0; i < TILE_COUNT; i++) {
            if (tiles[i].getText().isEmpty()) {
                return i;
            }
        }
        return -1;
    }

    // Find the index of a given tile
    private int findTileIndex(JButton tile) {
        for (int i = 0; i < TILE_COUNT; i++) {
            if (tiles[i] == tile) {
                return i;
            }
        }
        return -1;
    }

    // Check if a tile is adjacent to the empty tile
    private boolean isAdjacent(int emptyIndex, int tileIndex) {
        int emptyRow = emptyIndex / COLUMNS;
        int emptyCol = emptyIndex % COLUMNS;
        int tileRow = tileIndex / COLUMNS;
        int tileCol = tileIndex % COLUMNS;

        return Math.abs(emptyRow - tileRow) + Math.abs(emptyCol - tileCol) == 1;
    }

    // Swap the positions of two tiles
    private void swapTiles(int emptyIndex, int tileIndex) {
        JButton temp = tiles[emptyIndex];
        tiles[emptyIndex] = tiles[tileIndex];
        tiles[tileIndex] = temp;
    }

    // Update the UI to reflect the current state of the tiles
    private void updateUI() {
        getContentPane().removeAll();

        // Add puzzle panel
        JPanel puzzlePanel = new JPanel(new GridLayout(ROWS, COLUMNS));
        for (JButton tile : tiles) {
            puzzlePanel.add(tile);
        }
        add(puzzlePanel, BorderLayout.CENTER);

        // Add button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(shuffleButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add message label
        add(messageLabel, BorderLayout.NORTH);

        revalidate();
        repaint();
    }

    // Check if the puzzle is solved
    private boolean isPuzzleSolved() {
        for (int i = 0; i < TILE_COUNT - 1; i++) {
            if (!tiles[i].getText().equals(String.valueOf(i + 1))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FifteenPuzzle().setVisible(true);
        });
    }
}
