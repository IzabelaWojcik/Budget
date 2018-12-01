package learning.views;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
import learning.budget.IDatabaseWriter;
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
	private IDatabaseWriter databaseWriter;
	private Map<Integer, String> budgetIdToName;
	private int budgetId;
	private List<LocalDate> dates;
	private List<Transaction> transactions;
	private List<Transaction> transactionsForConcreteBudgetYearAndMonth;
	private Map<Integer, String> expenditureCategories;
	private Map<Integer, String> savingsCategories;
	private Map<Integer, String> incomeCategories;
	private List<UsersObject> userNamesIdsBudgetIds;
	private List<Triplet<String, String, String>>expendituresToFillPanel;
	private List<Triplet<String, String, String>> savingsToFillPanel;
	private List<Triplet<String, String, String>> incomeToFillPanel;
	private List<Transaction> expenditures;
	private List<Transaction> savings;
	private List<Transaction> income;
	private String clickedYear;
	private String clickedMonth;
	private String clickedBudgetName;
	
	public BudgetController(IDatabaseReader databaseReader, IDatabaseWriter databasewriter,
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
		this.databaseWriter = databasewriter;
		
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
		else if(notificationData.notifierId == panelToAddExpenditure.identifier) {
			handlePanelToAddDataToDatabase(notificationData, panelViewExpenditure, expenditureCategories, EXPENDITURE, EXPENDITURE_CATEGORY);
		}
		else if(notificationData.notifierId == panelToAddSavings.identifier) {
			handlePanelToAddDataToDatabase(notificationData, panelViewSavings, savingsCategories, SAVINGS, SAVINGS_CATEGORY);
		}
		else if(notificationData.notifierId == panelToAddIncome.identifier) {
			handlePanelToAddIncomeToDatabase(notificationData);
		}
		else
		{
			throw new IllegalArgumentException("Unexpected identifier: " + notificationData.notifierId);
		}
	}
	
	private void handlePanelToAddDataToDatabase(NotificationData notificationData, PanelViewTransaction panelViewTransaction, Map<Integer, String> categories, String tablenameTransaction, String tablenameCategory) {
		ButtonAddTransactionData buttonAdd = (ButtonAddTransactionData) notificationData;
		int idCategory = 0;
		List<Transaction> transaction = null;
		List<Triplet<String, String, String>> datesCategoriesAmounts;
		
		for(Entry<Integer, String> e: categories.entrySet()) {
			if(buttonAdd.category.equals(e.getValue())) {
				idCategory = e.getKey();	
			}
		}
		if(idCategory != 0) {
			LocalDate localDate = Instant.ofEpochMilli(buttonAdd.date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
			databaseWriter.writeExpenditureOrSavingsToDatabase(Double.parseDouble(buttonAdd.amount), localDate, idCategory, getBudgetId(clickedBudgetName), tablenameTransaction);
			
			try {
				transaction = readTransactionForBudgetYearMonth(tablenameTransaction, tablenameCategory);
			} catch (DatabaseNotInitialized e1) {
				e1.printStackTrace();
			}
			
			if(localDate.getYear() == Double.parseDouble(clickedYear) && localDate.getMonthValue() == Integer.parseInt(clickedMonth)) {
				panelViewTransaction.clearPanel();
				datesCategoriesAmounts = dataToFillPanel(transaction);
				panelViewTransaction.fillPanel(datesCategoriesAmounts, columnsNameDateCategoryAmount);
			}
		}
	}
	
	private void handlePanelToAddIncomeToDatabase(NotificationData notificationData) {
		ButtonAddIncomeData buttonAdd = (ButtonAddIncomeData) notificationData;
		int idCategory = 0;
		int idUser = 0;
		
		for(Entry<Integer, String> e: incomeCategories.entrySet()) {
			if(buttonAdd.category.equals(e.getValue())) {
				idCategory = e.getKey();	
			}
		}
		
		for(UsersObject uo: userNamesIdsBudgetIds) {
			if(buttonAdd.user.equals(uo.getUserName())) {
				idUser = uo.getUserId();
			}
		}
		
		if(idCategory != 0 && idUser != 0) {
			LocalDate localDate = Instant.ofEpochMilli(buttonAdd.date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
			databaseWriter.writeIncomeToDatabase(Double.parseDouble(buttonAdd.amount), Instant.ofEpochMilli(buttonAdd.date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate(), idUser, idCategory, getBudgetId(clickedBudgetName));
			try {
				income = readIncomeForBudgetYearMonth(INCOME, INCOME_CATEGORY);
			} catch (DatabaseNotInitialized e1) {
				e1.printStackTrace();
			}
			if(localDate.getYear() == Double.parseDouble(clickedYear) && localDate.getMonthValue() == Integer.parseInt(clickedMonth)) {
				panelViewIncome.clearPanel();
				incomeToFillPanel = dataToFillPanelIncome(income);
				panelViewIncome.fillPanel(incomeToFillPanel, columnsNameUserNameCategoryAmount);
			}
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
		
		panelWithYears.clearPanel();
		panelWithMonths.clearPanel();
		
		panelToAddExpenditure.clearComboBox();
		panelToAddSavings.clearComboBox();
		panelToAddIncome.clearComboBox(panelToAddIncome.getComboboxCategory());
		panelToAddIncome.clearComboBox(panelToAddIncome.getComboboxUser());
		
		panelViewExpenditure.clearPanel();
		panelViewSavings.clearPanel();
		panelViewIncome.clearPanel();
		
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
		
		panelViewExpenditure.clearPanel();
		panelViewSavings.clearPanel();
		panelViewIncome.clearPanel();
		
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
		
		panelToAddExpenditure.setVisible(true);
		panelToAddSavings.setVisible(true);
		panelToAddIncome.setVisible(true);
		
		try {
			expenditures = readTransactionForBudgetYearMonth(EXPENDITURE, EXPENDITURE_CATEGORY);
			savings = readTransactionForBudgetYearMonth(SAVINGS, SAVINGS_CATEGORY);
			income = readIncomeForBudgetYearMonth(INCOME, INCOME_CATEGORY);

			expenditureCategories = databaseReader.readCategoriesForBudgetFromDatabase(budgetId, EXPENDITURE_CATEGORY);
			savingsCategories = databaseReader.readCategoriesForBudgetFromDatabase(budgetId, SAVINGS_CATEGORY);
			incomeCategories = databaseReader.readCategoriesForBudgetFromDatabase(budgetId, INCOME_CATEGORY);
			
			userNamesIdsBudgetIds = databaseReader.readUsersFromDatabase();
			List<String> userNames = userNamesIdsBudgetIds.stream()
					.filter(u -> u.getBudgerId() == budgetId)
					.map(u -> u.getUserName())
					.collect(Collectors.toList());
			
			fillComboboxInPanelsToAddTransactions(expenditureCategories, savingsCategories, incomeCategories, userNames);
			
			expendituresToFillPanel = dataToFillPanel(expenditures);
			savingsToFillPanel = dataToFillPanel(savings);
			incomeToFillPanel = dataToFillPanelIncome(income);
			
			panelViewExpenditure.fillPanel(expendituresToFillPanel, columnsNameDateCategoryAmount);
			panelViewSavings.fillPanel(savingsToFillPanel, columnsNameDateCategoryAmount);
			panelViewIncome.fillPanel(incomeToFillPanel, columnsNameUserNameCategoryAmount);
	
		} catch (DatabaseNotInitialized e) {
			e.printStackTrace();
			return;
		}
	}

	private void fillComboboxInPanelsToAddTransactions(Map<Integer, String> expenditureCategories, Map<Integer, String> savingsCategories, Map<Integer, String> incomeCategories, List<String> userNames) throws DatabaseNotInitialized {
		List<String> expendituresCat = new ArrayList<String>();
		List<String> savingsCat = new ArrayList<String>();
		List<String> incomeCat = new ArrayList<String>();
		
		for(Entry<Integer, String> e: expenditureCategories.entrySet()) {
			expendituresCat.add(e.getValue());
		}
		
		for(Entry<Integer, String> e: savingsCategories.entrySet()) {
			savingsCat.add(e.getValue());
		}
		
		for(Entry<Integer, String> e: incomeCategories.entrySet()) {
			incomeCat.add(e.getValue());
		}
		
		panelToAddExpenditure.fillComboBox(expendituresCat);
		panelToAddSavings.fillComboBox(savingsCat);
		panelToAddIncome.fillComboBox(incomeCat, panelToAddIncome.getComboboxCategory());
		panelToAddIncome.fillComboBox(userNames, panelToAddIncome.getComboboxUser());
	}

	private List<Transaction> readTransactionForBudgetYearMonth(String transactionTablename, String categoryTablename) throws DatabaseNotInitialized {
		transactions = databaseReader.readConcreteTransactionsWithCategoryNameForConcreteBudget(transactionTablename, categoryTablename, budgetId);
		
		List<Transaction> transactionsForConcreteYearAndMonth = transactions.stream()
				.filter(t -> t.getYear() == Integer.parseInt(clickedYear)
						&& t.getMonth() == Integer.parseInt(clickedMonth))
				.collect(Collectors.toList());

		return transactionsForConcreteYearAndMonth;
	}
	
	private List<Transaction> readIncomeForBudgetYearMonth(String transactionTablename, String categoryTablename) throws DatabaseNotInitialized {
		transactions = databaseReader.readIncomeForConcreteBugdetFromDatabase(budgetId);
		
		List<Transaction> incomeForConcreteYearAndMonth = transactions.stream()
				.filter(t -> t.getYear() == Integer.parseInt(clickedYear)
						&& t.getMonth() == Integer.parseInt(clickedMonth))
				.collect(Collectors.toList());

		return incomeForConcreteYearAndMonth;
	}
	
	private List<Triplet<String, String, String>> dataToFillPanel(List<Transaction> transactions){
		List<Triplet<String, String, String>> list = new ArrayList<Triplet<String, String, String>>();
		
		for(Transaction t: transactions) {
			list.add(new Triplet(t.getDate().toString(), t.getCategoryName(), String.valueOf(t.getAmount())));
		}
		
		return list;
	}
	
	private List<Triplet<String, String, String>> dataToFillPanelIncome(List<Transaction> incomes){
		List<Triplet<String, String, String>> list = new ArrayList<Triplet<String, String, String>>();
		
		for(Transaction i: incomes) {
			list.add(new Triplet(i.getUserName(), i.getCategoryName(), String.valueOf(i.getAmount())));
		}
		
		return list;
	}
}
