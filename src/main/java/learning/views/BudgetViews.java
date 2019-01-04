package learning.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import learning.budget.DatabaseConnection;
import learning.budget.DatabaseNotInitialized;
import learning.budget.DatabaseReader;
import learning.budget.DatabaseWriter;
import learning.budget.IDatabaseReader;
import learning.budget.IDatabaseWriter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JButton;

public class BudgetViews extends JFrame {
	private static final int PANEL_WITH_BUDGET_BUTTONS_ID = 1;
	private static final int PANEL_WITH_YEARS_BUTTONS_ID = 2;
	private static final int PANEL_WITH_MONTHS_BUTTONS_ID = 3;
	private static final int PANEL_ADD_INCOME_ID = 4;
	private static final int PANEL_ADD_EXPENDITURE_ID = 5;
	private static final int PANEL_ADD_SAVINGS_ID = 6;
	private static final int PANEL_ADD_DUES_ID = 7;
	private static final int DIALOG_ADD_NEW_MONTH_ID = 8;
	private static final int DIALOG_CREATE_NEW_BUDGET_ID = 9;
	
	private JPanel contentPane;
	private JPanel jpanelForButtons;
	private JPanel jpanelWithAllContent;
	private JPanel jpanelForViews;
	private JPanel jpanelForAddingTransactions;
	private JLabel lblIncomeSum;
	private JLabel lblExpenditureSum;
	private JLabel lblSavingsSum;
	private AddNewYearMonthDialog addNewMonthDialog;
	
	private PanelWithButtons panelBudgetButtons, panelYearsButtons, panelMonthsButtons;
	private PanelAddTransaction panelAddExpenditure, panelAddSavings, panelAddDues;
	private PanelAddIncome panelAddIncome;
	private PanelViewTransaction panelViewIncome, panelViewExpenditure, panelViewSavings, panelViewDues;
	private BudgetController budgetController;
	private JScrollPane scrollPaneIncomeView;
	private JScrollPane scrollPaneExpenditureView;
	private JScrollPane scrollPaneSavingsView;
	private CreateNewBudgetDialog createNewBudgetDialog;
	private JButton btnNewMonth;
	private JScrollPane scrollPaneDues;
	private JLabel lblDuesSum;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IDatabaseWriter databaseWriter = DatabaseWriter.getInstance();
					DatabaseWriter.setConnection(DatabaseConnection.getInstance());
					
					IDatabaseReader databaseReader = DatabaseReader.getInstance();
					DatabaseReader.setConnection(DatabaseConnection.getInstance());
					
					BudgetViews frame = new BudgetViews(databaseReader, databaseWriter);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BudgetViews(IDatabaseReader databaseReader, IDatabaseWriter databaseWriter) throws DatabaseNotInitialized  {
		
		panelBudgetButtons = new PanelWithButtons(PANEL_WITH_BUDGET_BUTTONS_ID);
		panelYearsButtons = new PanelWithButtons(PANEL_WITH_YEARS_BUTTONS_ID);
		panelMonthsButtons = new PanelWithButtons(PANEL_WITH_MONTHS_BUTTONS_ID);
		
		panelViewIncome = new PanelViewTransaction();
		panelViewExpenditure = new PanelViewTransaction();
		panelViewSavings = new PanelViewTransaction();
		panelViewSavings.setPreferredSize(new Dimension(206, 188));
		panelViewDues = new PanelViewTransaction();
		
		panelAddIncome = new PanelAddIncome(PANEL_ADD_INCOME_ID);
		panelAddExpenditure = new PanelAddTransaction(PANEL_ADD_EXPENDITURE_ID);
		panelAddSavings = new PanelAddTransaction(PANEL_ADD_SAVINGS_ID);
		panelAddDues = new PanelAddTransaction(PANEL_ADD_DUES_ID);
		
		lblIncomeSum = new JLabel("");
		lblExpenditureSum = new JLabel("");
		lblSavingsSum = new JLabel("");
		lblDuesSum = new JLabel("");
		
		btnNewMonth = new JButton("Dodaj nowy miesiąc");
		btnNewMonth.setEnabled(false);
		
		createNewBudgetDialog = new CreateNewBudgetDialog(DIALOG_CREATE_NEW_BUDGET_ID);
		addNewMonthDialog = new AddNewYearMonthDialog(DIALOG_ADD_NEW_MONTH_ID);
		
		budgetController = new BudgetController(databaseReader, databaseWriter,
				panelBudgetButtons, panelYearsButtons, panelMonthsButtons,
				panelAddExpenditure, panelAddSavings, panelAddIncome, panelAddDues, 
				panelViewExpenditure, panelViewSavings, panelViewIncome, panelViewDues, 
				lblExpenditureSum, lblSavingsSum, lblIncomeSum, lblDuesSum,
				btnNewMonth, addNewMonthDialog, createNewBudgetDialog);
		
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1204, 880);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Opcje");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmCreateNewBudget = new JMenuItem("Stwórz nowy budżet");
		mnNewMenu.add(mntmCreateNewBudget);
		
		SetModalListener setNewBudgetModalListener = new SetModalListener(createNewBudgetDialog);
		mntmCreateNewBudget.addActionListener(setNewBudgetModalListener);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		jpanelForButtons = new JPanel();
		
		SetModalListener setNewMonthModalListener = new SetModalListener(addNewMonthDialog);
		btnNewMonth.addActionListener(setNewMonthModalListener);
		
