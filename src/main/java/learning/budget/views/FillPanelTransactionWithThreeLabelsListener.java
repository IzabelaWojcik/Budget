package learning.budget.views;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JPanel;
import learning.budget.Transaction;

public class FillPanelTransactionWithThreeLabelsListener implements ActionListener {
	private PanelTransactionViewWithThreeLabels panelTransactionView;
	private List<Transaction> list;
	private JPanel panelView;

	public FillPanelTransactionWithThreeLabelsListener(PanelTransactionViewWithThreeLabels panelTransactionViewWithThreeLabels,
			List<Transaction> transactionList, JPanel panelToDisplay) {
		panelTransactionView = panelTransactionViewWithThreeLabels;
		list = transactionList;
		panelView = panelToDisplay;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		panelView.removeAll();
		panelTransactionView.fillPanel(list);
		panelView.add(panelTransactionView);
		panelView.repaint();
		panelView.revalidate();
	}
}
