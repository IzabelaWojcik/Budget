package learning.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CreateNewBudgetDialog extends JDialog {
	private final JPanel contentPanel = new JPanel();
	
	private PanelAddUsersToNewBudget panelToAddUsers;
	private PanelDuesCategoriesInNewBudget panelToChooseDuesCategories;
	private PanelExpenditureCategoriesInNewBudget panelToChooseExpenditureCategories;
	private PanelSavingsCategoriesInNewBudget panelToChooseSavingsCategories;
	private PanelWithTabbedPanes panelWithTabbedPanes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateNewBudgetDialog dialog = new CreateNewBudgetDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateNewBudgetDialog() {
		initialize();
	}
	
	public void initialize() {
		setBounds(100, 100, 600, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		panelToAddUsers = new PanelAddUsersToNewBudget();
		panelToChooseDuesCategories = new PanelDuesCategoriesInNewBudget();
		panelToChooseExpenditureCategories = new PanelExpenditureCategoriesInNewBudget();
		panelToChooseSavingsCategories = new PanelSavingsCategoriesInNewBudget();
		
		panelWithTabbedPanes = new PanelWithTabbedPanes(panelToAddUsers, panelToChooseDuesCategories, panelToChooseExpenditureCategories, panelToChooseSavingsCategories);
	
		add(panelWithTabbedPanes);
	}

}
