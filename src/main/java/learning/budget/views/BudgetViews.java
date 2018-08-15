package learning.budget.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import learning.budget.ButtonAction;
import learning.budget.DatabaseConnection;
import learning.budget.DatabaseNotInitialized;
import learning.budget.DatabaseReader;
import learning.budget.DatabaseWriter;
import learning.budget.IDatabaseReader;
import learning.budget.IDatabaseWriter;
import learning.budget.TextFieldValidator;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.util.HashMap;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import java.awt.GridBagLayout;
import javax.swing.border.BevelBorder;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridLayout;

public class BudgetViews extends learning.budget.GenerateComponents{
	private JFrame frame;
	private JPanel panelMain;
	private JPanel panelPoprzednieBudzety;
	private CreateBudgetOptions myBudget;
	TextFieldValidator textFieldValidator;
	private IDatabaseWriter databaseWriter;
	private IDatabaseReader databaseReader;
	private JPanel panelMainBudgetsFromAllYears;
	private JPanel panelMainWithMonthsInYear;
	private JPanel panelUsersIncome;
	private JPanel panelAddOtherIncomes;
	private JButton btnAddOtherIncome;
	private JTextField textFieldOtherIncome;
	private JLabel lblIncomeOtherError;
	private JPanel panelMonthlyBudgetCardLayout;
	private JPanel panelMonthlyBudget;
	private JPanel panelMonthlyBudgetEmpty;
	private JPanel panelOtherIncomeView;
	private JScrollPane scrollPaneIncomeOtherView;
	private JScrollPane scrollPaneBudgetName;
	private JPanel panelBudgetName;
	private JButton btnBudgetForNewMonth;
	private JPanel panelExpenditure;
	private JDateChooser dateChooserExpenditureDate;
	private JTextField textFieldExpenditureAmount;
	private JLabel lblexpenditureSum;
	private JLabel lblIncomeSum;
	private JLabel lblOtherIncomeSum;
	private JScrollPane scrollPaneExpenditure;
	private JPanel panelExpenditureView;
	private JDateChooser dateChooserSavingsDate;
	private JTextField textFieldSavingsAmount;
	private JLabel lblSavingsSum;
	private JLabel lblErrorAddExpenditure;
	private JLabel lblErrorAddSavings;
	private HashMap<Integer, String> expenditureCategoryMap;
	private HashMap<Integer, String> savingsCategoryMap;
	private JPanel panelSavingsView;
	private JLabel lblInform;
	private JLabel lblSumaPozostaychPrzychodw;
	private JComboBox<String> comboBoxOtherIncome;
	private JComboBox<String> comboBoxUsers;
	private JComboBox<String> comboBoxSavingsCategory;
	private JComboBox<String> comboBoxExpenditureCategory;
	private JScrollPane scrollPaneTest;
	private JPanel panelAddExpenditure;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IDatabaseWriter databaseWriter = DatabaseWriter.getInstance();
					DatabaseWriter.setConnection(DatabaseConnection.getInstance());
					
					IDatabaseReader databaseReader = DatabaseReader.getInstance();
					DatabaseReader.setConnection(DatabaseConnection.getInstance());
					BudgetViews window = new BudgetViews(databaseReader, databaseWriter);
					window.frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BudgetViews(IDatabaseReader _databaseReader, IDatabaseWriter _databaseWriter) throws DatabaseNotInitialized {
		super(_databaseReader, _databaseWriter);
		databaseReader = _databaseReader;
		databaseWriter = _databaseWriter;
		try {

			textFieldValidator = new TextFieldValidator();

			expenditureCategoryMap = databaseReader.readCategoryFromDatabase("Expenditure_category");

			savingsCategoryMap = databaseReader.readCategoryFromDatabase("Savings_category");
			myBudget = new CreateBudgetOptions(databaseReader, databaseWriter);
			initialize();
		} catch (DatabaseNotInitialized e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initialize() throws DatabaseNotInitialized {
		frame = new JFrame();
		frame.setTitle("Bud\u017Cet domowy");
		frame.setBounds(100, 100, 1082, 898);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		mainPanel();
		poprzednieBudzetyPanel();

		///MENU
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menuPlik = new JMenu("Plik");
		menuBar.add(menuPlik);
		
		JMenuItem mntmNowyBudzetDomowy = new JMenuItem("Nowy Bud\u017Cet domowy");
		mntmNowyBudzetDomowy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myBudget.setModal(true);
				myBudget.setVisible(true);
			}
		});
		menuPlik.add(mntmNowyBudzetDomowy);
		
