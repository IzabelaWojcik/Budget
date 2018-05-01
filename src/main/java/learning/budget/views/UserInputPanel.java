package learning.budget.views;

import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

import javax.swing.GroupLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class UserInputPanel extends JPanel {
	JLabel userName;
	JFormattedTextField input;
	ErrorLabel errorLabel;

	public UserInputPanel(String _userName) {
		userName = new JLabel(_userName, JLabel.RIGHT);
		userName.setPreferredSize(new Dimension(90, 20));

		input = new JFormattedTextField(NumberFormat.getInstance());
		input.setColumns(10);

		add(userName);
		add(input);

		errorLabel = new ErrorLabel(Color.RED, new Dimension(130, 20), JLabel.LEFT);
		input.addPropertyChangeListener(errorLabel);
		add(errorLabel);

		validate();
		repaint();
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		input.addPropertyChangeListener(listener);
	}
}
