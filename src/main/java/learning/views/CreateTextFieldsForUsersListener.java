package learning.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreateTextFieldsForUsersListener implements ActionListener {
	private static final int MAX_NUMBER_OF_USERS_IN_BUDGET = 8;
	
	private JPanel panelForUsers;
	private JFormattedTextField formattedTextFieldNumberOfUsers;
	private JLabel lblError;
	private Integer numberOfUsers;
	
	public Integer getNumberOfUsers() {
		return numberOfUsers;
	}
	
	public CreateTextFieldsForUsersListener(JPanel panelForUsers, 
			JFormattedTextField formattedTextFieldNumberOfUsers, 
			JLabel lblError) {
		this.panelForUsers = panelForUsers;
		this.formattedTextFieldNumberOfUsers = formattedTextFieldNumberOfUsers;
		this.lblError = lblError;
		this.numberOfUsers = new Integer(0);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			numberOfUsers = Integer.parseInt(formattedTextFieldNumberOfUsers.getText());
			lblError.setText("");

			if (numberOfUsers > MAX_NUMBER_OF_USERS_IN_BUDGET) {
				lblError.setText("Budżet nie może mieć wiecej niż 8 użytkowników");
				panelForUsers.removeAll();
				return;
			}
			if (numberOfUsers == 0) {
				lblError.setText("Wpisz liczbę całkowitą większą od zera");
				panelForUsers.removeAll();
				return;
			}

			panelForUsers.removeAll();
			JTextField jTextField = new JTextField();
			JLabel jLabel = new JLabel();

			for(int i = 0; i < numberOfUsers; i++) {
				jLabel = new JLabel("Użytkownik " + (i + 1) + ":");
				jTextField = new JTextField(10);
				panelForUsers.add(jLabel);
				panelForUsers.add(jTextField);
			}
		
		} catch (NumberFormatException nfe) {
			lblError.setText("Wpisz liczbę całkowitą");
			panelForUsers.removeAll();
		} catch (NegativeArraySizeException excpt) {
			lblError.setText("Wpisz liczbę całkowitą większą od zera");
			panelForUsers.removeAll();
		}
		
		panelForUsers.revalidate();
		panelForUsers.repaint();
	}
}
