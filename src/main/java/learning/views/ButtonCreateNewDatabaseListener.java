package learning.views;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JButton;
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
		String budgetName = panelAddUsersToNewBudge.getTextFieldBugdetName().getText();
		
	
		int userNumbers = Integer.parseInt(panelAddUsersToNewBudge.getFormattedTextFieldNumberOfUsers().getText());

		String message = "";
		int idBudget = 0;
		try {
			
			if(checkIfBudgetNameIsUnique(budgetName) == true)
			{
				message += "Taka nazwa budżetu już istnieje \n";
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
				
			if(userNumbers > 0) {
				
				List<JTextField> textFields = new ArrayList<>();
				Component[] components = panelAddUsersToNewBudge.getPanelForUsers().getComponents();

				for (Component component : components) {
				    if (component.getClass().equals(JTextField.class)) {
				        textFields.add(((JTextField) component));
				    }
				}
				
				for(JTextField t: textFields) {
					 System.out.println(t.getText());
				}
				//jTextFields[i].addKeyListener(new KeyAdapter() {
//					@Override
//					public void keyReleased(KeyEvent e) {
//						boolean currentFieldIsEmptyOrHasOnlySpaces = 
//								checkIfTextFieldIsEmpty(currentField);
//						if(!currentFieldIsEmptyOrHasOnlySpaces){
//							users.add(currentField.getText().toString());
//						}
//					}
//				});
				
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
	//FIXME check if i use it
	public boolean checkIfTextFieldIsEmpty(JTextField textField) {
		return textField.getText() == null || textField.getText().isEmpty() || textField.getText().trim().isEmpty()
				|| textField.getText().length() < 2;
	}
}
