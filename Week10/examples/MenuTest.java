// MenuTest.java
// Demonstrating how MENUs work
// Also illustrates Radio Buttons and CheckBox Items
// Last modified February 11, 2019

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MenuTest
{
   public static void main (String [] args)
   {
      FrameWithMenu f = new FrameWithMenu();
      f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
   }
}

class FrameWithMenu extends JFrame
{
   private Color colorValues [] =
      { Color.BLACK, Color.BLUE, Color.RED, Color.GREEN };
   private JRadioButtonMenuItem colorItems[], fonts[];
   private JCheckBoxMenuItem styleItems[];
   private JLabel display;
   private ButtonGroup fontGroup, colorGroup;
   private int style;

   public FrameWithMenu()
   {
      super("Using JMenus");

      JMenuBar bar = new JMenuBar();    // create menubar
      setJMenuBar (bar);                // set the menubar for the JFrame
      Font f = new Font ("SansSerif", Font.BOLD, 24);
      // create File menu and Exit menu item
      JMenu fileMenu = new JMenu ("File");
      fileMenu.setFont(f);
      fileMenu.setMnemonic ('F');
      JMenuItem aboutItem = new JMenuItem ("About...");
      aboutItem.setFont(f);
      aboutItem.setMnemonic ('A');
      aboutItem.addActionListener      (
         new ActionListener()
          {
            public void actionPerformed (ActionEvent e)
             {
               JOptionPane.showMessageDialog (null,
                  "This is an example\nof using MENUs",
                  "About", JOptionPane.PLAIN_MESSAGE);
             }
          }
                                       );
      fileMenu.add (aboutItem);

      JMenuItem exitItem = new JMenuItem ("Exit");
      exitItem.setMnemonic ('x');
      exitItem.setFont(f);
      exitItem.addActionListener (
         new ActionListener()
          {
            public void actionPerformed (ActionEvent e)
             {
               System.exit (0);
             }
          }
                                 );
      fileMenu.add (exitItem);
      bar.add (fileMenu);    // add File menu

      // create the Format menu, its submenus and menu items
      JMenu formatMenu = new JMenu ("Format");
      formatMenu.setForeground (Color.BLUE.darker());
      formatMenu.setFont(f);
      formatMenu.setMnemonic ('r');

      // create Color submenu
      String [] colors  = { "Black", "Blue", "Red", "Green" };
      JMenu colorMenu = new JMenu ("Color");
      colorMenu.setFont (f);
      colorMenu.setMnemonic ('C');
      colorItems = new JRadioButtonMenuItem [colors.length];
      colorGroup = new ButtonGroup ();
      ItemHandler itemHandler = new ItemHandler ();

      for (int i = 0; i < colors.length; i++)
      {
         colorItems [i] =
            new JRadioButtonMenuItem (colors [i]);
         colorMenu.add (colorItems [i]);
         colorItems[i].setFont(f);
         colorGroup.add (colorItems [i]);
         colorItems [i].addActionListener (itemHandler);
      }

      colorItems[ 0 ].setSelected (true);
      formatMenu.add (colorMenu);
      formatMenu.addSeparator();

      // create Font submenu
      String fontNames[] = { "Serif", "MonoSpaced", "SansSerif" };
      JMenu fontMenu = new JMenu ("Font");
      fontMenu.setFont(f);
      fontMenu.setMnemonic ('n');
      fonts = new JRadioButtonMenuItem [fontNames.length];
      fontGroup = new ButtonGroup ();

      for (int i = 0; i < fonts.length; i++)
      {
          fonts[ i ] = new JRadioButtonMenuItem (fontNames[i]);
          fonts[i].setFont(f);
          fontMenu.add (fonts [i]);
          fontGroup.add (fonts [i]);
          fonts [i].addActionListener (itemHandler);
      }

      fonts [0].setSelected (true);
      fontMenu.addSeparator();

      String styleNames[] = { "Bold", "Italic" };
      styleItems = new JCheckBoxMenuItem[ styleNames.length ];
      StyleHandler styleHandler = new StyleHandler();

      for (int i = 0; i < styleNames.length; i++)
      {
         styleItems[ i ] =
            new JCheckBoxMenuItem (styleNames[ i ]);
         styleItems[i].setFont(f);
         fontMenu.add (styleItems[ i ]);
         styleItems [i].addItemListener (styleHandler);
      }

      formatMenu.add (fontMenu);
      bar.add (formatMenu);    // add Format menu

      display = new JLabel(
         "All Your Base Are Belong to Us", SwingConstants.CENTER);
      display.setForeground (colorValues[ 0 ]);
      display.setFont (new Font ("SansSerif", Font.PLAIN, 72));

      getContentPane().setBackground (Color.YELLOW);
      add (display, BorderLayout.CENTER);

      setSize (600, 300);
      setVisible (true);
   }


   class ItemHandler implements ActionListener
   {
      public void actionPerformed (ActionEvent e)
      {
         for (int i = 0; i < colorItems.length; i++)
            if (colorItems[ i ].isSelected())
              {
                display.setForeground (colorValues[ i ]);
                break;
              }

         for (int i = 0; i < fonts.length; i++)
         {
            if (e.getSource() == fonts [i])
              {
                display.setFont (new Font (
                    fonts[ i ].getText(), style, 72));
                break;
              }
         }
         repaint();
      }
   }

   class StyleHandler implements ItemListener
   {
      public void itemStateChanged (ItemEvent e)
      {
         style = 0;
         if (styleItems [0].isSelected()) style += Font.BOLD;
         if (styleItems [1].isSelected()) style += Font.ITALIC;
         display.setFont (new Font (
               display.getFont().getName(), style, 72));
         repaint();
      }
   }
}