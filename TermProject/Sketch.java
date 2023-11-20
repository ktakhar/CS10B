// Sketch.java

/**
 * TERM PROJECT - FALL 2023
 * 
 * 
    The "Sketch Paint App" is a simple Java program that lets users draw on a digital canvas using various colors. 
    It offers a GUI with color selection, canvas clearing, undo feature, and freehand drawing. 
    The DrawingPanel class handles drawing lines, represented by CustomShape for color management.
    Graphical interface is powered by Swing.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

public class Sketch {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Sketch | Paint App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DrawingPanel drawingPanel = new DrawingPanel();
        frame.add(drawingPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        JButton clearButton = new JButton("Clear");
        JButton undoButton = new JButton("Undo");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    drawingPanel.clear();
                } catch (EmptyCanvasException ex) {
                    showErrorDialog("Error", ex.getMessage());
                }
            }
        });

        undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    drawingPanel.undo();
                } catch (EmptyCanvasException ex) {
                    showErrorDialog("Error", ex.getMessage());
                }
            }
        });

        JComboBox<String> colorComboBox = new JComboBox<>(new String[]{"Black", "Red", "Orange", "Yellow", "Green", "Blue", "Purple", "Pink", "Cyan", "Magenta", "Gray"});
        colorComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedColor = (String) colorComboBox.getSelectedItem();
                drawingPanel.setColor(selectedColor);
            }
        });

        controlPanel.add(clearButton);
        controlPanel.add(undoButton);
        controlPanel.add(new JLabel("Select Color:"));
        controlPanel.add(colorComboBox);

        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    private static void showErrorDialog(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
}

class DrawingPanel extends JPanel {
    private List<CustomShape> shapes;
    private Color currentColor;
    private int undoCounter;

    public DrawingPanel() {
        shapes = new ArrayList<>();
        currentColor = Color.BLACK;
        undoCounter = 0;

        MouseAdapter listener = new MouseAdapter() {
            private Path2D.Double currentPath;

            public void mousePressed(MouseEvent e) {
                currentPath = new Path2D.Double();
                currentPath.moveTo(e.getPoint().getX(), e.getPoint().getY());
            }

            public void mouseDragged(MouseEvent e) {
                currentPath.lineTo(e.getPoint().getX(), e.getPoint().getY());
                repaint();
            }

            public void mouseReleased(MouseEvent e) {
                shapes.add(new CustomShape(currentPath, currentColor));
                currentPath = null;
                repaint();
                undoCounter = 0;
            }
        };

        addMouseListener(listener);
        addMouseMotionListener(listener);
    }

    public void setColor(String colorName) {
        switch (colorName) {
            case "Red":
                currentColor = Color.RED;
                break;
            case "Orange":
                currentColor = Color.ORANGE;
                break;
            case "Yellow":
                currentColor = Color.YELLOW;
                break;
            case "Green":
                currentColor = Color.GREEN;
                break;
            case "Blue":
                currentColor = Color.BLUE;
                break;
            case "Purple":
                currentColor = new Color(125, 18, 255);
                break;
            case "Pink":
                currentColor = Color.PINK;
                break;
            case "Cyan":
                currentColor = Color.CYAN;
                break;
            case "Magenta":
                currentColor = Color.MAGENTA;
                break;
            case "Gray":
                currentColor = Color.GRAY;
                break;
            default:
                currentColor = Color.BLACK;
        }
    }

    public void clear() throws EmptyCanvasException {
        if (shapes.isEmpty()) {
            throw new EmptyCanvasException("The canvas is already empty.");
        }
        shapes.clear();
        repaint();
    }

    public void undo() throws EmptyCanvasException {
        if (shapes.isEmpty()) {
            throw new EmptyCanvasException("There are no shapes to undo.");
        }
        if (undoCounter < shapes.size()) {
            shapes.remove(shapes.size() - 1);
            undoCounter++;
            repaint();
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (CustomShape shape : shapes) {
            shape.draw((Graphics2D) g);
        }
    }
}

class CustomShape {
    private Path2D.Double path;
    private Color color;

    public CustomShape(Path2D.Double path, Color color) {
        this.path = path;
        this.color = color;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.draw(path);
    }
}

class EmptyCanvasException extends Exception {
    public EmptyCanvasException(String message) {
        super(message);
    }
}