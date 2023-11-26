public class Write extends JFrame implements ActionListener {
    private JTextArea notePage = new JTextArea("Start writing.");
    private JButton save = new JButton("SAVE");

    JPanel mainPanel = new JPanel();

    public Write() {
        super ("Notes");

        mainPanel.add(JTextArea);
        mainPanel.add(JButton);
    }
}