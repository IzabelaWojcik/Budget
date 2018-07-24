package learning.budget.views;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelTransactionViewWithTwoLabels extends JPanel{
	JLabel lblName, lblSalary;
	
	public PanelTransactionViewWithTwoLabels(String userName, Double amount) {
		lblName = new JLabel(userName, JLabel.LEFT);
		lblName.setPreferredSize(new Dimension(90, 20));
		lblSalary = new JLabel(amount.toString(), JLabel.RIGHT);
		lblSalary.setPreferredSize(new Dimension(90, 20));
		
		add(lblName);
		add(lblSalary);
		
		validate();
		repaint();
	}
}
