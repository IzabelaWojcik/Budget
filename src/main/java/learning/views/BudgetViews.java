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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Dimension;

public class BudgetViews extends JFrame {
	private static final int PANEL_WITH_BUDGET_BUTTONS_ID = 1;
	private static final int PANEL_WITH_YEARS_BUTTONS_ID = 2;
	private static final int PANEL_WITH_MONTHS_BUTTONS_ID = 3;
	private static final int PANEL_INCOME_VIEW_ID = 4;
	private static final int PANEL_EXPENDITURE_VIEW_ID = 5;
	private static final int PANEL_SAVINGS_VIEW_ID = 6;
	private static final int PANEL_ADD_INCOME_ID = 7;
	private static final int PANEL_ADD_EXPENDITURE_ID = 8;
	private static final int PANEL_ADD_SAVINGS_ID = 9;
	
	private JButton btnAddNewMonth;
	private JPanel contentPane;
	private JPanel jpanelForButtons;
	private JPanel jpanelWithAllContent;
	private JPanel jpanelForViews;
	private JPanel jpanelForAddingTransactions;
	private JPanel jpanelIncomeView;
	private JPanel jpanelExpenditureView;
	private JPanel jpanelSavingsView;
	private JLabel lblIncomeSum;
	private JLabel lblExpenditureSum;
	private JLabel lblSavingsSum;
	
	private PanelWithButtons panelBudgetButtons, panelYearsButtons, panelMonthsButtons;
	private PanelAddTransaction panelAddExpenditure, panelAddSavings;
	private PanelAddIncome panelAddIncome;
	private PanelViewTransaction panelViewIncome, panelViewExpenditure, panelViewSavings;
	private IDatabaseWriter databaseWriter;
	private IDatabaseReader databaseReader;
	private BudgetController budgetController;
	private NotificationData notificationData;
	private JScrollPane scrollPaneIncomeView;
	private JScrollPane scrollPaneExpenditureView;
	private JScrollPane scrollPaneSavingsView;

	/**
	 * Launch the application.
	 */
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
	public BudgetViews(IDatabaseReader _databaseReader, IDatabaseWriter _databaseWriter) throws DatabaseNotInitialized  {
		databaseReader = _databaseReader;
		databaseWriter = _databaseWriter;
		
		panelBudgetButtons = new PanelWithButtons(PANEL_WITH_BUDGET_BUTTONS_ID);
		panelYearsButtons = new PanelWithButtons(PANEL_WITH_YEARS_BUTTONS_ID);
		panelMonthsButtons = new PanelWithButtons(PANEL_WITH_MONTHS_BUTTONS_ID);
		
		panelViewIncome = new PanelViewTransaction();
		panelViewExpenditure = new PanelViewTransaction();
		panelViewSavings = new PanelViewTransaction();
		
		panelAddIncome = new PanelAddIncome(PANEL_ADD_INCOME_ID);
		panelAddExpenditure = new PanelAddTransaction(PANEL_ADD_EXPENDITURE_ID);
		panelAddSavings = new PanelAddTransaction(PANEL_ADD_SAVINGS_ID);
		
		lblIncomeSum = new JLabel("New label");
		lblExpenditureSum = new JLabel("New label");
		lblSavingsSum = new JLabel("New label");
		
		budgetController = new BudgetController(databaseReader, databaseWriter,
				panelBudgetButtons, panelYearsButtons, panelMonthsButtons,
				panelAddExpenditure, panelAddSavings, panelAddIncome, 
				panelViewExpenditure, panelViewSavings, panelViewIncome,
				lblExpenditureSum, lblSavingsSum, lblIncomeSum);
			
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1045, 880);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		jpanelForButtons = new JPanel();
		
