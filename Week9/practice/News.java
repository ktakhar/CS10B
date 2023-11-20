import javax.swing.*;
import java.awt.*;

class News {
    public static void main(String[] args) {
        JFrame news = new JFrame("NEWS");
        Font h1 = new Font("SansSerif", Font.BOLD, 36);
        Font h2 = new Font("SansSerif", Font.BOLD, 24);
        Font p = new Font("SansSerif", Font.PLAIN, 16);

        JPanel topPanel = new JPanel();
        
        JLabel title = new JLabel("SF CHRONICLE"); 
        title.setFont(h1);

        topPanel.add(title);

        JPanel middlePanel = new JPanel(new BorderLayout());
        
        JPanel titlePane = new JPanel(new GridLayout(1, 4));

        // Create a panel for each column
        JPanel column1Panel = new JPanel(new BorderLayout());
        JPanel column2Panel = new JPanel(new BorderLayout());
        JPanel column3Panel = new JPanel(new BorderLayout());
        JPanel column4Panel = new JPanel(new BorderLayout());

        JLabel column1 = new JLabel("Extra!");
        JLabel column2 = new JLabel("A Story!");
        JLabel column3 = new JLabel("Sports");
        JLabel column4 = new JLabel("Opinion");

        column1.setFont(h2);
        column2.setFont(h2);
        column3.setFont(h2);
        column4.setFont(h2);

        // Add paragraph labels beneath each column title
        JLabel paragraph1 = new JLabel("This is the paragraph for Extra.");
        JLabel paragraph2 = new JLabel("This is the paragraph for A Story.");
        JLabel paragraph3 = new JLabel("This is the paragraph for Sports.");
        JLabel paragraph4 = new JLabel("This is the paragraph for Opinion.");

        paragraph1.setFont(p);
        paragraph2.setFont(p);
        paragraph3.setFont(p);
        paragraph4.setFont(p);

        column1Panel.add(column1, BorderLayout.NORTH);
        column1Panel.add(paragraph1, BorderLayout.CENTER);

        column2Panel.add(column2, BorderLayout.NORTH);
        column2Panel.add(paragraph2, BorderLayout.CENTER);

        column3Panel.add(column3, BorderLayout.NORTH);
        column3Panel.add(paragraph3, BorderLayout.CENTER);

        column4Panel.add(column4, BorderLayout.NORTH);
        column4Panel.add(paragraph4, BorderLayout.CENTER);

        titlePane.add(column1Panel);
        titlePane.add(column2Panel);
        titlePane.add(column3Panel);
        titlePane.add(column4Panel);

        middlePanel.add(titlePane, BorderLayout.NORTH);

        news.getContentPane().add(topPanel, BorderLayout.NORTH);
        news.getContentPane().add(middlePanel, BorderLayout.CENTER);
        
        news.setSize(750, 500);
        news.setVisible(true);
        news.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
