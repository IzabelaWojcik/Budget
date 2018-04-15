package learning.budget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.swing.JComboBox;

public class ComboBoxAction {
	private DatabaseReader databaseReader = new DatabaseReader();

	public void writeOtherIncomeCategoryToComboBox(JComboBox<String> cbBox, int budgetId) {
		ArrayList<UsersIncomeObject> userIncomeList = databaseReader.readIncomefromDatabase();
		HashMap<Integer, String> otherIncomeCategoryMap = databaseReader.readCategoryFromDatabase("Income_category");
		ArrayList<String> otherIncomeUniqueItemList = new ArrayList<String>();
		int idPayment = 1;
		for (UsersIncomeObject uio : userIncomeList) {
			int idBudget = uio.getBudgetId();
			int idIncomeCategory = uio.getIncomeCategoryId();
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

	public void writeExpenditureCategoryToComboBox(JComboBox<String> cbBox, int budgetId) {
		ArrayList<ExpendiutureObject> expenditureList = databaseReader.readExpenditureFromDataBase();
		HashMap<Integer, String> expenditureCategoryMap = databaseReader
				.readCategoryFromDatabase("Expenditure_category");
		ArrayList<String> expenditureUniqueItemList = new ArrayList<String>();
		for (ExpendiutureObject eo : expenditureList) {
			int idBudget = eo.getBudgetId();
			int idExpenditureCategory = eo.getExpenditureCategoryId();
			if (budgetId == idBudget) {
				for (Entry<Integer, String> entry : expenditureCategoryMap.entrySet()) {
					if (entry.getKey() == idExpenditureCategory) {
						if (!expenditureUniqueItemList.contains(entry.getValue())) {
							expenditureUniqueItemList.add(entry.getValue());
						}
					}
				}
			}
		}
		for (String s : expenditureUniqueItemList) {
			cbBox.addItem(s);
		}
	}

	public void writeSavingsCategoryToComboBox(JComboBox<String> cbBox, int budgetId) {
		ArrayList<SavingsObject> savingsList = databaseReader.readSavingsFromDataBase();
		HashMap<Integer, String> savingsCategoryMap = databaseReader.readCategoryFromDatabase("Savings_category");
		ArrayList<String> savingUniqueItemList = new ArrayList<String>();
		for (SavingsObject so : savingsList) {
			int idBudget = so.getBudgetId();
			int idSavingsCategory = so.getSavingsCategoryId();
			if (budgetId == idBudget) {
				for (Entry<Integer, String> entry : savingsCategoryMap.entrySet()) {
					if (entry.getKey() == idSavingsCategory) {
						if (!savingUniqueItemList.contains(entry.getValue())) {
							savingUniqueItemList.add(entry.getValue());
						}
					}
				}
			}
		}
		for (String s : savingUniqueItemList) {
			cbBox.addItem(s);
		}
	}

	public void writeUsersToComboBox(JComboBox<String> cbBox, int budgetId) {
		ArrayList<UsersObject> usersList = databaseReader.readUsersFromDatabase();
		for (UsersObject uo : usersList) {
			int idBudget = uo.getBudgerId();
			if (budgetId == idBudget) {
				cbBox.addItem(uo.getUserName());
			}
		}
	}
}
