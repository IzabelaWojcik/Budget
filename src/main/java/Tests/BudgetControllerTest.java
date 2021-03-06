package Tests;

import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.javatuples.Triplet;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import learning.budget.BudgetDate;
import learning.budget.DatabaseNotInitialized;
import learning.budget.IDatabaseReader;
import learning.budget.IDatabaseWriter;
import learning.budget.Transaction;
import learning.budget.UsersObject;
import learning.views.BudgetController;
import learning.views.BudgetNotFoundException;
import learning.views.ButtonAddIncomeData;
import learning.views.ButtonAddTransactionData;
import learning.views.ButtonsData;
import learning.views.CreateNewBudgetDialog;
import learning.views.AddNewYearMonthDialog;
import learning.views.PanelAddIncome;
import learning.views.PanelAddTransaction;
import learning.views.PanelViewTransaction;
import learning.views.PanelWithButtons;

public class BudgetControllerTest {
	private static final LocalDate DATE5 = LocalDate.parse("2015-05-15");
	private static final LocalDate DATE4 = LocalDate.parse("2017-12-01");
	private static final LocalDate DATE3 = LocalDate.parse("2017-03-17");
	private static final LocalDate DATE2 = LocalDate.parse("2017-02-14");
	private static final LocalDate DATE1 = LocalDate.parse("2016-01-01");
	
	private static final int year1 = 2015;
	private static final int year2 = 2016;
	private static final int year3 = 2017;
	private static final int month1 = 1;
	private static final int month2 = 2;
	private static final int month3 = 3;
	private static final int month4 = 5;
	private static final int month5 = 12;

	class DBEntry<N, I> {
		public final N name;
		public final I id;

		DBEntry(N n, I id) {
			name = n;
			this.id = id;
		}
	}

	final DBEntry<String, Integer> budget1 = new DBEntry<String, Integer>("budzet1", 1);
	final DBEntry<String, Integer> budget2 = new DBEntry<String, Integer>("budzet2", 2);
	final DBEntry<String, Integer> budget3 = new DBEntry<String, Integer>("budzet3", 3);

	final BudgetDate budgetDate1 = new BudgetDate(budget1.id, year1, month4);
	final BudgetDate budgetDate2 = new BudgetDate(budget1.id, year3, month5);
	final BudgetDate budgetDate3 = new BudgetDate(budget1.id, year3, month3);
	final BudgetDate budgetDate4 = new BudgetDate(budget1.id, year3, month2);
	final BudgetDate budgetDate5 = new BudgetDate(budget1.id, year2, month1);
	
	final DBEntry<String, Integer> category1 = new DBEntry<String, Integer>("zakupy", 1);
	final DBEntry<String, Integer> category2 = new DBEntry<String, Integer>("oplaty", 2);
	final DBEntry<String, Integer> category3 = new DBEntry<String, Integer>("ksiazki", 3);

	final DBEntry<String, Integer> user1 = new DBEntry<String, Integer>("Iza", 1);
	final DBEntry<String, Integer> user2 = new DBEntry<String, Integer>("Piotr", 2);
	final DBEntry<String, Integer> user3 = new DBEntry<String, Integer>("Ania", 3);
	final DBEntry<String, Integer> user4 = new DBEntry<String, Integer>("Kuba", 4);
	
	final HashMap<Integer, String> budgetIdToName = new HashMap<Integer, String>() {{put(budget1.id, budget1.name); 
																				     put(budget2.id, budget2.name); 
																				     put(budget3.id, budget3.name);}};
	
	private final List<BudgetDate> budgetDates = new ArrayList<BudgetDate>() {{
		add(budgetDate1); 
		add(budgetDate2);
		add(budgetDate3);
		add(budgetDate4);
		add(budgetDate5);
	}};
	
	private final List<UsersObject> userNamesIdsBudgetIds = new ArrayList<UsersObject>() {
		{
			add(new UsersObject(user1.id, user1.name, budget1.id));
			add(new UsersObject(user2.id, user2.name, budget2.id));
			add(new UsersObject(user3.id, user3.name, budget1.id));
			add(new UsersObject(user4.id, user4.name, budget1.id));
		}
	};

	final int transactionId = 123;
	final double amount = 10.0;

