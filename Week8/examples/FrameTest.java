import javax.swing.*;

class FrameTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Frame Demo"); // Use 'JFrame' instead of 'Frame'
        frame.setSize(300, 320);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
