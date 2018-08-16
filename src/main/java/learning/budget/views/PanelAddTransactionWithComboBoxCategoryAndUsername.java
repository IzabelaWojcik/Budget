package learning.budget.views;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PanelAddTransactionWithComboBoxCategoryAndUsername extends JPanel{
	private  JFormattedTextField textFieldAmount;
	private ErrorLabel errorLabel;
	private JComboBox<String> comboBoxCategory;
	private JButton btnAdd;
	private JLabel lblUser;
	private JComboBox comboBoxUser;
	
	
	public PanelAddTransactionWithComboBoxCategoryAndUsername(List<String> categoryList, List<String> usersList) {
			
		JLabel lblDate = new JLabel("Data:");
		JLabel lblCategory = new JLabel("Kategoria:");
		JLabel lblAmount = new JLabel("Kwota:");
		JDateChooser dateChooser = new JDateChooser();
		textFieldAmount = new JFormattedTextField(NumberFormat.getInstance());
		textFieldAmount.setColumns(10);
		comboBoxCategory = new JComboBox();
		errorLabel = new ErrorLabel(Color.RED, new Dimension(130, 20), JLabel.LEFT);
		textFieldAmount.addPropertyChangeListener(errorLabel);
		
		btnAdd = new JButton("Dodaj");
		
		lblUser = new JLabel("Użytkownik:");
		
		for(String user: usersList) comboBoxUser.addItem(user);
		comboBoxUser = new JComboBox();

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(23)
							.addComponent(lblDate)
							.addGap(18)
							.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCategory)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(comboBoxUser, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(comboBoxCategory, Alignment.LEADING, 0, 108, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
							.addComponent(errorLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(20))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblUser)
						.addComponent(lblAmount))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textFieldAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(242, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(296, Short.MAX_VALUE)
					.addComponent(btnAdd)
					.addGap(69))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDate)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(6)
									.addComponent(errorLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblCategory)
										.addComponent(comboBoxCategory, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)))))
						.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUser)
						.addComponent(comboBoxUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAmount)
						.addComponent(textFieldAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addComponent(btnAdd)
					.addGap(27))
		);
		setLayout(groupLayout);

	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		textFieldAmount.addPropertyChangeListener(listener);
	}
}