	private Map<Integer, String> categories;
	private List<Transaction> expenditures, savings, income;
	private List<Triplet<String, String, String>> listOfTripletsExpenditure, listOfTripletsSavings, listOfTripletsIncome;
	private BudgetController budgetController;
	private JButton btnAddNewMonth;

	private JLabel lblExpenditureSum, lblSavingsSum, lblIncomeSum, lblDuesSum;
	@Mock
	IDatabaseReader databaseReaderForTest;
	@Mock
	IDatabaseWriter databaseWriterForTest;
	@Mock
	PanelWithButtons panelWithBudget;
	@Mock
	PanelWithButtons panelWithYears;
	@Mock
	PanelWithButtons panelWithMonths;
	@Mock
	PanelAddTransaction panelAddExpenditure;
	@Mock
	PanelAddTransaction panelAddSavings;
	@Mock
	PanelAddIncome panelAddIncome;
	@Mock
	PanelAddTransaction panelAddDues;
	@Mock
	PanelViewTransaction panelDuesView;
	@Mock
	PanelViewTransaction panelExpenditureView;
	@Mock
	PanelViewTransaction panelSavingsView;
	@Mock
	PanelViewTransaction panelIncomeView;
	@Mock
	AddNewYearMonthDialog addNewMonthJDialog;
	@Mock
	CreateNewBudgetDialog createNewBudgetDialog;
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Before
	public void setup() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, DatabaseNotInitialized, ParseException {
		lblExpenditureSum = new JLabel();
		lblSavingsSum = new JLabel();
		lblIncomeSum = new JLabel();
		lblDuesSum = new JLabel();
		btnAddNewMonth = new JButton();

		when(databaseReaderForTest.readBudgetIdNameFromDatabase()).thenReturn(budgetIdToName);
		when(databaseReaderForTest.readUsersFromDatabase()).thenReturn(userNamesIdsBudgetIds);
		when(databaseReaderForTest.readYearsAndMonthsForConcreteBudgetFromDatabase(budget1.id)).thenReturn(budgetDates);
		
		budgetController = new BudgetController(databaseReaderForTest, databaseWriterForTest,
												panelWithBudget, panelWithYears, panelWithMonths,
												panelAddExpenditure, panelAddSavings, panelAddIncome, panelAddDues,
												panelExpenditureView, panelSavingsView, panelIncomeView, panelDuesView, 
												lblExpenditureSum, lblSavingsSum, lblIncomeSum, lblDuesSum,
												btnAddNewMonth, addNewMonthJDialog, createNewBudgetDialog
												);
		
		setIdentifier(panelWithBudget, 123);
		setIdentifier(panelWithYears, 124);
		setIdentifier(panelWithMonths, 125);
		setIdentifier(panelAddExpenditure, 126);
		setIdentifier(panelAddSavings, 127);
		setIdentifier(panelAddIncome, 128);
		setIdentifier(addNewMonthJDialog, 129);
		setIdentifier(createNewBudgetDialog, 130);
		
		expenditures = new ArrayList<Transaction>(){{
			add(new Transaction(transactionId, category1.id, amount, DATE1, budget1.id, category1.name)); 
			add(new Transaction(transactionId, category1.id, amount, DATE2, budget1.id, category1.name)); 
			add(new Transaction(transactionId, category2.id, amount, DATE3, budget1.id, category2.name)); 
			}};
			
		savings = new ArrayList<Transaction>(){{
			add(new Transaction(transactionId, category1.id, amount, DATE3, budget1.id, category1.name)); 
			add(new Transaction(transactionId, category2.id, amount, DATE3, budget1.id, category2.name));
			add(new Transaction(transactionId, category2.id, amount, DATE4, budget1.id, category2.name));
			}};
			
		income = new ArrayList<Transaction>(){{
			add(new Transaction(transactionId, category2.id, amount, DATE3, budget1.id, category2.name, user1.id, user1.name));
			add(new Transaction(transactionId, category1.id, amount, DATE4, budget1.id, category1.name, user1.id, user1.name));
			add(new Transaction(transactionId, category1.id, amount, DATE5, budget1.id, category1.name, user2.id, user2.name));
			}};
			
		categories = new HashMap<Integer, String>(){{
			put(category1.id, category1.name); 
			put(category2.id, category2.name); 
			}};
			
		listOfTripletsExpenditure = new ArrayList<Triplet<String, String, String>>(){{
			add(new Triplet<String, String, String>(DATE3.toString(), category2.name, String.valueOf(amount))); 
			}};
			
		listOfTripletsSavings = new ArrayList<Triplet<String, String, String>>(){{
			add(new Triplet<String, String, String>(DATE3.toString(), category1.name, String.valueOf(amount))); 
			add(new Triplet<String, String, String>(DATE3.toString(), category2.name, String.valueOf(amount))); 
			}};

		listOfTripletsIncome = new ArrayList<Triplet<String, String, String>>(){{
			add(new Triplet<String, String, String>(user1.name, category2.name, String.valueOf(amount))); 
			}};
	}

