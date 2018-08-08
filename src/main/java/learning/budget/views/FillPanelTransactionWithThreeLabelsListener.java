package learning.budget.views;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import learning.budget.Transaction;

public class FillPanelTransactionWithThreeLabelsListener implements ActionListener {
	private List<Transaction> list;
	private JPanel panelView;

	public FillPanelTransactionWithThreeLabelsListener(List<Transaction> transactionList, JPanel panelToDisplay) {
		list = transactionList;
		panelView = panelToDisplay;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		panelView.removeAll();
		panelView.setLayout(new BoxLayout(panelView, BoxLayout.Y_AXIS));
		for(Transaction t: list) {
			PanelTransactionViewWithThreeLabels panelTransaction = new PanelTransactionViewWithThreeLabels(t.getDate(), t.getCategoryName(), t.getAmount());
			panelView.add(panelTransaction);
		}
		
		panelView.repaint();
		panelView.revalidate();
	}
}
