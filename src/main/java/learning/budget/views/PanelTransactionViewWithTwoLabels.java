package learning.budget.views;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import javafx.util.Pair;
import learning.budget.Transaction;

public class PanelTransactionViewWithTwoLabels extends JPanel{
	JLabel lblName, lblSalary;
	
	public void fillPanel(List<Pair<String, Double>> pairs) {
		for(Pair<String, Double> p: pairs) {
			lblName = new JLabel(p.getKey(), JLabel.LEFT);
			lblName.setPreferredSize(new Dimension(90, 20));
			lblSalary = new JLabel(p.getValue().toString(), JLabel.RIGHT);
			lblSalary.setPreferredSize(new Dimension(90, 20));
			
			add(lblName);
			add(lblSalary);
		}
		
		validate();
		repaint();
	}
	
}
