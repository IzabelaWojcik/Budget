package learning.budget.views;

import static learning.budget.Constants.MAX_NUMBER_OF_USERS_IN_BUDGET;

import java.awt.Component;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserIncomnePanel extends JPanel {
	void fillWithUserIncomnes(Component[] errorLabels, PropertyChangeListener inputListener) {
		int numberOfInputFields = errorLabels.length;

		for (int i = 0; i < numberOfInputFields && i < MAX_NUMBER_OF_USERS_IN_BUDGET; i++) {
			JTextField textField = new JFormattedTextField(NumberFormat.getInstance());
			textField.setColumns(10);
			textField.addPropertyChangeListener((ErrorLabel)errorLabels[i]);
			textField.addPropertyChangeListener(inputListener);
			
			add(textField);
		}
		
		validate();
		repaint();
	}
}
