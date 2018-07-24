package learning.budget.views;

import java.awt.Dimension;
import java.time.LocalDate;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelTransactionViewWithThreeLabels extends JPanel{
	JLabel lblDate, lblCategory, lblAmount;
	
	public PanelTransactionViewWithThreeLabels(LocalDate date, String category, Double amount) {
		lblDate = new JLabel(date.toString(), JLabel.LEFT);
		lblDate.setPreferredSize(new Dimension(90, 20));
		lblCategory = new JLabel(category, JLabel.CENTER);
		lblCategory.setPreferredSize(new Dimension(90,  20));
		lblAmount = new JLabel(amount.toString(), JLabel.RIGHT);
		lblAmount.setPreferredSize(new Dimension(90, 20));
		
		add(lblDate);
		add(lblCategory);
		add(lblAmount);
		
		validate();
		repaint();
	}
}
