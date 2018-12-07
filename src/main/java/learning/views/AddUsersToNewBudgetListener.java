package learning.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddUsersToNewBudgetListener implements ActionListener {
	private static final int MAX_NUMBER_OF_USERS_IN_BUDGET = 8;
	
	private JFormattedTextField formattedTextField;
	private JPanel panelForUsers;
	private JLabel errorLabel;
	
	private List<String> users;

	public AddUsersToNewBudgetListener(JFormattedTextField formattedTextField, JPanel panelForUsers, JLabel errorMessage) {
		this.formattedTextField = formattedTextField;
		this.panelForUsers = panelForUsers;
		this.errorLabel = errorMessage;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		createUsers();
	}

	private void createUsers() {
		try {
			users = new ArrayList<>();
			int numberOfUsers = Integer.parseInt(formattedTextField.getText());
			panelForUsers.removeAll();
		
			JTextField jTextFields[] = new JTextField[numberOfUsers];;
			JLabel jLabels[] = new JLabel[numberOfUsers];
		
			int displayUserNumber = 1;
		
			errorLabel.setText("");
			
			for(int i = 0; i < jLabels.length; i++) {
				jLabels[i] = new JLabel("Użytkownik " + displayUserNumber + ":");
				jTextFields[i] = new JTextField(10);
				JTextField currentField = new JTextField(10);
				jTextFields[i] = currentField;
				panelForUsers.add(jLabels[i]);
				panelForUsers.add(jTextFields[i]);
				displayUserNumber++;
			
				jTextFields[i].addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						boolean currentFieldIsEmptyOrHasOnlySpaces = 
								checkIfTextFieldIsEmpty(currentField);
						if(!currentFieldIsEmptyOrHasOnlySpaces){
							users.add(currentField.getText().toString());
						}
					}
				});
			}

			if (numberOfUsers > MAX_NUMBER_OF_USERS_IN_BUDGET) {
				errorLabel.setText("Budżet nie może mieć wiecej niż 8 użytkowników");
				panelForUsers.removeAll();
			}
		
		} catch (NumberFormatException nfe) {
			errorLabel.setText("Wpisz liczbę całkowitą");
			panelForUsers.removeAll();
		} catch (NegativeArraySizeException excpt) {
			errorLabel.setText("Wpisz liczbę całkowitą większą od zera");
			panelForUsers.removeAll();
		}
		
		panelForUsers.revalidate();
		panelForUsers.repaint();
	}

	public List<String> getUsers() {
		return users;
	}
	
	public boolean checkIfTextFieldIsEmpty(JTextField textField) {
		return textField.getText() == null || textField.getText().isEmpty() || textField.getText().trim().isEmpty()
				|| textField.getText().length() < 2;
	}
}
