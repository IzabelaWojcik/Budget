package learning.budget.views;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import learning.budget.DatabaseNotInitialized;
import learning.budget.IDatabaseReader;
import learning.budget.Transaction;

public class BudgetController implements IListener{
	private PanelWithButtons panelWithBudget, panelWithYears, panelWithMonths;
	private PanelAddTransaction panelToAddTransaction;
	private IDatabaseReader databaseReader;
	private Map<Integer, String> budgetIdToName;
	private int budgetId;
	private List<LocalDate> dates;
	private List<Transaction> expenditures;
	private List<Transaction> transactionsForConcreteBudgetYearAndMonth;
	private String clickedYear;
	private String clickedMonth;
	private String clickedBudgetName;
	
	public BudgetController(IDatabaseReader databaseReader, PanelWithButtons panelBudget, PanelWithButtons panelYears, PanelWithButtons panelMonths, PanelAddTransaction panelAddTransaction) {
		panelWithBudget = panelBudget;
		panelWithYears = panelYears;
		panelWithMonths = panelMonths;
		panelToAddTransaction = panelAddTransaction;
		
		panelWithBudget.register(this);
		panelWithYears.register(this);
		panelWithMonths.register(this);
		
		this.databaseReader = databaseReader;
	}
	
	public void initializePanelBudget() throws DatabaseNotInitialized {
		budgetIdToName = databaseReader.readBudgetIdNameFromDatabase();
		SortedSet<String> names = new TreeSet<String>(budgetIdToName.values());
		panelWithBudget.createButtons(names);
	}
	
	private int getBudgetId(String budgetName) {
		for(Entry<Integer, String> e: budgetIdToName.entrySet()) {
			if(e.getValue().equals(budgetName))
				return e.getKey();
		}
		throw new NoSuchElementException("Budget id not found");
	}
	
	@Override
	public void notify(NotificationData notificationData) {
		if(notificationData.notifierId == panelWithBudget.identifier) {
			handlePanelWithBudgetNotification(notificationData);
		}
		else if(notificationData.notifierId == panelWithYears.identifier) {
			handlePanelWithYearsNotification(notificationData); 
		}
		else if(notificationData.notifierId == panelWithMonths.identifier) {
			handlePanelWithMonthsNotification(notificationData);
		}
	
	}

	private void handlePanelWithBudgetNotification(NotificationData notificationData) {
		ButtonsData buttonsData = (ButtonsData) notificationData;
		clickedBudgetName = buttonsData.name;
		
		for(Entry<Integer, String> entry: budgetIdToName.entrySet()) {
				if(entry.getValue() == clickedBudgetName) {
					budgetId = entry.getKey();
				}
		}
		
		try {
			dates = databaseReader.readDatesForBudgetFromDatabase(budgetId);
		} catch (DatabaseNotInitialized e) {
			e.printStackTrace();
			return;
		}
		
		Set<String> years = dates.stream()
								.map(LocalDate::getYear)
								.map(year -> year.toString())
								.collect(Collectors.toSet());
		panelWithYears.createButtons(new TreeSet<String>(years));
	}
	
	private void handlePanelWithYearsNotification(NotificationData notificationData) {
		ButtonsData buttonsData = (ButtonsData) notificationData;
		clickedYear = buttonsData.name;
		
		Set<String> months = dates.stream()
								.filter(t -> t.getYear() == Integer.parseInt(clickedYear))
								.map(LocalDate::getMonthValue)
								.map(month -> month.toString())
								.collect(Collectors.toSet());
		panelWithMonths.createButtons(new TreeSet<String>(months));
	}
	
	private void handlePanelWithMonthsNotification(NotificationData notificationData) {
		ButtonsData buttonsData = (ButtonsData) notificationData;
		clickedMonth = buttonsData.name;
		
		try {
			expenditures = databaseReader.readConcreteTransactionsWithCategoryNameForConcreteBudget("Expenditure", "Expenditure_category", budgetId);
			
			List<Transaction> expendituresForConcreteYearAndMonth = expenditures.stream()
					.filter(t -> t.getYear() == Integer.parseInt(clickedYear)
							&& t.getMonth() == Integer.parseInt(clickedMonth))
					.collect(Collectors.toList());

			List<String> categories = databaseReader.readCategoriesForBudgetFromDatabase(budgetId);
			for(String s: categories) System.out.println(s);

			panelToAddTransaction.fillComboBox(categories);
		
		} catch (DatabaseNotInitialized e) {
			e.printStackTrace();
			return;
		}
		
	}
}
