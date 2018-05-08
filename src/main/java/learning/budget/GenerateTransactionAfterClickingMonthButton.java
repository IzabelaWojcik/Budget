package learning.budget;

import static learning.budget.DataFormatter.setAmountFormat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GenerateTransactionAfterClickingMonthButton implements ActionListener{
	private JPanel panel;
	private List<Transaction> transactions;
	private LayoutOptions layoutOptions;
	
	public GenerateTransactionAfterClickingMonthButton(JPanel jpanel, List<Transaction> trensactionList, LayoutOptions layout) {
		panel = jpanel;
		transactions = trensactionList;
		layoutOptions = layout;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		double sum = 0;
		panel.removeAll();
		
		for (Transaction t : transactions) {
			JLabel jLabelDate = new JLabel(t.getDate().toString());
			JLabel jLabelsSavingsAmount = new JLabel(String.valueOf(t.getAmount()));
			JLabel jLabelSavingsCategory = new JLabel(t.getCategoryName());
					
			layoutOptions.gridBagLayoutOptionsForPanelsWithThreeLabels(panel, jLabelDate,
					jLabelSavingsCategory, jLabelsSavingsAmount);

			sum += t.getAmount();
		}
		//labelSavingsSum.setText(setAmountFormat(sum));
		JLabel labelSavingSum = new JLabel(setAmountFormat(sum));
		panel.add(labelSavingSum);
		
		panel.revalidate();
		panel.repaint();
	}
}
