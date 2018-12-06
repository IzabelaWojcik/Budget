package learning.views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.util.List;
import javax.swing.JFormattedTextField;

public class PanelAddUsersToNewBudget extends JPanel{
	private JFormattedTextField formattedTextFieldNumberOfUsers;
	private JTextField textFieldBugdetName;
	private List<String> usersNames;
	private JPanel panelForUsers;

	public PanelAddUsersToNewBudget() {
		
		JLabel labelInfo = new JLabel("Ilość użytkowników gospodarstwa domowego posiadających dochody:");
		
		JLabel labelBudgetName = new JLabel("Nazwa nowego budżetu:");
		
		textFieldBugdetName = new JTextField();
		textFieldBugdetName.setColumns(10);
		
		JButton button = new JButton("Dodaj użytkowników");
		
		panelForUsers = new JPanel();
		panelForUsers.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		formattedTextFieldNumberOfUsers = new JFormattedTextField();
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
								.addComponent(textFieldBugdetName, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)))
						.addComponent(panelForUsers, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(129, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(59, Short.MAX_VALUE)
					.addComponent(labelInfo)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelBudgetName)
						.addComponent(textFieldBugdetName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(formattedTextFieldNumberOfUsers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addGap(35)
					.addComponent(panelForUsers, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
					.addGap(79))
		);
		setLayout(groupLayout);
	}
	
	public List<String> getUsersNames() {
		return usersNames;
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

}
