package learning.budget;

import static learning.budget.Constants.INCOME_TYPE_SALARY;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.javatuples.Triplet;

import javafx.util.Pair;
import learning.budget.views.AddPanelToAddTransactionWithComboBoxCategoryAndUserListener;
import learning.budget.views.AddPanelToAddTransactionWithComboBoxCategoryListener;
import learning.budget.views.AddTransactionToDatabaseListener;
import learning.budget.views.FillPanelTransactionWithThreeLabelsListener;
import learning.budget.views.FillPanelTransactionWithTwoLabelsListener;
import learning.budget.views.PanelAddTransactionWithComboBoxCategory;
import learning.budget.views.SumOfTransactionAmountListener;

public class GenerateComponents {
	protected IDatabaseReader databaseReader;
	protected IDatabaseWriter databaseWriter;
	private HashMap<Integer, String> budgetIdNameMap;
	private ArrayList<Transaction> usersIncomeObjectList;
	private HashMap<Integer, String> usersNameHashMap;
	private HashMap<Integer, String> incomeCategoryMap;
	private HashMap<Integer, String> expenditureCategoryMap;
	private HashMap<Integer, String> savingsCategoryMap;
	private static String yearAndMonth;
	private Sort sort;
	private ArrayList<Transaction> expenditureObjectListWithItsId;
	private ArrayList<Transaction> savingsObjectListWithItsId;
	private FillComponentsFromDataInDatabase fillcomponentsWithDataFromDatabase;
	private ListFilter listFilter;
	private JButton jButtonWithMonthName;
	private List<Pair<Integer, String>> expenditureCategoryNameIdBudgetPairList;
	private List<UsersObject> usersObjectList;
	//FIXME do i use it and that name is bad
	public static int id;
	private List<Triplet<Integer, Integer, String>> categoryNameBudgetIdTripletList;

	public GenerateComponents(IDatabaseReader _databaseReader, IDatabaseWriter _databaseWriter) throws DatabaseNotInitialized {
		databaseReader = _databaseReader;
		databaseWriter = _databaseWriter;
		budgetIdNameMap = databaseReader.readBudgetIdNameFromDatabase();
		usersIncomeObjectList = databaseReader.readIncomefromDatabase();
		usersNameHashMap = databaseReader.readUsersFromDatabasetoHashMap();
		incomeCategoryMap = databaseReader.readCategoryFromDatabase("Income_category");
		expenditureCategoryMap = databaseReader.readCategoryFromDatabase("Expenditure_category");
		savingsCategoryMap = databaseReader.readCategoryFromDatabase("Savings_category");
		expenditureObjectListWithItsId = databaseReader.readExpenditureWithItsIdFromDataBase();
		savingsObjectListWithItsId = databaseReader.readSavingsWithItsIdFromDataBase();
		listFilter = new ListFilter();
		fillcomponentsWithDataFromDatabase = new FillComponentsFromDataInDatabase();
		expenditureCategoryNameIdBudgetPairList = databaseReader.readCategoryNameWithBudgetIdFromDatabase("Expenditure_category");
		usersObjectList = databaseReader.readUsersFromDatabase();
		categoryNameBudgetIdTripletList = databaseReader.readCategoryNameCategoryIdAndBudgetIdFromDatabase("Expenditure_category");
		sort = new Sort();
	}
	
	public String getYearAndMonth() {
		return yearAndMonth;
	}
//FIXME DO i use it?
	public int  getId() {
		return id;
	}
	
		protected void generateButtonsWithBudgetsNames(JPanel panelAddOtherIncome, JPanel panelAddExpenditure, JPanel panelWithBudgetsName, JPanel panelWithYears,
			JPanel panelWithMonths, JPanel panelUsersIncome, JPanel panelBudget, JPanel panelBudgetEmpty,JPanel panelOtherIncomeView, JPanel panelExpenditureView, JPanel panelSavingsView, JLabel lblInform, JLabel lblExpenditureSum, JLabel labelSavingsSum,
			JLabel labelOtherIncomeSum, JLabel labelIncomeSum) {

		int budgetCurrentId = 0;
		
		for (Entry<Integer, String> entry : budgetIdNameMap.entrySet()) {
			budgetCurrentId = entry.getKey();
			JButton jButtonBudgetName = new JButton(entry.getValue());
			
		
			panelWithBudgetsName.add(jButtonBudgetName);
			generateYearButtonsAfterClickingBudgetNamesButtons(panelAddOtherIncome, panelAddExpenditure, jButtonBudgetName, budgetCurrentId, panelWithYears,
					panelWithMonths, panelUsersIncome, panelBudget, panelBudgetEmpty, panelOtherIncomeView,
					panelExpenditureView, panelSavingsView, lblInform, lblExpenditureSum, labelSavingsSum,
					labelOtherIncomeSum, labelIncomeSum);
		}
		panelWithBudgetsName.revalidate();
		panelWithBudgetsName.repaint();
	}
	
