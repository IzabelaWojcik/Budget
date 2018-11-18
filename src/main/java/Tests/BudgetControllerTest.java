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
import learning.budget.Transaction;
import learning.views.BudgetController;
import learning.views.ButtonsData;
import learning.views.PanelAddTransaction;
import learning.views.PanelViewTransaction;
import learning.views.PanelWithButtons;

public class BudgetControllerTest {
	private HashMap<Integer, String> budgetIdToName = new HashMap<Integer, String>() {{put(1, "budzet1"); put(2, "budzet2"); put(3, "budzet3");}};
	private BudgetController budgetController;
	private int clickedBudgetId, categoryId1, categoryId2;
	private double amount;
	private List<LocalDate> dates;
	private List<String> categories;
	private List<Transaction> expenditures, savings, incomes, transactions;
	private String categoryName1;
	private String categoryName2;
	
	@Mock
	IDatabaseReader databaseForTest;
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
	PanelAddTransaction panelAddIncome;
	@Mock
	PanelViewTransaction panelExpenditureView;
	@Mock
	PanelViewTransaction panelSavingsView;
	@Mock
	PanelViewTransaction panelOtherIncomeView;
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Before
	public void setup() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		budgetController = new BudgetController(databaseForTest, 
												panelWithBudget, panelWithYears, panelWithMonths,
												panelAddExpenditure, panelAddSavings, panelAddIncome,
												panelExpenditureView, panelSavingsView, panelOtherIncomeView
												);
		setIdentifier(panelWithBudget, 123);
		setIdentifier(panelWithYears, 124);
		setIdentifier(panelWithMonths, 125);
		setIdentifier(panelAddExpenditure, 126);
		
		clickedBudgetId = 1;
		categoryId1 = 1; 
		categoryId2 = 2; 
		categoryName1 = "Zakupy";
		categoryName2 = "Opłaty";
		amount = 10.0;
		
		expenditures = new ArrayList<Transaction>(){{
			add(new Transaction(categoryId1, amount, LocalDate.parse("2016-01-01"), clickedBudgetId)); 
			add(new Transaction(categoryId1, amount, LocalDate.parse("2017-02-14"), clickedBudgetId)); 
			add(new Transaction(categoryId2, amount, LocalDate.parse("2017-03-17"), clickedBudgetId)); 
			}};
			
		savings = new ArrayList<Transaction>(){{
			add(new Transaction(categoryId1, amount, LocalDate.parse("2017-03-17"), clickedBudgetId)); 
			add(new Transaction(categoryId2, amount, LocalDate.parse("2017-03-17"), clickedBudgetId));
			add(new Transaction(categoryId1, amount, LocalDate.parse("2017-12-01"), clickedBudgetId));
			}};
			
		incomes = new ArrayList<Transaction>(){{
			add(new Transaction(categoryId2, amount, LocalDate.parse("2017-03-17"), clickedBudgetId));
			add(new Transaction(categoryId1, amount, LocalDate.parse("2017-12-01"), clickedBudgetId));
			add(new Transaction(categoryId1, amount, LocalDate.parse("2015-05-15"), clickedBudgetId));
			}};
			
		dates = new ArrayList<LocalDate>(){{
			add(LocalDate.parse("2016-01-01")); 
			add(LocalDate.parse("2017-02-14")); 
			add(LocalDate.parse("2017-03-17")); 
			add(LocalDate.parse("2017-03-17"));
			add(LocalDate.parse("2017-12-01"));
			add(LocalDate.parse("2015-05-15"));
			}};
			
		categories = new ArrayList<String>(){{
			add(categoryName1); 
			add(categoryName2); 
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

	private void initializeController() throws DatabaseNotInitialized {
		when(databaseForTest.readBudgetIdNameFromDatabase()).thenReturn(budgetIdToName);
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

		when(databaseForTest.readDatesForBudgetFromDatabase(clickedBudgetId)).thenReturn(dates);
		
		budgetController.notify(new ButtonsData(panelWithBudget.identifier, "budzet1"));
		
		verify(panelWithYears).createButtons(new TreeSet<String>() {{add("2016"); add("2017"); add("2015");}});
	}
	
	@Test
	public void notify_redMonthsFromDatabase_controllerRedMonthsFromDatabaseAndCreateButtons() throws DatabaseNotInitialized {
		initializeController();
		
		when(databaseForTest.readDatesForBudgetFromDatabase(clickedBudgetId)).thenReturn(dates);
		
		budgetController.notify(new ButtonsData(panelWithBudget.identifier, "budzet1"));
		budgetController.notify(new ButtonsData(panelWithYears.identifier, "2017"));
		
		verify(panelWithMonths).createButtons(new TreeSet<String>() {{add("2"); add("3"); add("12");}});
	}
	
	@Test
	public void notify_fillCategoryFromTransactionList_controllerRedCategoryFromDatabaseAndPassItToMethodFillingComboBox() throws DatabaseNotInitialized {
		initializeController();
			
		when(databaseForTest.readCategoriesForBudgetFromDatabase(clickedBudgetId, BudgetController.EXPENDITURE_CATEGORY)).thenReturn(categories);
		when(databaseForTest.readCategoriesForBudgetFromDatabase(clickedBudgetId, BudgetController.SAVINGS_CATEGORY)).thenReturn(categories);
		when(databaseForTest.readCategoriesForBudgetFromDatabase(clickedBudgetId, BudgetController.INCOME_CATEGORY)).thenReturn(categories);
		
		budgetController.notify(new ButtonsData(panelWithBudget.identifier, "budzet1"));
		budgetController.notify(new ButtonsData(panelWithYears.identifier, "2017"));
		budgetController.notify(new ButtonsData(panelWithMonths.identifier, "3"));
		
		verify(panelAddExpenditure).fillComboBox( new ArrayList<String>() {{add(categoryName1); add(categoryName2);}});
		verify(panelAddSavings).fillComboBox( new ArrayList<String>() {{add(categoryName1); add(categoryName2);}});
		verify(panelAddIncome).fillComboBox( new ArrayList<String>() {{add(categoryName1); add(categoryName2);}});
	}
}
