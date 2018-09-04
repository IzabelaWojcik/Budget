package learning.budget;

import java.util.regex.Matcher;
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

	//FIXME: this method should only validate passed string - it should not fill JLabel and it should not have control on button
	public boolean valueIsANumberForGeneratedTextFields(String str, JLabel lblError, JButton button) {
		String number_pattern1 = "^[0-9]{0,9}\\.[0-9]{0,2}$";
		String number_pattern2 = "^[0-9]{0,9}";
		Pattern pattern1 = Pattern.compile(number_pattern1);
		Pattern pattern2 = Pattern.compile(number_pattern2);
		Matcher regexMatcher1 = pattern1.matcher(str);
		Matcher regexMatcher2 = pattern2.matcher(str);
		if (regexMatcher1.matches() || regexMatcher2.matches()) {
			lblError.setText("");
		} else {
			lblError.setText("Poprawny format #.##");
			button.setEnabled(false);
		}
		return true;
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
