package learning.budget.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;

public class AddPanelToAddTransactionListener implements ActionListener{
	private List<String> listCategory;
	private JPanel panelView;
	
	public AddPanelToAddTransactionListener(List<String> categoryList, JPanel panelToDisplay) {
		listCategory = categoryList;
		panelView = panelToDisplay;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		panelView.removeAll();
		PanelAddTransactionWithoutUsername panelAddTransaction = new PanelAddTransactionWithoutUsername(listCategory);
		panelView.add(panelAddTransaction);
	}
}
