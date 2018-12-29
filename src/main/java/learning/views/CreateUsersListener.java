package learning.views;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class CreateUsersListener implements DocumentListener {
	private List<String> usersNames;
	private Map<Object, String> textFieldsToValues;
	private JLabel errorLabel;
	private JButton addToDatabaseButton;
	
	public CreateUsersListener(List<String> usersNames, JLabel errorLabel, JButton addUsersButton) {
		this.usersNames = usersNames;
		this.errorLabel = errorLabel;
		this.addToDatabaseButton = addUsersButton;
		this.textFieldsToValues = new HashMap<Object, String>();
	}

	public void handleFieldEdit(Object source, String userName) {
		
		if(!checkIfTextFieldIsEmpty(userName)) {
			textFieldsToValues.put(source, userName);
		}
		else {
			textFieldsToValues.put(source, null);
		}
		
		if(!textFieldsToValues.values().contains(null)) {
			usersNames.clear();
			usersNames.addAll(textFieldsToValues.values());
			errorLabel.setText("");
			addToDatabaseButton.setEnabled(true);
		}
		else {
			errorLabel.setText("Wpisz imiona użytkowników, ilość znaków > 3");
			addToDatabaseButton.setEnabled(false);
		}
	}
	
	public boolean checkIfTextFieldIsEmpty(String userName) {
		return userName == null || 
				userName.isEmpty() || 
				userName.trim().isEmpty() || 
				userName.length() < 3;
	}

	public void updateTextFields(List<JTextField> fields) {
		textFieldsToValues.clear();
		
		for(JTextField field : fields) {
			field.getDocument().addDocumentListener(this);
			textFieldsToValues.put(field.getDocument(), null);
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		update(e);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		update(e);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		update(e);
	}
	
	private void update(DocumentEvent e) {
		try {
			String userName = e.getDocument().getText(0, e.getDocument().getLength());
			handleFieldEdit(e.getDocument(), userName);
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}
}
