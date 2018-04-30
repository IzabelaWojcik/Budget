package learning.budget.views;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import learning.budget.TextFieldValidator;

public class UserIncomneInputFiledListener extends KeyAdapter {
	private final JTextField field;
	//TODO: create interface for type below and user DI
	private final TextFieldValidator textFieldValidator = new TextFieldValidator();
	
	private JButton button;
	private JLabel errorLabel;
	
	public UserIncomneInputFiledListener(JTextField field, JLabel errorLabel, JButton button) {
		this.field = field;
		this.errorLabel = errorLabel;
		this.button = button;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		try {
			String value = field.getText().toString();
			boolean isNumber = textFieldValidator.valueIsANumberForGeneratedTextFields(value, errorLabel, button);

			if (isNumber) {
				//TODO: is second validation really necessary?
				double amount = Double.parseDouble(value);
				button.setEnabled(true);
			} else {
				button.setEnabled(false);
			}
		} catch (java.lang.NumberFormatException nfe) {
			errorLabel.setText("Podaj kwotę");
			button.setEnabled(false);
		}
	}
	
}
