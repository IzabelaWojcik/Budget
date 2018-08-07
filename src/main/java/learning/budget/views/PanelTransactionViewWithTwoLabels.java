package learning.budget.views;

import static learning.budget.DataFormatter.setAmountFormat;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javafx.util.Pair;
import learning.budget.LayoutOptions;
import learning.budget.Transaction;

public class PanelTransactionViewWithTwoLabels extends JPanel{
	public PanelTransactionViewWithTwoLabels() {
	}
	JLabel lblName, lblSalary;
	JPanel panel;
	public void fillPanel(List<Pair<String, Double>> pairs) {
		for(Pair<String, Double> p: pairs) {
			panel = new JPanel();
			panel.setPreferredSize(new Dimension(130, 20));
			panel.setBorder(getBorder());
			lblName = new JLabel(p.getKey(), JLabel.LEFT);
			lblName.setPreferredSize(new Dimension(60, 20));
			lblSalary = new JLabel(p.getValue().toString(), JLabel.LEFT);
			lblSalary.setPreferredSize(new Dimension(50, 20));
			
			panel.add(lblName);
			panel.add(lblSalary);
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			
			add(panel);
		}
		
		
	}
	
	
	
}


