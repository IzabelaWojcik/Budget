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
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class CreateNewBudgetDialog extends JDialog {
	private final JPanel contentPanel = new JPanel();
	
	private PanelAddUsersToNewBudget panelToAddUsers;
	private PanelDuesCategoriesInNewBudget panelToChooseDuesCategories;
	private PanelExpenditureCategoriesInNewBudget panelToChooseExpenditureCategories;
	private PanelSavingsCategoriesInNewBudget panelToChooseSavingsCategories;
	private PanelWithTabbedPanes panelWithTabbedPanes;
	private JPanel panelButtons;
	private JButton buttonCancel;
	private JButton buttonFinish;

	public static void main(String[] args) {
		IDatabaseReader databaseReader = DatabaseReader.getInstance();
		IDatabaseWriter databaseWriter = DatabaseWriter.getInstance();
		
		DatabaseReader.setConnection(DatabaseConnection.getInstance());
		DatabaseWriter.setConnection(DatabaseConnection.getInstance());
		
		try {
			CreateNewBudgetDialog dialog = new CreateNewBudgetDialog(databaseReader, databaseWriter);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CreateNewBudgetDialog(IDatabaseReader databaseReader, IDatabaseWriter databaseWriter) {
		
		setTitle("Utwórz nowy budżet");
		initialize(databaseReader, databaseWriter);
	}
	
	public void initialize(IDatabaseReader databaseReader, IDatabaseWriter databaseWriter) {
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
	
		getContentPane().add(panelWithTabbedPanes);
		
		panelButtons = new JPanel();
		getContentPane().add(panelButtons, BorderLayout.SOUTH);
		
		buttonCancel = new JButton("Anuluj");
		
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateNewBudgetDialog.this.dispose();
			}
		});
		
		buttonCancel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					CreateNewBudgetDialog.this.dispose();
				}
			}
		});
		
		buttonCancel.setPreferredSize(new Dimension(80, 25));
		panelButtons.add(buttonCancel);
		
		buttonFinish = new JButton("Dodaj");
		buttonFinish.setPreferredSize(new Dimension(80, 25));
		panelButtons.add(buttonFinish);
		
		CreateNewBudgetController controller = new CreateNewBudgetController(databaseReader, databaseWriter);
		ButtonCreateNewDatabaseListener buttonCreateNewDatabaseListener = new ButtonCreateNewDatabaseListener(controller, databaseReader, databaseWriter, panelToAddUsers, panelToChooseDuesCategories, panelToChooseExpenditureCategories, panelToChooseSavingsCategories);
		buttonFinish.addActionListener(buttonCreateNewDatabaseListener);
	}
}
