package learning.budget.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;

public class AddPanelToAddTransactionWithComboBoxCategoryListener implements ActionListener{
	private List<String> listCategory;
	private JPanel panelView;
	
	public AddPanelToAddTransactionWithComboBoxCategoryListener(List<String> categoryList, JPanel panelToDisplay) {
		listCategory = categoryList;
		panelView = panelToDisplay;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		panelView.removeAll();
		PanelAddTransactionWithComboBoxCategory panelAddTransaction = new PanelAddTransactionWithComboBoxCategory(listCategory);
		panelView.add(panelAddTransaction);
	}
}
