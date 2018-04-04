package classes;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TextFieldAction {
	TextFieldValidator txFieldValidator = new TextFieldValidator();
	
	public void checkIfTextFieldHaveNumberValue(JTextField txField, JLabel lblError,  JButton button){
		txField.addKeyListener(new KeyAdapter(){
			@Override
			public void keyReleased(KeyEvent e){
				txFieldValidator.valueIsANumberForOneTextField(txField.getText(), lblError, button);
			}
		});
	}
}
