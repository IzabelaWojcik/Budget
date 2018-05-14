package learning.budget;

import static learning.budget.DataFormatter.setAmountFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GenerateTransactionAfterClickingMonthButton implements ActionListener {
	private JPanel panel;
	private JLabel labelSum;
	private List<Transaction> transactions;
	private LayoutOptions layoutOptions;

	public GenerateTransactionAfterClickingMonthButton(JPanel jpanelForTransaction, JLabel jlabelSum, List<Transaction> trensactionList,
			LayoutOptions layout) {
		panel = jpanelForTransaction;
		labelSum = jlabelSum;
		transactions = trensactionList;
		layoutOptions = layout;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		double sum = 0;
		panel.removeAll();
		for (Transaction t : transactions) {
			JLabel jLabelDate = new JLabel(t.getDate().toString());
			JLabel jLabelAmount = new JLabel(String.valueOf(t.getAmount()));
			JLabel jLabelCategory = new JLabel(t.getCategoryName());
			
			layoutOptions.gridBagLayoutOptionsForPanelsWithThreeLabels(panel, jLabelDate, jLabelCategory,
					jLabelAmount);
			
			sum += t.getAmount();
		}
		labelSum.setText(setAmountFormat(sum));
		panel.revalidate();
		panel.repaint();
	}
}
