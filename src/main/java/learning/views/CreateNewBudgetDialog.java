package learning.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import learning.budget.DatabaseNotInitialized;

public class CreateNewBudgetDialog extends JDialog implements INotifier{
	public final int identifier;
	private Set<IListener> listeners;

	private PanelAddUsersToNewBudget panelToAddUsers;
	private PanelDuesCategoriesInNewBudget panelToChooseDuesCategories;
	private PanelExpenditureCategoriesInNewBudget panelToChooseExpenditureCategories;
	private PanelSavingsCategoriesInNewBudget panelToChooseSavingsCategories;
	private PanelWithTabbedPanes panelWithTabbedPanes;
	
	private JPanel panelButtons;
	private JButton buttonCancel;
	private JButton buttonAdd;
	
	public CreateNewBudgetDialog(int id) {
		identifier = id;
		listeners = new HashSet<IListener>();
		
		setBounds(100, 100, 600, 600);
		getContentPane().setLayout(new BorderLayout());
		
		buttonAdd = new JButton("Dodaj");
		buttonAdd.addActionListener(e -> {listeners.stream().forEach(listener -> {
			try {
				listener.notify(
						new ButtonCreateNewBudgetData(identifier, 
								panelToChooseExpenditureCategories.getCheckedCategories(),
								panelToChooseSavingsCategories.getCheckedCategories(), 
								panelToChooseDuesCategories.getCheckedCategories(), 
								panelToAddUsers.getUsers(), 
								panelToAddUsers.getTextFieldBugdetName().getText(),
								this));
			} catch (DatabaseNotInitialized | BudgetNotFoundException e1) {
				e1.printStackTrace();
			}});
		});
		
		panelToAddUsers = new PanelAddUsersToNewBudget(buttonAdd);
		panelToChooseDuesCategories = new PanelDuesCategoriesInNewBudget();
		panelToChooseExpenditureCategories = new PanelExpenditureCategoriesInNewBudget();
		panelToChooseSavingsCategories = new PanelSavingsCategoriesInNewBudget();
	
		panelWithTabbedPanes = new PanelWithTabbedPanes(panelToAddUsers, panelToChooseDuesCategories, panelToChooseExpenditureCategories, panelToChooseSavingsCategories);
		getContentPane().add(panelWithTabbedPanes);
		
		panelButtons = new JPanel();
		getContentPane().add(panelButtons, BorderLayout.SOUTH);
		
		buttonCancel = new JButton("Anuluj");
		
		ButtonCancelListener buttonCancelListener = new ButtonCancelListener(this);
		buttonCancel.addActionListener(buttonCancelListener);
		
		buttonCancel.setPreferredSize(new Dimension(80, 25));
		panelButtons.add(buttonCancel);
	
		buttonAdd.setPreferredSize(new Dimension(80, 25));
		panelButtons.add(buttonAdd);
	}
	
	@Override
	public void register(IListener listener) {
		listeners.add(listener);
	}

	@Override
	public void deregister(IListener listener) {
		listeners.remove(listener);
	}
}
