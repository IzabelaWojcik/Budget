package learning.budget;

import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TextFieldValidator {
	private final Pattern doubleNumber = Pattern.compile("^[0-9]{0,9}\\.[0-9]{0,2}$");
	private final Pattern number = Pattern.compile("^[0-9]{0,9}");

	public boolean validate(String str){
		return doubleNumber.matcher(str).matches() || number.matcher(str).matches();
	}

	public boolean checkIfTextFieldIsEmpty(JTextField textField, JButton button, JLabel lblError) {
		if (textField.getText() == null || textField.getText().isEmpty()) {
			button.setEnabled(false);
			lblError.setText("Wpisz kwotę");
			return true;
		} else {
			button.setEnabled(true);
			lblError.setText("");
			return false;
		}
	}

	public boolean checkIfTextFieldIsEmpty(JTextField textField) {
		if (textField.getText() == null || textField.getText().isEmpty() || textField.getText().trim().isEmpty()
				|| textField.getText().length() < 2) {
			return true;
		} else {
			return false;
		}
	}
}
