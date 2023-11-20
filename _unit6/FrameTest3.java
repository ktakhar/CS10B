import javax.swing.*;

class FrameTest3
{
    public static void main (String [] args)
    {
        JFrame frame = new JFrame ("Frame Demo");
        frame.setSize (400, 500);

        JLabel jl = new JLabel ("I am a label!");
        JButton jb = new JButton ("I am a button!");

        frame.add (jl, "North");
        frame.add (jb, "West");

        frame.setVisible (true);
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    }
}