	private void setIdentifier(PanelWithButtons panel, int identifier) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field budgetIdField = PanelWithButtons.class.getField("identifier");
		budgetIdField.setAccessible(true);
		budgetIdField.set(panel, identifier);
	}
	
	private void setIdentifier(PanelAddTransaction panel, int identifier) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field budgetIdField = PanelAddTransaction.class.getField("identifier");
		budgetIdField.setAccessible(true);
		budgetIdField.set(panel, identifier);
	}
	
	private void setIdentifier(PanelAddIncome panel, int identifier) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field budgetIdField = PanelAddIncome.class.getField("identifier");
		budgetIdField.setAccessible(true);
		budgetIdField.set(panel, identifier);
	}

	private void setIdentifier(AddNewYearMonthDialog dialog, int identifier) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field budgetIdField = AddNewYearMonthDialog.class.getField("identifier");
		budgetIdField.setAccessible(true);
		budgetIdField.set(dialog, identifier);
	}
	
	private void setIdentifier(CreateNewBudgetDialog dialog, int identifier) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field budgetIdField = CreateNewBudgetDialog.class.getField("identifier");
		budgetIdField.setAccessible(true);
		budgetIdField.set(dialog, identifier);
	}
	
	private Transaction createTransaction(DBEntry<String, Integer> category, DBEntry<String, Integer> user, LocalDate date) {
		return new Transaction(transactionId, category.id, amount, date, budget1.id, category.name, user.id, user.name);
	}
	
	private Date asDate(LocalDate date) {
		return Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
	
	@Test
	public void initializePanelBudget_RedBudgetsNameFromDatabase_controllerRedBudgetsNameFromDatabaseAndCreateButtons() throws DatabaseNotInitialized {
		SortedSet<String> expectedButtonsNames = new TreeSet<String>(budgetIdToName.values());
		verify(panelWithBudget).createButtons(expectedButtonsNames);
	}
	
	@Test
	public void notify_redYearsFromDatabase_controllerRedYearsFromDatabaseAndCreateButtons() throws DatabaseNotInitialized, BudgetNotFoundException {
		budgetController.notify(new ButtonsData(panelWithBudget.identifier, budget1.name));
		
		verify(panelWithYears).createButtons(new TreeSet<String>() {{add("2016"); add("2017"); add("2015");}});
	}
	
	@Test
	public void notify_redMonthsFromDatabase_controllerRedMonthsFromDatabaseAndCreateButtons() throws DatabaseNotInitialized, BudgetNotFoundException {
		
		budgetController.notify(new ButtonsData(panelWithBudget.identifier, budget1.name));
		budgetController.notify(new ButtonsData(panelWithYears.identifier, String.valueOf(DATE3.getYear())));
		
		verify(panelWithMonths).createButtons(new TreeSet<String>() {{add("2"); add("3"); add("12");}});
	}

	private List<Transaction> extendTransactions(List<Transaction> transactions, Transaction t){
		List<Transaction> withNewTransaction = new ArrayList<>(transactions);
		withNewTransaction.add(t);
		
		return withNewTransaction;
	}

	@Test
	public void notify_fillPanelsWithCollectedData_controllerCollectsDataAndFillsDependentPanels() throws DatabaseNotInitialized, BudgetNotFoundException {
		when(databaseReaderForTest.readConcreteTransactionsWithCategoryNameForConcreteBudget(BudgetController.EXPENDITURE, BudgetController.EXPENDITURE_CATEGORY, budget1.id)).thenReturn(expenditures);
		when(databaseReaderForTest.readConcreteTransactionsWithCategoryNameForConcreteBudget(BudgetController.SAVINGS, BudgetController.SAVINGS_CATEGORY, budget1.id)).thenReturn(savings);
		when(databaseReaderForTest.readIncomeForConcreteBugdetFromDatabase(budget1.id)).thenReturn(income);
		
		when(databaseReaderForTest.readCategoriesForBudgetFromDatabase(budget1.id, BudgetController.EXPENDITURE_CATEGORY)).thenReturn(categories);
		when(databaseReaderForTest.readCategoriesForBudgetFromDatabase(budget1.id, BudgetController.SAVINGS_CATEGORY)).thenReturn(categories);
		when(databaseReaderForTest.readIncomeCategory()).thenReturn(categories);
		
		budgetController.notify(new ButtonsData(panelWithBudget.identifier, budget1.name));
		budgetController.notify(new ButtonsData(panelWithYears.identifier, String.valueOf(DATE3.getYear())));
		budgetController.notify(new ButtonsData(panelWithMonths.identifier, String.valueOf(DATE3.getMonthValue())));
		
		verify(panelAddExpenditure).fillComboBox( new ArrayList<String>() {{add(category1.name); add(category2.name);}});
		verify(panelAddSavings).fillComboBox( new ArrayList<String>() {{add(category1.name); add(category2.name);}});
		verify(panelAddIncome).fillComboBoxCategory( new ArrayList<String>() {{add(category1.name); add(category2.name);}});
		verify(panelAddIncome).fillComboBoxUser(new ArrayList<String>() {{add(user1.name); add(user3.name); add(user4.name);}});
		
		verify(panelExpenditureView).fillPanel(listOfTripletsExpenditure, BudgetController.columnsNameDateCategoryAmount);
		verify(panelSavingsView).fillPanel(listOfTripletsSavings, BudgetController.columnsNameDateCategoryAmount);
		verify(panelIncomeView).fillPanel(listOfTripletsIncome, BudgetController.columnsNameUserNameCategoryAmount);
	}
	
	@Test
	public void notify_AddExpenditureToDatabaseAndRefreshExpenditureView_ControllerAddExpenditureToDatabaseAfterClickingAddButtonAndRefreshExpenditureView() throws DatabaseNotInitialized, BudgetNotFoundException {
		when(databaseReaderForTest.readConcreteTransactionsWithCategoryNameForConcreteBudget(BudgetController.EXPENDITURE, BudgetController.EXPENDITURE_CATEGORY, budget1.id)).thenReturn(expenditures);
		when(databaseReaderForTest.readCategoriesForBudgetFromDatabase(budget1.id, BudgetController.EXPENDITURE_CATEGORY)).thenReturn(categories);
		
		budgetController.notify(new ButtonsData(panelWithBudget.identifier, budget1.name));
		budgetController.notify(new ButtonsData(panelWithYears.identifier, String.valueOf(DATE3.getYear())));
		budgetController.notify(new ButtonsData(panelWithMonths.identifier, String.valueOf(DATE3.getMonthValue())));
		
		List<Transaction> withNewTransaction = extendTransactions(expenditures, createTransaction(category1, user1, DATE3));
		when(databaseReaderForTest.readConcreteTransactionsWithCategoryNameForConcreteBudget(BudgetController.EXPENDITURE, BudgetController.EXPENDITURE_CATEGORY, budget1.id)).thenReturn(withNewTransaction);

		budgetController.notify(new ButtonAddTransactionData(panelAddExpenditure.identifier, asDate(DATE3), category1.name, String.valueOf(amount)));
		
		verify(databaseWriterForTest).writeTransactionToDatabase(amount, DATE3, category1.id, budget1.id, BudgetController.EXPENDITURE);
		
		List<Triplet<String, String, String>> listOfTripletsIncomeWithNewTriplet = new ArrayList<>(listOfTripletsExpenditure);
		listOfTripletsIncomeWithNewTriplet.add(new Triplet<String, String, String>(DATE3.toString(), category1.name, String.valueOf(amount)));
		verify(panelExpenditureView).fillPanel(listOfTripletsIncomeWithNewTriplet, BudgetController.columnsNameDateCategoryAmount);
	}
	
	@Test
	public void notify_AddSavingsToDatabaseAndRefreshSavingsView_ControllerAddSavingsToDatabaseAfterClickingAddButtonAndRefresSavingsView() throws DatabaseNotInitialized, BudgetNotFoundException {
		when(databaseReaderForTest.readConcreteTransactionsWithCategoryNameForConcreteBudget(BudgetController.SAVINGS, BudgetController.SAVINGS_CATEGORY, budget1.id)).thenReturn(savings);
		when(databaseReaderForTest.readCategoriesForBudgetFromDatabase(budget1.id, BudgetController.SAVINGS_CATEGORY)).thenReturn(categories);
		
		budgetController.notify(new ButtonsData(panelWithBudget.identifier, budget1.name));
		budgetController.notify(new ButtonsData(panelWithYears.identifier, String.valueOf(DATE3.getYear())));
		budgetController.notify(new ButtonsData(panelWithMonths.identifier, String.valueOf(DATE3.getMonthValue())));

		List<Transaction> withNewTransaction = extendTransactions(savings, createTransaction(category2, user1, DATE3));
		when(databaseReaderForTest.readConcreteTransactionsWithCategoryNameForConcreteBudget(BudgetController.SAVINGS, BudgetController.SAVINGS_CATEGORY, budget1.id)).thenReturn(withNewTransaction);

		budgetController.notify(new ButtonAddTransactionData(panelAddSavings.identifier, asDate(DATE3), category2.name, String.valueOf(amount)));
		
		verify(databaseWriterForTest).writeTransactionToDatabase(amount, DATE3, category2.id, budget1.id, BudgetController.SAVINGS);
		verify(panelSavingsView).fillPanel(listOfTripletsSavings, BudgetController.columnsNameDateCategoryAmount);

		List<Triplet<String, String, String>> listOfTripletsIncomeWithNewTriplet = new ArrayList<>(listOfTripletsSavings);
		listOfTripletsIncomeWithNewTriplet.add(new Triplet<String, String, String>(DATE3.toString(), category2.name, String.valueOf(amount)));
		verify(panelSavingsView).fillPanel(listOfTripletsIncomeWithNewTriplet, BudgetController.columnsNameDateCategoryAmount);
	}
	
	@Test
	public void notify_AddIncomeToDatabaseAndRefreshIncomeView_ControllerAddIncomeToDatabaseAfterClickingAddButtonAndRefresIncomeView() throws DatabaseNotInitialized, BudgetNotFoundException {
		when(databaseReaderForTest.readIncomeForConcreteBugdetFromDatabase(budget1.id)).thenReturn(income);
		when(databaseReaderForTest.readIncomeCategory()).thenReturn(categories);
		
		budgetController.notify(new ButtonsData(panelWithBudget.identifier, budget1.name));
		budgetController.notify(new ButtonsData(panelWithYears.identifier, String.valueOf(DATE3.getYear())));
		budgetController.notify(new ButtonsData(panelWithMonths.identifier, String.valueOf(DATE3.getMonthValue())));

		List<Transaction> withNewTransaction = extendTransactions(income, createTransaction(category2, user1, DATE3));
		when(databaseReaderForTest.readIncomeForConcreteBugdetFromDatabase(budget1.id)).thenReturn(withNewTransaction);
		
		budgetController.notify(new ButtonAddIncomeData(panelAddIncome.identifier, asDate(DATE3), category2.name, user1.name, String.valueOf(amount)));
		
		verify(databaseWriterForTest).writeIncomeToDatabase(amount, DATE3, user1.id, category2.id, budget1.id);
		verify(panelIncomeView).fillPanel(listOfTripletsIncome, BudgetController.columnsNameUserNameCategoryAmount);
		
		List<Triplet<String, String, String>> listOfTripletsIncomeWithNewTriplet = new ArrayList<>(listOfTripletsIncome);
		listOfTripletsIncomeWithNewTriplet.add(new Triplet<String, String, String>(user1.name, category2.name, String.valueOf(amount)));
		verify(panelIncomeView).fillPanel(listOfTripletsIncomeWithNewTriplet, BudgetController.columnsNameUserNameCategoryAmount);
	}
}
