// File GridBagDemo.java 
// This program demonstrates the GridBagLayout

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GridBagLayoutDemo extends JFrame 
{ 
   private Container container;
   private GridBagLayout gbLayout;
   private GridBagConstraints gbConstraints; 
    
   public GridBagLayoutDemo ()
   {
      super ("GridBagLayout");

      container = getContentPane ();
      gbLayout = new GridBagLayout ();
      container.setLayout (gbLayout );   

      // instantiate gridbag constraints
      gbConstraints = new GridBagConstraints ();

      Font f = new Font ("Times", Font.BOLD, 24);
      JTextArea ta = new JTextArea ("TextArea1", 5, 10 );
      ta.setFont (f);  
      ta.setBackground (Color.RED);
      JTextArea tx = new JTextArea ("TextArea2", 2, 2 );
      tx.setFont (f);  
      tx.setBackground (Color.GREEN.brighter());
      String names [] = { "Moe", "Larry", "Curly" };
      JComboBox cb = new JComboBox (names );
      JTextField tf = new JTextField ("TextField" );
      JButton b1 = new JButton ("Button 1" );
      b1.setFont( f);  
      b1.setBackground (Color.YELLOW);
      JButton b2 = new JButton("Button 2" );
      b2.setFont (f);  
      b2.setBackground (Color.PINK);
      JButton b3 = new JButton("Button 3" );

      // text area
      // weightx and weighty are both 0: the default
      // anchor for all components is CENTER: the default
      gbConstraints.fill = GridBagConstraints.BOTH;
      addComponent (ta, 0, 0, 1, 3 );    
       
      // button b1
      // weightx and weighty are both 0: the default
      gbConstraints.fill = GridBagConstraints.HORIZONTAL;
      addComponent (b1, 0, 1, 2, 1 );
      
      // combo box
      // weightx and weighty are both 0: the default
      // fill is HORIZONTAL
      addComponent (cb, 2, 1, 2, 1 );             

      // button b2
      gbConstraints.weightx = 1000;  // can grow wider
      gbConstraints.weighty = 1;     // can grow taller
      gbConstraints.fill = GridBagConstraints.BOTH;
      addComponent (b2, 1, 1, 1, 1 );
       
      // button b3
      // fill is BOTH
      gbConstraints.weightx = 0;
      gbConstraints.weighty = 0;    
      addComponent (b3, 1, 2, 1, 1 );
       
      // textfield
      // weightx and weighty are both 0: fill is BOTH
      addComponent (tf, 3, 0, 2, 1 );

      // textarea
      // weightx and weighty are both 0: fill is BOTH
      addComponent (tx, 3, 2, 1, 1 );

      setSize( 400, 350 );
      setVisible(true);
   }

   // addComponent is programmer defined
   private void addComponent (Component c,
                              int row, int column, int width, int height )
   {
      // set gridx and gridy 
      gbConstraints.gridx = column;
      gbConstraints.gridy = row;

      // set gridwidth and gridheight
      gbConstraints.gridwidth = width;   
      gbConstraints.gridheight = height;

      // set constraints
      gbLayout.setConstraints (c, gbConstraints );  
      container.add (c);      // add component 
   }

   public static void main (String [] args )
   {
      GridBagLayoutDemo app = new GridBagLayoutDemo ();
      app.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
   }
   
}

