package learning.budget;

import java.time.LocalDate;
import java.util.List;

import javax.swing.JPanel;

import javafx.util.Pair;
import learning.budget.views.PanelTransactionViewWithThreeLabels;
import learning.budget.views.PanelTransactionViewWithTwoLabels;

public class FillPanelWithViewsWithDataFromDatabase {
	JPanel panel;
	
	public void FillPanelTransattionViewWithTwoLabels(List<Pair<String, Double>> pairs) {
		for(Pair<String, Double> p: pairs) {
			panel = new PanelTransactionViewWithTwoLabels(p.getKey(), p.getValue());
		}
	}
	
	public void FillPanelTransattionViewWithThreeLabels(List<Transaction> list) {
		for(Transaction t: list) {
			panel = new PanelTransactionViewWithThreeLabels(t.getDate(), t.getCategoryName(), t.getAmount());
		}
	}
}
