import javax.swing.*;

public class DemoOptionPane {
    public static void main(String[] args) {

        // Read user's name graphically
        String name = JOptionPane.showInputDialog(null, "What's your name?");

        // Ask the user a yes/no question
        int choice = JOptionPane.showConfirmDialog(null,
                "Do you like the Red Sox, " + name + "?");

        // Show different responses depending on the answer
        if (choice == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Of course! Who doesn't?");
        } else { // choice == NO_OPTION or CANCEL_OPTION
            JOptionPane.showMessageDialog(null, "We'll have to agree to disagree, ignoramus!");
        }
    }
}