		JMenuItem mntmEdytujUstawieniaBudzetu = new JMenuItem("Edytuj ustawienia bud\u017Cetu");
		menuPlik.add(mntmEdytujUstawieniaBudzetu);
		////END MENU
	}
	
	public void mainPanel() throws DatabaseNotInitialized{
		panelMain = new JPanel();
		frame.getContentPane().add(panelMain, "name_8689288017164");
		
		panelMainBudgetsFromAllYears = new JPanel();
		panelMainWithMonthsInYear = new JPanel();
		
		panelMonthlyBudgetCardLayout = new JPanel();
		
		scrollPaneBudgetName = new JScrollPane();
		
		GroupLayout gl_panelMain = new GroupLayout(panelMain);
		gl_panelMain.setHorizontalGroup(
			gl_panelMain.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelMain.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelMainBudgetsFromAllYears, GroupLayout.DEFAULT_SIZE, 1229, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panelMain.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneBudgetName, GroupLayout.DEFAULT_SIZE, 1229, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panelMain.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelMainWithMonthsInYear, GroupLayout.DEFAULT_SIZE, 1229, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(panelMonthlyBudgetCardLayout, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		gl_panelMain.setVerticalGroup(
			gl_panelMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelMain.createSequentialGroup()
					.addGap(6)
					.addComponent(scrollPaneBudgetName, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelMainBudgetsFromAllYears, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelMainWithMonthsInYear, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelMonthlyBudgetCardLayout, GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE))
		);
		
		panelBudgetName = new JPanel();
		scrollPaneBudgetName.setViewportView(panelBudgetName);
		panelMonthlyBudgetCardLayout.setLayout(new CardLayout(0, 0));
		
		panelMonthlyBudget = new JPanel();
		panelMonthlyBudget.setVisible(false);
		
		panelMonthlyBudgetEmpty = new JPanel();
		panelMonthlyBudgetCardLayout.add(panelMonthlyBudgetEmpty, "name_21739435967548");
		
		panelMonthlyBudgetCardLayout.add(panelMonthlyBudget, "name_29829779746988");
		
		panelUsersIncome = new JPanel();
		panelUsersIncome.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		

		
		panelAddOtherIncomes = new JPanel();
		
		JLabel lblChooseIncome = new JLabel("Rodzaj przychodu:");
		
		JLabel lblChooseUser = new JLabel("U\u017Cytkownik:");
		
		JLabel lblValueOfAmount = new JLabel("Kwota:");
		
		btnAddOtherIncome = new JButton("Dodaj");
		btnAddOtherIncome.setEnabled(false);
		
		comboBoxOtherIncome = new JComboBox<String>();
		
		textFieldOtherIncome = new JTextField();
		textFieldOtherIncome.setColumns(10);
		
		comboBoxUsers = new JComboBox<String>();
		
		lblIncomeOtherError = new JLabel("");
		lblIncomeOtherError.setForeground(Color.RED);
		
		JDateChooser dateChooser = new JDateChooser();
		GroupLayout gl_panelAddOtherIncomes = new GroupLayout(panelAddOtherIncomes);
		gl_panelAddOtherIncomes.setHorizontalGroup(
			gl_panelAddOtherIncomes.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelAddOtherIncomes.createSequentialGroup()
					.addGroup(gl_panelAddOtherIncomes.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelAddOtherIncomes.createSequentialGroup()
							.addGap(86)
							.addComponent(lblIncomeOtherError, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
						.addGroup(gl_panelAddOtherIncomes.createSequentialGroup()
							.addGap(11)
							.addComponent(lblChooseIncome)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBoxOtherIncome, 0, 175, Short.MAX_VALUE))
						.addGroup(gl_panelAddOtherIncomes.createSequentialGroup()
							.addGap(43)
							.addGroup(gl_panelAddOtherIncomes.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblChooseUser)
								.addComponent(lblValueOfAmount))
							.addGap(18)
							.addGroup(gl_panelAddOtherIncomes.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBoxUsers, 0, 188, Short.MAX_VALUE)
								.addComponent(textFieldOtherIncome, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)))
						.addGroup(gl_panelAddOtherIncomes.createSequentialGroup()
							.addContainerGap()
							.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelAddOtherIncomes.createSequentialGroup()
							.addContainerGap(239, Short.MAX_VALUE)
							.addComponent(btnAddOtherIncome)))
					.addContainerGap())
		);
		gl_panelAddOtherIncomes.setVerticalGroup(
			gl_panelAddOtherIncomes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAddOtherIncomes.createSequentialGroup()
					.addContainerGap()
					.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelAddOtherIncomes.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxOtherIncome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblChooseIncome))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelAddOtherIncomes.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblChooseUser)
						.addComponent(comboBoxUsers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(19)
					.addGroup(gl_panelAddOtherIncomes.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblValueOfAmount)
						.addComponent(textFieldOtherIncome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddOtherIncome)
					.addGap(36)
					.addComponent(lblIncomeOtherError)
					.addGap(64))
		);
		panelAddOtherIncomes.setLayout(gl_panelAddOtherIncomes);
		scrollPaneIncomeOtherView = new JScrollPane();
		btnBudgetForNewMonth = new JButton("Utw\u00F3rz nowy miesi\u0105c");
		
		panelExpenditure = new JPanel();
		
		lblOtherIncomeSum = new JLabel("0");
		
		scrollPaneExpenditure = new JScrollPane();
		
		JPanel panelSavings = new JPanel();
		
		JScrollPane scrollPaneSavingsView = new JScrollPane();
		
		lblInform = new JLabel("");
		lblInform.setForeground(Color.BLUE);
		
		JPanel panelSum = new JPanel();
		
		lblSumaPozostaychPrzychodw = new JLabel("Suma pozosta\u0142ych przychod\u00F3w:");
		
		scrollPaneTest = new JScrollPane();
		
		panelAddExpenditure = new JPanel();
		GroupLayout gl_panelMonthlyBudget = new GroupLayout(panelMonthlyBudget);
		gl_panelMonthlyBudget.setHorizontalGroup(
			gl_panelMonthlyBudget.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelMonthlyBudget.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelMonthlyBudget.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelMonthlyBudget.createSequentialGroup()
							.addComponent(scrollPaneIncomeOtherView, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPaneExpenditure, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addComponent(scrollPaneSavingsView, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelMonthlyBudget.createSequentialGroup()
							.addGap(212)
							.addComponent(lblInform))
						.addComponent(panelSum, GroupLayout.PREFERRED_SIZE, 754, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBudgetForNewMonth)
						.addGroup(gl_panelMonthlyBudget.createSequentialGroup()
							.addGap(37)
							.addComponent(panelExpenditure, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panelSavings, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
							.addGap(56)
							.addComponent(panelAddExpenditure, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelMonthlyBudget.createSequentialGroup()
							.addComponent(lblSumaPozostaychPrzychodw)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblOtherIncomeSum, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelMonthlyBudget.createSequentialGroup()
							.addComponent(panelAddOtherIncomes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(panelUsersIncome, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
							.addGap(101)
							.addComponent(scrollPaneTest, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		gl_panelMonthlyBudget.setVerticalGroup(
			gl_panelMonthlyBudget.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelMonthlyBudget.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelMonthlyBudget.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnBudgetForNewMonth)
						.addGroup(gl_panelMonthlyBudget.createSequentialGroup()
							.addComponent(lblInform)
							.addGap(10)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelMonthlyBudget.createParallelGroup(Alignment.LEADING)
						.addComponent(panelAddExpenditure, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
						.addComponent(panelSavings, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
						.addComponent(panelExpenditure, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
					.addGap(13)
					.addComponent(panelSum, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelMonthlyBudget.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSumaPozostaychPrzychodw)
						.addComponent(lblOtherIncomeSum))
					.addGap(27)
					.addGroup(gl_panelMonthlyBudget.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelMonthlyBudget.createParallelGroup(Alignment.LEADING)
							.addComponent(scrollPaneIncomeOtherView, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
							.addComponent(scrollPaneExpenditure, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
						.addGroup(gl_panelMonthlyBudget.createSequentialGroup()
							.addComponent(scrollPaneSavingsView, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
							.addGap(24)))
					.addGap(7)
					.addGroup(gl_panelMonthlyBudget.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelMonthlyBudget.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panelAddOtherIncomes, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelMonthlyBudget.createSequentialGroup()
							.addGap(15)
							.addGroup(gl_panelMonthlyBudget.createParallelGroup(Alignment.LEADING, false)
								.addComponent(scrollPaneTest, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(panelUsersIncome, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))))
					.addContainerGap())
		);
		
		JPanel panelTest = new JPanel();
		scrollPaneTest.setViewportView(panelTest);
		
		panelSavingsView = new JPanel();
		scrollPaneSavingsView.setViewportView(panelSavingsView);
		GridBagLayout gbl_panelSavingsView = new GridBagLayout();
		gbl_panelSavingsView.columnWidths = new int[]{0};
		gbl_panelSavingsView.rowHeights = new int[]{0};
		gbl_panelSavingsView.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_panelSavingsView.rowWeights = new double[]{Double.MIN_VALUE};
		panelSavingsView.setLayout(gbl_panelSavingsView);
		
		panelExpenditureView = new JPanel();
		scrollPaneExpenditure.setViewportView(panelExpenditureView);
		GridBagLayout gbl_panelExpenditureView = new GridBagLayout();
		gbl_panelExpenditureView.columnWidths = new int[]{0};
		gbl_panelExpenditureView.rowHeights = new int[]{0};
		gbl_panelExpenditureView.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_panelExpenditureView.rowWeights = new double[]{Double.MIN_VALUE};
		panelExpenditureView.setLayout(gbl_panelExpenditureView);
		panelOtherIncomeView = new JPanel();
		scrollPaneIncomeOtherView.setViewportView(panelOtherIncomeView);
		
		GridBagLayout gbl_panelOtherIncomeView = new GridBagLayout();
		gbl_panelOtherIncomeView.columnWidths = new int[]{0, 0};
		gbl_panelOtherIncomeView.rowHeights = new int[]{0, 0};
		gbl_panelOtherIncomeView.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelOtherIncomeView.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelOtherIncomeView.setLayout(gbl_panelOtherIncomeView);
		
		JLabel lblSumaPrzychodow = new JLabel("Suma przychod\u00F3w:");
		
		lblIncomeSum = new JLabel("0");
		
		JLabel lblSumaWydatkw = new JLabel("Suma wydatk\u00F3w:");
		
		lblexpenditureSum = new JLabel("0");
		
		JLabel lblSumaOszczdnoci = new JLabel("Suma oszcz\u0119dno\u015Bci: ");
		
		lblSavingsSum = new JLabel("0");
		GroupLayout gl_panelSum = new GroupLayout(panelSum);
		gl_panelSum.setHorizontalGroup(
			gl_panelSum.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSum.createSequentialGroup()
					.addComponent(lblSumaPrzychodow)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblIncomeSum, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addGap(77)
					.addComponent(lblSumaWydatkw)
					.addGap(18)
					.addComponent(lblexpenditureSum, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addGap(43)
					.addComponent(lblSumaOszczdnoci)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblSavingsSum, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(28, Short.MAX_VALUE))
		);
		gl_panelSum.setVerticalGroup(
			gl_panelSum.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSum.createSequentialGroup()
					.addGroup(gl_panelSum.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSumaPrzychodow)
						.addComponent(lblIncomeSum)
						.addComponent(lblSumaWydatkw)
						.addComponent(lblexpenditureSum)
						.addComponent(lblSumaOszczdnoci)
						.addComponent(lblSavingsSum))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelSum.setLayout(gl_panelSum);
		
		JLabel lblData_1 = new JLabel("Data:");
		
		JLabel lblRodzajOszczdnoci = new JLabel("Rodzaj oszcz\u0119dno\u015Bci:");
		
		JLabel lblKwota_1 = new JLabel("Kwota:");
		
		dateChooserSavingsDate = new JDateChooser();
		
		comboBoxSavingsCategory = new JComboBox<String>();
		
		textFieldSavingsAmount = new JTextField();
		textFieldSavingsAmount.setColumns(10);
		
		JButton btnSavingsAdd = new JButton("Dodaj");
		
		lblErrorAddSavings = new JLabel("");
		lblErrorAddSavings.setForeground(Color.RED);
		GroupLayout gl_panelSavings = new GroupLayout(panelSavings);
		gl_panelSavings.setHorizontalGroup(
			gl_panelSavings.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSavings.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelSavings.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panelSavings.createSequentialGroup()
							.addGroup(gl_panelSavings.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblKwota_1)
								.addComponent(lblData_1)
								.addComponent(lblRodzajOszczdnoci))
							.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
							.addGroup(gl_panelSavings.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textFieldSavingsAmount)
								.addComponent(comboBoxSavingsCategory, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(dateChooserSavingsDate, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)))
						.addGroup(Alignment.TRAILING, gl_panelSavings.createSequentialGroup()
							.addComponent(lblErrorAddSavings)
							.addGap(134))
						.addComponent(btnSavingsAdd, Alignment.TRAILING))
					.addContainerGap())
		);
		gl_panelSavings.setVerticalGroup(
			gl_panelSavings.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSavings.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelSavings.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelSavings.createSequentialGroup()
							.addComponent(dateChooserSavingsDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBoxSavingsCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelSavings.createSequentialGroup()
							.addComponent(lblData_1)
							.addGap(18)
							.addComponent(lblRodzajOszczdnoci)
							.addGap(18)
							.addGroup(gl_panelSavings.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblKwota_1)
								.addComponent(textFieldSavingsAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelSavings.createParallelGroup(Alignment.LEADING)
						.addComponent(lblErrorAddSavings)
						.addComponent(btnSavingsAdd))
					.addGap(25))
		);
		panelSavings.setLayout(gl_panelSavings);
		
		dateChooserExpenditureDate = new JDateChooser();
		
		JLabel lblRodzajwydatku = new JLabel("Rodzaj Wydatku:");
		
		JLabel lblKwota = new JLabel("Kwota:");
		
		JLabel lblData = new JLabel("Data:");
		
		comboBoxExpenditureCategory = new JComboBox<String>();
		
		textFieldExpenditureAmount = new JTextField();
		textFieldExpenditureAmount.setColumns(10);
		
		JButton btnExpenditureAdd = new JButton("Dodaj");
		
		lblErrorAddExpenditure = new JLabel("");
		lblErrorAddExpenditure.setForeground(Color.RED);
		GroupLayout gl_panelExpenditure = new GroupLayout(panelExpenditure);
		gl_panelExpenditure.setHorizontalGroup(
			gl_panelExpenditure.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelExpenditure.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelExpenditure.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelExpenditure.createSequentialGroup()
							.addGroup(gl_panelExpenditure.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblData)
								.addComponent(lblRodzajwydatku)
								.addGroup(gl_panelExpenditure.createParallelGroup(Alignment.LEADING)
									.addComponent(lblErrorAddExpenditure)
									.addComponent(lblKwota)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelExpenditure.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelExpenditure.createSequentialGroup()
									.addComponent(textFieldExpenditureAmount, 169, 169, 169)
									.addContainerGap())
								.addGroup(Alignment.TRAILING, gl_panelExpenditure.createSequentialGroup()
									.addGroup(gl_panelExpenditure.createParallelGroup(Alignment.TRAILING)
										.addComponent(dateChooserExpenditureDate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
										.addComponent(comboBoxExpenditureCategory, 0, 169, Short.MAX_VALUE))
									.addContainerGap())))
						.addGroup(Alignment.TRAILING, gl_panelExpenditure.createSequentialGroup()
							.addComponent(btnExpenditureAdd)
							.addContainerGap())))
		);
		gl_panelExpenditure.setVerticalGroup(
			gl_panelExpenditure.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelExpenditure.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelExpenditure.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblData)
						.addComponent(dateChooserExpenditureDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelExpenditure.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxExpenditureCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRodzajwydatku))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelExpenditure.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldExpenditureAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblKwota))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblErrorAddExpenditure)
					.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
					.addComponent(btnExpenditureAdd)
					.addContainerGap())
		);
		panelExpenditure.setLayout(gl_panelExpenditure);
		panelMonthlyBudget.setLayout(gl_panelMonthlyBudget);
		panelMainWithMonthsInYear.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelMain.setLayout(gl_panelMain);
		
		ButtonAction buttonAction = new ButtonAction(databaseReader, databaseWriter);
		buttonAction.addOtherIncomeToDatabaseAfterClickingButton(btnAddOtherIncome, comboBoxUsers, comboBoxOtherIncome, textFieldOtherIncome, lblIncomeOtherError, dateChooser, panelOtherIncomeView, lblOtherIncomeSum);
		
		buttonAction.addExpenditureOrSavingsToDatabaseAfterClickingButton(btnExpenditureAdd, comboBoxExpenditureCategory, comboBoxUsers, textFieldExpenditureAmount, lblErrorAddExpenditure, dateChooserExpenditureDate, expenditureCategoryMap, "Expenditure", panelExpenditureView, panelSavingsView, lblexpenditureSum, lblSavingsSum);
		buttonAction.addExpenditureOrSavingsToDatabaseAfterClickingButton(btnSavingsAdd, comboBoxSavingsCategory, comboBoxUsers, textFieldSavingsAmount, lblErrorAddSavings, dateChooserSavingsDate, savingsCategoryMap, "Savings", panelExpenditureView, panelSavingsView, lblexpenditureSum, lblSavingsSum);
		generateButtonsWithBudgetsNames(panelAddExpenditure, panelBudgetName, panelMainBudgetsFromAllYears, panelMainWithMonthsInYear, panelTest, panelMonthlyBudget, panelMonthlyBudgetEmpty, panelOtherIncomeView, comboBoxUsers, comboBoxOtherIncome, comboBoxExpenditureCategory, comboBoxSavingsCategory, panelExpenditureView, panelSavingsView, lblInform, lblexpenditureSum, lblSavingsSum, lblOtherIncomeSum, lblIncomeSum);
		panelUsersIncome.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		textFieldValidator.valueIsANumberForOneTextField(textFieldOtherIncome, lblIncomeOtherError, btnAddOtherIncome);
		textFieldValidator.valueIsANumberForOneTextField(textFieldExpenditureAmount, lblErrorAddExpenditure, btnExpenditureAdd);
		textFieldValidator.valueIsANumberForOneTextField(textFieldSavingsAmount, lblErrorAddSavings, btnSavingsAdd);
	}
	
	public void poprzednieBudzetyPanel(){
		panelPoprzednieBudzety = new JPanel();
		frame.getContentPane().add(panelPoprzednieBudzety, "name_8807481040704");
		panelPoprzednieBudzety.setLayout(null);
	}
}
