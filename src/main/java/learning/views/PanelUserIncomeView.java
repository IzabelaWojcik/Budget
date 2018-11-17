package learning.views;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class PanelUserIncomeView extends JPanel{
	
	public PanelUserIncomeView(String name, double salary) {
		setPreferredSize(new Dimension(110, 20));
		
		JLabel lblName = new JLabel(name, JLabel.LEFT);
		lblName.setPreferredSize(new Dimension(60, 20));
		
		JLabel lblSalary = new JLabel(Double.toString(salary), JLabel.LEFT);
		lblSalary.setPreferredSize(new Dimension(50, 20));
		
		add(lblName);
		add(lblSalary);
	}

}
