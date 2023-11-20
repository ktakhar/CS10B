import javax.swing.*;

class FrameTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Frame Demo"); // Use 'JFrame' instead of 'Frame'
        frame.setSize(300, 320);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = 1; i <=15; i++) {
            frame.setLocation (rint (30, 300), rint (40, 200));
            try {
                Thread.sleep(900) ;
            } catch (Exception e) {};
        }
        frame.setSize (250, 275);
    }

    static int rint (int a, int b) {
        return a + (int) (Math.random() * (b-a+1));
    }
}
