package learning.budget.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;

public class AddPanelToAddTransactionWithComboBoxCategoryAndUserListener implements ActionListener{
	private List<String> listCategory;
	private List<String> listUsersName;
	private JPanel panelView;
	
	public AddPanelToAddTransactionWithComboBoxCategoryAndUserListener(List<String> categoryList, List<String> usersNameList, JPanel panelToDisplay) {
		listCategory = categoryList;
		listUsersName = usersNameList;
		panelView = panelToDisplay;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		panelView.removeAll();
		PanelAddTransactionWithComboBoxCategoryAndUsername panelAddTransaction = new PanelAddTransactionWithComboBoxCategoryAndUsername(listCategory, listUsersName);
		panelView.add(panelAddTransaction);
	}
}
