package learning.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import learning.budget.DatabaseNotInitialized;
import learning.budget.IDatabaseReader;
import learning.budget.IDatabaseWriter;

public class ButtonCreateNewDatabaseListener implements ActionListener {
	public static final String DUES_CATEGORY = "Dues_category";
	public static final String SAVINGS_CATEGORY = "Savings_category";
	public static final String EXPENDITURE_CATEGORY = "Expenditure_category";
	
	private IDatabaseReader databaseReader;
	private IDatabaseWriter databaseWriter;
	private PanelAddUsersToNewBudget panelAddUsersToNewBudge;
	private PanelDuesCategoriesInNewBudget panelDuesCategoriesInNewBudget;
	private PanelExpenditureCategoriesInNewBudget panelExpenditureCategoriesInNewBudget;
	private PanelSavingsCategoriesInNewBudget PanelSavingsCategoriesInNewBudget;

	public ButtonCreateNewDatabaseListener(IDatabaseReader databaseReader,
			IDatabaseWriter databaseWriter, PanelAddUsersToNewBudget panelAddUsersToNewBudget,
			PanelDuesCategoriesInNewBudget panelDuesCategoriesInNewBudget,
			PanelExpenditureCategoriesInNewBudget panelExpenditureCategoriesInNewBudget,
			PanelSavingsCategoriesInNewBudget PanelSavingsCategoriesInNewBudget) {
		this.databaseReader = databaseReader;
		this.databaseWriter = databaseWriter;
		this.panelAddUsersToNewBudge = panelAddUsersToNewBudget;
		this.panelDuesCategoriesInNewBudget = panelDuesCategoriesInNewBudget;
		this.panelExpenditureCategoriesInNewBudget = panelExpenditureCategoriesInNewBudget;
		this.PanelSavingsCategoriesInNewBudget = PanelSavingsCategoriesInNewBudget;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		List<String> checkedExpenditures = panelExpenditureCategoriesInNewBudget.getCheckedCategories();
		List<String> checkedSavings = PanelSavingsCategoriesInNewBudget.getCheckedCategories();
		List<String> checkedDues = panelDuesCategoriesInNewBudget.getCheckedCategories();
		List<String> users = panelAddUsersToNewBudge.getUsersNames();
		String budgetName = panelAddUsersToNewBudge.getTextFieldBugdetName().getText();

		String message = "";
		int idBudget = 0;
		try {
			
			if(checkIfBudgetNameIsUnique(budgetName) == true)
			{
				message += "Taka nazwa budæetu już istnieje \n";
			}
			
			if(panelAddUsersToNewBudge.getTextFieldBugdetName().getText().equals("") || 
					panelAddUsersToNewBudge.getTextFieldBugdetName().getText().isEmpty() ||
					panelAddUsersToNewBudge.getTextFieldBugdetName().getText().trim().isEmpty()){
				
				message += "Wpisz nazwę budżetu \n";
			}
			
			if(checkedExpenditures.size() == 0) {
				message += "Wybierz kategorie wydatków \n";
			}
			if(checkedSavings.size() == 0) {
				message += "Wybierz kategorie oszczędności \n";
			}
			if(checkedDues.size() == 0) {
				message += "Wybierz kategorie opłat ";
			}
				
			JOptionPane.showMessageDialog(null, message);
			
			//FIXME add condition
			if(idBudget == 999) {
				databaseWriter.writeBudgetNameToDatabase(budgetName);
				idBudget = getBudgetIdFromDatabase(budgetName);
				databaseWriter.writeCategoryListTodatabase(checkedExpenditures, idBudget, EXPENDITURE_CATEGORY);
				databaseWriter.writeCategoryListTodatabase(checkedSavings, idBudget, SAVINGS_CATEGORY);
				databaseWriter.writeCategoryListTodatabase(checkedDues, idBudget, DUES_CATEGORY);
			}
			
			
		} catch (BudgetNotFoundException | DatabaseNotInitialized e) {
			e.printStackTrace();
		}

	}
	
	private boolean checkIfBudgetNameIsUnique(String budgetName) throws BudgetNotFoundException, DatabaseNotInitialized{
		HashMap<Integer, String> budgetIdNameMap = databaseReader.readBudgetIdNameFromDatabase();
		return budgetIdNameMap.containsValue(budgetName);
	}
	
	private int getBudgetIdFromDatabase(String budgetName) throws BudgetNotFoundException, DatabaseNotInitialized{
		HashMap<Integer, String> budgetIdNameMap = databaseReader.readBudgetIdNameFromDatabase();

		for(Entry<Integer, String> entry: budgetIdNameMap.entrySet()){
			if(budgetName.equals(entry.getValue())){
				return entry.getKey();
			}
		}
		throw new BudgetNotFoundException(budgetName);
	}
}
