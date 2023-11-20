import javax.swing.*;

class Age {
    public static void main(String[] args) {
        String ageText = JOptionPane.showInputDialog(null, "How old are you?");
        int age = Integer.parseInt(ageText);

        if (age < 40) {
            JOptionPane.showMessageDialog(null, "Enjoy it!");
        } else JOptionPane.showMessageDialog(null, "Time flies.");
     System.exit(0);
    }
}