package learning.budget.views;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JTextField;

public class UserIncomeInputFiledListener extends KeyAdapter {
	private final JTextField field;
	
	private JButton button;
	
	public UserIncomeInputFiledListener(JTextField field, JButton button) {
		this.field = field;
		this.button = button;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		try {
			String value = field.getText().toString();
			boolean isNumber = validate(value);

			if (isNumber) {
				//TODO: is second validation really necessary?
				double amount = Double.parseDouble(value);
				button.setEnabled(true);
			} else {
				button.setEnabled(false);
			}
		} catch (java.lang.NumberFormatException nfe) {
			button.setEnabled(false);
		}
	}
	
	private boolean validate(String str)
	{
		String number_pattern1 = "^[0-9]{0,9}\\.[0-9]{0,2}$";
		String number_pattern2 = "^[0-9]{0,9}";
		Pattern pattern1 = Pattern.compile(number_pattern1);
		Pattern pattern2 = Pattern.compile(number_pattern2);
		Matcher regexMatcher1 = pattern1.matcher(str);
		Matcher regexMatcher2 = pattern2.matcher(str);
		
		return regexMatcher1.matches() || regexMatcher2.matches();
	}
	
}
