package Tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import learning.budget.DatabaseNotInitialized;
import learning.budget.DatabaseReader;
import learning.budget.IDatabaseReader;
import learning.budget.Transaction;
import learning.budget.views.BudgetController;
import learning.budget.views.PanelWithButtons;

public class BudgetControllerTest {

	HashMap<Integer, String> budgetIdToName = new HashMap<Integer, String>() {{put(1, "a"); put(2, "b"); put(3, "c");}};
	BudgetController budgetController;
	
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
		setIdentifier(panelWithBudget, "panelWithButtons");
	}

	private void setIdentifier(PanelWithButtons panel, String identifier) throws NoSuchFieldException, IllegalAccessException {
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
	public void notify_redYearsFromDatabase_controllerRedYearsFromDatabaseAdnCreateButtons() throws DatabaseNotInitialized {
		initializeController();
		
		int clickedBudgetId = 1, categoryId = 1; 
		double amount = 10.0;
		List<Transaction> transactions = new ArrayList<Transaction>(){{
			add(new Transaction(categoryId, amount, LocalDate.parse("2016-01-01"), clickedBudgetId)); 
			add(new Transaction(categoryId, amount, LocalDate.parse("2017-03-17"), clickedBudgetId)); 
			add(new Transaction(categoryId, amount, LocalDate.parse("2017-03-17"), clickedBudgetId)); 
			add(new Transaction(categoryId, amount, LocalDate.parse("2015-03-15"), clickedBudgetId));
			}};

		when(databaseForTest.readBudgetFromDatabase(clickedBudgetId)).thenReturn(transactions);
		
		budgetController.notify(panelWithBudget.identifier, "a");
		
		verify(panelWithYears).createButtons(new TreeSet<String>() {{add("2016"); add("2017"); add("2015");}});
	}

	private void initializeController() throws DatabaseNotInitialized {
		when(databaseForTest.readBudgetIdNameFromDatabase()).thenReturn(budgetIdToName);
		budgetController.initializePanelBudget();
	}

}
