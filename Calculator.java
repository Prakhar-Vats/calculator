/**
 * Simple Calculator logic class (OOP).
 */
public class Calculator {

    private double firstOperand;
    private double secondOperand;
    private String operator;
    private boolean startNewNumber;

    public Calculator() {
        clear();
    }

    public void clear() {
        firstOperand = 0.0;
        secondOperand = 0.0;
        operator = "";
        startNewNumber = true;
    }

    /**
     * Append a digit or dot to the current number.
     */
    public String inputDigit(String currentDisplay, String digit) {
        if (startNewNumber || currentDisplay.equals("0")) {
            currentDisplay = digit.equals(".") ? "0." : digit;
            startNewNumber = false;
        } else {
            if (digit.equals(".") && currentDisplay.contains(".")) {
                return currentDisplay;
            }
            currentDisplay += digit;
        }
        return currentDisplay;
    }

    /**
     * Store the current number and the operator.
     */
    public void setOperator(String currentDisplay, String op) {
        if (!operator.isEmpty()) {
            // If an operator already exists, compute first
            double result = calculate(firstOperand, Double.parseDouble(currentDisplay), operator);
            firstOperand = result;
        } else {
            firstOperand = Double.parseDouble(currentDisplay);
        }
        operator = op;
        startNewNumber = true;
    }

    /**
     * Perform the calculation when '=' is pressed.
     */
    public String evaluate(String currentDisplay) {
        if (operator.isEmpty()) {
            return currentDisplay;
        }
        secondOperand = Double.parseDouble(currentDisplay);
        double result = calculate(firstOperand, secondOperand, operator);
        firstOperand = result;
        operator = "";
        startNewNumber = true;
        if (Double.isInfinite(result) || Double.isNaN(result)) {
            clear();
            return "Error";
        }
        if (result == (long) result) {
            return String.valueOf((long) result);
        }
        return String.valueOf(result);
    }

    private double calculate(double a, double b, String op) {
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return b == 0 ? Double.NaN : a / b;
            default:
                return b;
        }
    }
}

