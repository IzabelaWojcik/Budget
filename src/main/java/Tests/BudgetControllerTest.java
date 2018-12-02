package Tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import org.javatuples.Triplet;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import Tests.Helpers.TestHelper;
import Tests.Helpers.PanelForRemove;
import learning.budget.DatabaseNotInitialized;
import learning.budget.DatabaseReader;
import learning.budget.IDatabaseReader;
import learning.budget.IDatabaseWriter;
import learning.budget.Transaction;
import learning.budget.UsersObject;
import learning.views.BudgetController;
import learning.views.ButtonsData;
import learning.views.PanelAddIncome;
import learning.views.PanelAddTransaction;
import learning.views.PanelViewTransaction;
import learning.views.PanelWithButtons;

public class BudgetControllerTest {
	private HashMap<Integer, String> budgetIdToName = new HashMap<Integer, String>() {{put(1, "budzet1"); put(2, "budzet2"); put(3, "budzet3");}};
	private BudgetController budgetController;
	private int clickedBudgetId, noClickedBudgetId, categoryId1, categoryId2, transactionId;
	private JLabel lblExpenditureSum, lblSavingsSum, lblIncomeSum;
	private double amount;
	private List<LocalDate> dates;
	private Map<Integer, String> categories;
	private List<Transaction> expenditures, savings, income, transactions;
	private String categoryName1;
	private String categoryName2;
	private String categoryName3;
	private String userName1;
	private String userName2;
	private String userName3;
	private String userName4;
	private int userId1;
	private int userId2;
	private int userId3;
	private int userId4;
	private ArrayList<UsersObject> userNamesIdsBudgetIds;
	private List<Triplet<String, String, String>> listOfTripletsExpenditure, listOfTripletsSavings, listOfTripletsIncome;
	private Triplet<String, String, String> columnsName;
	
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
	PanelViewTransaction panelExpenditureView;
	@Mock
	PanelViewTransaction panelSavingsView;
	@Mock
	PanelViewTransaction panelIncomeView;
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Before
	public void setup() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, DatabaseNotInitialized {
		lblExpenditureSum = new JLabel();
		lblSavingsSum = new JLabel();
		lblIncomeSum = new JLabel();
		budgetController = new BudgetController(databaseReaderForTest, databaseWriterForTest,
												panelWithBudget, panelWithYears, panelWithMonths,
												panelAddExpenditure, panelAddSavings, panelAddIncome,
												panelExpenditureView, panelSavingsView, panelIncomeView,
												lblExpenditureSum, lblSavingsSum, lblIncomeSum
												);
		
		setIdentifier(panelWithBudget, 123);
		setIdentifier(panelWithYears, 124);
		setIdentifier(panelWithMonths, 125);
		setIdentifier(panelAddExpenditure, 126);
		setIdentifier(panelAddSavings, 127);
		setIdentifier(panelAddIncome, 128);
		
		clickedBudgetId = 1;
		noClickedBudgetId = 2;
		categoryId1 = 1; 
		categoryId2 = 2; 
		transactionId  = 123;
		categoryName1 = "Zakupy";
		categoryName2 = "Opłaty";
		categoryName3 = "Kredyty";
		userName1 = "Iza";
		userName2 = "Piotr";
		userName3 = "Ania";
		userName4 = "Kuba";
		userId1 = 11;
		userId2 = 12;
		userId3 = 13;
		userId4 = 14;
		
		amount = 10.0;
		
		expenditures = new ArrayList<Transaction>(){{
			add(new Transaction(transactionId, categoryId1, amount, LocalDate.parse("2016-01-01"), clickedBudgetId, categoryName1)); 
			add(new Transaction(transactionId, categoryId1, amount, LocalDate.parse("2017-02-14"), clickedBudgetId, categoryName1)); 
			add(new Transaction(transactionId, categoryId2, amount, LocalDate.parse("2017-03-17"), clickedBudgetId, categoryName2)); 
			}};
			
		savings = new ArrayList<Transaction>(){{
			add(new Transaction(transactionId, categoryId1, amount, LocalDate.parse("2017-03-17"), clickedBudgetId, categoryName1)); 
			add(new Transaction(transactionId, categoryId2, amount, LocalDate.parse("2017-03-17"), clickedBudgetId, categoryName2));
			add(new Transaction(transactionId, categoryId2, amount, LocalDate.parse("2017-12-01"), clickedBudgetId, categoryName2));
			}};
			
		income = new ArrayList<Transaction>(){{
			add(new Transaction(transactionId, categoryId2, amount, LocalDate.parse("2017-03-17"), clickedBudgetId, categoryName2, userId1, userName1));
			add(new Transaction(transactionId, categoryId1, amount, LocalDate.parse("2017-12-01"), clickedBudgetId, categoryName1, userId1, userName1));
			add(new Transaction(transactionId, categoryId1, amount, LocalDate.parse("2015-05-15"), clickedBudgetId, categoryName1, userId2, userName2));
			}};
			
		dates = new ArrayList<LocalDate>(){{
			add(LocalDate.parse("2016-01-01")); 
			add(LocalDate.parse("2017-02-14")); 
			add(LocalDate.parse("2017-03-17")); 
			add(LocalDate.parse("2017-03-17"));
			add(LocalDate.parse("2017-12-01"));
			add(LocalDate.parse("2015-05-15"));
			}};
			
		categories = new HashMap<Integer, String>(){{
			put(categoryId1, categoryName1); 
			put(categoryId2, categoryName2); 
			}};
			
		userNamesIdsBudgetIds = new ArrayList<UsersObject>() {{
			add(new UsersObject(userId1, userName1, clickedBudgetId));
			add(new UsersObject(userId2, userName2, noClickedBudgetId));
			add(new UsersObject(userId3, userName3, clickedBudgetId));
			add(new UsersObject(userId4, userName4, clickedBudgetId));
			}};	

			
		listOfTripletsExpenditure = new ArrayList<Triplet<String, String, String>>(){{
			add(new Triplet("2017-03-17", categoryName2, String.valueOf(amount))); 
			}};
			
		listOfTripletsSavings = new ArrayList<Triplet<String, String, String>>(){{
			add(new Triplet("2017-03-17", categoryName1, String.valueOf(amount))); 
			add(new Triplet("2017-03-17", categoryName2, String.valueOf(amount))); 
			}};

		listOfTripletsIncome = new ArrayList<Triplet<String, String, String>>(){{
			add(new Triplet(userName1, categoryName2, String.valueOf(amount))); 
			}};

		columnsName = new Triplet<String, String, String>("column1", "column2", "column3");
			
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

	private void initializeController() throws DatabaseNotInitialized {
		when(databaseReaderForTest.readBudgetIdNameFromDatabase()).thenReturn(budgetIdToName);
		budgetController.initializePanelBudget();
	}
	
	@Test
	public void initializePanelBudget_RedBudgetsNameFromDatabase_controllerRedBudgetsNameFromDatabaseAndCreateButtons() throws DatabaseNotInitialized {
		initializeController();
		
		SortedSet<String> expectedButtonsNames = new TreeSet<String>(budgetIdToName.values());
		verify(panelWithBudget).createButtons(expectedButtonsNames);
	}
	
	@Test
	public void notify_redYearsFromDatabase_controllerRedYearsFromDatabaseAndCreateButtons() throws DatabaseNotInitialized {
		initializeController();

		when(databaseReaderForTest.readDatesForBudgetFromDatabase(clickedBudgetId)).thenReturn(dates);
		
		budgetController.notify(new ButtonsData(panelWithBudget.identifier, "budzet1"));
		
		verify(panelWithYears).createButtons(new TreeSet<String>() {{add("2016"); add("2017"); add("2015");}});
	}
	
	@Test
	public void notify_redMonthsFromDatabase_controllerRedMonthsFromDatabaseAndCreateButtons() throws DatabaseNotInitialized {
		initializeController();
		
		when(databaseReaderForTest.readDatesForBudgetFromDatabase(clickedBudgetId)).thenReturn(dates);
		
		budgetController.notify(new ButtonsData(panelWithBudget.identifier, "budzet1"));
		budgetController.notify(new ButtonsData(panelWithYears.identifier, "2017"));
		
		verify(panelWithMonths).createButtons(new TreeSet<String>() {{add("2"); add("3"); add("12");}});
	}
	
	@Test
	public void notify_fillPanelsWithCollectedData_controllerCollectsDataAndFillsDependentPanels() throws DatabaseNotInitialized {
		initializeController();
		when(databaseReaderForTest.readConcreteTransactionsWithCategoryNameForConcreteBudget(BudgetController.EXPENDITURE, BudgetController.EXPENDITURE_CATEGORY, clickedBudgetId)).thenReturn(expenditures);
		when(databaseReaderForTest.readConcreteTransactionsWithCategoryNameForConcreteBudget(BudgetController.SAVINGS, BudgetController.SAVINGS_CATEGORY, clickedBudgetId)).thenReturn(savings);
		when(databaseReaderForTest.readUsersFromDatabase()).thenReturn(userNamesIdsBudgetIds);
		when(databaseReaderForTest.readIncomeForConcreteBugdetFromDatabase(clickedBudgetId)).thenReturn(income);
		
		when(databaseReaderForTest.readCategoriesForBudgetFromDatabase(clickedBudgetId, BudgetController.EXPENDITURE_CATEGORY)).thenReturn(categories);
		when(databaseReaderForTest.readCategoriesForBudgetFromDatabase(clickedBudgetId, BudgetController.SAVINGS_CATEGORY)).thenReturn(categories);
		when(databaseReaderForTest.readCategoriesForBudgetFromDatabase(clickedBudgetId, BudgetController.INCOME_CATEGORY)).thenReturn(categories);
		
		budgetController.notify(new ButtonsData(panelWithBudget.identifier, "budzet1"));
		budgetController.notify(new ButtonsData(panelWithYears.identifier, "2017"));
		budgetController.notify(new ButtonsData(panelWithMonths.identifier, "3"));
		
		verify(panelAddExpenditure).fillComboBox( new ArrayList<String>() {{add(categoryName1); add(categoryName2);}});
		verify(panelAddSavings).fillComboBox( new ArrayList<String>() {{add(categoryName1); add(categoryName2);}});
		verify(panelAddIncome).fillComboBoxCategory( new ArrayList<String>() {{add(categoryName1); add(categoryName2);}});
		verify(panelAddIncome).fillComboBoxUser(new ArrayList<String>() {{add(userName1); add(userName3); add(userName4);}});
		
		verify(panelExpenditureView).fillPanel(listOfTripletsExpenditure, budgetController.columnsNameDateCategoryAmount);
		verify(panelSavingsView).fillPanel(listOfTripletsSavings, budgetController.columnsNameDateCategoryAmount);
		verify(panelIncomeView).fillPanel(listOfTripletsIncome, budgetController.columnsNameUserNameCategoryAmount);
	}
	
}
