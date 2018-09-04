package learning.budget.views;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JLabel;

import learning.budget.DataFormatter;
import learning.budget.SumAmount;
import learning.budget.Transaction;

public class SumOfTransactionAmountListener implements ActionListener{
	private JLabel sumLabel;
	private List<Transaction> list;
	
	public SumOfTransactionAmountListener(List<Transaction> transactionList, JLabel lblSum) {
		list = transactionList;
		sumLabel = lblSum;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		sumLabel.removeAll();

		Double sum = list.stream().mapToDouble(Transaction::getAmount).sum();
		sumLabel.setText(DataFormatter.setAmountFormat(sum));
	}
}
