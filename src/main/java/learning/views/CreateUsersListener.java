package learning.views;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreateUsersListener implements PropertyChangeListener, ActionListener {
	private List<String> usersNames;
	private Map<Object, String> textFieldsToValues;
	private JLabel errorLabel;
	private JPanel panelForUsers;
	
	public CreateUsersListener(JPanel panelForUsers, List<String> usersNames, JLabel errorLabel) {
		this.usersNames = usersNames;
		this.errorLabel = errorLabel;
		this.panelForUsers = panelForUsers;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String userName = (String)evt.getNewValue();
		if(checkIfTextFieldIsEmpty(userName)) {
			textFieldsToValues.put(evt.getSource(), userName);
		}
		
		if(!textFieldsToValues.values().contains(null)) {
			usersNames.clear();
			usersNames.addAll(textFieldsToValues.values());
			errorLabel.setText("");
		}
		else {
			errorLabel.setText("Wpisz imiona użytkowników, ilość znaków > 3");
		}
	}
	
	public boolean checkIfTextFieldIsEmpty(String userName) {
		return userName == null || 
				userName.isEmpty() || 
				userName.trim().isEmpty() || 
				userName.length() < 3;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		List<JFormattedTextField> textFields = getFormattedTextFields(panelForUsers);
		
		for(JFormattedTextField field : textFields) {
			field.addPropertyChangeListener(this);
			textFieldsToValues.put(field, null);
		}
	}
	
	private List<JFormattedTextField> getFormattedTextFields(JPanel panel) {
		Component[] components = panel.getComponents();
		
		Stream<Component> componentsStream = Arrays.stream(components);
		List<JFormattedTextField> formattedTextFields = componentsStream
				.filter(c -> c.getClass().equals(JFormattedTextField.class))
				.map(c -> (JFormattedTextField) c)
				.collect(Collectors.toList());
		
		return formattedTextFields;
	}
}
