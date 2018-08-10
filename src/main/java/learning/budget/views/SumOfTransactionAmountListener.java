package learning.budget.views;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JLabel;
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
		sumLabel.setText(SumAmount.sumAmount(list));
	}
}