		GroupLayout gl_panelForButtons = new GroupLayout(jpanelForButtons);
		gl_panelForButtons.setHorizontalGroup(
			gl_panelForButtons.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelForButtons.createSequentialGroup()
					.addContainerGap(38, Short.MAX_VALUE)
					.addGroup(gl_panelForButtons.createParallelGroup(Alignment.LEADING)
						.addComponent(panelMonthsButtons, GroupLayout.PREFERRED_SIZE, 934, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelYearsButtons, GroupLayout.PREFERRED_SIZE, 934, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelBudgetButtons, GroupLayout.PREFERRED_SIZE, 934, GroupLayout.PREFERRED_SIZE))
					.addGap(946))
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
					.addContainerGap(54, Short.MAX_VALUE))
		);
		jpanelForButtons.setLayout(gl_panelForButtons);
		
		jpanelWithAllContent = new JPanel();
		
		btnAddNewMonth = new JButton("Add new month");
		
		jpanelForViews = new JPanel();
		
		jpanelForAddingTransactions = new JPanel();
		GroupLayout gl_panelWithAllContent = new GroupLayout(jpanelWithAllContent);
		gl_panelWithAllContent.setHorizontalGroup(
			gl_panelWithAllContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelWithAllContent.createSequentialGroup()
					.addGroup(gl_panelWithAllContent.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelWithAllContent.createSequentialGroup()
							.addGap(35)
							.addComponent(btnAddNewMonth))
						.addGroup(gl_panelWithAllContent.createSequentialGroup()
							.addGap(26)
							.addGroup(gl_panelWithAllContent.createParallelGroup(Alignment.LEADING)
								.addComponent(jpanelForAddingTransactions, GroupLayout.PREFERRED_SIZE, 935, GroupLayout.PREFERRED_SIZE)
								.addComponent(jpanelForViews, GroupLayout.PREFERRED_SIZE, 947, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panelWithAllContent.setVerticalGroup(
			gl_panelWithAllContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelWithAllContent.createSequentialGroup()
					.addGap(36)
					.addComponent(btnAddNewMonth)
					.addGap(24)
					.addComponent(jpanelForViews, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jpanelForAddingTransactions, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(22, Short.MAX_VALUE))
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
					.addContainerGap(124, Short.MAX_VALUE))
		);
		gl_panelForAddingTransactions.setVerticalGroup(
			gl_panelForAddingTransactions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelForAddingTransactions.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelForAddingTransactions.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panelAddSavings, GroupLayout.PREFERRED_SIZE, 190, Short.MAX_VALUE)
						.addComponent(panelAddIncome, 0, 0, Short.MAX_VALUE)
						.addComponent(panelAddExpenditure, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		jpanelForAddingTransactions.setLayout(gl_panelForAddingTransactions);
		
		JLabel lblIncome = new JLabel("Income:");
		
		JLabel lblExpenditure = new JLabel("Expenditure:");
		
		JLabel lblSavings = new JLabel("Savings:");
		
		scrollPaneIncomeView = new JScrollPane();
		scrollPaneIncomeView.setPreferredSize(new Dimension(210, 20));
		
		scrollPaneExpenditureView = new JScrollPane();
		
		scrollPaneSavingsView = new JScrollPane();
		scrollPaneSavingsView.setPreferredSize(new Dimension(210, 20));
		
		GroupLayout gl_panelForViews = new GroupLayout(jpanelForViews);
		gl_panelForViews.setHorizontalGroup(
			gl_panelForViews.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelForViews.createSequentialGroup()
					.addGroup(gl_panelForViews.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panelForViews.createSequentialGroup()
							.addGap(25)
							.addComponent(lblIncomeSum))
						.addGroup(gl_panelForViews.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelForViews.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPaneIncomeView, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblIncome))))
					.addGap(6)
					.addGroup(gl_panelForViews.createParallelGroup(Alignment.LEADING)
						.addComponent(lblExpenditure)
						.addGroup(gl_panelForViews.createSequentialGroup()
							.addGap(14)
							.addGroup(gl_panelForViews.createParallelGroup(Alignment.LEADING)
								.addComponent(lblExpenditureSum)
								.addComponent(scrollPaneExpenditureView, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))))
					.addGroup(gl_panelForViews.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelForViews.createSequentialGroup()
							.addGap(88)
							.addComponent(lblSavings))
						.addGroup(gl_panelForViews.createSequentialGroup()
							.addGap(43)
							.addComponent(lblSavingsSum))
						.addGroup(gl_panelForViews.createSequentialGroup()
							.addGap(18)
							.addComponent(scrollPaneSavingsView, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)))
					.addGap(238))
		);
		gl_panelForViews.setVerticalGroup(
			gl_panelForViews.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelForViews.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelForViews.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIncome)
						.addComponent(lblExpenditure)
						.addComponent(lblSavings))
					.addGap(18)
					.addGroup(gl_panelForViews.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneSavingsView, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
						.addComponent(scrollPaneIncomeView, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
						.addComponent(scrollPaneExpenditureView, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelForViews.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIncomeSum)
						.addComponent(lblExpenditureSum)
						.addComponent(lblSavingsSum))
					.addGap(176))
		);
		
		jpanelSavingsView = new JPanel();
		scrollPaneSavingsView.setViewportView(panelViewSavings);
		
		jpanelExpenditureView = new JPanel();
		scrollPaneExpenditureView.setViewportView(panelViewExpenditure);
		
		jpanelIncomeView = new JPanel();
		scrollPaneIncomeView.setViewportView(panelViewIncome);
		
		jpanelForViews.setLayout(gl_panelForViews);
		jpanelWithAllContent.setLayout(gl_panelWithAllContent);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(jpanelWithAllContent, GroupLayout.PREFERRED_SIZE, 982, GroupLayout.PREFERRED_SIZE)
						.addComponent(jpanelForButtons, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpanelForButtons, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jpanelWithAllContent, GroupLayout.PREFERRED_SIZE, 612, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		contentPane.setLayout(gl_contentPane);
	}
}
