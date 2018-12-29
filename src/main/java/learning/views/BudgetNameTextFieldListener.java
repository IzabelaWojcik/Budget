package learning.views;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class BudgetNameTextFieldListener implements KeyListener {
	private JLabel labelError;
	
	public BudgetNameTextFieldListener(JLabel labelError) {
		this.labelError = labelError;
	}
	
	@Override
	public void keyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent evt) {
		JTextField field = (JTextField)evt.getSource();
		if(!checkIfTextFieldIsEmpty(field)) {
			labelError.setText("");
		}
		else {
			labelError.setText("Wpisz nazwę budżetu");
		}
	}

	@Override
	public void keyTyped(KeyEvent evt) {
		// TODO Auto-generated method stub

	}
	
	public boolean checkIfTextFieldIsEmpty(JTextField textField) {
		return 	textField.getText() == null || 
				textField.getText().equals("") ||
				textField.getText().isEmpty() || 
				textField.getText().trim().isEmpty();
	}
}
