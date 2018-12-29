package learning.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import learning.budget.DatabaseConnection;
import learning.budget.DatabaseReader;
import learning.budget.DatabaseWriter;
import learning.budget.IDatabaseReader;
import learning.budget.IDatabaseWriter;

import javax.swing.JButton;
import java.awt.Dimension;

public class CreateNewBudgetDialog_111111 extends JDialog {
	private final JPanel contentPanel = new JPanel();
	
	private PanelAddUsersToNewBudget panelToAddUsers;
	private PanelDuesCategoriesInNewBudget panelToChooseDuesCategories;
	private PanelExpenditureCategoriesInNewBudget panelToChooseExpenditureCategories;
	private PanelSavingsCategoriesInNewBudget panelToChooseSavingsCategories;
	private PanelWithTabbedPanes panelWithTabbedPanes;
	private static PanelWithButtons panelWithBudgetButtons;
	private static int panelWithBudgetButtonsIdentifier;
	private JPanel panelButtons;
	private JButton buttonCancel;
	private JButton buttonAdd;

	public static void main(String[] args) {
		IDatabaseReader databaseReader = DatabaseReader.getInstance();
		IDatabaseWriter databaseWriter = DatabaseWriter.getInstance();
		
		DatabaseReader.setConnection(DatabaseConnection.getInstance());
		DatabaseWriter.setConnection(DatabaseConnection.getInstance());
		
		try {
			CreateNewBudgetDialog_111111 dialog = new CreateNewBudgetDialog_111111(databaseReader, databaseWriter);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CreateNewBudgetDialog_111111(IDatabaseReader databaseReader, IDatabaseWriter databaseWriter) {
		
		setTitle("Utwórz nowy budżet");
		initialize(databaseReader, databaseWriter);
	}
	
	public void initialize(IDatabaseReader databaseReader, IDatabaseWriter databaseWriter) {
		setBounds(100, 100, 600, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		//FIXME remowve main and object initialization from here
		panelToAddUsers = new PanelAddUsersToNewBudget();
		panelToChooseDuesCategories = new PanelDuesCategoriesInNewBudget();
		panelToChooseExpenditureCategories = new PanelExpenditureCategoriesInNewBudget();
		panelToChooseSavingsCategories = new PanelSavingsCategoriesInNewBudget();
		
		//FIXME it is here for refreshinb panel but that doesnt work
		panelWithBudgetButtons = new PanelWithButtons(panelWithBudgetButtonsIdentifier);
		
		panelWithTabbedPanes = new PanelWithTabbedPanes(panelToAddUsers, panelToChooseDuesCategories, panelToChooseExpenditureCategories, panelToChooseSavingsCategories);
	
		getContentPane().add(panelWithTabbedPanes);
		
		panelButtons = new JPanel();
		getContentPane().add(panelButtons, BorderLayout.SOUTH);
		
		buttonCancel = new JButton("Anuluj");
		
		ButtonCancelListener buttonCancelListener = new ButtonCancelListener(this);
		buttonCancel.addActionListener(buttonCancelListener);
		
		buttonCancel.setPreferredSize(new Dimension(80, 25));
		panelButtons.add(buttonCancel);
		
		buttonAdd = new JButton("Dodaj");
		buttonAdd.setPreferredSize(new Dimension(80, 25));
		panelButtons.add(buttonAdd);
		
		ButtonCreateNewDatabaseListener_ buttonCreateNewDatabaseListener = new ButtonCreateNewDatabaseListener_(databaseReader,
				databaseWriter, panelToAddUsers, panelToChooseDuesCategories, 
				panelToChooseExpenditureCategories, panelToChooseSavingsCategories, panelWithBudgetButtons, this);
		buttonAdd.addActionListener(buttonCreateNewDatabaseListener);
	}
}
