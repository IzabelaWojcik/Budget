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
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class BudgetViews extends JFrame {
	private JButton btnAddNewMonth;
	private JPanel contentPane;
	private JPanel jpanelForButtons;
	private JPanel jpanelEmpty;
	private JPanel jpanelWithAllContent;
	private JPanel jpanelForViews;
	private JPanel jpanelForAddingTransactions;
	private JPanel jpanelIncomeView;
	private JPanel jpanelExpenditureView;
	private JPanel jpanelAddIncome;
	private JScrollPane scrollPaneSavingsView;
	private JPanel jpanelSavingsView;
	private JLabel lblIncomeSum;
	private JLabel lblexpenditureSum;
	private JLabel lblSavingsSum;
	private JScrollPane scrollPaneAddExpenditure;
	private JScrollPane scrollPaneAddSavings;
	private JPanel jpanelAddExpenditure;
	private JPanel jpanelAddSavings;
	
	private PanelAddTransaction panelAddIncome, panelAddExpenditure, panelAddSavings;
	private PanelViewTransaction panelViewIncome, panelViewExpenditure, panelViewSavings;
	private IDatabaseWriter databaseWriter;
	private IDatabaseReader databaseReader;

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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1025, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		jpanelForButtons = new JPanel();
		
		JPanel panelWithCardLayout = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(jpanelForButtons, GroupLayout.PREFERRED_SIZE, 968, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelWithCardLayout, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpanelForButtons, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelWithCardLayout, GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE))
		);
		panelWithCardLayout.setLayout(new CardLayout(0, 0));
		
		jpanelEmpty = new JPanel();
		panelWithCardLayout.add(jpanelEmpty, "name_14638012768301");
		
		jpanelWithAllContent = new JPanel();
		panelWithCardLayout.add(jpanelWithAllContent, "name_14640815504738");
		
		btnAddNewMonth = new JButton("Add new month");
		
		jpanelForViews = new JPanel();
		
		jpanelForAddingTransactions = new JPanel();
		GroupLayout gl_panelWithAllContent = new GroupLayout(jpanelWithAllContent);
		gl_panelWithAllContent.setHorizontalGroup(
			gl_panelWithAllContent.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelWithAllContent.createSequentialGroup()
					.addGap(35)
					.addComponent(btnAddNewMonth)
					.addContainerGap(864, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_panelWithAllContent.createSequentialGroup()
					.addGroup(gl_panelWithAllContent.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelWithAllContent.createSequentialGroup()
							.addGap(38)
							.addComponent(jpanelForAddingTransactions, GroupLayout.PREFERRED_SIZE, 935, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_panelWithAllContent.createSequentialGroup()
							.addGap(26)
							.addComponent(jpanelForViews, GroupLayout.PREFERRED_SIZE, 947, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(49, Short.MAX_VALUE))
		);
		gl_panelWithAllContent.setVerticalGroup(
			gl_panelWithAllContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelWithAllContent.createSequentialGroup()
					.addGap(36)
					.addComponent(btnAddNewMonth)
					.addGap(24)
					.addComponent(jpanelForViews, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jpanelForAddingTransactions, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		
		JScrollPane scrollPaneAddIncome = new JScrollPane();
		
		scrollPaneAddExpenditure = new JScrollPane();
		
		scrollPaneAddSavings = new JScrollPane();
		GroupLayout gl_panelForAddingTransactions = new GroupLayout(jpanelForAddingTransactions);
		gl_panelForAddingTransactions.setHorizontalGroup(
			gl_panelForAddingTransactions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelForAddingTransactions.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneAddIncome, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
					.addGap(43)
					.addComponent(scrollPaneAddExpenditure, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
					.addGap(43)
					.addComponent(scrollPaneAddSavings, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(186, Short.MAX_VALUE))
		);
		gl_panelForAddingTransactions.setVerticalGroup(
			gl_panelForAddingTransactions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelForAddingTransactions.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelForAddingTransactions.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneAddSavings, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPaneAddExpenditure, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPaneAddIncome, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		
		jpanelAddSavings = new JPanel();
		scrollPaneAddSavings.setViewportView(jpanelAddSavings);
		
		jpanelAddExpenditure = new JPanel();
		scrollPaneAddExpenditure.setViewportView(jpanelAddExpenditure);
		
		jpanelAddIncome = new JPanel();
		scrollPaneAddIncome.setViewportView(jpanelAddIncome);
		jpanelForAddingTransactions.setLayout(gl_panelForAddingTransactions);
		
		JLabel lblIncome = new JLabel("Income:");
		
		JLabel lblExpenditure = new JLabel("Expenditure:");
		
		JLabel lblSavings = new JLabel("Savings:");
		
		JScrollPane scrollPaneIncomeView = new JScrollPane();
		
		JScrollPane scrollPaneExpenditureView = new JScrollPane();
		
		scrollPaneSavingsView = new JScrollPane();
		
		lblIncomeSum = new JLabel("New label");
		
		lblexpenditureSum = new JLabel("New label");
		
		lblSavingsSum = new JLabel("New label");
		GroupLayout gl_panelForViews = new GroupLayout(jpanelForViews);
		gl_panelForViews.setHorizontalGroup(
			gl_panelForViews.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelForViews.createSequentialGroup()
					.addGroup(gl_panelForViews.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelForViews.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelForViews.createParallelGroup(Alignment.LEADING)
								.addComponent(lblIncome)
								.addComponent(scrollPaneIncomeView, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panelForViews.createSequentialGroup()
							.addGap(25)
							.addComponent(lblIncomeSum)))
					.addGap(52)
					.addGroup(gl_panelForViews.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelForViews.createSequentialGroup()
							.addGroup(gl_panelForViews.createParallelGroup(Alignment.LEADING)
								.addComponent(lblExpenditure)
								.addComponent(scrollPaneExpenditureView, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelForViews.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelForViews.createSequentialGroup()
									.addGap(41)
									.addComponent(lblSavings))
								.addGroup(gl_panelForViews.createSequentialGroup()
									.addGap(27)
									.addGroup(gl_panelForViews.createParallelGroup(Alignment.LEADING)
										.addComponent(lblSavingsSum)
										.addComponent(scrollPaneSavingsView, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)))))
						.addComponent(lblexpenditureSum))
					.addContainerGap(291, Short.MAX_VALUE))
		);
		gl_panelForViews.setVerticalGroup(
			gl_panelForViews.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelForViews.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panelForViews.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIncome)
						.addComponent(lblExpenditure)
						.addComponent(lblSavings))
					.addGap(18)
					.addGroup(gl_panelForViews.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneExpenditureView, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPaneIncomeView, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPaneSavingsView, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelForViews.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIncomeSum)
						.addComponent(lblexpenditureSum)
						.addComponent(lblSavingsSum))
					.addGap(176))
		);
		
		jpanelSavingsView = new JPanel();
		scrollPaneSavingsView.setViewportView(jpanelSavingsView);
		
		jpanelExpenditureView = new JPanel();
		scrollPaneExpenditureView.setViewportView(jpanelExpenditureView);
		
		jpanelIncomeView = new JPanel();
		scrollPaneIncomeView.setViewportView(jpanelIncomeView);
		jpanelForViews.setLayout(gl_panelForViews);
		jpanelWithAllContent.setLayout(gl_panelWithAllContent);
		GroupLayout gl_panelForButtons = new GroupLayout(jpanelForButtons);
		gl_panelForButtons.setHorizontalGroup(
			gl_panelForButtons.createParallelGroup(Alignment.LEADING)
				.addGap(0, 948, Short.MAX_VALUE)
		);
		gl_panelForButtons.setVerticalGroup(
			gl_panelForButtons.createParallelGroup(Alignment.LEADING)
				.addGap(0, 200, Short.MAX_VALUE)
		);
		jpanelForButtons.setLayout(gl_panelForButtons);
		contentPane.setLayout(gl_contentPane);
	}
}
