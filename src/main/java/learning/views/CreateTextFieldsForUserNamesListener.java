package learning.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreateTextFieldsForUserNamesListener implements ActionListener {
	private static final int MAX_NUMBER_OF_USERS_IN_BUDGET = 8;
	
	private JFormattedTextField formattedTextField;
	private JPanel panelForUsers;
	private JLabel errorLabel;
	
	public CreateTextFieldsForUserNamesListener(JFormattedTextField formattedTextField, JPanel panelForUsers, JLabel errorMessage) {
		this.formattedTextField = formattedTextField;
		this.panelForUsers = panelForUsers;
		this.errorLabel = errorMessage;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		createTextFieldsForUsers();
	}

	private void createTextFieldsForUsers() {
		try {
			int numberOfUsers = Integer.parseInt(formattedTextField.getText());
			panelForUsers.removeAll();
		
			JTextField jTextFields[] = new JTextField[numberOfUsers];;
			JLabel jLabels[] = new JLabel[numberOfUsers];
		
			int displayUserNumber = 1;
		
			errorLabel.setText("");
			
			for(int i = 0; i < jLabels.length; i++) {
				jLabels[i] = new JLabel("Użytkownik " + displayUserNumber + ":");
				jTextFields[i] = new JTextField(10);
				panelForUsers.add(jLabels[i]);
				panelForUsers.add(jTextFields[i]);
				displayUserNumber++;
			}

			if (numberOfUsers > MAX_NUMBER_OF_USERS_IN_BUDGET) {
				errorLabel.setText("Budżet nie może mieć wiecej niż 8 użytkowników");
				panelForUsers.removeAll();
			}
			if (numberOfUsers == 0) {
				errorLabel.setText("Wpisz liczbę całkowitą większą od zera");
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
}
