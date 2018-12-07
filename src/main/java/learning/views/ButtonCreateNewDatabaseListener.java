package learning.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		String message = "";
		int idBudget = 0;
		try {
			
			if(controller.checkIfBudgetNameIsUnique(budgetName) == true)
			{
				message += "Taka nazwa budæetu już istnieje \n";
			}
			
			if(panelAddUsersToNewBudge.getTextFieldBugdetName().getText().equals("") || 
					panelAddUsersToNewBudge.getTextFieldBugdetName().getText().isEmpty() ||
					panelAddUsersToNewBudge.getTextFieldBugdetName().getText().trim().isEmpty()){
				
				message += "Wpisz nazwę budżetu \n";
			}
			
			if(checkedExpenditures.size() == 0 || checkedSavings.size() == 0 || checkedDues.size() == 0) {
				
				if(checkedExpenditures.size() == 0) {
					message += "Wybierz kategorie wydatków \n";
				}
				if(checkedSavings.size() == 0) {
					message += "Wybierz kategorie oszczędności \n";
				}
				if(checkedDues.size() == 0) {
					message += "Wybierz kategorie opłat ";
				}
				
				String numberOfUsers = panelAddUsersToNewBudge.getFormattedTextFieldNumberOfUsers().getText();
				int numberUsers = Integer.parseInt(numberOfUsers);
				JOptionPane.showMessageDialog(null, message);
			}
			
			
			
			if(idBudget == 999) {
				databaseWriter.writeBudgetNameToDatabase(budgetName);
				idBudget = controller.getBudgetIdFromDatabase(budgetName);
				databaseWriter.writeCategoryListTodatabase(checkedExpenditures, idBudget, EXPENDITURE_CATEGORY);
				databaseWriter.writeCategoryListTodatabase(checkedSavings, idBudget, SAVINGS_CATEGORY);
				databaseWriter.writeCategoryListTodatabase(checkedDues, idBudget, DUES_CATEGORY);
			}
			
			
			//controller.createUsers(panelAddUsersToNewBudge);
		} catch (BudgetNotFoundException | DatabaseNotInitialized e) {
			e.printStackTrace();
		}

	}

}
