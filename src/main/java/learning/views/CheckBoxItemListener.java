package learning.views;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JTextField;

public class CheckBoxItemListener implements ItemListener{
	private JTextField textField;
	
	public CheckBoxItemListener(JTextField textField) {
		this.textField = textField;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED){
			textField.setEnabled(true);
		}
		if(e.getStateChange() == ItemEvent.DESELECTED){
			textField.setText("");
			textField.setEnabled(false);
		}
	}
}
