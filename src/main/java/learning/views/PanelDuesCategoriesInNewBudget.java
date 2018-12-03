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

public class PanelDuesCategoriesInNewBudget extends JPanel{
	private JButton btncancel;
	private JTextField textFieldCategory1;
	private JTextField textFieldCategory2;
	private JTextField textFieldCategory3;
	private JTextField textFieldCategory4;
	private JTextField textFieldCategory5;
	public PanelDuesCategoriesInNewBudget() {
		
		JPanel panelForButtons = new JPanel();
		
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
		
		JLabel lblDues = new JLabel("OPŁATY");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDues)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelDues, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(panelCustomCategories, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(56, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(290, Short.MAX_VALUE)
					.addComponent(panelForButtons, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
					.addGap(36))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(29)
					.addComponent(lblDues)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelDues, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelCustomCategories, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
					.addGap(129)
					.addComponent(panelForButtons, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		
		btncancel = new JButton("Anuluj");
		panelForButtons.add(btncancel);
		btncancel.setPreferredSize(new Dimension(80, 25));
		
		JButton btnFinish = new JButton("Zakończ");
		btnFinish.setEnabled(false);
		panelForButtons.add(btnFinish);
		btnFinish.setPreferredSize(new Dimension(80, 25));
		setLayout(groupLayout);
	}
}
