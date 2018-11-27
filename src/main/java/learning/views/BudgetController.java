package learning.views;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.swing.JButton;

import org.javatuples.Triplet;

import learning.budget.DatabaseNotInitialized;
import learning.budget.IDatabaseReader;
import learning.budget.Transaction;
import learning.budget.UsersObject;

public class BudgetController implements IListener{
	public static final String INCOME = "Income";
	public static final String SAVINGS = "Savings";
	public static final String EXPENDITURE = "Expenditure";
	public static final String INCOME_CATEGORY = "Income_category";
	public static final String SAVINGS_CATEGORY = "Savings_category";
	public static final String EXPENDITURE_CATEGORY = "Expenditure_category";
	public static final Triplet<String, String, String> columnsNameDateCategoryAmount = new Triplet<String, String, String>("Date:", "Category:", "Amount:");
	public static final Triplet<String, String, String> columnsNameUserNameCategoryAmount = new Triplet<String, String, String>("User:", "Category:", "Amount:");
	
	
	private PanelWithButtons panelWithBudget, panelWithYears, panelWithMonths;
	private PanelAddTransaction panelToAddExpenditure, panelToAddSavings;
	private PanelAddIncome panelToAddIncome;
	private PanelViewTransaction panelViewExpenditure, panelViewSavings, panelViewIncome;
	private IDatabaseReader databaseReader;
	private Map<Integer, String> budgetIdToName;
	private int budgetId;
	private List<LocalDate> dates;
	private List<Transaction> transactions;
	private List<Transaction> transactionsForConcreteBudgetYearAndMonth;
	private String clickedYear;
	private String clickedMonth;
	private String clickedBudgetName;
	
	public BudgetController(IDatabaseReader databaseReader, 
							PanelWithButtons panelBudget, PanelWithButtons panelYears, PanelWithButtons panelMonths, 
							PanelAddTransaction panelAddExpenditure, PanelAddTransaction panelAddSavings, PanelAddIncome panelAddIncome,
							PanelViewTransaction panelExpenditureView, PanelViewTransaction panelSavingsView, PanelViewTransaction panelIncomeView) throws DatabaseNotInitialized 
	{
		panelWithBudget = panelBudget;
		panelWithYears = panelYears;
		panelWithMonths = panelMonths;
		panelToAddExpenditure = panelAddExpenditure;
		panelToAddSavings = panelAddSavings;
		panelToAddIncome = panelAddIncome;
		panelViewExpenditure = panelExpenditureView;
		panelViewSavings = panelSavingsView;
		panelViewIncome = panelIncomeView;
		
		this.databaseReader = databaseReader;
		
		panelWithBudget.register(this);
		panelWithYears.register(this);
		panelWithMonths.register(this);
		panelToAddExpenditure.register(this);
		panelToAddSavings.register(this);
		panelToAddIncome.register(this);
		initializePanelBudget();
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
		else
		{
			throw new IllegalArgumentException("Unexpected identifier: " + notificationData.notifierId);
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
		
		panelToAddExpenditure.clearComboBox();
		panelToAddSavings.clearComboBox();
		panelToAddIncome.clearComboBox(panelToAddIncome.getComboboxCategory());
		panelToAddIncome.clearComboBox(panelToAddIncome.getComboboxUser());
		
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
		
		panelToAddExpenditure.clearComboBox();
		panelToAddSavings.clearComboBox();
		panelToAddIncome.clearComboBox(panelToAddIncome.getComboboxCategory());
		panelToAddIncome.clearComboBox(panelToAddIncome.getComboboxUser());
		
		try {
			List<Transaction> expenditures = getTransactionForBudgetYearMonth(EXPENDITURE, EXPENDITURE_CATEGORY);
			List<Transaction> savings = getTransactionForBudgetYearMonth(SAVINGS, SAVINGS_CATEGORY);
			List<Transaction> income = getTransactionForBudgetYearMonth(INCOME, INCOME_CATEGORY);

			List<String> expenditureCategories = databaseReader.readCategoriesForBudgetFromDatabase(budgetId, EXPENDITURE_CATEGORY);
			List<String> savingsCategories = databaseReader.readCategoriesForBudgetFromDatabase(budgetId, SAVINGS_CATEGORY);
			List<String> incomeCategories = databaseReader.readCategoriesForBudgetFromDatabase(budgetId, INCOME_CATEGORY);
			
			List<UsersObject> userNamesIdsBudgetIds = databaseReader.readUsersFromDatabase();
			List<String> userNames = userNamesIdsBudgetIds.stream()
					.filter(u -> u.getBudgerId() == budgetId)
					.map(u -> u.getUserName())
					.collect(Collectors.toList());
			
			fillPanelsToAddTransactions(expenditureCategories, savingsCategories, incomeCategories, userNames);
			
			List<Triplet<String, String, String>> expendituresToFillPanel = dataToFillPanel(expenditures);
			List<Triplet<String, String, String>> savingsToFillPanel = dataToFillPanel(savings);
			List<Triplet<String, String, String>> incomeToFillPanel = dataToFillPanel(income);
			
			panelViewExpenditure.fillPanel(expendituresToFillPanel, columnsNameDateCategoryAmount);
			panelViewSavings.fillPanel(savingsToFillPanel, columnsNameDateCategoryAmount);
			panelViewIncome.fillPanel(incomeToFillPanel, columnsNameUserNameCategoryAmount);
	
		} catch (DatabaseNotInitialized e) {
			e.printStackTrace();
			return;
		}
	}

	private void fillPanelsToAddTransactions(List<String> expenditureCategories, List<String> savingsCategories, List<String> incomeCategories, List<String> userNames) throws DatabaseNotInitialized {
		panelToAddExpenditure.fillComboBox(expenditureCategories);
		panelToAddSavings.fillComboBox(savingsCategories);
		panelToAddIncome.fillComboBox(incomeCategories, panelToAddIncome.getComboboxCategory());
		panelToAddIncome.fillComboBox(userNames, panelToAddIncome.getComboboxUser());
	}

	private List<Transaction> getTransactionForBudgetYearMonth(String transactionTablename, String categoryTablename) throws DatabaseNotInitialized {
		transactions = databaseReader.readConcreteTransactionsWithCategoryNameForConcreteBudget(transactionTablename, categoryTablename, budgetId);
		
		List<Transaction> transactionsForConcreteYearAndMonth = transactions.stream()
				.filter(t -> t.getYear() == Integer.parseInt(clickedYear)
						&& t.getMonth() == Integer.parseInt(clickedMonth))
				.collect(Collectors.toList());

		return transactionsForConcreteYearAndMonth;
	}
	
	private List<Triplet<String, String, String>> dataToFillPanel(List<Transaction> transactions){
		List<Triplet<String, String, String>> list = new ArrayList<Triplet<String, String, String>>();
		
		for(Transaction t: transactions) {
			list.add(new Triplet(t.getDate().toString(), t.getCategoryName(), String.valueOf(t.getAmount())));
		}
		
		return list;
	}
}
