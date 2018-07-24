package learning.budget.views;

import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelUserInput extends JPanel {
	JLabel userName;
	JFormattedTextField input;
	ErrorLabel errorLabel;

	public PanelUserInput(String _userName) {
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