		GroupLayout gl_panelForButtons = new GroupLayout(jpanelForButtons);
		gl_panelForButtons.setHorizontalGroup(
			gl_panelForButtons.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelForButtons.createSequentialGroup()
					.addContainerGap(38, Short.MAX_VALUE)
					.addGroup(gl_panelForButtons.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnNewMonth)
						.addComponent(panelBudgetButtons, GroupLayout.DEFAULT_SIZE, 1123, Short.MAX_VALUE)
						.addComponent(panelYearsButtons, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panelMonthsButtons, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(757))
		);
		gl_panelForButtons.setVerticalGroup(
			gl_panelForButtons.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelForButtons.createSequentialGroup()
					.addGap(36)
					.addComponent(panelBudgetButtons, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelYearsButtons, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelMonthsButtons, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewMonth)
					.addContainerGap(16, Short.MAX_VALUE))
		);
		jpanelForButtons.setLayout(gl_panelForButtons);
		
		jpanelWithAllContent = new JPanel();
		
		jpanelForViews = new JPanel();
		
		jpanelForAddingTransactions = new JPanel();
		GroupLayout gl_panelWithAllContent = new GroupLayout(jpanelWithAllContent);
		gl_panelWithAllContent.setHorizontalGroup(
			gl_panelWithAllContent.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelWithAllContent.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelWithAllContent.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelWithAllContent.createSequentialGroup()
							.addComponent(jpanelForAddingTransactions, GroupLayout.DEFAULT_SIZE, 1223, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panelWithAllContent.createSequentialGroup()
							.addComponent(jpanelForViews, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(22))))
		);
		gl_panelWithAllContent.setVerticalGroup(
			gl_panelWithAllContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelWithAllContent.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpanelForViews, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(22)
					.addComponent(jpanelForAddingTransactions, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		
		GroupLayout gl_panelForAddingTransactions = new GroupLayout(jpanelForAddingTransactions);
		gl_panelForAddingTransactions.setHorizontalGroup(
			gl_panelForAddingTransactions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelForAddingTransactions.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelAddIncome, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(panelAddExpenditure, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(panelAddSavings, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(panelAddDues, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(594, Short.MAX_VALUE))
		);
		gl_panelForAddingTransactions.setVerticalGroup(
			gl_panelForAddingTransactions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelForAddingTransactions.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelForAddingTransactions.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panelAddSavings, GroupLayout.PREFERRED_SIZE, 190, Short.MAX_VALUE)
						.addComponent(panelAddIncome, 0, 0, Short.MAX_VALUE)
						.addComponent(panelAddExpenditure, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelAddDues, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
						)
					.addContainerGap(212, Short.MAX_VALUE))
		);
		
		jpanelForAddingTransactions.setLayout(gl_panelForAddingTransactions);
		
		JLabel lblIncome = new JLabel("Dochód:");
		
		JLabel lblExpenditure = new JLabel("Wydatki:");
		
		JLabel lblSavings = new JLabel("Oszczędności:");
		
		JLabel lblDues = new JLabel("Opłaty:");
		
		scrollPaneIncomeView = new JScrollPane();
		scrollPaneIncomeView.setPreferredSize(new Dimension(210, 20));
		
		scrollPaneExpenditureView = new JScrollPane();
		
		scrollPaneSavingsView = new JScrollPane();
		scrollPaneSavingsView.setPreferredSize(new Dimension(210, 20));
		
		scrollPaneDues = new JScrollPane();
		scrollPaneDues.setPreferredSize(new Dimension(210, 20));
		
		GroupLayout gl_panelForViews = new GroupLayout(jpanelForViews);
		gl_panelForViews.setHorizontalGroup(
			gl_panelForViews.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelForViews.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelForViews.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneIncomeView, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIncome)
						.addComponent(lblIncomeSum))
					.addGap(54)
					.addGroup(gl_panelForViews.createParallelGroup(Alignment.LEADING)
						.addComponent(lblExpenditureSum)
						.addComponent(lblExpenditure)
						.addComponent(scrollPaneExpenditureView, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
					.addGap(56)
					.addGroup(gl_panelForViews.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSavings)
						.addComponent(scrollPaneSavingsView, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSavingsSum))
					.addGap(58)
					.addGroup(gl_panelForViews.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneDues, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDues, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDuesSum))
					.addGap(115))
		);
		gl_panelForViews.setVerticalGroup(
			gl_panelForViews.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelForViews.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelForViews.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIncome)
						.addComponent(lblExpenditure)
						.addComponent(lblSavings)
						.addComponent(lblDues))
					.addGap(18)
					.addGroup(gl_panelForViews.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelForViews.createSequentialGroup()
							.addGroup(gl_panelForViews.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPaneExpenditureView, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPaneIncomeView, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelForViews.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblIncomeSum)
								.addComponent(lblExpenditureSum))
							.addGap(192))
						.addGroup(gl_panelForViews.createSequentialGroup()
							.addGroup(gl_panelForViews.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(scrollPaneSavingsView, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(scrollPaneDues, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelForViews.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDuesSum)
								.addComponent(lblSavingsSum))
							.addContainerGap())))
		);
		
		scrollPaneDues.setViewportView(panelViewDues);
		
		scrollPaneSavingsView.setViewportView(panelViewSavings);
		
		scrollPaneExpenditureView.setViewportView(panelViewExpenditure);
		
		scrollPaneIncomeView.setViewportView(panelViewIncome);
		
		jpanelForViews.setLayout(gl_panelForViews);
		jpanelWithAllContent.setLayout(gl_panelWithAllContent);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(jpanelWithAllContent, GroupLayout.PREFERRED_SIZE, 1204, GroupLayout.PREFERRED_SIZE)
						.addComponent(jpanelForButtons, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(19, Short.MAX_VALUE)
					.addComponent(jpanelForButtons, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jpanelWithAllContent, GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		contentPane.setLayout(gl_contentPane);
	}
}
