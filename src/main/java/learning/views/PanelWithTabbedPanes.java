package learning.views;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.sun.glass.events.KeyEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class PanelWithTabbedPanes extends JPanel{
	
	public PanelWithTabbedPanes(PanelAddUsersToNewBudget panelToAddUsers,
			PanelDuesCategoriesInNewBudget panelToChooseDuesCategories,
			PanelExpenditureCategoriesInNewBudget panelToChooseExpenditureCategories,
			PanelSavingsCategoriesInNewBudget panelToChooseSavingsCategories) {
		
		JTabbedPane tabbedPane = new JTabbedPane();

		tabbedPane.addTab("Użytkownicy", panelToAddUsers);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		tabbedPane.addTab("Opłaty", panelToChooseDuesCategories);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		tabbedPane.addTab("Wydatki", panelToChooseExpenditureCategories);
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

		tabbedPane.addTab("Oszczędności", panelToChooseSavingsCategories);
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
					.addContainerGap())
		);
		setLayout(groupLayout);
	}
	
}
