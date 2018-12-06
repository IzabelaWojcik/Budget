package learning.views;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import learning.budget.DatabaseNotInitialized;
import learning.budget.IDatabaseReader;
import learning.budget.IDatabaseWriter;

public class CreateNewBudgetController {
	private static final String USERS = "Users";
	private static final int MAX_NUMBER_OF_USERS_IN_BUDGET = 8;
	private IDatabaseWriter databaseWriter;
	private IDatabaseReader databaseReader;

	private List<String> users;

	public CreateNewBudgetController(IDatabaseReader databaseReader, IDatabaseWriter databaseWriter) {
		this.databaseReader = databaseReader;
		this.databaseWriter = databaseWriter;
	}

	private List<String> collectDataFromSelectedCheckBoxes(PanelExpenditureCategoriesInNewBudget panel) {
		List<String> categoriesFromCheckBoxes = panel.getCheckedCategories();
		return categoriesFromCheckBoxes;
	}
	
	private List<String> collectDataFromSelectedCheckBoxes(PanelSavingsCategoriesInNewBudget panel) {
		List<String> categoriesFromCheckBoxes = panel.getCheckedCategories();
		return categoriesFromCheckBoxes;
	}
	
	private List<String> collectDataFromSelectedCheckBoxes(PanelDuesCategoriesInNewBudget panel) {
		List<String> categoriesFromCheckBoxes = panel.getCheckedCategories();
		return categoriesFromCheckBoxes;
	}
	
	public void writeCheckedCategoriesToDatabase(List<String> categoriesFromCheckBoxes, int idBudget, String tablename) {
		databaseWriter.writeCategoryListTodatabase(categoriesFromCheckBoxes, idBudget, tablename);
	}
	
	public void writeBudgetNameToDatabase(PanelAddUsersToNewBudget panel) throws DatabaseNotInitialized {
		if(checkIfBudgetNameIsUnique(panel) == true)
		{
		databaseWriter.writeBudgetNameToDatabase(panel.getTextFieldBugdetName().getText());
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Taka nazwa budæetu juæ istnieje");
		}
	}
	
	public void writeUsersNameToDatabase(PanelAddUsersToNewBudget panel, int idBudget) {
		databaseWriter.writeUsersListTodatabase(panel.getUsersNames(), idBudget, USERS);
	}
	
	private boolean checkIfBudgetNameIsUnique(PanelAddUsersToNewBudget panel) throws DatabaseNotInitialized{
		String budgetName = panel.getTextFieldBugdetName().getText();
		HashMap<Integer, String> budgetIdNameMap = databaseReader.readBudgetIdNameFromDatabase();
		for(Entry<Integer, String> entry: budgetIdNameMap.entrySet()){
			if(budgetName.equals(entry.getValue())){
				return false;
			}
		}
		return true;
	}
	
	//TODO check this method
	private void createUsers(PanelAddUsersToNewBudget panel) {
		String usersQuantity = panel.getFormattedTextFieldNumberOfUsers().getText();
		int num = 0;
		JTextField jTextFieldsUsers[];
		JPanel panelUsers = panel.getPanelForUsers();
		try {
			panelUsers.removeAll();
			num = Integer.parseInt(usersQuantity);
			//TODO
			//lblErrorMessageUserNumber.setText("");
			JLabel jLabelsUsers[] = new JLabel[num];
			jTextFieldsUsers = new JTextField[num];
			
			int displayUserNumber = 1;

			for (int i = 0; i < jLabelsUsers.length; i++) {
				jLabelsUsers[i] = new JLabel("Uæytkownik " + displayUserNumber + ":");
				//usersMap.clear();
				jTextFieldsUsers[i] = new JTextField(10);
				JTextField currentField = new JTextField(10);
				jTextFieldsUsers[i] = currentField;
				String currentLabel = jLabelsUsers[i].getText();
				panelUsers.add(jLabelsUsers[i]);
				panelUsers.add(jTextFieldsUsers[i]);
				displayUserNumber++;

				jTextFieldsUsers[i].addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						String key = currentLabel;
						String value = currentField.getText().toString();
						boolean currentFieldIsEmptyOrHasOnlySpaces = checkIfTextFieldIsEmpty(currentField);
					}

				});
			}

			if (num >  MAX_NUMBER_OF_USERS_IN_BUDGET) {
				//TODO
				//lblErrorMessageUserNumber.setText("Budzet mone mien maksymalnie 8 uzytkowniknw");
				panelUsers.removeAll();
			}
		} catch (NumberFormatException nfe) {
			//TODO
			//lblErrorMessageUserNumber.setText("Wpisz liczbn cankowitn");
			panelUsers.removeAll();
		} catch (NegativeArraySizeException excpt) {
			//TODO
			//lblErrorMessageUserNumber.setText("Wpisz liczbn cankowitn winkszn od zera");
			panelUsers.removeAll();
		}

		panelUsers.revalidate();
		panelUsers.repaint();
	}

	public boolean checkIfTextFieldIsEmpty(JTextField textField) {
		return textField.getText() == null || textField.getText().isEmpty() || textField.getText().trim().isEmpty()
				|| textField.getText().length() < 2;
	}
}
