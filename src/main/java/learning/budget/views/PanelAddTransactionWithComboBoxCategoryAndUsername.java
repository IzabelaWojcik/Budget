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
	private ErrorLabelPropertyChangeListener errorLabel;
	private JComboBox<String> comboBoxUser;
	
	public PanelAddTransactionWithComboBoxCategoryAndUsername(List<String> categoryList, List<String> usersList) {
		JLabel lblDate = new JLabel("Data:");
		JLabel lblCategory = new JLabel("Kategoria:");
		JLabel lblAmount = new JLabel("Kwota:");
		JLabel lblUser = new JLabel("Użytkownik:");
		JDateChooser dateChooser = new JDateChooser();
		JComboBox<String> comboBoxCategory = new JComboBox<String>();
		JButton btnAdd = new JButton("Dodaj");
		comboBoxUser = new JComboBox<String>();
		
		errorLabel = new ErrorLabelPropertyChangeListener(Color.RED, new Dimension(130, 20), JLabel.LEFT);
		
		textFieldAmount = new JFormattedTextField(NumberFormat.getInstance());
		textFieldAmount.setColumns(10);
		textFieldAmount.addPropertyChangeListener(errorLabel);
		
		
		for (String category : categoryList) {
			comboBoxCategory.addItem(category);
		}
		
		for(String user: usersList) {
			comboBoxUser.addItem(user);
		}
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
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
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(errorLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(20))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnAdd)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblUser)
								.addComponent(lblAmount))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textFieldAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(151, Short.MAX_VALUE))
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
					.addGap(18)
					.addComponent(btnAdd)
					.addGap(38))
		);
		setLayout(groupLayout);

	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		textFieldAmount.addPropertyChangeListener(listener);
	}
}
