import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JTextAreaDemo {
    public static void main(String[] args) {
        JTextAreaDemoWindow jtaDemo = new JTextAreaDemoWindow();
        jtaDemo.setVisible(true);
    }
}

class JTextAreaDemoWindow extends JFrame {

    final int ROWS=8, COLUMNS=25;

    private JTextArea displayArea       = new JTextArea(ROWS, COLUMNS); // 5 rows, 40 colums
    private JButton btnInsertLoremIpsum = new JButton("Insert Lorem Ipsum"),
                    btnToggleLineWrap   = new JButton("Toggle Line Wrap");

    public JTextAreaDemoWindow() {
        layoutComponents();
        addListeners();
    }

    private void layoutComponents() {
        this.displayArea.setEditable( true );
        this.displayArea.setLineWrap( false );
        this.displayArea.setWrapStyleWord( false );

        JScrollPane scrollPane = new JScrollPane( this.displayArea );

        JPanel buttonPanel = new JPanel();
        buttonPanel.add( btnInsertLoremIpsum );
        buttonPanel.add( btnToggleLineWrap );

        this.add( scrollPane, BorderLayout.CENTER );
        this.add( buttonPanel, BorderLayout.SOUTH );

        this.pack();
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setLocationRelativeTo( null );
        this.setTitle( "JTextArea Demo" );
    }

    private void addListeners() {
        btnInsertLoremIpsum.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnInsertLoremIpsumClicked();
                }
            }
        );
        btnToggleLineWrap.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnToggleLineWrapClicked();
                }
            }
        );
    }


    /** Handle the button clicks. */
    public void btnInsertLoremIpsumClicked() {
        String loremIpsum = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
        System.out.printf( "When the button was clicked, JTextArea contained \"%s\"\n", displayArea.getText() );
        displayArea.setText(loremIpsum);
    }

    public void btnToggleLineWrapClicked() {
        displayArea.setLineWrap( !displayArea.getLineWrap() );
    }
}
