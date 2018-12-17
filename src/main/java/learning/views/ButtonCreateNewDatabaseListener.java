package learning.views;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
	private String message;
	
	private CreateNewBudgetDialog dialog;

	public ButtonCreateNewDatabaseListener(IDatabaseReader databaseReader,
			IDatabaseWriter databaseWriter, PanelAddUsersToNewBudget panelAddUsersToNewBudget,
			PanelDuesCategoriesInNewBudget panelDuesCategoriesInNewBudget,
			PanelExpenditureCategoriesInNewBudget panelExpenditureCategoriesInNewBudget,
			PanelSavingsCategoriesInNewBudget PanelSavingsCategoriesInNewBudget,
			CreateNewBudgetDialog dialog) {
		this.databaseReader = databaseReader;
		this.databaseWriter = databaseWriter;
		this.panelAddUsersToNewBudge = panelAddUsersToNewBudget;
		this.panelDuesCategoriesInNewBudget = panelDuesCategoriesInNewBudget;
		this.panelExpenditureCategoriesInNewBudget = panelExpenditureCategoriesInNewBudget;
		this.PanelSavingsCategoriesInNewBudget = PanelSavingsCategoriesInNewBudget;
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		List<String> checkedExpenditures = panelExpenditureCategoriesInNewBudget.getCheckedCategories();
		List<String> checkedSavings = PanelSavingsCategoriesInNewBudget.getCheckedCategories();
		List<String> checkedDues = panelDuesCategoriesInNewBudget.getCheckedCategories();
		List<String> usersNames = new ArrayList<String>();
		
		String budgetName = panelAddUsersToNewBudge.getTextFieldBugdetName().getText();

		message = "";
		int userCounter = 0;
		boolean filledBudgetName = false;
		
		try {
			int usersNumber = Integer.parseInt(panelAddUsersToNewBudge.getFormattedTextFieldNumberOfUsers().getText());
			
			if(checkIfBudgetNameIsUnique(budgetName) == true)
			{
				message += "Taka nazwa budżetu już istnieje \n";
				filledBudgetName = false;
			}
			
			filledBudgetName = checkIfBudgetNameFieldIsntEmpty();
			userCounter = checkIfUserNamesFieldsAreFilled(usersNames, userCounter, usersNumber);
			checkIfCategoriesAreChoosen(checkedExpenditures, checkedSavings, checkedDues);
				
			createNewBudget(checkedExpenditures, checkedSavings, checkedDues, budgetName, userCounter, filledBudgetName,
					usersNumber);
			
		} catch (BudgetNotFoundException | DatabaseNotInitialized e) {
			
			e.printStackTrace();
			
		} catch(java.lang.NumberFormatException exc) {
			
			JOptionPane.showMessageDialog(null, "Wpisz ilość użytkowników");
			
		}
	}

	private void createNewBudget(List<String> checkedExpenditures, List<String> checkedSavings,
			List<String> checkedDues, String budgetName, int userCounter, boolean filledBudgetName, int usersNumber)
			throws BudgetNotFoundException, DatabaseNotInitialized {
		int idBudget;
		if(filledBudgetName && userCounter == usersNumber && 
				checkedExpenditures.size() > 0 && checkedSavings.size() > 0 && checkedDues.size() > 0) {
			
			databaseWriter.writeBudgetNameToDatabase(budgetName);
			idBudget = getBudgetIdFromDatabase(budgetName);
			databaseWriter.writeCategoryListTodatabase(checkedExpenditures, idBudget, EXPENDITURE_CATEGORY);
			databaseWriter.writeCategoryListTodatabase(checkedSavings, idBudget, SAVINGS_CATEGORY);
			databaseWriter.writeCategoryListTodatabase(checkedDues, idBudget, DUES_CATEGORY);
			dialog.dispose();
		}
		
		else {
			
			JOptionPane.showMessageDialog(null, message);
		
		}
	}

	private void checkIfCategoriesAreChoosen(List<String> checkedExpenditures, List<String> checkedSavings,
			List<String> checkedDues) {
		if(checkedExpenditures.size() == 0) {
			message += "Wybierz kategorie wydatków \n";
		}
		if(checkedSavings.size() == 0) {
			message += "Wybierz kategorie oszczędności \n";
		}
		if(checkedDues.size() == 0) {
			message += "Wybierz kategorie opłat ";
		}
	}

	private int checkIfUserNamesFieldsAreFilled(List<String> usersNames, int userCounter, int usersNumber) {
		if(usersNumber > 0) {
			List<JTextField> textFields = new ArrayList<>();
			Component[] components = panelAddUsersToNewBudge.getPanelForUsers().getComponents();

			for (Component component : components) {
			    if (component.getClass().equals(JTextField.class)) {
			        textFields.add(((JTextField) component));
			    }
			}
			
			for(JTextField t: textFields) {
				boolean currentFieldIsEmptyOrHasOnlySpaces = checkIfTextFieldIsEmpty(t);
				if(!currentFieldIsEmptyOrHasOnlySpaces){
					usersNames.add(t.getText());
					userCounter++;
				}
			}
			
			if(userCounter != usersNumber) {
				panelAddUsersToNewBudge.getErrorLabel().setText("Wpisz imiona użytkowników, ilość znaków > 3");
				message += "Wpisz imiona użytkowników, ilość znaków > 3 \n";
			}
			else {
				panelAddUsersToNewBudge.getErrorLabel().setText("");
			}
		}
		return userCounter;
	}

	private boolean checkIfBudgetNameFieldIsntEmpty() {
		boolean correctBudgetName;
		if(panelAddUsersToNewBudge.getTextFieldBugdetName().getText().equals("") || 
				panelAddUsersToNewBudge.getTextFieldBugdetName().getText().isEmpty() ||
				panelAddUsersToNewBudge.getTextFieldBugdetName().getText().trim().isEmpty()){
				
				message += "Wpisz nazwę budżetu \n";
				correctBudgetName = false;
		}
		else {
			correctBudgetName = true;
		}
		return correctBudgetName;
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

	public boolean checkIfTextFieldIsEmpty(JTextField textField) {
		return textField.getText() == null || 
				textField.getText().isEmpty() || 
				textField.getText().trim().isEmpty() || 
				textField.getText().length() < 3;
	}
}
