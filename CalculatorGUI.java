import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI class using Swing, separated from Calculator logic (OOP).
 */
public class CalculatorGUI extends JFrame implements ActionListener {

    private final Calculator calculator;
    private final JTextField displayField;

    public CalculatorGUI() {
        super("Simple Calculator");
        calculator = new Calculator();

        // Display
        displayField = new JTextField("0");
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setFont(new Font("SansSerif", Font.BOLD, 24));

        // Buttons
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "C", "+",
                "=", 
        };

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));

        for (String text : buttonLabels) {
            JButton button = new JButton(text);
            button.setFont(new Font("SansSerif", Font.PLAIN, 18));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        // Layout frame
        setLayout(new BorderLayout(5, 5));
        add(displayField, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null); // center on screen
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String currentDisplay = displayField.getText();

        if ("0123456789.".contains(command)) {
            String updated = calculator.inputDigit(currentDisplay, command);
            displayField.setText(updated);
        } else if ("+-*/".contains(command)) {
            calculator.setOperator(currentDisplay, command);
        } else if ("=".equals(command)) {
            String result = calculator.evaluate(currentDisplay);
            displayField.setText(result);
        } else if ("C".equals(command)) {
            calculator.clear();
            displayField.setText("0");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorGUI gui = new CalculatorGUI();
            gui.setVisible(true);
        });
    }
}

