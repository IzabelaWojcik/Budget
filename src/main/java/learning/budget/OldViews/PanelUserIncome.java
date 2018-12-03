package learning.budget.OldViews;

import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelUserIncome extends JPanel {
	JLabel userName;
	JFormattedTextField salary;
	ErrorLabelPropertyChangeListener errorLabel;

	public PanelUserIncome(String _userName) {
		userName = new JLabel(_userName, JLabel.RIGHT);
		userName.setPreferredSize(new Dimension(90, 20));

		salary = new JFormattedTextField(NumberFormat.getInstance());
		salary.setColumns(10);

		add(userName);
		add(salary);

		//FIXME
	//	errorLabel = new ErrorLabelPropertyChangeListener(null, Color.RED, new Dimension(130, 20), JLabel.LEFT);
		//salary.addPropertyChangeListener(errorLabel);
		//add(errorLabel);

		validate();
		repaint();
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		salary.addPropertyChangeListener(listener);
	}
}
