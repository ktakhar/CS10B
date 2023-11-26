import java.util.*;

public class ScannerInput extends JFrame implements ActionListener {
    private JLabel prompt = new JLabel("Enter age");
    private JTextarea display = new JTextArea("");
    private JTextField ageField = new JTextField(3);
    private JButton enterAge = new JButton("ENTER");

    private JPanel mainPanel = new JPanel();

    public ScannerInput() {
        super ("Scanner Demo");

        mainPanel.setLayout(new GridLayout (2,2,5,5))
        
        enterAge.addActionListener(this);

        mainPanel.add(prompt);
        mainPanel.add(display);
        mainPanel.add(ageField);
        mainPanel.add(enterAge);

        display.setLineWrap(true);
        setLayout(new BorderLayout());
        add (mainPanel, BorderLayout.NORTH);
        add (new JScrollPane (display), borderLayout.CENTER);
    }
    
    private void ScannerInput(JLabel prompt, JTextField ) {
        Scanner input = new Scanner();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            mainPanel.append(line + "\n");
        }    
    }
}