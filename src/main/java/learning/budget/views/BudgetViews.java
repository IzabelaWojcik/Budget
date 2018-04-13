package learning.budget.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import learning.budget.ButtonAction;
//import classes.ButtonAction;
import learning.budget.ComboBoxAction;
import learning.budget.DatabaseConnection;
import learning.budget.DatabaseReader;
import learning.budget.DatabaseWriter;
import learning.budget.ExpendiutureObject;
import learning.budget.GenerateComponents;
import learning.budget.IDatabaseReader;
import learning.budget.IDatabaseWriter;
import learning.budget.Sort;
import learning.budget.TextFieldAction;
import learning.budget.TextFieldValidator;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.HierarchyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.awt.event.HierarchyEvent;
import java.awt.Dimension;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.border.TitledBorder;
import javax.xml.soap.Text;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.Insets;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDayChooser;

public class BudgetViews extends learning.budget.GenerateComponents{

	private JFrame frame;
	private JPanel panelMain;
	private JPanel panelPoprzednieBudzety;
	private CreateBudgetOptions myBudget;
//	private CreateBudgetForNewMonth createBudgetForNewMonth = new CreateBudgetForNewMonth();
	TextFieldValidator textFieldValidator = new TextFieldValidator();
	private IDatabaseWriter databaseWriter;
	private IDatabaseReader databaseReader;
	private JPanel panelMainBudgetsFromAllYears;
	//private GenerateComponents generateComponents = new GenerateComponents();
	private JPanel panelMainWithMonthsInYear;
	private JPanel panelUsersIncome;
	private JPanel panelUsers;
	private JPanel panelIncome;
	private JPanel panelAddOtherIncomes;
	private JComboBox comboBoxOtherIncome;
	private JButton btnAddOtherIncome;
	private JTextField textFieldOtherIncome;
	private JComboBox comboBoxUsers;
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
	private JComboBox comboBoxExpenditureCategory;
	private JTextField textFieldExpenditureAmount;
	private JLabel lblexpenditureSum;
	private JLabel lblIncomeSum;
	private JLabel lblOtherIncomeSum;
	private JScrollPane scrollPaneExpenditure;
	private JPanel panelExpenditureView;
	private JDateChooser dateChooserSavingsDate;
	private JComboBox comboBoxSavingsCategory;
	private JTextField textFieldSavingsAmount;
	private JLabel lblSavingsSum;
	private JLabel lblErrorAddExpenditure;
	private JLabel lblErrorAddSavings;
	private HashMap<Integer, String> expenditureCategoryMap;
	private HashMap<Integer, String> savingsCategoryMap;
	private JPanel panelSavingsView;
	private JLabel lblInform;
	private JLabel lblSumaPozostaychPrzychodw;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BudgetViews window = new BudgetViews(new DatabaseReader(new DatabaseConnection()), new DatabaseWriter(new DatabaseConnection()));
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BudgetViews(IDatabaseReader _databaseReader, IDatabaseWriter _databaseWriter) {
		super(_databaseReader, _databaseWriter);
		databaseReader = _databaseReader;
		databaseWriter = _databaseWriter;
		
		expenditureCategoryMap = databaseReader.readCategoryFromDatabase("Expenditure_category");
		savingsCategoryMap = databaseReader.readCategoryFromDatabase("Savings_category");
		 myBudget = new CreateBudgetOptions(databaseReader, databaseWriter);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
	
	
	public void mainPanel(){
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
		panelUsers = new JPanel();
		panelIncome = new JPanel();
		
		panelUsersIncome = new JPanel();
		
		
		GroupLayout gl_panelUsersIncome = new GroupLayout(panelUsersIncome);
		gl_panelUsersIncome.setHorizontalGroup(
			gl_panelUsersIncome.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelUsersIncome.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelUsers, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelIncome, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panelUsersIncome.setVerticalGroup(
			gl_panelUsersIncome.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelUsersIncome.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelUsersIncome.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelIncome, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
						.addComponent(panelUsers, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
					.addContainerGap())
		);
		panelUsersIncome.setLayout(gl_panelUsersIncome);
		
		panelAddOtherIncomes = new JPanel();
		
		JLabel lblChooseIncome = new JLabel("Rodzaj przychodu:");
		
		JLabel lblChooseUser = new JLabel("U\u017Cytkownik:");
		
		JLabel lblValueOfAmount = new JLabel("Kwota:");
		
		btnAddOtherIncome = new JButton("Dodaj");
		btnAddOtherIncome.setEnabled(false);
		
		
		comboBoxOtherIncome = new JComboBox();
		
		textFieldOtherIncome = new JTextField();
		textFieldOtherIncome.setColumns(10);
		
		comboBoxUsers = new JComboBox();
		
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
		GroupLayout gl_panelMonthlyBudget = new GroupLayout(panelMonthlyBudget);
		gl_panelMonthlyBudget.setHorizontalGroup(
			gl_panelMonthlyBudget.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelMonthlyBudget.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelMonthlyBudget.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelMonthlyBudget.createSequentialGroup()
							.addGap(212)
							.addComponent(lblInform))
						.addComponent(panelSum, GroupLayout.PREFERRED_SIZE, 754, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBudgetForNewMonth)
						.addGroup(gl_panelMonthlyBudget.createSequentialGroup()
							.addComponent(scrollPaneIncomeOtherView, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(scrollPaneExpenditure, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(scrollPaneSavingsView, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelMonthlyBudget.createSequentialGroup()
							.addComponent(lblSumaPozostaychPrzychodw)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblOtherIncomeSum, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
						.addComponent(panelAddOtherIncomes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelMonthlyBudget.createSequentialGroup()
							.addComponent(panelUsersIncome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(panelExpenditure, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panelSavings, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(265, Short.MAX_VALUE))
		);
		gl_panelMonthlyBudget.setVerticalGroup(
			gl_panelMonthlyBudget.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelMonthlyBudget.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelMonthlyBudget.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblInform)
						.addComponent(btnBudgetForNewMonth))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelMonthlyBudget.createParallelGroup(Alignment.LEADING)
						.addComponent(panelSavings, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panelExpenditure, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
						.addComponent(panelUsersIncome, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
					.addGap(13)
					.addComponent(panelSum, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_panelMonthlyBudget.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelMonthlyBudget.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPaneSavingsView, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
						.addGroup(gl_panelMonthlyBudget.createSequentialGroup()
							.addGap(12)
							.addComponent(scrollPaneIncomeOtherView, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelMonthlyBudget.createSequentialGroup()
							.addGap(18)
							.addComponent(scrollPaneExpenditure, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelMonthlyBudget.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSumaPozostaychPrzychodw)
						.addComponent(lblOtherIncomeSum))
					.addGap(18)
					.addComponent(panelAddOtherIncomes, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
					.addGap(313))
		);
		
		
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
		
		comboBoxSavingsCategory = new JComboBox();
		
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
		
		comboBoxExpenditureCategory = new JComboBox();
		
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
		
		TextFieldAction txFielsAction = new TextFieldAction();
		ButtonAction buttonAction = new ButtonAction(databaseReader, databaseWriter);
		
		txFielsAction.checkIfTextFieldHaveNumberValue(textFieldOtherIncome, lblIncomeOtherError, btnAddOtherIncome);
		txFielsAction.checkIfTextFieldHaveNumberValue(textFieldExpenditureAmount, lblErrorAddExpenditure, btnExpenditureAdd);
		txFielsAction.checkIfTextFieldHaveNumberValue(textFieldSavingsAmount, lblErrorAddSavings, btnSavingsAdd);
		buttonAction.addOtherIncomeToDatabaseAfterClickingButton(btnAddOtherIncome, comboBoxUsers, comboBoxOtherIncome, textFieldOtherIncome, lblIncomeOtherError, dateChooser, panelOtherIncomeView, lblOtherIncomeSum);
		generateButtonsWithBudgetsNames(panelBudgetName, panelMainBudgetsFromAllYears, panelMainWithMonthsInYear, panelUsers, panelIncome, panelMonthlyBudget, panelMonthlyBudgetEmpty, panelOtherIncomeView, comboBoxUsers, comboBoxOtherIncome, comboBoxExpenditureCategory, comboBoxSavingsCategory, panelExpenditureView, panelSavingsView, lblInform, lblexpenditureSum, lblSavingsSum, lblOtherIncomeSum, lblIncomeSum);
		buttonAction.addExpenditureOrSavingsToDatabaseAfterClickingButton(btnExpenditureAdd, comboBoxExpenditureCategory, comboBoxUsers, textFieldExpenditureAmount, lblErrorAddExpenditure, dateChooserExpenditureDate, expenditureCategoryMap, "Expenditure", panelExpenditureView, panelSavingsView, lblexpenditureSum, lblSavingsSum);
		buttonAction.addExpenditureOrSavingsToDatabaseAfterClickingButton(btnSavingsAdd, comboBoxSavingsCategory, comboBoxUsers, textFieldSavingsAmount, lblErrorAddSavings, dateChooserSavingsDate, savingsCategoryMap, "Savings", panelExpenditureView, panelSavingsView, lblexpenditureSum, lblSavingsSum);
		
		
		
		
		
	
	}
	
	public void poprzednieBudzetyPanel(){
		panelPoprzednieBudzety = new JPanel();
		frame.getContentPane().add(panelPoprzednieBudzety, "name_8807481040704");
		panelPoprzednieBudzety.setLayout(null);
	}
	
	
	private void setPanelMainVisible(){
		panelMain.setVisible(true);
		panelPoprzednieBudzety.setVisible(false);
	}
	
	private void setPanelOldBudgetsVisible(){
		panelMain.setVisible(false);
		panelPoprzednieBudzety.setVisible(true);
	}
}
