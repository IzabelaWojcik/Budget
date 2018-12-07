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

	public void writeBudgetNameToDatabase(String budgetName) throws DatabaseNotInitialized{
		if(checkIfBudgetNameIsUnique(budgetName) == true){
			databaseWriter.writeBudgetNameToDatabase(budgetName);
		}
		else{
			JOptionPane.showMessageDialog(null, "Taka nazwa budæetu juæ istnieje");
		}
	}
	
	private boolean checkIfBudgetNameIsUnique(String budgetName) throws DatabaseNotInitialized{
		HashMap<Integer, String> budgetIdNameMap = databaseReader.readBudgetIdNameFromDatabase();
		return budgetIdNameMap.containsValue(budgetName);
	}
	
	public int getBudgetIdFromDatabase(String budgetName) throws BudgetNotFoundException, DatabaseNotInitialized{
		HashMap<Integer, String> budgetIdNameMap = databaseReader.readBudgetIdNameFromDatabase();

		for(Entry<Integer, String> entry: budgetIdNameMap.entrySet()){
			if(budgetName.equals(entry.getValue())){
				return entry.getKey();
			}
		}
		
		throw new BudgetNotFoundException(budgetName);
	}
	
	//TODO check this method
	public void createUsers(PanelAddUsersToNewBudget panel) {
		//List<String> users = new ArrayList<String>();
		String usersQuantity = panel.getFormattedTextFieldNumberOfUsers().getText();
		int numberOfUsers = 0;
		JTextField jTextFieldsUsers[];
		JPanel panelUsers = panel.getPanelForUsers();

		panelUsers.removeAll();
		numberOfUsers = Integer.parseInt(usersQuantity);
			//TODO
			//lblErrorMessageUserNumber.setText("");
		JLabel jLabelsUsers[] = new JLabel[numberOfUsers];
		jTextFieldsUsers = new JTextField[numberOfUsers];
			
		int displayUserNumber = 1;

		for (int i = 0; i < jLabelsUsers.length; i++) {
			jLabelsUsers[i] = new JLabel("Uæytkownik " + displayUserNumber + ":");
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

			if (numberOfUsers >  MAX_NUMBER_OF_USERS_IN_BUDGET) {
				JOptionPane.showMessageDialog(null, "Maksymalna ilość użytkowników bazy jest równa 8.");
			}

		panelUsers.revalidate();
		panelUsers.repaint();
	}

	public boolean checkIfTextFieldIsEmpty(JTextField textField) {
		return textField.getText() == null || textField.getText().isEmpty() || textField.getText().trim().isEmpty()
				|| textField.getText().length() < 2;
	}
}
