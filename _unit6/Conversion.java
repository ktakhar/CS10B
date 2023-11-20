import  javax.swing.*;

public class ConvertFeetToMeters
{
    public static void main (String [] args)
    {
        final double FEET_TO_METERS = 0.3048;
        String feetString = JOptionPane.showInputDialog (null, "How many FEET to convert into meters?");
        double feet = Double.parseDouble(feetString);

        JOptionPane.showMessageDialog (null, feetString + " feet is equal to " + FEET_TO_METERS*feet + " meters!");
     }
}