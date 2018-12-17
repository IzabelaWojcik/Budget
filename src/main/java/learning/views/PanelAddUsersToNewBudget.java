package learning.views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import java.awt.Color;

public class PanelAddUsersToNewBudget extends JPanel{
	private static final int MAX_NUMBER_OF_USERS_IN_BUDGET = 8;
	
	private JFormattedTextField formattedTextFieldNumberOfUsers;
	private JTextField textFieldBugdetName;
	private JPanel panelForUsers;
	
	final static NumberFormat integerFieldFormatter = NumberFormat.getIntegerInstance();
	private JLabel lblError;

	public PanelAddUsersToNewBudget() {
		
		JLabel labelInfo = new JLabel("Ilość użytkowników gospodarstwa domowego posiadających dochody:");
		
		JLabel labelBudgetName = new JLabel("Nazwa nowego budżetu:");
		
		textFieldBugdetName = new JTextField();
		textFieldBugdetName.setColumns(10);
		
		JButton button = new JButton("Dodaj użytkowników");
		
		panelForUsers = new JPanel();
		panelForUsers.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		integerFieldFormatter.setMaximumFractionDigits(0);
		formattedTextFieldNumberOfUsers = new JFormattedTextField(integerFieldFormatter);
		formattedTextFieldNumberOfUsers.setColumns(10);
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		
 		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createTextFieldsForUsers();
				
			}
		});
 		
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
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelForUsers, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblError, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(18, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(labelInfo)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelBudgetName)
						.addComponent(textFieldBugdetName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(formattedTextFieldNumberOfUsers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblError)
							.addGap(30))
						.addComponent(panelForUsers, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
					.addGap(123))
		);
		setLayout(groupLayout);
	}
	

	private void createTextFieldsForUsers() {
		try {
			int numberOfUsers = Integer.parseInt(formattedTextFieldNumberOfUsers.getText());
			panelForUsers.removeAll();
		
			JTextField jTextFields[] = new JTextField[numberOfUsers];;
			JLabel jLabels[] = new JLabel[numberOfUsers];
		
			int displayUserNumber = 1;
		
			lblError.setText("");
			
			for(int i = 0; i < jLabels.length; i++) {
				jLabels[i] = new JLabel("Użytkownik " + displayUserNumber + ":");
				jTextFields[i] = new JTextField(10);
				panelForUsers.add(jLabels[i]);
				panelForUsers.add(jTextFields[i]);
				displayUserNumber++;
			}

			if (numberOfUsers > MAX_NUMBER_OF_USERS_IN_BUDGET) {
				lblError.setText("Budżet nie może mieć wiecej niż 8 użytkowników");
				panelForUsers.removeAll();
			}
			if (numberOfUsers == 0) {
				lblError.setText("Wpisz liczbę całkowitą większą od zera");
				panelForUsers.removeAll();
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
		return lblError;
	}
}