	private void generateYearButtonsAfterClickingBudgetNamesButtons(JPanel panelAddOtherIncome, JPanel panelAddExpenditure, JButton button, int budgetId, JPanel panelWithYears,
			JPanel panelWithMonths, JPanel panelUsersIncome, JPanel panelBudget, JPanel panelBudgetEmpty,
			JPanel panelOtherIncomeView,JPanel panelExpenditureView, JPanel panelSavingsView, JLabel lblInform, JLabel lblExpenditureSum,
			JLabel labelSavingsSum, JLabel labelOtherIncomeSum, JLabel labelIncomeSum) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBudget.setVisible(false);
				panelBudgetEmpty.setVisible(true);
				//FIXME DO i use it?
				id = budgetId;
				
				List<Container> components = Arrays.asList(lblInform, panelWithYears, panelWithMonths, panelUsersIncome, panelOtherIncomeView, panelExpenditureView, panelSavingsView);

				components.stream().forEach(c -> {c.removeAll();});
				
				Map<Integer, TreeSet<Integer>> budgetsToYears = sort.sortYearsInConcredeBudgetId(usersIncomeObjectList);
				for (Entry<Integer, TreeSet<Integer>> entry : budgetsToYears.entrySet()) {
					Integer idBudget = entry.getKey();
					if (idBudget == budgetId) {
						for(Integer year : entry.getValue()) {
							JButton jButtonWithYear = new JButton(year + "");
							panelWithYears.add(jButtonWithYear);
							generateMonthsButtonsForConcreteYearAfterClickingYearButton(panelAddOtherIncome, panelAddExpenditure, jButtonWithYear, idBudget,
									panelWithMonths, year, panelUsersIncome, panelBudget, panelBudgetEmpty,
									panelOtherIncomeView, panelExpenditureView, panelSavingsView, lblInform,
									lblExpenditureSum, labelSavingsSum, labelOtherIncomeSum, labelIncomeSum);
						}
					}

				}
				components.stream().forEach(c -> {c.revalidate(); c.repaint();});
			}
		});
	}
	
	private void generateMonthsButtonsForConcreteYearAfterClickingYearButton(JPanel panelAddOtherIncome, JPanel panelAddExpenditure, JButton button, int budgetId,
			JPanel panelWithMonths, int year, JPanel panelUsersIncome, JPanel panelBudget,
			JPanel panelBudgetEmpty, JPanel panelOtherIncomeView, JPanel panelExpenditureView, JPanel panelSavingsView,
			JLabel lblInform, JLabel lblExpenditureSum, JLabel labelSavingsSum, JLabel labelOtherIncomeSum,
			JLabel labelIncomeSum) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelBudget.setVisible(false);
				panelBudgetEmpty.setVisible(true);
				
				List<Container> components = Arrays.asList(lblInform, panelWithMonths, panelUsersIncome, panelOtherIncomeView,
						panelExpenditureView, panelSavingsView);
				
				components.stream().forEach(c -> {c.removeAll();});
				
				DataFormatter dataFormatter = new DataFormatter();
				ArrayList<Integer> listOfMonths = sort.sortMonthsForConcreteYearAndBudgetId(usersIncomeObjectList, budgetId, year);
				
				List<Transaction> usersConstrained = usersIncomeObjectList.stream()
						.filter(uio -> uio.getBudgetId() == budgetId && uio.getDate().getYear() == year
						&& uio.getCategoryId() == INCOME_TYPE_SALARY)
						.collect(Collectors.toList());
				
				//List<String> expenditureCategoryNameList = listFilter.filterCategoryByBudgetId(expenditureCategoryNameIdBudgetPairList, budgetId);
				//AddPanelToAddTransactionWithComboBoxCategoryListener addPanelToAddTransactionWithComboBoxCategoryListener = new AddPanelToAddTransactionWithComboBoxCategoryListener(expenditureCategoryNameList, panelAddExpenditure);
				//
				HashMap<Integer, String> expenditureCategoryHashMap = listFilter.filterCategoryByBudgetId(categoryNameBudgetIdTripletList, budgetId);
				//PanelAddTransactionWithComboBoxCategory panelAddTransaction = new PanelAddTransactionWithComboBoxCategory(expenditureCategoryHashMap);
				AddPanelToAddTransactionWithComboBoxCategoryListener addPanelToAddTransactionWithComboBoxCategoryListener = new AddPanelToAddTransactionWithComboBoxCategoryListener(expenditureCategoryHashMap, panelAddExpenditure, databaseWriter, budgetId, "Expenditure");
				//
				
				List<String> otherIncomeCategoryNameList = listFilter.filterIncomeCategoryByIncomeType(incomeCategoryMap);
				List<String> usersNameList = listFilter.filterUsersByBudgetId(usersObjectList, budgetId);
				AddPanelToAddTransactionWithComboBoxCategoryAndUserListener addPanelToAddTransactionWithComboBoxCategoryAndUserNameListener = new AddPanelToAddTransactionWithComboBoxCategoryAndUserListener(otherIncomeCategoryNameList, usersNameList, panelAddOtherIncome);
						
				for (int i = 0; i < listOfMonths.size(); i++) {
					int month = listOfMonths.get(i);
					//FIXME only what need to be in loop stays in the loop 
					
					String monthName = DataFormatter.changeMonhNumberFromMonthName(month);
					jButtonWithMonthName = new JButton(monthName + "");
					panelWithMonths.add(jButtonWithMonthName);

					List<Transaction> users = usersConstrained.stream().filter(uio -> uio.getDate().getMonthValue() == month).collect(Collectors.toList());
					List<Pair<String, Double>> usersPairs1 = new ArrayList<Pair<String, Double>>();
					for (Transaction uio : users) {
						usersPairs1.add(new Pair<String, Double>(usersNameHashMap.get(uio.getTransactionId()), uio.getAmount()));
					}
					
					List<Transaction> expenditureListPlusCategoryName=  makeTransactionListWithCategoryNameFromListAndHashMap(expenditureObjectListWithItsId, expenditureCategoryMap, budgetId, year, month);
					fillPanelTransactionWithDataCategoryAndAmount(expenditureListPlusCategoryName, panelExpenditureView, lblExpenditureSum);
					
					List<Transaction> savingsListPlusCategoryName = makeTransactionListWithCategoryNameFromListAndHashMap(savingsObjectListWithItsId, savingsCategoryMap, budgetId, year, month);
					fillPanelTransactionWithDataCategoryAndAmount(savingsListPlusCategoryName, panelSavingsView, labelSavingsSum);
					
					List<Transaction> otherIncomeListWithUserName = makeTransactionListWithCategoryNameFromListAndHashMap(usersIncomeObjectList, usersNameHashMap, budgetId, year, month);
					fillPanelTransactionWithUserNameCategoryAndAmount(otherIncomeListWithUserName, panelOtherIncomeView, labelOtherIncomeSum);
					
					fillPanelIncome(usersIncomeObjectList, budgetId, year, month, true, panelUsersIncome, labelIncomeSum);
					
					
					//FIXME Twice list is added to comboBox and i dont know why
					jButtonWithMonthName.addActionListener(addPanelToAddTransactionWithComboBoxCategoryListener); 
					jButtonWithMonthName.addActionListener(addPanelToAddTransactionWithComboBoxCategoryAndUserNameListener);
					jButtonWithMonthName.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							generateMonthNameAndYearInfoAfterClickingMonthButton(lblInform, year, month);
							panelBudget.setVisible(true);
							panelBudgetEmpty.setVisible(false);
						}
					});
				}
				components.stream().forEach(c -> {c.revalidate(); c.repaint();});
				getId();
			}
		});
	}

	private void generateMonthNameAndYearInfoAfterClickingMonthButton(JLabel lblInform, int year, int month) {
		String date = month + " " + year;
		yearAndMonth = date;
		DataFormatter dataFormatter = new DataFormatter();
		String monthName = dataFormatter.changeMonhNumberFromMonthName(month);
		lblInform.setText(year + " " + monthName.toUpperCase());
	}
	
	private List<Triplet<String, String, String>> makeListWithDateCategoryAmount(List<Transaction> listTransactionInConcreteBudgetIdYearAndMonth) {
		List<Triplet<String, String, String>> list = new ArrayList<>();
		for(Transaction t: listTransactionInConcreteBudgetIdYearAndMonth) {
			Triplet<String, String, String> triplet = new Triplet<String, String, String>(t.getDate().toString(), t.getCategoryName(), String.valueOf(t.getAmount()));
			list.add(triplet);
		}
		return list;
	}
	
	private List<Triplet<String, String, String>> makeTripletListWithUserNameCategoryAmount(List<Transaction> listTransactionInConcreteBudgetIdYearAndMonth) {
		List<Triplet<String, String, String>> list = new ArrayList<>();
		for(Transaction t: listTransactionInConcreteBudgetIdYearAndMonth) {
			Triplet<String, String, String> triplet = new Triplet<String, String, String>(t.getUserName(), t.getCategoryName(), String.valueOf(t.getAmount()));
			list.add(triplet);
		}
		return list;
	}
	
	private List<Transaction> makeTransactionListWithCategoryNameFromListAndHashMap(List<Transaction> transactionObjectListWithItsId, HashMap<Integer, String> transactionHashMap, int budgetId, int year, int month) {
		List<Transaction> transactionConstrained = listFilter.filterIncomeByBudgetIdYearMonthIncomeType(usersIncomeObjectList, budgetId, year, month, false);
		List<Transaction> transactionSorted = sort.sortTransactionAfterItsDay(transactionConstrained, year, month, budgetId);
		List<Transaction> transactionListPlusCategoryName = fillcomponentsWithDataFromDatabase.fillTransactionIncomeList(transactionSorted, incomeCategoryMap, usersNameHashMap);
		return transactionListPlusCategoryName;
	}
	
	private void fillPanelTransactionWithDataCategoryAndAmount(List<Transaction> transactionListPlusCategoryName, JPanel panelToDisplay, JLabel lblSum) {
		List<Triplet<String, String, String>> transactionTripletListOnlyWithDateCategoryNameAndAmount = makeListWithDateCategoryAmount(transactionListPlusCategoryName);
		SumOfTransactionAmountListener sumOfTransactionAmountListener = new SumOfTransactionAmountListener(transactionListPlusCategoryName, lblSum);
		FillPanelTransactionWithThreeLabelsListener fillPanellTransactionWithThreeLabelsListener = new FillPanelTransactionWithThreeLabelsListener(transactionTripletListOnlyWithDateCategoryNameAndAmount, panelToDisplay);
		jButtonWithMonthName.addActionListener(sumOfTransactionAmountListener);
		jButtonWithMonthName.addActionListener(fillPanellTransactionWithThreeLabelsListener);
	}
	
	private void fillPanelTransactionWithUserNameCategoryAndAmount(List<Transaction> transactionListPlusCategoryName, JPanel panelToDisplay, JLabel lblSum) {
		List<Triplet<String, String, String>> transactionTripletListOnlyWithUserNameCategoryNameAndAmount = makeTripletListWithUserNameCategoryAmount(transactionListPlusCategoryName);
		SumOfTransactionAmountListener sumOfTransactionAmountListener = new SumOfTransactionAmountListener(transactionListPlusCategoryName, lblSum);
		FillPanelTransactionWithThreeLabelsListener fillPanellTransactionWithThreeLabelsListener = new FillPanelTransactionWithThreeLabelsListener(transactionTripletListOnlyWithUserNameCategoryNameAndAmount, panelToDisplay);
		jButtonWithMonthName.addActionListener(sumOfTransactionAmountListener);
		jButtonWithMonthName.addActionListener(fillPanellTransactionWithThreeLabelsListener);
	}
	


	
	private void fillPanelIncome(List<Transaction> usersIncomeObjectList, int budgetId, int year, int month, boolean salary, JPanel panelUsersIncome, JLabel labelIncomeSum) {
		List<Transaction> incomeConstrained = listFilter.filterIncomeByBudgetIdYearMonthIncomeType(usersIncomeObjectList, budgetId, year, month, true);
		List<Pair<String, Double>> usersPairs = fillcomponentsWithDataFromDatabase.fillTransactionIncomePairList(incomeConstrained, usersNameHashMap);
		FillPanelTransactionWithTwoLabelsListener fillPanelTransactionViewWithTwoLabelsListener = new FillPanelTransactionWithTwoLabelsListener(usersPairs, panelUsersIncome);
		jButtonWithMonthName.addActionListener(fillPanelTransactionViewWithTwoLabelsListener);
		SumOfTransactionAmountListener sumOfIncomeListener = new SumOfTransactionAmountListener(incomeConstrained, labelIncomeSum);
		jButtonWithMonthName.addActionListener(sumOfIncomeListener);
	}
}
