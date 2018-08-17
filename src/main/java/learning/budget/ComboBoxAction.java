package learning.budget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.swing.JComboBox;

public class ComboBoxAction {
	private IDatabaseReader databaseReader;
	
	public ComboBoxAction(IDatabaseReader _databaseReader) {
		databaseReader = _databaseReader;
	}
	

	public void writeOtherIncomeCategoryToComboBox(JComboBox<String> cbBox, int budgetId) throws DatabaseNotInitialized {
		ArrayList<Transaction> userIncomeList = databaseReader.readIncomefromDatabase();
		HashMap<Integer, String> otherIncomeCategoryMap = databaseReader.readCategoryFromDatabase("Income_category");
		ArrayList<String> otherIncomeUniqueItemList = new ArrayList<String>();
		
		int idPayment = 1;
		for (Transaction uio : userIncomeList) {
			int idBudget = uio.getBudgetId();
			int idIncomeCategory = uio.getCategoryId();
			
			if (budgetId == idBudget) {
				for (Entry<Integer, String> entry : otherIncomeCategoryMap.entrySet()) {
					if (entry.getKey() == idIncomeCategory) {
						if (!entry.getKey().equals(idPayment)) {
							if (!otherIncomeUniqueItemList.contains(entry.getValue())) {
								otherIncomeUniqueItemList.add(entry.getValue());
							}
						}
					}
				}
			}
		}
		for (String s : otherIncomeUniqueItemList) {
			cbBox.addItem(s);
		}
	}

	public void writeUsersToComboBox(JComboBox<String> cbBox, int budgetId) throws DatabaseNotInitialized {
		ArrayList<UsersObject> usersList = databaseReader.readUsersFromDatabase();
		for (UsersObject uo : usersList) {
			int idBudget = uo.getBudgerId();
			if (budgetId == idBudget) {
				cbBox.addItem(uo.getUserName());
			}
		}
	}
}
