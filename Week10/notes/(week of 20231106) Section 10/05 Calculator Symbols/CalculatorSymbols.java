// Demonstrates how calculator buttons might use particular unicode characters to represent special symbols.
// Sometimes the button's text needs to be a concatenation of two characters.
// The 1/x representation is "rough" but it's the best I could do.

// When you click on a button, the terminal window shows what my program says should be fed to feedChar.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorSymbols {
    public static void main(String [] args) {
        CalculatorSymbolsFrame calculatorSymbolsFrame = new CalculatorSymbolsFrame();
        calculatorSymbolsFrame.setVisible(true);
    }
}


class CalculatorSymbolsFrame extends JFrame {

    // A CalculatorButton is a JButton that has a String representing the button's text and a character
    // that represents what gets passed to feedChar when the button is clicked.
    class CalculatorButton extends JButton {
        private char charToPassToFeedchar;
        private String stringToDisplayOnButton;
        private char [] unicodeChars;

        private CalculatorButton ( char charToPassToFeedchar, String stringToDisplayOnButton, char ... unicodeChars ) {
            this.charToPassToFeedchar = charToPassToFeedchar;
            this.stringToDisplayOnButton = stringToDisplayOnButton;
            this.unicodeChars = new char[unicodeChars.length];

            for ( int i=0; i<unicodeChars.length; i++ ) {
                this.unicodeChars[i] = unicodeChars[i];
            }

            this.setText( stringToDisplayOnButton );
            this.setActionCommand( stringToDisplayOnButton );
        }
    }

    // These are the "special" characters
    private final char squareRootChar             = '\u221A';
    private final char plusMinusSign              = '\u00B1';
    private final char divisionSign               = '\u00F7';
    private final char multiplicationSign         = '\u00D7';
    private final char superscriptTwo             = '\u00B2';
    private final char fractionNumeratorOne       = '\u215F';

    private final char latinSmallLetterX          = '\u0078';
    private final char modifierLetterCrossAccent  = '\u02DF';
    private final char modifierLetterSmallX       = '\u02E3';
    private final char subscriptX                 = '\u0353';
    private final char combiningLatinSmallLetterX = '\u036F';
    private final char greekCapitalLetterChi      = '\u03A7';
    private final char modifierLetterSmallChi     = '\u1D61';
    private final char latinSubscriptSmallLetterX = '\u2093';
    private final char vectorOrCrossProduct       = '\u2A2F';
    private final char fullwidthLatinSmallLetterX = '\uFF58';
    private final char MSReferenceSpecialtyOneOverN = '\u5F8C';

    // calculatorButtons is an array of JButtons (errr, CalculatorButton objects, but a CalculatorButton is a JButton)
    private CalculatorButton calculatorButtons [] = {
        new CalculatorButton( squareRootChar,       ""  + squareRootChar, squareRootChar ),
        new CalculatorButton( plusMinusSign,        ""  + plusMinusSign, plusMinusSign ),
        new CalculatorButton( divisionSign,         ""  + divisionSign, divisionSign ),
        new CalculatorButton( multiplicationSign,   ""  + multiplicationSign, multiplicationSign ),

        new CalculatorButton( superscriptTwo,       ""  + latinSmallLetterX + superscriptTwo, latinSmallLetterX, superscriptTwo ),
        new CalculatorButton( superscriptTwo,       ""  + vectorOrCrossProduct + superscriptTwo, vectorOrCrossProduct, superscriptTwo ),

        new CalculatorButton( fractionNumeratorOne, ""  + fractionNumeratorOne + latinSubscriptSmallLetterX, fractionNumeratorOne, latinSubscriptSmallLetterX ),
        new CalculatorButton( fractionNumeratorOne, ""  + fractionNumeratorOne + vectorOrCrossProduct, fractionNumeratorOne, vectorOrCrossProduct ),
        new CalculatorButton( fractionNumeratorOne, ""  + fractionNumeratorOne + subscriptX, fractionNumeratorOne, subscriptX ),
        new CalculatorButton( fractionNumeratorOne, ""  + fractionNumeratorOne + latinSmallLetterX, fractionNumeratorOne, latinSmallLetterX ),
        new CalculatorButton( fractionNumeratorOne, ""  + fractionNumeratorOne + modifierLetterSmallX, fractionNumeratorOne, modifierLetterSmallX ),
        new CalculatorButton( fractionNumeratorOne, ""  + fractionNumeratorOne + fullwidthLatinSmallLetterX, fractionNumeratorOne, fullwidthLatinSmallLetterX ),
        new CalculatorButton( fractionNumeratorOne, ""  + fractionNumeratorOne + modifierLetterCrossAccent, fractionNumeratorOne, modifierLetterCrossAccent ),
        new CalculatorButton( fractionNumeratorOne, ""  + fractionNumeratorOne + combiningLatinSmallLetterX, fractionNumeratorOne, combiningLatinSmallLetterX ),
        new CalculatorButton( fractionNumeratorOne, ""  + fractionNumeratorOne + greekCapitalLetterChi, fractionNumeratorOne, greekCapitalLetterChi ),
      //new CalculatorButton( fractionNumeratorOne, ""  + fractionNumeratorOne + modifierLetterSmallChi, fractionNumeratorOne, modifierLetterSmallChi ),
        new CalculatorButton( fractionNumeratorOne, ""  + fractionNumeratorOne + multiplicationSign, fractionNumeratorOne, multiplicationSign ),
        new CalculatorButton( fractionNumeratorOne, ""  + MSReferenceSpecialtyOneOverN, fractionNumeratorOne, MSReferenceSpecialtyOneOverN )
    };

