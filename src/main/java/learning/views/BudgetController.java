package learning.views;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.javatuples.Triplet;

import learning.budget.BudgetDate;
import learning.budget.DatabaseNotInitialized;
import learning.budget.IDatabaseReader;
import learning.budget.IDatabaseWriter;
import learning.budget.Transaction;
import learning.budget.UsersObject;

public class BudgetController implements IListener{
	public static final String INCOME = "Income";
	public static final String SAVINGS = "Savings";
	public static final String EXPENDITURE = "Expenditure";
	public static final String DUES = "Dues";
	public static final String INCOME_CATEGORY = "Income_category";
	public static final String SAVINGS_CATEGORY = "Savings_category";
	public static final String DUES_CATEGORY = "Dues_category";
	public static final String EXPENDITURE_CATEGORY = "Expenditure_category";
	public static final Triplet<String, String, String> columnsNameDateCategoryAmount = new Triplet<String, String, String>("Date:", "Category:", "Amount:");
	public static final Triplet<String, String, String> columnsNameUserNameCategoryAmount = new Triplet<String, String, String>("User:", "Category:", "Amount:");
	
	private PanelWithButtons panelWithBudget, panelWithYears, panelWithMonths;
	private PanelAddTransaction panelToAddExpenditure, panelToAddSavings, panelToAddDues;
	private PanelAddIncome panelToAddIncome;
	private PanelViewTransaction panelViewExpenditure;
	private PanelViewTransaction panelViewSavings;
	private PanelViewTransaction panelViewIncome;
	private PanelViewTransaction panelViewDues;
	private AddNewYearMonthDialog addNewMonthDialog;
	private CreateNewBudgetDialog createBudgetDialog;
	private JLabel lblExpenditureSum, lblSavingsSum, lblIncomeSum, lblDuesSum;
	private JButton btnAddNewMonth;
	
	private IDatabaseReader databaseReader;
	private IDatabaseWriter databaseWriter;
	
	private Integer budgetId;
	private Integer clickedYear;
	private Integer clickedMonth;

	private Map<Integer, String> budgetIdToName;
	private Map<Integer, String> expenditureCategories;
	private Map<Integer, String> savingsCategories;
	private Map<Integer, String> duesCategories;
	private Map<Integer, String> incomeCategories;
	private List<UsersObject> userNamesIdsBudgetIds;
	private String message;
	
