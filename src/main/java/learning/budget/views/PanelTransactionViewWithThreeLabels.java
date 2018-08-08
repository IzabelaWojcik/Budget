package learning.budget.views;
import java.awt.Dimension;
import java.time.LocalDate;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelTransactionViewWithThreeLabels extends JPanel{
	
	public PanelTransactionViewWithThreeLabels(LocalDate date, String category, double amount) {
			setPreferredSize(new Dimension(210, 20));
			
			JLabel lblDate = new JLabel(date.toString(), JLabel.LEFT);
			lblDate.setPreferredSize(new Dimension(80, 20));
			
			JLabel lblCategory = new JLabel(category, JLabel.LEFT);
			lblCategory.setPreferredSize(new Dimension(80,  20));
			
			JLabel lblAmount = new JLabel(String.valueOf(amount), JLabel.LEFT);
			lblAmount.setPreferredSize(new Dimension(50, 20));
			
			add(lblDate);
			add(lblCategory);
			add(lblAmount);
	}
}

	
	