    public CalculatorSymbolsFrame() {
        layoutComponents();
        addListeners();
    }

    private void layoutComponents() {
        JPanel jpanel = new JPanel( new GridLayout( 5, 4 ) );
        for ( CalculatorButton calculatorButton : calculatorButtons ) {
            jpanel.add( calculatorButton );
        }

        this.setLayout( new BorderLayout() );
        this.add( jpanel, BorderLayout.NORTH );
        this.pack();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Calculator Symbols");
    }

    private void addListeners() {
        for ( CalculatorButton calculatorButton: calculatorButtons ) {
            calculatorButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed( ActionEvent ae ) {
                        calculatorButtonClicked( ae );
                    }
                }
            );
        }
    }

    public void calculatorButtonClicked( ActionEvent ae ) {
        CalculatorButton calculatorButton        = (CalculatorButton) ae.getSource();
        int              charToPassToFeedchar    = calculatorButton.charToPassToFeedchar;
        char []          unicodeChars            = calculatorButton.unicodeChars;
        String           stringToDisplayOnButton = calculatorButton.stringToDisplayOnButton;

        String unicodeCharToPassToFeedchar = toUnicodeString( charToPassToFeedchar );

        String codePointNames = toNameAndValue( unicodeChars[0] );
        for ( int i=1; i<calculatorButton.unicodeChars.length; i++ ) {
            codePointNames += " + " + toNameAndValue( unicodeChars[i] );
        }

        System.out.printf( "\nButton text is %s\nchar that I would pass to feedChar: %s.\n", codePointNames, unicodeCharToPassToFeedchar );
    }

    // Returns a String that is the characters unicode name followed by its parenthesized hex value.
    private static String toNameAndValue( int codePoint ) {
        return String.format( "%s (%s)", toUnicodeString( codePoint ), Character.getName( codePoint ) );
    }

    // Returns a String that is the unicode representation of the character.
    private static String toUnicodeString( int codePoint ) {
        String unicodeString = "";
        if ( codePoint >= 0xFFF )     { unicodeString =         Integer.toHexString(codePoint).toUpperCase(); }
        else if ( codePoint >= 0xFF ) { unicodeString = "0"   + Integer.toHexString(codePoint).toUpperCase(); }
        else if ( codePoint >= 0xF )  { unicodeString = "00"  + Integer.toHexString(codePoint).toUpperCase(); }
        else if ( codePoint >= 0 )    { unicodeString = "000" + Integer.toHexString(codePoint).toUpperCase(); }
        return String.format( "\'\\u%s\'", unicodeString );
    }
}
