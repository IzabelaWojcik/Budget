package learning.views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.TitledBorder;
import java.awt.Rectangle;
import java.awt.Point;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class PanelDuesCategoriesInNewBudget extends JPanel{
	private List<String> checkedDuesCategories;
	
	public PanelDuesCategoriesInNewBudget() {
		
		JPanel panelDues = new JPanel();
		panelDues.setLocation(new Point(10, 10));
		panelDues.setBounds(new Rectangle(20, 20, 20, 0));
		panelDues.setBorder(new TitledBorder(null, "Wybierz op\u0142aty:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JCheckBox checkBoxRent = new JCheckBox("Czynsz");
		
		JCheckBox checkBoxPower = new JCheckBox("Prąd");
		
		JCheckBox checkBoxInternet = new JCheckBox("Internet");
		
		JCheckBox checkBoxPhone = new JCheckBox("Opłaty za telefon");
		
		JCheckBox checkBoxloan = new JCheckBox("Pożyczka");
		
		JCheckBox checkBoxCreditCard = new JCheckBox("Spłata karty kredytowej");
		checkBoxCreditCard.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JCheckBox checkBoxMortgage = new JCheckBox("Kredyt");
		
		JCheckBox checkBoxRenovationFund = new JCheckBox("Fundusz remontowy");
		
		GroupLayout gl_panelDues = new GroupLayout(panelDues);
		gl_panelDues.setHorizontalGroup(
			gl_panelDues.createParallelGroup(Alignment.LEADING)
				.addGap(0, 186, Short.MAX_VALUE)
				.addGroup(gl_panelDues.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelDues.createParallelGroup(Alignment.LEADING)
						.addComponent(checkBoxRent)
						.addComponent(checkBoxPower)
						.addComponent(checkBoxInternet)
						.addComponent(checkBoxPhone)
						.addComponent(checkBoxloan)
						.addComponent(checkBoxCreditCard)
						.addComponent(checkBoxMortgage)
						.addComponent(checkBoxRenovationFund))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panelDues.setVerticalGroup(
			gl_panelDues.createParallelGroup(Alignment.LEADING)
				.addGap(0, 249, Short.MAX_VALUE)
				.addGroup(gl_panelDues.createSequentialGroup()
					.addContainerGap()
					.addComponent(checkBoxRent)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBoxPower)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBoxRenovationFund)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBoxInternet)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBoxPhone)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBoxloan)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBoxCreditCard)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBoxMortgage)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		panelDues.setLayout(gl_panelDues);
		
		JPanel panelCustomCategories = new JPanel();
		panelCustomCategories.setBorder(new TitledBorder(null, "Wpisz w\u0142asne kategorie", TitledBorder.LEADING,
						TitledBorder.TOP, null, null));
		
		JCheckBox checkBoxCategory1 = new JCheckBox("Kategoria 1:");
		
		JTextField textFieldCategory1 = new JTextField();
		textFieldCategory1.setEnabled(false);
		textFieldCategory1.setColumns(10);
		
		JCheckBox checkBoxCategory2 = new JCheckBox("Kategoria 2:");
		
		JTextField textFieldCategory2 = new JTextField();
		textFieldCategory2.setEnabled(false);
		textFieldCategory2.setColumns(10);
		
		JCheckBox checkBoxCategory3 = new JCheckBox("Kategoria 3:");
		
		JTextField textFieldCategory3 = new JTextField();
		textFieldCategory3.setEnabled(false);
		textFieldCategory3.setColumns(10);
		
		JCheckBox checkBoxCategory4 = new JCheckBox("Kategoria 4:");
		
		JTextField textFieldCategory4 = new JTextField();
		textFieldCategory4.setEnabled(false);
		textFieldCategory4.setColumns(10);
		
		JCheckBox checkBoxCategory5 = new JCheckBox("Kategoria 5:");
		
		JTextField textFieldCategory5 = new JTextField();
		textFieldCategory5.setEnabled(false);
		textFieldCategory5.setColumns(10);
		
		checkedDuesCategories = new ArrayList<>();
		
		CheckBoxItemStateListener itemListener = new CheckBoxItemStateListener(checkedDuesCategories);
		
		checkBoxRent.addItemListener(itemListener); 
		checkBoxPower.addItemListener(itemListener);
		checkBoxInternet.addItemListener(itemListener);
		checkBoxPhone.addItemListener(itemListener);
		checkBoxloan.addItemListener(itemListener);
		checkBoxCreditCard.addItemListener(itemListener);
		checkBoxMortgage.addItemListener(itemListener);
		checkBoxRenovationFund.addItemListener(itemListener);
		
		checkBoxCategory1.addItemListener(new CheckBoxItemListener(textFieldCategory1));
		checkBoxCategory2.addItemListener(new CheckBoxItemListener(textFieldCategory2));
		checkBoxCategory3.addItemListener(new CheckBoxItemListener(textFieldCategory3));
		checkBoxCategory4.addItemListener(new CheckBoxItemListener(textFieldCategory4));
		checkBoxCategory5.addItemListener(new CheckBoxItemListener(textFieldCategory5));
		
		TextFieldKeyListener keyListener = new TextFieldKeyListener(checkedDuesCategories);
		textFieldCategory1.addKeyListener(keyListener);
		textFieldCategory2.addKeyListener(keyListener);
		textFieldCategory3.addKeyListener(keyListener);
		textFieldCategory4.addKeyListener(keyListener);
		textFieldCategory5.addKeyListener(keyListener);
		
		GroupLayout gl_panelCustomCategories = new GroupLayout(panelCustomCategories);
		gl_panelCustomCategories.setHorizontalGroup(
			gl_panelCustomCategories.createParallelGroup(Alignment.LEADING)
				.addGap(0, 266, Short.MAX_VALUE)
				.addGroup(gl_panelCustomCategories.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCustomCategories.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panelCustomCategories.createSequentialGroup()
							.addComponent(checkBoxCategory1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldCategory1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelCustomCategories.createSequentialGroup()
							.addComponent(checkBoxCategory2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldCategory2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelCustomCategories.createSequentialGroup()
							.addComponent(checkBoxCategory3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldCategory3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelCustomCategories.createSequentialGroup()
							.addComponent(checkBoxCategory4)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldCategory4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelCustomCategories.createSequentialGroup()
							.addComponent(checkBoxCategory5)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(textFieldCategory5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		gl_panelCustomCategories.setVerticalGroup(
			gl_panelCustomCategories.createParallelGroup(Alignment.LEADING)
				.addGap(0, 200, Short.MAX_VALUE)
				.addGroup(gl_panelCustomCategories.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCustomCategories.createParallelGroup(Alignment.BASELINE)
						.addComponent(checkBoxCategory1)
						.addComponent(textFieldCategory1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCustomCategories.createParallelGroup(Alignment.BASELINE)
						.addComponent(checkBoxCategory2)
						.addComponent(textFieldCategory2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCustomCategories.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldCategory3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkBoxCategory3))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelCustomCategories.createParallelGroup(Alignment.BASELINE)
						.addComponent(checkBoxCategory4)
						.addComponent(textFieldCategory4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelCustomCategories.createParallelGroup(Alignment.BASELINE)
						.addComponent(checkBoxCategory5)
						.addComponent(textFieldCategory5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelCustomCategories.setLayout(gl_panelCustomCategories);
		
		JLabel lblDues = new JLabel("OPŁATY");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDues)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelDues, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(panelCustomCategories, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(56, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addComponent(lblDues)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelDues, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelCustomCategories, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(188, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	
	public List<String> getCheckedDuesCategories() {
		return checkedDuesCategories;
	}
}
