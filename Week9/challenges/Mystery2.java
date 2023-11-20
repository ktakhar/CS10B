import javax.swing.*;
import java.awt.*;

class Mystery2 {
    public static void main(String[] args) {
        JFrame mystery = new JFrame ("Mystery");
        Font f = new Font("SansSerif", Font.BOLD, 36);
        mystery.setSize (500,500);
       
        
        JPanel topPanel = new JPanel();
        
        JButton b1 = new JButton("Button1");
        JButton b2 = new JButton("Button2");
        
        topPanel.add(b1);
        topPanel.add(b2);

        JButton b3 = new JButton("Center");
        
        b3.setBackground(Color.PINK.darker());

        JPanel bottomPanel = new JPanel(new GridLayout(1,2));

        JLabel l1 = new JLabel("Southwest");
        JLabel l2 = new JLabel("Southeast");

        FlowLayout leftFlowLayout = new FlowLayout(FlowLayout.LEFT);
        JPanel leftPanel = new JPanel(leftFlowLayout);
        leftPanel.add(l1);

        bottomPanel.add(l1);
        bottomPanel.add(l2);

         mystery.setLayout(new BorderLayout());

         mystery.add(topPanel, BorderLayout.NORTH);
         mystery.add(b3, BorderLayout.CENTER);
         mystery.add(bottomPanel, BorderLayout.SOUTH);

         mystery.setVisible(true);
         mystery.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
    }
}