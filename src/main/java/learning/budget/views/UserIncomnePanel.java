package learning.budget.views;

import static learning.budget.Constants.MAX_NUMBER_OF_USERS_IN_BUDGET;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserIncomnePanel extends JPanel {
	void fillWithUserIncomnes(int numberOfInputFields, Component[] errorLabels, JButton button) {
		for (int i = 0; i < numberOfInputFields && i < MAX_NUMBER_OF_USERS_IN_BUDGET; i++) {
			JTextField textField = new JTextField(10);
			
			add(textField);
			textField.addKeyListener(new UserIncomneInputFiledListener(textField, (JLabel)errorLabels[i], button));
		}
		
		validate();
		repaint();
	}
}
