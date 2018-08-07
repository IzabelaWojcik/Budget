package learning.budget.views;
import java.awt.Dimension;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import learning.budget.Transaction;

public class PanelTransactionViewWithThreeLabels extends JPanel{
	JLabel lblDate, lblCategory, lblAmount;
	JPanel panel;
	
	public void fillPanel(List<Transaction> list) {
		removeAll();
		for(Transaction t: list) {
			panel = new JPanel();
			panel.setPreferredSize(new Dimension(280, 20));
			lblDate = new JLabel(t.getDate().toString(), JLabel.LEFT);
			lblDate.setPreferredSize(new Dimension(80, 20));
			lblCategory = new JLabel(t.getCategoryName(), JLabel.LEFT);
			lblCategory.setPreferredSize(new Dimension(80,  20));
			lblAmount = new JLabel(String.valueOf(t.getAmount()), JLabel.LEFT);
			lblAmount.setPreferredSize(new Dimension(50, 20));
			
			panel.add(lblDate);
			panel.add(lblCategory);
			panel.add(lblAmount);
			
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			add(panel);
		}
	}
}

	
	