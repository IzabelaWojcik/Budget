package learning.budget.views;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import learning.budget.Transaction;

public class PanelTransactionViewWithThreeLabels extends JPanel{
	JLabel lblDate, lblCategory, lblAmount;
	
	public void fillPanel(List<Transaction> list) {
		for(Transaction t: list) {
			lblDate = new JLabel(t.getDate().toString(), JLabel.LEFT);
			lblDate.setPreferredSize(new Dimension(90, 20));
			lblCategory = new JLabel(t.getCategoryName(), JLabel.CENTER);
			lblCategory.setPreferredSize(new Dimension(90,  20));
			lblAmount = new JLabel(String.valueOf(t.getAmount()), JLabel.RIGHT);
			lblAmount.setPreferredSize(new Dimension(90, 20));
			
			add(lblDate);
			add(lblCategory);
			add(lblAmount);
		}
		
		validate();
		repaint();
	}
	
}
