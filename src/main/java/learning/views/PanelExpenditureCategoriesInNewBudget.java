package learning.views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.border.TitledBorder;
import java.awt.Rectangle;
import java.awt.Point;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Color;

public class PanelExpenditureCategoriesInNewBudget extends JPanel{
	private JTextField textFieldCategory1;
	private JTextField textFieldCategory2;
	private JTextField textFieldCategory3;
	private JTextField textFieldCategory4;
	private JTextField textFieldCategory5;
	public PanelExpenditureCategoriesInNewBudget() {
		
		JPanel panelCustomCategories = new JPanel();
		panelCustomCategories.setBorder(new TitledBorder(null, "Wpisz w\u0142asne kategorie", TitledBorder.LEADING,
						TitledBorder.TOP, null, null));
		
		JCheckBox checkBoxCategory1 = new JCheckBox("Kategoria 1:");
		
		textFieldCategory1 = new JTextField();
		textFieldCategory1.setEnabled(false);
		textFieldCategory1.setColumns(10);
		
		JCheckBox checkBoxCategory2 = new JCheckBox("Kategoria 2:");
		checkBoxCategory2.setEnabled(false);
		
		textFieldCategory2 = new JTextField();
		textFieldCategory2.setEnabled(false);
		textFieldCategory2.setColumns(10);
		
		JCheckBox checkBoxCategory3 = new JCheckBox("Kategoria 3:");
		checkBoxCategory3.setEnabled(false);
		
		textFieldCategory3 = new JTextField();
		textFieldCategory3.setEnabled(false);
		textFieldCategory3.setColumns(10);
		
		JCheckBox checkBoxCategory4 = new JCheckBox("Kategoria 4:");
		checkBoxCategory4.setEnabled(false);
		
		textFieldCategory4 = new JTextField();
		textFieldCategory4.setEnabled(false);
		textFieldCategory4.setColumns(10);
		
		JCheckBox checkBoxCategory5 = new JCheckBox("Kategoria 5:");
		checkBoxCategory5.setEnabled(false);
		
		textFieldCategory5 = new JTextField();
		textFieldCategory5.setEnabled(false);
		textFieldCategory5.setColumns(10);
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
		
		JLabel lblExpenditure = new JLabel("WYDATKI");
		
		JPanel panelExpenditures = new JPanel();
		panelExpenditures.setBounds(new Rectangle(20, 20, 20, 0));
		panelExpenditures.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Wybierz wydatki:",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JCheckBox checkBoxShopping = new JCheckBox("Zakupy");
		
		JCheckBox checkBoxFood = new JCheckBox("Jedzenie");
		
		JCheckBox checkBoxDomesticDetergents = new JCheckBox("Chemia");
		
		JCheckBox checkBoxFuel = new JCheckBox("Paliwo");
		
		JCheckBox checkBoxCommute = new JCheckBox("Dojazdy");
		
		JCheckBox checkBoxAnimals = new JCheckBox("Zwierzęta");
		
		JCheckBox checkBoxFastFood = new JCheckBox("Fast Food");
		
		JCheckBox checkBoxSport = new JCheckBox("Sport");
		
		JCheckBox checkBoxKultureEntertaiment = new JCheckBox("Kultura/rozrywka");
		
		JCheckBox checkBoxEquipment = new JCheckBox("Sprzęty");
		
		JCheckBox checkBoxChildren = new JCheckBox("Dzieci");
		
		JCheckBox checkBoxBooksTextBooks = new JCheckBox("Podręczniki/zeszyty");
		
		JCheckBox checkBoxMedicines = new JCheckBox("Leki");
		GroupLayout gl_panelExpenditures = new GroupLayout(panelExpenditures);
		gl_panelExpenditures.setHorizontalGroup(
			gl_panelExpenditures.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelExpenditures.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelExpenditures.createParallelGroup(Alignment.LEADING)
						.addComponent(checkBoxShopping)
						.addComponent(checkBoxFood)
						.addComponent(checkBoxDomesticDetergents)
						.addComponent(checkBoxFuel)
						.addComponent(checkBoxCommute)
						.addComponent(checkBoxAnimals)
						.addComponent(checkBoxFastFood)
						.addComponent(checkBoxSport)
						.addComponent(checkBoxKultureEntertaiment)
						.addComponent(checkBoxEquipment)
						.addComponent(checkBoxChildren)
						.addComponent(checkBoxBooksTextBooks)
						.addComponent(checkBoxMedicines))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		gl_panelExpenditures.setVerticalGroup(
			gl_panelExpenditures.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelExpenditures.createSequentialGroup()
					.addComponent(checkBoxShopping)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBoxFood)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBoxDomesticDetergents)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBoxFuel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBoxCommute)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBoxAnimals)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBoxFastFood)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBoxSport)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBoxKultureEntertaiment)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBoxEquipment)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBoxChildren)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBoxBooksTextBooks)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBoxMedicines)
					.addContainerGap(27, Short.MAX_VALUE))
		);
		panelExpenditures.setLayout(gl_panelExpenditures);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblExpenditure)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelExpenditures, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(panelCustomCategories, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(56, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addComponent(lblExpenditure)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelCustomCategories, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelExpenditures, GroupLayout.PREFERRED_SIZE, 352, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(85, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}
