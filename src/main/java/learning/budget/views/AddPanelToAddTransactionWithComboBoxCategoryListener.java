package learning.budget.views;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JPanel;
import learning.budget.IDatabaseWriter;

public class AddPanelToAddTransactionWithComboBoxCategoryListener implements ActionListener{
	private HashMap<Integer,String> mapCategory;
	private JPanel panelView;
	private IDatabaseWriter _databaseWriter;
	private int budgetId;
	private String dbTablename;
	
	public AddPanelToAddTransactionWithComboBoxCategoryListener(HashMap<Integer,String> expenditureCategoryHashMap, JPanel panelToDisplay, IDatabaseWriter databaseWriter, int clickedBudgetId, String tablename) {
		mapCategory = expenditureCategoryHashMap;
		panelView = panelToDisplay;
		_databaseWriter = databaseWriter;
		budgetId = clickedBudgetId;
		dbTablename = tablename;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		panelView.removeAll();
		PanelAddTransactionWithComboBoxCategory panelAddTransaction = new PanelAddTransactionWithComboBoxCategory(mapCategory, _databaseWriter, budgetId, dbTablename);
		panelView.add(panelAddTransaction);
	}
}
