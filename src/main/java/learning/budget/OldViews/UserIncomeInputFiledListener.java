package learning.budget.OldViews;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;

public class UserIncomeInputFiledListener implements PropertyChangeListener{
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		JFormattedTextField field = (JFormattedTextField)evt.getSource();
		button.setEnabled(field.isValid() && !field.getText().isEmpty());
	}

	private JButton button;
	
	public UserIncomeInputFiledListener(JButton button) {
		this.button = button;
	}
}
