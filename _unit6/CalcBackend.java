// Implement your calculator math logic in this class.

// You MUST initialize the calculator's state in the zero-arg constructor. You MUST NOT
// change feedChar's and getDisplayVal's signature or functionality. You MUST NOT have
// ANY other non-private members of this class.

// I will test your CalcBackend class via an expanded version of CalcBackendTest.java,
// which will create an instance of CalcBackend with the zero-arg constructor, pass
// simulated button clicks via feedChar, and retrieve resulting display Strings with
// getDisplayVal.

// Note that when I test your CalcBackend class, I am ONLY using your feedChar and
// getDisplayVal methods. That means that my tests COMPLETELY bypass your Calculator
// class. That's because  your Calculator class is not supposed to have ANY involvement
// with your calculator's computations. Your Calculator class should ONLY layout the
// calculator's JFrame, attach listeners, feed button clicks to CalcBackend via feedChar
// and then update the calculator's display via getDisplayVal.

// I would appreciate it if you would include comments like these (but with your actual char
// values) in CalcBackend.java, because it will let me test your code without investigating
// to see what character is passed to feedChar for the various operators:

//@@ CLEARCHAR =          'C',
//@@ SQRTCHAR  =          '\u221A',
//@@ MULTIPLICATIONCHAR = '*',
//@@ DIVISIONCHAR =       '/',
//@@ ADDITIONCHAR =       '+',
//@@ SUBTRACTIONCHAR =    '-';


public class CalcBackend {

    // Variables defining calculator's internal state
    double displayVal; // Always contains double value matching GUI's display
    ...

    // Zero-arg constructor initializes calculator's state
    public CalcBackend() {
        ...
    }

    // feedChar is called by GUI to tell CalcBackend that a particular button was clicked
    public void feedChar(char c) {
        // Everytime feedChar is called, it must update the double value representing
        // what should currently be displayed in response to the clicked button. So the
        // CalcBackend "business logic" originates in feedChar.
    }

    // getDisplayVal is called by GUI right after GUI called feedChar,
    // to get the String that the GUI should display.
    public String getDisplayVal() {
        String displayString = "" + this.displayVal;
        ...
        // Adjust displayString as necessary, say to show multiple
        // trailing zeroes to the right of the decimal point, or to
        // limit the length of displayString.
        ...
        return displayString;
    }
}