	public BudgetController(IDatabaseReader databaseReader, IDatabaseWriter databasewriter,
							PanelWithButtons panelBudget, PanelWithButtons panelYears, PanelWithButtons panelMonths, 
							PanelAddTransaction panelAddExpenditure, PanelAddTransaction panelAddSavings, 
							PanelAddIncome panelAddIncome, PanelAddTransaction panelAddDues,
							PanelViewTransaction panelExpenditureView, PanelViewTransaction panelSavingsView,
							PanelViewTransaction panelIncomeView, PanelViewTransaction panelDuesView,
							JLabel labelExpenditureSum, JLabel labelSavingsSum, JLabel labelIncomeSum, JLabel labelDuesSum,
							JButton buttonAddNewMonth, AddNewYearMonthDialog addNewMonthJDialog, CreateNewBudgetDialog createNewBudgetDialog) throws DatabaseNotInitialized 
	{
		panelWithBudget = panelBudget;
		panelWithYears = panelYears;
		panelWithMonths = panelMonths;
		panelToAddExpenditure = panelAddExpenditure;
		panelToAddSavings = panelAddSavings;
		panelToAddIncome = panelAddIncome;
		panelToAddDues = panelAddDues;
		panelViewExpenditure = panelExpenditureView;
		panelViewSavings = panelSavingsView;
		panelViewIncome = panelIncomeView;
		panelViewDues = panelDuesView;
		lblExpenditureSum = labelExpenditureSum;
		lblSavingsSum = labelSavingsSum;
		lblIncomeSum = labelIncomeSum;
		lblDuesSum = labelDuesSum;
		btnAddNewMonth = buttonAddNewMonth;
		addNewMonthDialog = addNewMonthJDialog;
		createBudgetDialog = createNewBudgetDialog;
		
		this.databaseReader = databaseReader;
		this.databaseWriter = databasewriter;
		
		panelWithBudget.register(this);
		panelWithYears.register(this);
		panelWithMonths.register(this);
		panelToAddExpenditure.register(this);
		panelToAddSavings.register(this);
		panelToAddIncome.register(this);
		panelToAddDues.register(this);
		addNewMonthDialog.register(this);
		createBudgetDialog.register(this);
		
		createBudgetButtons();
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
	public void notify(NotificationData notificationData) throws DatabaseNotInitialized, BudgetNotFoundException {
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
		else if(notificationData.notifierId == panelToAddDues.identifier) {
			handlePanelToAddDataToDatabase(notificationData, panelViewDues, duesCategories, DUES, DUES_CATEGORY, lblDuesSum);
		}
		else if(notificationData.notifierId == panelToAddIncome.identifier) {
			handlePanelToAddIncomeToDatabase(notificationData);
		}
		else if(notificationData.notifierId == addNewMonthDialog.identifier) {
			handleDialogToAddNewMonth(notificationData);
		}
		else if(notificationData.notifierId == createBudgetDialog.identifier) {
			handleDialogToCreateNewBudge(notificationData);
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
		panelToAddDues.clearComboBox();
		panelToAddIncome.clearComboBoxCategory();
		panelToAddIncome.clearComboBoxUser();		
		panelViewExpenditure.clearPanel();
		panelViewSavings.clearPanel();
		panelViewDues.clearPanel();
		panelViewIncome.clearPanel();
		
		panelToAddExpenditure.setVisible(false);
		panelToAddSavings.setVisible(false);
		panelToAddDues.setVisible(false);
		panelToAddIncome.setVisible(false);
		btnAddNewMonth.setEnabled(false);
		
		createYearsButtons();
		Set<Integer> years = getYears(budgetId);
		if(years.size() == 0) {
			btnAddNewMonth.setEnabled(true);
		}else {
			btnAddNewMonth.setEnabled(false);
		}
	}
	
	private void handlePanelWithYearsNotification(NotificationData notificationData) throws DatabaseNotInitialized {
		ButtonsData buttonsData = (ButtonsData) notificationData;
		clickedYear = Integer.parseInt(buttonsData.name);
		
		panelViewExpenditure.clearPanel();
		panelViewSavings.clearPanel();
		panelViewDues.clearPanel();
		panelViewIncome.clearPanel();
		
		panelToAddExpenditure.setVisible(false);
		panelToAddSavings.setVisible(false);
		panelToAddDues.setVisible(false);
		panelToAddIncome.setVisible(false);
		btnAddNewMonth.setEnabled(false);
		
		createMonthsButtons();
	}

	private void handlePanelWithMonthsNotification(NotificationData notificationData) throws DatabaseNotInitialized {
		ButtonsData buttonsData = (ButtonsData) notificationData;
		clickedMonth = Integer.parseInt(buttonsData.name);
		
		panelToAddExpenditure.clearComboBox();
		panelToAddSavings.clearComboBox();
		panelToAddDues.clearComboBox();
		panelToAddIncome.clearComboBoxCategory();
		panelToAddIncome.clearComboBoxUser();
		
		panelToAddExpenditure.setVisible(true);
		panelToAddSavings.setVisible(true);
		panelToAddDues.setVisible(true);
		panelToAddIncome.setVisible(true);
		btnAddNewMonth.setEnabled(true);
		
		expenditureCategories = databaseReader.readCategoriesForBudgetFromDatabase(budgetId, EXPENDITURE_CATEGORY);
		savingsCategories = databaseReader.readCategoriesForBudgetFromDatabase(budgetId, SAVINGS_CATEGORY);
		duesCategories = databaseReader.readCategoriesForBudgetFromDatabase(budgetId, DUES_CATEGORY);
		incomeCategories = databaseReader.readIncomeCategory();
		
		userNamesIdsBudgetIds = databaseReader.readUsersFromDatabase();
		List<String> userNames = userNamesIdsBudgetIds.stream()
				.filter(u -> u.getBudgerId() == budgetId)
				.map(u -> u.getUserName())
				.collect(Collectors.toList());
		
		fillComboboxInPanelsToAddTransactions(expenditureCategories, savingsCategories, duesCategories, incomeCategories, userNames);

		List<Transaction> expenditures = readTransactionForBudgetYearMonth(EXPENDITURE, EXPENDITURE_CATEGORY);
		List<Transaction> savings = readTransactionForBudgetYearMonth(SAVINGS, SAVINGS_CATEGORY);
		List<Transaction> dues = readTransactionForBudgetYearMonth(DUES, DUES_CATEGORY);
		List<Transaction> income = readIncomeForBudgetYearMonth(INCOME, INCOME_CATEGORY);

		List<Triplet<String, String, String>> expendituresToFillPanel = dataToFillPanel(expenditures);
		List<Triplet<String, String, String>> savingsToFillPanel = dataToFillPanel(savings);
		List<Triplet<String, String, String>> duesToFillPanel = dataToFillPanel(dues);
		List<Triplet<String, String, String>> incomeToFillPanel = dataToFillPanelIncome(income);
		
		panelViewExpenditure.fillPanel(expendituresToFillPanel, columnsNameDateCategoryAmount);
		panelViewSavings.fillPanel(savingsToFillPanel, columnsNameDateCategoryAmount);
		panelViewDues.fillPanel(duesToFillPanel, columnsNameDateCategoryAmount);
		panelViewIncome.fillPanel(incomeToFillPanel, columnsNameUserNameCategoryAmount);
		
		lblExpenditureSum.setText("suma = " + sumOfAmount(expendituresToFillPanel));
		lblSavingsSum.setText("suma = " + sumOfAmount(savingsToFillPanel));
		lblDuesSum.setText("suma = " + sumOfAmount(duesToFillPanel));
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
		databaseWriter.writeTransactionToDatabase(Double.parseDouble(buttonAdd.amount), localDate, idCategory, budgetId, tablenameTransaction);
		
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
		
		List<BudgetDate> datesInBudget = databaseReader.readYearsAndMonthsForConcreteBudgetFromDatabase(budgetId);
		BudgetDate bd = new BudgetDate(budgetId, localDate.getYear(), localDate.getMonthValue());
		if(!datesInBudget.contains(bd))
		{
			databaseWriter.writeBudgetIdYearMonthToDatabase(budgetId, localDate.getYear(),localDate.getMonthValue());
			createYearsButtons();
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
	
		List<BudgetDate> datesInBudget = databaseReader.readYearsAndMonthsForConcreteBudgetFromDatabase(budgetId);
		BudgetDate bd = new BudgetDate(budgetId, localDate.getYear(), localDate.getMonthValue());
		if(!datesInBudget.contains(bd))
		{
			databaseWriter.writeBudgetIdYearMonthToDatabase(budgetId, localDate.getYear(),localDate.getMonthValue());
			createYearsButtons();
			createMonthsButtons();
		}
	}
	
	private void handleDialogToAddNewMonth(NotificationData notificationData) throws DatabaseNotInitialized {
		ButtonAddNewMonthData buttonAdd = (ButtonAddNewMonthData) notificationData;
		List<BudgetDate> datesInBudget = databaseReader.readYearsAndMonthsForConcreteBudgetFromDatabase(budgetId);
	
		if(datesInBudget.isEmpty()) {
			databaseWriter.writeBudgetIdYearMonthToDatabase(budgetId, buttonAdd.year, buttonAdd.month);
			buttonAdd.dialog.dispose();
			createYearsButtons();
		}
		
		else {
			BudgetDate bd = new BudgetDate(budgetId, buttonAdd.year, buttonAdd.month);
			if(datesInBudget.contains(bd))
			{
				JOptionPane.showMessageDialog(null, "Taki miesiąc już istnieje dla " + buttonAdd.year);
			}
			else
			{
				databaseWriter.writeBudgetIdYearMonthToDatabase(budgetId, buttonAdd.year, buttonAdd.month);
				buttonAdd.dialog.dispose();
				createYearsButtons();
				createMonthsButtons();
			}
		}
	}
	
	private void handleDialogToCreateNewBudge(NotificationData notificationData) throws BudgetNotFoundException, DatabaseNotInitialized {
		ButtonCreateNewBudgetData buttonCreate = (ButtonCreateNewBudgetData) notificationData;
		List<String> checkedExpenditures = buttonCreate.expenditureCategories;
		List<String> checkedSavings = buttonCreate.savingsCategories;
		List<String> checkedDues = buttonCreate.duesCategories;
		List<String> usersNames = buttonCreate.usersNames;
		String budgetName = buttonCreate.budgetName;
		
		message = "";
		
		boolean filledBudgetName = false;
		boolean uniqueBudgetName = false;
		
		uniqueBudgetName = checkIfBudgetNameIsUnique(budgetName);
		filledBudgetName = checkIfBudgetNameIsntEmpty(budgetName);
		checkIfUserListIsField(usersNames);
		checkIfCategoriesAreChoosen(checkedExpenditures, checkedSavings, checkedDues);
			
		createNewBudget(checkedExpenditures, checkedSavings, checkedDues, usersNames, budgetName, filledBudgetName,
				uniqueBudgetName, buttonCreate.dialog);
	}
	
	private void createNewBudget(List<String> expenditureCategories, List<String> savingsCategories,
			List<String> duesCategories, List<String> users, String budgetName, boolean filledBudgetName, 
			boolean uniqueBudgetName, JDialog dialog)
			throws BudgetNotFoundException, DatabaseNotInitialized {
		int idBudget;
		if(filledBudgetName && uniqueBudgetName && users.size() > 0 && expenditureCategories.size() > 0 &&
				savingsCategories.size() > 0 && duesCategories.size() > 0) {
			
			databaseWriter.writeBudgetNameToDatabase(budgetName);
			idBudget = getBudgetIdFromDatabase(budgetName);
			
			databaseWriter.writeUsersListTodatabase(users, idBudget);
			databaseWriter.writeCategoryListTodatabase(expenditureCategories, idBudget, EXPENDITURE_CATEGORY);
			databaseWriter.writeCategoryListTodatabase(savingsCategories, idBudget, SAVINGS_CATEGORY);
			databaseWriter.writeCategoryListTodatabase(duesCategories, idBudget, DUES_CATEGORY);
			
			createBudgetButtons();
			
			dialog.dispose();
		}
		
		else {
			
			JOptionPane.showMessageDialog(null, message);
		
		}
	}
	
	private int getBudgetIdFromDatabase(String budgetName) throws BudgetNotFoundException, DatabaseNotInitialized{
		HashMap<Integer, String> budgetIdNameMap = databaseReader.readBudgetIdNameFromDatabase();

		for(Entry<Integer, String> entry: budgetIdNameMap.entrySet()){
			if(budgetName.equals(entry.getValue())){
				return entry.getKey();
			}
		}
		throw new BudgetNotFoundException(budgetName);
	}
	
	private boolean checkIfBudgetNameIsUnique(String budgetName) throws BudgetNotFoundException, DatabaseNotInitialized{
		HashMap<Integer, String> budgetIdNameMap = databaseReader.readBudgetIdNameFromDatabase();
		if(budgetIdNameMap.containsValue(budgetName))
		{
			message += "Taka nazwa budżetu już istnieje \n";
			return false;
		}
		return true;
	}
	
	private boolean checkIfBudgetNameIsntEmpty(String budgetName) {
		boolean filledBudgetName;
		if(budgetName.equals("") || 
				budgetName.isEmpty() ||
				budgetName.trim().isEmpty()){
				message += "Wpisz nazwę budżetu \n";
				filledBudgetName = false;
		}
		else {
			filledBudgetName = true;
		}
		return filledBudgetName;
	}
	
	private void checkIfCategoriesAreChoosen(List<String> expenditureCategories, List<String> savingsCategories,
			List<String> duesCategories) {
		if(expenditureCategories.size() == 0) {
			message += "Wybierz kategorie wydatków \n";
		}
		if(savingsCategories.size() == 0) {
			message += "Wybierz kategorie oszczędności \n";
		}
		if(duesCategories.size() == 0) {
			message += "Wybierz kategorie opłat \n";
		}
	}
	
	private void checkIfUserListIsField(List<String> users) {
		if(users.size() == 0) {
			message += "Wpisz użytkowników \n";
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
	
	private void fillComboboxInPanelsToAddTransactions(Map<Integer, String> expenditureCategories, 
														Map<Integer, String> savingsCategories, 
														Map<Integer, String> duesCategories,
														Map<Integer, String> incomeCategories, 
														List<String> userNames) throws DatabaseNotInitialized {
		List<String> expendituresCat = new ArrayList<>(expenditureCategories.values());
		List<String> savingsCat = new ArrayList<>(savingsCategories.values());
		List<String> duesCat = new ArrayList<>(duesCategories.values());
		List<String> incomeCat = new ArrayList<>(incomeCategories.values());

		panelToAddExpenditure.fillComboBox(expendituresCat);
		panelToAddSavings.fillComboBox(savingsCat);
		panelToAddDues.fillComboBox(duesCat);
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
		List<BudgetDate> dates = databaseReader.readYearsAndMonthsForConcreteBudgetFromDatabase(budgetId);
		Set<Integer> years = dates.stream()
				.map(BudgetDate::getYear)
				.collect(Collectors.toSet());
		
		return years;
	}
	
	private Set<Integer> getMonths(int budgetId) throws DatabaseNotInitialized {
		List<BudgetDate> dates = databaseReader.readYearsAndMonthsForConcreteBudgetFromDatabase(budgetId);
		
		Set<Integer> months = dates.stream()
				.filter(d -> d.getYear() == clickedYear)
				.map(BudgetDate::getMonth)
				.collect(Collectors.toSet());
		
		return months;
	}
	
	private void createBudgetButtons() throws DatabaseNotInitialized {
		budgetIdToName = databaseReader.readBudgetIdNameFromDatabase();
		SortedSet<String> names = new TreeSet<String>(budgetIdToName.values());
		panelWithBudget.createButtons(names);
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
				map(month -> month.toString()).
				collect(Collectors.toCollection(LinkedHashSet::new));
		
		panelWithMonths.clearPanel();
		panelWithMonths.createButtons(asStrings);
	}
}
