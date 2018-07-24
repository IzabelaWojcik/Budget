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

public class PanelAddTransactionWithoutUsername extends JPanel{
	private JFormattedTextField textFieldAmount;
	private JLabel lblDate, lblCategory, lblAmount;
	private ErrorLabel errorLabel;
	private JDateChooser dateChooser;
	private JComboBox<String> comboBoxCategory;
	private JButton btnAdd;
	
	
	public PanelAddTransactionWithoutUsername(List<String> categoryList) {
			
		lblDate = new JLabel("Data:");
		lblCategory = new JLabel("Kategoria:");
		lblAmount = new JLabel("Kwota:");
		dateChooser = new JDateChooser();
		textFieldAmount = new JFormattedTextField(NumberFormat.getInstance());
		textFieldAmount.setColumns(10);
		comboBoxCategory = new JComboBox();
		for(String cat: categoryList) comboBoxCategory.addItem(cat);
		errorLabel = new ErrorLabel(Color.RED, new Dimension(130, 20), JLabel.LEFT);
		textFieldAmount.addPropertyChangeListener(errorLabel);
		
		btnAdd = new JButton("Dodaj");
		

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblDate)
							.addGap(5))
						.addComponent(lblAmount, Alignment.TRAILING)
						.addComponent(lblCategory, Alignment.TRAILING))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(comboBoxCategory, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(errorLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
					.addGap(20))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(217, Short.MAX_VALUE)
					.addComponent(btnAdd)
					.addGap(83))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(16)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(6)
									.addComponent(errorLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblCategory)
										.addComponent(comboBoxCategory, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblDate)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(43)
							.addComponent(btnAdd))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblAmount)
							.addComponent(textFieldAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(26))
		);
		setLayout(groupLayout);

	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		textFieldAmount.addPropertyChangeListener(listener);
	}
}
