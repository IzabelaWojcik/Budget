package learning.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import learning.budget.DatabaseNotInitialized;
import learning.budget.IDatabaseReader;
import learning.budget.IDatabaseWriter;

public class ButtonCreateNewDatabaseListener implements ActionListener {
	public static final String DUES_CATEGORY = "Dues_category";
	public static final String SAVINGS_CATEGORY = "Savings_category";
	public static final String EXPENDITURE_CATEGORY = "Expenditure_category";
	
	private IDatabaseReader databaseReader;
	private IDatabaseWriter databaseWriter;
	private CreateNewBudgetController controller;
	private PanelAddUsersToNewBudget panelAddUsersToNewBudge;
	private PanelDuesCategoriesInNewBudget panelDuesCategoriesInNewBudget;
	private PanelExpenditureCategoriesInNewBudget panelExpenditureCategoriesInNewBudget;
	private PanelSavingsCategoriesInNewBudget PanelSavingsCategoriesInNewBudget;
	


	public ButtonCreateNewDatabaseListener(CreateNewBudgetController controller, IDatabaseReader databaseReader,
			IDatabaseWriter databaseWriter, PanelAddUsersToNewBudget panelAddUsersToNewBudget,
			PanelDuesCategoriesInNewBudget panelDuesCategoriesInNewBudget,
			PanelExpenditureCategoriesInNewBudget panelExpenditureCategoriesInNewBudget,
			PanelSavingsCategoriesInNewBudget PanelSavingsCategoriesInNewBudget) {
		this.controller = controller;
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
		
		int idBudget = 0;
		try {
			controller.writeBudgetNameToDatabase(budgetName);
			
			idBudget = controller.getBudgetIdFromDatabase(budgetName);
			
		} catch (BudgetNotFoundException | DatabaseNotInitialized e) {
			e.printStackTrace();
		}

		if(checkedExpenditures.size() == 0 || checkedSavings.size() == 0 || checkedDues.size() == 0) {
			String message = "";
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
		}
		
		databaseWriter.writeCategoryListTodatabase(checkedExpenditures, idBudget, EXPENDITURE_CATEGORY);
		databaseWriter.writeCategoryListTodatabase(checkedSavings, idBudget, SAVINGS_CATEGORY);
		databaseWriter.writeCategoryListTodatabase(checkedDues, idBudget, DUES_CATEGORY);
		
		//controller.createUsers(panelAddUsersToNewBudge);
	}

}
