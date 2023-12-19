// MyPanel()
// {
//     addMouseListener(this); // Register the panel as a mouse listener
// }

// public void actionPerformed(ActionEvent e)
// {
//     if (e.getSource() == circleButton)
//     {
//         circleOrSquare = false; // Set to draw circles
//     }
//     else if (e.getSource() == squareButton)
//     {
//         circleOrSquare = true; // Set to draw squares
//     }
// }

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class Prob7Frame extends JFrame {
    JButton circleButton = new JButton("Press to Draw CIRCLES");
    JButton squareButton = new JButton("Press to Draw SQUARES");
    MyPanel mp = new MyPanel();
    boolean circleOrSquare = false;
    int xValue, yValue;

    public Prob7Frame(String name, int h, int w) {
        setTitle(name);
        setSize(h, w);
        Container c = getContentPane();
        ButtonListener bl = new ButtonListener();
        circleButton.addActionListener(bl);
        squareButton.addActionListener(bl);
        c.add(mp, BorderLayout.CENTER);
        c.add(squareButton, BorderLayout.SOUTH);
        c.add(circleButton, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    class MyPanel extends JPanel implements MouseListener {
        MyPanel() {
            addMouseListener(this);
        }

        public void mouseClicked(MouseEvent e) {
            Graphics g = getGraphics();
            xValue = e.getX();
            yValue = e.getY();

            if (circleOrSquare)
                g.fillRect(xValue, yValue, 20, 20);
            else
                g.fillOval(xValue, yValue, 20, 20);
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == circleButton) {
                circleOrSquare = false; // Set to draw circles
            } else if (e.getSource() == squareButton) {
                circleOrSquare = true; // Set to draw squares
            }
        }
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            Prob7Frame f = new Prob7Frame("blah, blah", 500, 500);
        });
    }
}

