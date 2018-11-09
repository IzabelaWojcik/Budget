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

import Tests.Helpers.GetButtonsTextHelper;
import Tests.Helpers.PanelForRemove;
import learning.budget.DatabaseNotInitialized;
import learning.budget.DatabaseReader;
import learning.budget.IDatabaseReader;
import learning.budget.Transaction;
import learning.budget.views.BudgetController;
import learning.budget.views.ButtonsData;
import learning.budget.views.PanelWithButtons;

public class BudgetControllerTest {
	
	HashMap<Integer, String> budgetIdToName = new HashMap<Integer, String>() {{put(1, "budzet1"); put(2, "budzet2"); put(3, "budzet3");}};
	BudgetController budgetController;
	int clickedBudgetId, categoryId;
	double amount;
	List<Transaction> transactions;
	
	@Mock
	IDatabaseReader databaseForTest;
	@Mock
	PanelWithButtons panelWithBudget;
	@Mock
	PanelWithButtons panelWithYears;
	@Mock
	PanelWithButtons panelWithMonths;
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Before
	public void setup() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		budgetController = new BudgetController(databaseForTest, panelWithBudget, panelWithYears, panelWithMonths);
		setIdentifier(panelWithBudget, 123);
		
		clickedBudgetId = 1;
		categoryId = 1; 
		amount = 10.0;
		
		transactions = new ArrayList<Transaction>(){{
			add(new Transaction(categoryId, amount, LocalDate.parse("2016-01-01"), clickedBudgetId)); 
			add(new Transaction(categoryId, amount, LocalDate.parse("2017-02-14"), clickedBudgetId)); 
			add(new Transaction(categoryId, amount, LocalDate.parse("2017-03-17"), clickedBudgetId)); 
			add(new Transaction(categoryId, amount, LocalDate.parse("2017-03-17"), clickedBudgetId)); 
			add(new Transaction(categoryId, amount, LocalDate.parse("2017-12-01"), clickedBudgetId));
			add(new Transaction(categoryId, amount, LocalDate.parse("2015-05-15"), clickedBudgetId));
			}};
			
		
	}

	private void setIdentifier(PanelWithButtons panel, int identifier) throws NoSuchFieldException, IllegalAccessException {
		Field budgetIdField = PanelWithButtons.class.getField("identifier");
		budgetIdField.setAccessible(true);
		budgetIdField.set(panel, identifier);
	}
	
	@Test
	public void initializePanelBudget_redBudgetNamesFromDatabase_controllerRedBudgetNamesFromDatabaseAndCreateButtons() throws DatabaseNotInitialized {
		initializeController();
		
		SortedSet<String> expectedButtonNames = new TreeSet<String>(budgetIdToName.values());
		verify(panelWithBudget).createButtons(expectedButtonNames);
	}

	@Test
	public void notify_redYearsFromDatabase_controllerRedYearsFromDatabaseAndCreateButtons() throws DatabaseNotInitialized {
		initializeController();

		when(databaseForTest.readBudgetFromDatabase(clickedBudgetId)).thenReturn(transactions);
		
		budgetController.notify(new ButtonsData(panelWithBudget.identifier, "budzet1"));
		
		verify(panelWithYears).createButtons(new TreeSet<String>() {{add("2016"); add("2017"); add("2015");}});
	}
	
	@Test
	public void notify_redMonthsFromDatabase_controllerRedMonthsFromDatabaseAndCreateButtons() throws DatabaseNotInitialized {
		initializeController();
		
		when(databaseForTest.readBudgetFromDatabase(clickedBudgetId)).thenReturn(transactions);
		
		budgetController.notify(new ButtonsData(panelWithBudget.identifier, "budzet1"));
		budgetController.notify(new ButtonsData(panelWithYears.identifier, "2017"));
		
		verify(panelWithMonths).createButtons(new TreeSet<String>() {{add("2"); add("3"); add("12");}});
	}

	private void initializeController() throws DatabaseNotInitialized {
		when(databaseForTest.readBudgetIdNameFromDatabase()).thenReturn(budgetIdToName);
		budgetController.initializePanelBudget();
	}

}
