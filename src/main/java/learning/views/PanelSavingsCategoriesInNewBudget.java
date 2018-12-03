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

public class PanelSavingsCategoriesInNewBudget extends JPanel{
	private JTextField textFieldCategory1;
	private JTextField textFieldCategory2;
	private JTextField textFieldCategory3;
	private JTextField textFieldCategory4;
	private JTextField textFieldCategory5;
	public PanelSavingsCategoriesInNewBudget() {
		
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
