package learning.views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import java.awt.Color;

public class PanelAddUsersToNewBudget extends JPanel{
	final static NumberFormat integerFieldFormatter = NumberFormat.getIntegerInstance();

	private JFormattedTextField formattedTextFieldNumberOfUsers;
	private JTextField textFieldBugdetName;
	private JPanel panelForUsers;
	private List<String> users = new ArrayList<>();
	
	private JLabel lblErrorForUsersTextFields;
	private JButton addToDatabaseButton;
	private JLabel lblErrorForBudgetNameTextField;

	public PanelAddUsersToNewBudget(JButton addToDatabaseButton) {
		this.addToDatabaseButton = addToDatabaseButton;
		this.addToDatabaseButton.setEnabled(false);
		
		JLabel labelInfo = new JLabel("Ilość użytkowników gospodarstwa domowego posiadających dochody:");
		
		JLabel labelBudgetName = new JLabel("Nazwa nowego budżetu:");
		
		lblErrorForBudgetNameTextField = new JLabel("");
		lblErrorForBudgetNameTextField.setForeground(Color.RED);
		
		textFieldBugdetName = new JTextField();
		textFieldBugdetName.setColumns(10);
		BudgetNameTextFieldListener k = new BudgetNameTextFieldListener(lblErrorForBudgetNameTextField);
		textFieldBugdetName.addKeyListener(k);
		
		JButton button = new JButton("Dodaj użytkowników");
		
		panelForUsers = new JPanel();
		panelForUsers.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		integerFieldFormatter.setMaximumFractionDigits(0);
		formattedTextFieldNumberOfUsers = new JFormattedTextField(integerFieldFormatter);
		formattedTextFieldNumberOfUsers.setColumns(10);
		
		lblErrorForUsersTextFields = new JLabel("");
		lblErrorForUsersTextFields.setForeground(Color.RED);

		CreateUsersListener createUsersListener = new CreateUsersListener(users, lblErrorForUsersTextFields, this.addToDatabaseButton);
		CreateTextFieldsForUsersListener createTextFieldsForUsersListener = new CreateTextFieldsForUsersListener(panelForUsers, formattedTextFieldNumberOfUsers, lblErrorForUsersTextFields, createUsersListener);
 		
		button.addActionListener(createTextFieldsForUsersListener);
 		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(61)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(labelInfo)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(labelBudgetName, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
								.addComponent(formattedTextFieldNumberOfUsers, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(textFieldBugdetName, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblErrorForBudgetNameTextField))))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelForUsers, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblErrorForUsersTextFields, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(32, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(labelInfo)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelBudgetName)
						.addComponent(textFieldBugdetName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblErrorForBudgetNameTextField))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(formattedTextFieldNumberOfUsers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblErrorForUsersTextFields)
						.addComponent(panelForUsers, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
					.addGap(123))
		);
		setLayout(groupLayout);
	}
	
	public JFormattedTextField getFormattedTextFieldNumberOfUsers() {
		return formattedTextFieldNumberOfUsers;
	}

	public JTextField getTextFieldBugdetName() {
		return textFieldBugdetName;
	}
	
	public JPanel getPanelForUsers() {
		return panelForUsers;
	}
	
	public JLabel getErrorLabel() {
		return lblErrorForUsersTextFields;
	}
	
	public List<String> getUsers(){
		return users;
	}
}
