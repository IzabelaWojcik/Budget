package learning.views;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.swing.JLabel;

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
	private JLabel lblExpenditureSum, lblSavingsSum, lblIncomeSum;
	
	private IDatabaseReader databaseReader;
	private IDatabaseWriter databaseWriter;
	
	private Integer budgetId;
	private Integer clickedYear;
	private Integer clickedMonth;

	private final Map<Integer, String> budgetIdToName;
	private Map<Integer, String> expenditureCategories;
	private Map<Integer, String> savingsCategories;
	private Map<Integer, String> incomeCategories;
	private List<UsersObject> userNamesIdsBudgetIds;
	
	public BudgetController(IDatabaseReader databaseReader, IDatabaseWriter databasewriter,
							PanelWithButtons panelBudget, PanelWithButtons panelYears, PanelWithButtons panelMonths, 
							PanelAddTransaction panelAddExpenditure, PanelAddTransaction panelAddSavings, PanelAddIncome panelAddIncome,
							PanelViewTransaction panelExpenditureView, PanelViewTransaction panelSavingsView, PanelViewTransaction panelIncomeView,
							JLabel labelExpenditureSum, JLabel labelSavingsSum, JLabel labelIncomeSum) throws DatabaseNotInitialized 
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
		lblExpenditureSum = labelExpenditureSum;
		lblSavingsSum = labelSavingsSum;
		lblIncomeSum = labelIncomeSum;
		
		this.databaseReader = databaseReader;
		this.databaseWriter = databasewriter;
		
		panelWithBudget.register(this);
		panelWithYears.register(this);
		panelWithMonths.register(this);
		panelToAddExpenditure.register(this);
		panelToAddSavings.register(this);
		panelToAddIncome.register(this);
		
		budgetIdToName = databaseReader.readBudgetIdNameFromDatabase();
		SortedSet<String> names = new TreeSet<String>(budgetIdToName.values());
		panelWithBudget.createButtons(names);
	}
	
	private <K, V> K getKey(Map<K, V> map, V value) {
		for (Entry<K, V> e: map.entrySet()) {
			if(e.getValue().equals(value)) {
				return e.getKey();
			}
		}
		
		throw new NoSuchElementException("Value not found: " + value.toString());
	}
	
	@Override
	public void notify(NotificationData notificationData) throws DatabaseNotInitialized {
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
			handlePanelToAddDataToDatabase(notificationData, panelViewExpenditure, expenditureCategories, EXPENDITURE, EXPENDITURE_CATEGORY, lblExpenditureSum);
		}
		else if(notificationData.notifierId == panelToAddSavings.identifier) {
			handlePanelToAddDataToDatabase(notificationData, panelViewSavings, savingsCategories, SAVINGS, SAVINGS_CATEGORY, lblSavingsSum);
		}
		else if(notificationData.notifierId == panelToAddIncome.identifier) {
			handlePanelToAddIncomeToDatabase(notificationData);
		}
		else
		{
			throw new IllegalArgumentException("Unexpected identifier: " + notificationData.notifierId);
		}
	}

	private void handlePanelWithBudgetNotification(NotificationData notificationData) throws DatabaseNotInitialized {
		ButtonsData buttonsData = (ButtonsData) notificationData;
		String clickedBudgetName = buttonsData.name;
		budgetId = getKey(budgetIdToName, clickedBudgetName);
		
		panelWithYears.clearPanel();
		panelWithMonths.clearPanel();
		
		panelToAddExpenditure.clearComboBox();
		panelToAddSavings.clearComboBox();
		panelToAddIncome.clearComboBoxCategory();
		panelToAddIncome.clearComboBoxUser();		
		panelViewExpenditure.clearPanel();
		panelViewSavings.clearPanel();
		panelViewIncome.clearPanel();
		
		createYearsButtons();
	}
	
	private void handlePanelWithYearsNotification(NotificationData notificationData) throws DatabaseNotInitialized {
		ButtonsData buttonsData = (ButtonsData) notificationData;
		clickedYear = Integer.parseInt(buttonsData.name);
		
		panelViewExpenditure.clearPanel();
		panelViewSavings.clearPanel();
		panelViewIncome.clearPanel();
		
		createMonthsButtons();
	}

	private void handlePanelWithMonthsNotification(NotificationData notificationData) throws DatabaseNotInitialized {
		ButtonsData buttonsData = (ButtonsData) notificationData;
		clickedMonth = Integer.parseInt(buttonsData.name);
		
		panelToAddExpenditure.clearComboBox();
		panelToAddSavings.clearComboBox();
		panelToAddIncome.clearComboBoxCategory();
		panelToAddIncome.clearComboBoxUser();
		
		panelToAddExpenditure.setVisible(true);
		panelToAddSavings.setVisible(true);
		panelToAddIncome.setVisible(true);
		
		expenditureCategories = databaseReader.readCategoriesForBudgetFromDatabase(budgetId, EXPENDITURE_CATEGORY);
		savingsCategories = databaseReader.readCategoriesForBudgetFromDatabase(budgetId, SAVINGS_CATEGORY);
		incomeCategories = databaseReader.readCategoriesForBudgetFromDatabase(budgetId, INCOME_CATEGORY);
		
		userNamesIdsBudgetIds = databaseReader.readUsersFromDatabase();
		List<String> userNames = userNamesIdsBudgetIds.stream()
				.filter(u -> u.getBudgerId() == budgetId)
				.map(u -> u.getUserName())
				.collect(Collectors.toList());
		
		fillComboboxInPanelsToAddTransactions(expenditureCategories, savingsCategories, incomeCategories, userNames);

		List<Transaction> expenditures = readTransactionForBudgetYearMonth(EXPENDITURE, EXPENDITURE_CATEGORY);
		List<Transaction> savings = readTransactionForBudgetYearMonth(SAVINGS, SAVINGS_CATEGORY);
		List<Transaction> income = readIncomeForBudgetYearMonth(INCOME, INCOME_CATEGORY);

		List<Triplet<String, String, String>> expendituresToFillPanel = dataToFillPanel(expenditures);
		List<Triplet<String, String, String>> savingsToFillPanel = dataToFillPanel(savings);
		List<Triplet<String, String, String>> incomeToFillPanel = dataToFillPanelIncome(income);
		
		panelViewExpenditure.fillPanel(expendituresToFillPanel, columnsNameDateCategoryAmount);
		panelViewSavings.fillPanel(savingsToFillPanel, columnsNameDateCategoryAmount);
		panelViewIncome.fillPanel(incomeToFillPanel, columnsNameUserNameCategoryAmount);
		
		lblExpenditureSum.setText("suma = " + sumOfAmount(expendituresToFillPanel));
		lblSavingsSum.setText("suma = " + sumOfAmount(savingsToFillPanel));
		lblIncomeSum.setText("suma = " + sumOfAmount(incomeToFillPanel));
	}

	private void handlePanelToAddDataToDatabase(NotificationData notificationData, 
			PanelViewTransaction panelViewTransaction, Map<Integer, String> categories, 
			String tablenameTransaction, String tablenameCategory, 
			JLabel lblSum) throws DatabaseNotInitialized {
		ButtonAddTransactionData buttonAdd = (ButtonAddTransactionData) notificationData;

		Set<Integer> displayedYears = getYears(budgetId);
		Set<Integer> displayedMonths = getMonths(budgetId);
		
		int idCategory = getKey(categories, buttonAdd.category);
		LocalDate localDate = Instant.ofEpochMilli(buttonAdd.date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		databaseWriter.writeExpenditureOrSavingsToDatabase(Double.parseDouble(buttonAdd.amount), localDate, idCategory, budgetId, tablenameTransaction);
		
		if(localDate.getYear() == clickedYear && localDate.getMonthValue() == clickedMonth) {
			List<Transaction> transaction = readTransactionForBudgetYearMonth(tablenameTransaction, tablenameCategory);
			List<Triplet<String, String, String>> datesCategoriesAmounts = dataToFillPanel(transaction);
			
			panelViewTransaction.clearPanel();
			panelViewTransaction.fillPanel(datesCategoriesAmounts, columnsNameDateCategoryAmount);
		
			lblSum.setText("suma = " + sumOfAmount(datesCategoriesAmounts));
		}

		if(!displayedYears.contains(localDate.getYear())) {
			createYearsButtons();
		}
		
		if(!displayedMonths.contains(localDate.getMonthValue())) {
			createMonthsButtons();
		}
	}

	private void handlePanelToAddIncomeToDatabase(NotificationData notificationData) throws DatabaseNotInitialized {
		ButtonAddIncomeData buttonAdd = (ButtonAddIncomeData) notificationData;
		int idCategory = getKey(incomeCategories, buttonAdd.category);
		int idUser = 0;
		
		for(UsersObject uo: userNamesIdsBudgetIds) {
			if(buttonAdd.user.equals(uo.getUserName())) {
				idUser = uo.getUserId();
			}
		}
		
		Set<Integer> displayedYears = getYears(budgetId);
		Set<Integer> displayedMonths = getMonths(budgetId);
		
		LocalDate localDate = Instant.ofEpochMilli(buttonAdd.date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		databaseWriter.writeIncomeToDatabase(Double.parseDouble(buttonAdd.amount), Instant.ofEpochMilli(buttonAdd.date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate(), idUser, idCategory, budgetId);
		
		List<Transaction> income = readIncomeForBudgetYearMonth(INCOME, INCOME_CATEGORY);
		if(localDate.getYear() == clickedYear && localDate.getMonthValue() == clickedMonth) {
			List<Triplet<String, String, String>> incomeToFillPanel = dataToFillPanelIncome(income);

			panelViewIncome.clearPanel();
			panelViewIncome.fillPanel(incomeToFillPanel, columnsNameUserNameCategoryAmount);
			
			lblIncomeSum.setText("suma = " + sumOfAmount(incomeToFillPanel));
		}
		
		if(!displayedYears.contains(localDate.getYear())) {
			createYearsButtons();
		}

		if(!displayedMonths.contains(localDate.getMonthValue())) {
			createMonthsButtons();
		}
	}
	
	private List<Transaction> readTransactionForBudgetYearMonth(String transactionTablename, String categoryTablename) throws DatabaseNotInitialized {
		List<Transaction> transactions = databaseReader.readConcreteTransactionsWithCategoryNameForConcreteBudget(transactionTablename, categoryTablename, budgetId);
		
		List<Transaction> transactionsForConcreteYearAndMonth =
				transactions.stream()
				.filter(t -> t.getYear() == clickedYear && t.getMonth() == clickedMonth)
				.collect(Collectors.toList());

		return transactionsForConcreteYearAndMonth;
	}
	
	private List<Transaction> readIncomeForBudgetYearMonth(String transactionTablename, String categoryTablename) throws DatabaseNotInitialized {
		List<Transaction> transactions = databaseReader.readIncomeForConcreteBugdetFromDatabase(budgetId);
		
		List<Transaction> incomeForConcreteYearAndMonth =
				transactions.stream()
				.filter(t -> t.getYear() == clickedYear && t.getMonth() == clickedMonth)
				.collect(Collectors.toList());

		return incomeForConcreteYearAndMonth;
	}
	
	private void fillComboboxInPanelsToAddTransactions(Map<Integer, String> expenditureCategories, Map<Integer, String> savingsCategories, Map<Integer, String> incomeCategories, List<String> userNames) throws DatabaseNotInitialized {
		List<String> expendituresCat = new ArrayList<>(expenditureCategories.values());
		List<String> savingsCat = new ArrayList<>(savingsCategories.values());
		List<String> incomeCat = new ArrayList<>(incomeCategories.values());

		panelToAddExpenditure.fillComboBox(expendituresCat);
		panelToAddSavings.fillComboBox(savingsCat);
		panelToAddIncome.fillComboBoxCategory(incomeCat);
		panelToAddIncome.fillComboBoxUser(userNames);
	}
	
	private List<Triplet<String, String, String>> dataToFillPanel(List<Transaction> transactions){
		List<Triplet<String, String, String>> list = new ArrayList<Triplet<String, String, String>>();
		
		for(Transaction t: transactions) {
			list.add(new Triplet<String, String, String>(t.getDate().toString(), t.getCategoryName(), String.valueOf(t.getAmount())));
		}
		
		return list;
	}
	
	private List<Triplet<String, String, String>> dataToFillPanelIncome(List<Transaction> incomes){
		List<Triplet<String, String, String>> list = new ArrayList<Triplet<String, String, String>>();
		
		for(Transaction i: incomes) {
			list.add(new Triplet<String, String, String>(i.getUserName(), i.getCategoryName(), String.valueOf(i.getAmount())));
		}
		
		return list;
	}
	
	public String sumOfAmount(List<Triplet<String, String, String>> dataToFillPanel) {
		double sum = 0;
		for(Triplet<String, String, String> t: dataToFillPanel) {
			sum += Double.parseDouble((String) t.getValue2());
		}
		return String.valueOf(sum);
	}
	
	private Set<Integer> getYears(int budgetId) throws DatabaseNotInitialized {
		List<LocalDate> dates = databaseReader.readDatesForBudgetFromDatabase(budgetId);
		Set<Integer> years = dates.stream()
				.map(LocalDate::getYear)
				.collect(Collectors.toSet());

		return years;
	}
	
	private Set<Integer> getMonths(int budgetId) throws DatabaseNotInitialized {
		List<LocalDate> dates = databaseReader.readDatesForBudgetFromDatabase(budgetId);
		Set<Integer> years = dates.stream()
				.filter(t -> t.getYear() == clickedYear)
				.map(LocalDate::getMonthValue)
				.collect(Collectors.toSet());
		
		return years;
	}
	
	private void createYearsButtons() throws DatabaseNotInitialized {
		SortedSet<Integer> years = new TreeSet<>(getYears(budgetId)).descendingSet();
		Set<String> asStrings = years.stream().
				map(year -> year.toString()).
				collect(Collectors.toCollection(LinkedHashSet::new));
		
		panelWithYears.clearPanel();
		panelWithYears.createButtons(asStrings);
	}
	
	private void createMonthsButtons() throws DatabaseNotInitialized {
		SortedSet<Integer> months = new TreeSet<>(getMonths(budgetId)).descendingSet();
		Set<String> asStrings = months.stream().
				map(year -> year.toString()).
				collect(Collectors.toCollection(LinkedHashSet::new));
		
		panelWithMonths.clearPanel();
		panelWithMonths.createButtons(asStrings);
	}
}
