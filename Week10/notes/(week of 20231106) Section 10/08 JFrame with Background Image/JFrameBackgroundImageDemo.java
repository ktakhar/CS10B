// Demonstrates how to render a backgound image in a JFrame, such that other widgets
// can be layered on top of it.

// The secret is to use the JFrame class's setContentPane method to have a JPanel be the
// backgound layer. That JPanel hosts the backgound image.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class JFrameBackgroundImageDemo {
    public static void main(String [] args) {
        JFrameBackgroundImageDemoWindow jfbidWindow = new JFrameBackgroundImageDemoWindow();
        jfbidWindow.setVisible(true);
    }
}

class JFrameBackgroundImageDemoWindow extends JFrame {

    private JLabel myLabel1 = new JLabel("North"),
                   myLabel2 = new JLabel("South"),
                   myLabel3 = new JLabel("East"),
                   myLabel4 = new JLabel("West"),
                   myLabel5 = new JLabel("Center");

    public JFrameBackgroundImageDemoWindow() {
        layoutComponents();
        addListeners();
    }

    private void layoutComponents() {

        // Set the JFrame's content pane to a JPanel that hosts the backgound image.
        this.setContentPane(new MyBackgroundPanel());

        this.setLayout(new BorderLayout());

        myLabel1.setBorder(BorderFactory.createLineBorder(Color.black));
        myLabel2.setBorder(BorderFactory.createLineBorder(Color.black));
        myLabel3.setBorder(BorderFactory.createLineBorder(Color.black));
        myLabel4.setBorder(BorderFactory.createLineBorder(Color.black));
        myLabel5.setBorder(BorderFactory.createLineBorder(Color.black));

        this.add(myLabel1, BorderLayout.NORTH);
        this.add(myLabel2, BorderLayout.SOUTH);
        this.add(myLabel3, BorderLayout.EAST);
        this.add(myLabel4, BorderLayout.WEST);
        this.add(myLabel5, BorderLayout.CENTER);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("JFrameBackgroundImageDemoWindow");
        this.setSize(400, 400);
    }

    private void addListeners() {
    }

    // This is a JPanel that hosts the backgound image.
    private class MyBackgroundPanel extends JPanel {

        private BufferedImage image;

        public MyBackgroundPanel() {
            try {
                image = ImageIO.read(MyBackgroundPanel.class.getResource("backgroundImage.jpg"));
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }

}
