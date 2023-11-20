// CalcBackend.java

/**
 * PSET4 - #7
 * 
 * Calculator program utilizing stacks to manage operands and operators.
 * Supports addition, subtraction, multiplication, division, square root, and clearing functionalities.
 * Handles edge cases such as division by zero, providing an error message for invalid square root inputs.
 * 
 * @author Kuljit Takhar
 * @version November 10 2023
 * 
 */

// ADDITIONCHAR = '+'
// SUBTRACTIONCHAR = '-'
// MULTIPLICATIONCHAR = '*'
// DIVISIONCHAR = '/'
// EQUALCHAR = '='
// SQRTCHAR = '\u221A'
// CLEARCHAR = 'C'
// OPENPAREN = '('
// CLOSEPAREN = ')'
// MEMORYCLEAR = 'MC'
// MEMORYSET = 'MS'
// MEMORYRECALL = 'MR'

import java.util.Stack;

public class CalcBackend {
    private String currentNumber;
    private String displayValue;

    private Stack<Double> operandStack;
    private Stack<Character> operatorStack;

    // Memory variable
    private double memory;

    public CalcBackend() {
        currentNumber = "";
        displayValue = "0";
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
        memory = 0.0;
    }

    public void feedChar(char c) {
        if (Character.isDigit(c) || c == '.') {
            currentNumber += c;
            displayValue = currentNumber;
        } else if (c == '+' || c == '-' || c == '*' || c == '/') {
            performOperation(c);
        } else if (c == '=') {
            performEquals();
        } else if (c == '√') {
            performSqrt();
        } else if (c == 'C') {
            clearAll();
        } else if (c == 'M') {
            // Handle memory operations (MC, MS, MR)
            clearMemory();
        }
    }

    public String getDisplayVal() {
        return displayValue;
    }

    private void performOperation(char operator) {
        if (!currentNumber.isEmpty()) {
            operandStack.push(Double.parseDouble(currentNumber));
            currentNumber = "";
        }

        while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(operator)) {
            evaluate();
        }

        operatorStack.push(operator);
        displayValue = Double.toString(operandStack.peek());
    }

    private void performEquals() {
        if (!currentNumber.isEmpty()) {
            operandStack.push(Double.parseDouble(currentNumber));
            currentNumber = "";
        }

        while (!operatorStack.isEmpty()) {
            evaluate();
        }

        displayValue = Double.toString(operandStack.peek());
    }

    private void performSqrt() {
        if (!currentNumber.isEmpty()) {
            double num = Double.parseDouble(currentNumber);
            if (num >= 0) {
                double result = Math.sqrt(num);
                displayValue = Double.toString(result);
                currentNumber = "";
            } else {
                displayValue = "Error";
            }
        }
    }

    private void evaluate() {
        char operator = operatorStack.pop();
        double operand2 = operandStack.pop();
        double operand1 = operandStack.pop();
        double result = performArithmetic(operand1, operator, operand2);
        operandStack.push(result);
    }

    private double performArithmetic(double operand1, char operator, double operand2) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {
                    // Handle division by zero
                    return Double.POSITIVE_INFINITY;
                }
            default:
                return 0.0;
        }
    }

    private int precedence(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        } else {
            return 0;
        }
    }

    private void clearAll() {
    currentNumber = "";
    displayValue = "0";
    operandStack.clear();
    operatorStack.clear(); 
    }


    // Memory methods

    public void clearMemory() {
        memory = 0.0;
    }

    public void setMemory() {
        memory = Double.parseDouble(displayValue);
    }

    public void recallMemory() {
        currentNumber = Double.toString(memory);
        displayValue = currentNumber;
    }
}
