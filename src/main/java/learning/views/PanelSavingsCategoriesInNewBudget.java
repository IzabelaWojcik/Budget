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
import javax.swing.JCheckBox;
import javax.swing.UIManager;
import java.awt.Color;

public class PanelSavingsCategoriesInNewBudget extends JPanel{
	private List<String> checkedSavingsCategories;
	
	public PanelSavingsCategoriesInNewBudget() {
		
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
		
		JLabel lblSavings = new JLabel("OSZCZĘDNOŚCI");
		
		JPanel panelSavings = new JPanel();
		panelSavings.setBounds(new Rectangle(20, 20, 20, 0));
		panelSavings.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
						"Wybierz oszcz\u0119dno\u015Bci:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JCheckBox checkBoxRenovation = new JCheckBox("Remonty");
		
		JCheckBox checkBoxFurnitures = new JCheckBox("Meble");
		
		JCheckBox checkBoxElectronicEquipment = new JCheckBox("Sprzęty elektroniczne");
		
		JCheckBox checkBoxHollidays = new JCheckBox("Wakacje");
		
		JCheckBox checkBoxGifts = new JCheckBox("Prezenty");
		
		JCheckBox checkBoxForChildren = new JCheckBox("Dla dziecka");
		
		JCheckBox checkBoxWedding = new JCheckBox("Ślub");
		
		JCheckBox checkBoxCar = new JCheckBox("Samochód");
		
		checkedSavingsCategories = new ArrayList<>();
		
		CheckBoxItemStateListener itemListener = new CheckBoxItemStateListener(checkedSavingsCategories);
		
		checkBoxRenovation.addItemListener(itemListener);
		checkBoxFurnitures.addItemListener(itemListener);
		checkBoxElectronicEquipment.addItemListener(itemListener);
		checkBoxHollidays.addItemListener(itemListener);
		checkBoxGifts.addItemListener(itemListener);
		checkBoxForChildren.addItemListener(itemListener);
		checkBoxWedding.addItemListener(itemListener);
		checkBoxCar.addItemListener(itemListener);
		
		checkBoxCategory1.addItemListener(new CheckBoxItemListener(textFieldCategory1));
		checkBoxCategory2.addItemListener(new CheckBoxItemListener(textFieldCategory2));
		checkBoxCategory3.addItemListener(new CheckBoxItemListener(textFieldCategory3));
		checkBoxCategory4.addItemListener(new CheckBoxItemListener(textFieldCategory4));
		checkBoxCategory5.addItemListener(new CheckBoxItemListener(textFieldCategory5));
		
		TextFieldKeyListener keyListener = new TextFieldKeyListener(checkedSavingsCategories);
		textFieldCategory1.addKeyListener(keyListener);
		textFieldCategory2.addKeyListener(keyListener);
		textFieldCategory3.addKeyListener(keyListener);
		textFieldCategory4.addKeyListener(keyListener);
		textFieldCategory5.addKeyListener(keyListener);
		
		GroupLayout gl_panelSavings = new GroupLayout(panelSavings);
		gl_panelSavings.setHorizontalGroup(
			gl_panelSavings.createParallelGroup(Alignment.LEADING)
				.addGap(0, 186, Short.MAX_VALUE)
				.addGroup(gl_panelSavings.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelSavings.createParallelGroup(Alignment.LEADING)
						.addComponent(checkBoxRenovation)
						.addComponent(checkBoxFurnitures)
						.addComponent(checkBoxElectronicEquipment)
						.addComponent(checkBoxHollidays)
						.addComponent(checkBoxGifts)
						.addComponent(checkBoxForChildren)
						.addComponent(checkBoxWedding)
						.addComponent(checkBoxCar))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		gl_panelSavings.setVerticalGroup(
			gl_panelSavings.createParallelGroup(Alignment.LEADING)
				.addGap(0, 240, Short.MAX_VALUE)
				.addGroup(gl_panelSavings.createSequentialGroup()
					.addContainerGap()
					.addComponent(checkBoxRenovation)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBoxFurnitures)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBoxElectronicEquipment)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBoxHollidays)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBoxGifts)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBoxForChildren)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBoxWedding)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBoxCar)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelSavings.setLayout(gl_panelSavings);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSavings)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelSavings, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(panelCustomCategories, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(56, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addComponent(lblSavings)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelCustomCategories, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelSavings, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(197, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}
