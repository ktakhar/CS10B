import javax.swing.*;
import java.awt.*;

class Swing {
    public static void main (String[] args) {
        JFrame jf = new JFrame("Swing!");
        Font h1 = new Font("Helvetica", Font.BOLD, 36);
        Font h2 = new Font("Helvetica", Font.PLAIN, 24);
        jf.setLayout(new BorderLayout());
        jf.setSize(500,500);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel topLabel = new JLabel("TOP");
        topLabel.setFont(h1);

        JButton b1 = new JButton("CLICKY");
        b1.setFont(h2);
        b1.setForeground(Color.GREEN.darker());
        b1.setSize(new Dimension(200, 50));
        b1.setHorizontalTextPosition(JButton.CENTER);

        jf.add(topLabel, BorderLayout.NORTH);
        jf.add(b1, BorderLayout.WEST);
    }

}