package learning.budget;

import static learning.budget.Constants.INCOME_TYPE_SALARY;
import static learning.budget.DataFormatter.setAmountFormat;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javafx.util.Pair;

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
	private ComboBoxAction comboBoxAction;
	private Sort sort;
	private ArrayList<Transaction> expenditureObjectListWithItsId;
	private ArrayList<Transaction> savingsObjectListWithItsId;
	private FillComponentsFromDataInDatabase fillcomponentsWithDataFromDatabase;
	private GenerateTransactionAfterClickingMonthButton generateTransactionAfterClickingMonthButton;
	private ListFilter listFilter;

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
		comboBoxAction = new ComboBoxAction(databaseReader);
		listFilter = new ListFilter();
		fillcomponentsWithDataFromDatabase = new FillComponentsFromDataInDatabase();
		sort = new Sort();
	}
	
	public String getYearAndMonth() {
		return yearAndMonth;
	}

	protected void generateButtonsWithBudgetsNames(JPanel panelWithBudgetsName, JPanel panelWithYears,
			JPanel panelWithMonths, JPanel panelUser, JPanel panelIncome, JPanel panelBudget, JPanel panelBudgetEmpty,
			JPanel panelOtherIncomeView, JComboBox<String> comboBoxUsers, JComboBox<String> comboBoxOtherIncome,
			JComboBox<String> comboBoxExpenditureCategory, JComboBox<String> comboBoxSavings, JPanel panelExpenditureView,
			JPanel panelSavingsView, JLabel lblInform, JLabel lblExpenditureSum, JLabel labelSavingsSum,
			JLabel labelOtherIncomeSum, JLabel labelIncomeSum) {

		int budgetCurrentId;
		for (Entry<Integer, String> entry : budgetIdNameMap.entrySet()) {
			budgetCurrentId = entry.getKey();
			JButton jButtonBudgetName = new JButton(entry.getValue());
			panelWithBudgetsName.add(jButtonBudgetName);
			generateYearButtonsAfterClickingBudgetNamesButtons(jButtonBudgetName, budgetCurrentId, panelWithYears,
					panelWithMonths, panelUser, panelIncome, panelBudget, panelBudgetEmpty, panelOtherIncomeView,
					comboBoxUsers, comboBoxOtherIncome, comboBoxExpenditureCategory, comboBoxSavings,
					panelExpenditureView, panelSavingsView, lblInform, lblExpenditureSum, labelSavingsSum,
					labelOtherIncomeSum, labelIncomeSum);
		}
		panelWithBudgetsName.revalidate();
		panelWithBudgetsName.repaint();
	}
	
	private void generateYearButtonsAfterClickingBudgetNamesButtons(JButton button, int budgetId, JPanel panelWithYears,
			JPanel panelWithMonths, JPanel panelUser, JPanel panelIncome, JPanel panelBudget, JPanel panelBudgetEmpty,
			JPanel panelOtherIncomeView, JComboBox<String> comboBoxUsers, JComboBox<String> comboBoxOtherIncome,
			JComboBox<String> comboBoxExpenditureCategory, JComboBox<String> comboBoxSavings,
			JPanel panelExpenditureView, JPanel panelSavingsView, JLabel lblInform, JLabel lblExpenditureSum,
			JLabel labelSavingsSum, JLabel labelOtherIncomeSum, JLabel labelIncomeSum) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBudget.setVisible(false);
				panelBudgetEmpty.setVisible(true);

				List<Container> components = Arrays.asList(lblInform, panelWithYears, panelWithMonths, panelUser,
						panelIncome, panelOtherIncomeView, panelExpenditureView, panelSavingsView, comboBoxUsers,
						comboBoxOtherIncome, comboBoxExpenditureCategory, comboBoxSavings);

				components.stream().forEach(c -> {c.removeAll();});
				
				HashMap<Integer, ArrayList<Integer>> mapOfYearsInConcreteBudgetId = sort
						.sortYearsInConcredeBudgetId(usersIncomeObjectList);
				for (Entry<Integer, ArrayList<Integer>> entry : mapOfYearsInConcreteBudgetId.entrySet()) {
					Integer idBudget = entry.getKey();
					int sizeOfListOfYears = entry.getValue().size();
					if (idBudget == budgetId) {
						for (int i = 0; i < sizeOfListOfYears; i++) {
							int year = entry.getValue().get(i);
							JButton jButtonWithYear = new JButton(year + "");
							panelWithYears.add(jButtonWithYear);
							generateMonthsButtonsForConcreteYearAfterClickingYearButton(jButtonWithYear, idBudget,
									panelWithMonths, year, panelUser, panelIncome, panelBudget, panelBudgetEmpty,
									panelOtherIncomeView, panelExpenditureView, panelSavingsView, lblInform,
									lblExpenditureSum, labelSavingsSum, labelOtherIncomeSum, labelIncomeSum);
						}
					}

				}
				try {
					comboBoxAction.writeUsersToComboBox(comboBoxUsers, budgetId);
					comboBoxAction.writeOtherIncomeCategoryToComboBox(comboBoxOtherIncome, budgetId);
					comboBoxAction.writeExpenditureCategoryToComboBox(comboBoxExpenditureCategory, budgetId);
					comboBoxAction.writeSavingsCategoryToComboBox(comboBoxSavings, budgetId);

				} catch (DatabaseNotInitialized e1) {
					e1.printStackTrace();
				}
				
				components.stream().forEach(c -> {c.revalidate(); c.repaint();});
			}
		});
	}
	
	private void generateMonthsButtonsForConcreteYearAfterClickingYearButton(JButton button, int budgetId,
			JPanel panelWithMonths, int year, JPanel panelUser, JPanel panelIncome, JPanel panelBudget,
			JPanel panelBudgetEmpty, JPanel panelOtherIncomeView, JPanel panelExpenditureView, JPanel panelSavingsView,
			JLabel lblInform, JLabel lblExpenditureSum, JLabel labelSavingsSum, JLabel labelOtherIncomeSum,
			JLabel labelIncomeSum) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelBudget.setVisible(false);
				panelBudgetEmpty.setVisible(true);
				
				LayoutOptions layoutOptions = new LayoutOptions();
				
				List<Container> components = Arrays.asList(lblInform, panelWithMonths, panelUser, panelIncome, panelOtherIncomeView,
						panelExpenditureView, panelSavingsView);
				
				components.stream().forEach(c -> {c.removeAll();});
				
				DataFormatter dataFormatter = new DataFormatter();
				ArrayList<Integer> listOfMonths = sort.sortMonthsForConcreteYearAndBudgetId(usersIncomeObjectList, budgetId, year);
				
				List<Transaction> usersConstrained = usersIncomeObjectList.stream()
						.filter(uio -> uio.getBudgetId() == budgetId && uio.getDate().getYear() == year
						&& uio.getCategoryId() == INCOME_TYPE_SALARY)
						.collect(Collectors.toList());
				
				List<Transaction> userOtherIncomeConstrained = usersIncomeObjectList.stream()
						.filter(uio -> uio.getBudgetId() == budgetId && uio.getDate().getYear() == year
								&& uio.getCategoryId() != INCOME_TYPE_SALARY)
						.collect(Collectors.toList());
				
				for (int i = 0; i < listOfMonths.size(); i++) {
					int month = listOfMonths.get(i);
					
					
					String monthName = dataFormatter.changeMonhNumberFromMonthName(month);
					JButton jButtonWithMonthName = new JButton(monthName + "");
					panelWithMonths.add(jButtonWithMonthName);

					List<Transaction> users = usersConstrained.stream().filter(uio -> uio.getDate().getMonthValue() == month).collect(Collectors.toList());
					List<Pair<String, Double>> usersPairs = new ArrayList<Pair<String, Double>>();
					for (Transaction uio : users) {
						usersPairs.add(new Pair<String, Double>(usersNameHashMap.get(uio.getTransactionId()), uio.getAmount()));
					}
					
					List<Transaction> usersOtherIncome = userOtherIncomeConstrained.stream()
							.filter(uio -> uio.getDate().getMonthValue() == month)
							.collect(Collectors.toList());
					
					ArrayList<Transaction> expenditureObjectSortedList = sort.sortExpenditureAfterItsDay(expenditureObjectListWithItsId, year, month,
							budgetId);
					List<Transaction> expenditureConstrained = expenditureObjectSortedList.stream()
							.filter(eo -> eo.getBudgetId() == budgetId && eo.getDate().getYear() == year 
							&& eo.getDate().getMonthValue() == month)
							.collect(Collectors.toList());
					
					jButtonWithMonthName.addActionListener(new ActionListener() {
						
						public void actionPerformed(ActionEvent e) {
							generateMonthNameAndYearInfoAfterClickingMonthButton(lblInform, year, month);
						}
					});
					
					List<Transaction> savingsConstrained = listFilter.filterByBudgetIdYearMonth(savingsObjectListWithItsId, budgetId, year, month);
					List<Transaction> savingsSorted = sort.sortTransactionAfterItsDay(savingsConstrained, year, month, budgetId);
					List<Transaction> savingsListWithCategoryName = fillcomponentsWithDataFromDatabase.fillTransactionList(savingsSorted, savingsCategoryMap);
					generateTransactionAfterClickingMonthButton = new GenerateTransactionAfterClickingMonthButton(panelSavingsView, labelSavingsSum, savingsListWithCategoryName, layoutOptions);
					jButtonWithMonthName.addActionListener(generateTransactionAfterClickingMonthButton);
					
					
					generateIncomeAfterClickingMonthButton(usersPairs, jButtonWithMonthName, 
							panelUser, panelIncome, 
							panelBudget, panelBudgetEmpty, labelIncomeSum);
					
					generateOtherIncomeAfterClickingMonthButton(usersOtherIncome, jButtonWithMonthName, panelOtherIncomeView, budgetId, year,
							month, labelOtherIncomeSum, layoutOptions);
					generateExpenditureAfterClickingMonthButton(expenditureConstrained, jButtonWithMonthName, panelExpenditureView, budgetId, year,
							month, lblExpenditureSum, layoutOptions);
					//generateSavingsAfterClickingMonthButton(savingsConstrained, jButtonWithMonthName, panelSavingsView, budgetId, year, month,
					//		labelSavingsSum, layoutOptions);
					
					
					
				}
				components.stream().forEach(c -> {c.revalidate(); c.repaint();});
			}
		});
	}

	//FIXME that will be to delete if i pass LocalDate to function instead of year and month, or i can pass month name
	public void generateMonthNameAndYearInfoAfterClickingMonthButton(JLabel lblInform, int year, int month) {
		String date = month + " " + year;
		yearAndMonth = date;
		DataFormatter dataFormatter = new DataFormatter();
		String monthName = dataFormatter.changeMonhNumberFromMonthName(month);
		lblInform.setText(year + " " + monthName.toUpperCase());
	}
	
	protected void generateIncomeAfterClickingMonthButton(List<Pair<String, Double>> pairs, JButton button, 
			JPanel panelUser, JPanel panelIncome,
			JPanel panelBudget, JPanel panelBudgetEmpty, JLabel labelIncomeSum) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double sum = 0;
				
				List<Container> components = Arrays.asList(panelIncome, panelUser);
				components.stream().forEach(c -> {c.removeAll();});
				
				panelBudget.setVisible(true);
				panelBudgetEmpty.setVisible(false);
				
				for (Pair<String, Double> pair : pairs) {
					JLabel jLabelUsers = new JLabel(pair.getKey());
					JLabel jLabelIncomaAmount = new JLabel(String.valueOf(pair.getValue()));
					
					panelUser.add(jLabelUsers);
					panelIncome.add(jLabelIncomaAmount);
					
					sum += pair.getValue();
							
				}
				labelIncomeSum.setText(setAmountFormat(sum));
				components.stream().forEach(c -> {c.revalidate(); c.repaint();});
			}
		});
	}

	protected void generateOtherIncomeAfterClickingMonthButton(List<Transaction> usersOtherIncome, JButton button, JPanel panelOtherIncomeView,
			int budgetId, int year, int month, JLabel labelOtherIncomeSum, LayoutOptions layoutOptions) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double sum = 0;
				panelOtherIncomeView.removeAll();

				for (Transaction uio : usersOtherIncome) {
					JLabel jLabelsUsers = new JLabel(usersNameHashMap.get(uio.getTransactionId()));
					JLabel jLabelsIncomeCategory = new JLabel(incomeCategoryMap.get(uio.getCategoryId()));
					JLabel jLabelsIncomeAmount = new JLabel(String.valueOf(uio.getAmount()));
					
					layoutOptions.gridBagLayoutOptionsForPanelsWithThreeLabels(panelOtherIncomeView, jLabelsUsers,
							jLabelsIncomeCategory, jLabelsIncomeAmount);

					sum += uio.getAmount();

				}
				labelOtherIncomeSum.setText(setAmountFormat(sum));
				panelOtherIncomeView.revalidate();
				panelOtherIncomeView.repaint();
			}
		});
	}

	protected void generateExpenditureAfterClickingMonthButton(List<Transaction> expenditureConstrained, JButton button, JPanel panelExpenditureView,
			int budgetId, int year, int month, JLabel labelExpenditureSum, LayoutOptions layoutOptions) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double sum = 0;
				panelExpenditureView.removeAll();
				
				for (Transaction eo : expenditureConstrained) {
					JLabel jLabelDate = new JLabel(eo.getDate().toString());
					JLabel jLabelsExpenditureAmount = new JLabel(String.valueOf(eo.getAmount()));	
					JLabel jLabelsExpenditureCategory = new JLabel(expenditureCategoryMap.get(eo.getCategoryId()));
					
					layoutOptions.gridBagLayoutOptionsForPanelsWithThreeLabels(panelExpenditureView,
							jLabelDate, jLabelsExpenditureCategory, jLabelsExpenditureAmount);

					sum += eo.getAmount();
				}
				labelExpenditureSum.setText(setAmountFormat(sum));
				panelExpenditureView.revalidate();
				panelExpenditureView.repaint();
			}
		});
	}

	protected void generateSavingsAfterClickingMonthButton(List<Transaction> savingsConstrained, JButton button, JPanel panelSavingsView, int budgetId,
			int year, int month, JLabel labelSavingsSum, LayoutOptions layoutOptions) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double sum = 0;
				panelSavingsView.removeAll();
				
				for (Transaction so : savingsConstrained) {
					JLabel jLabelDate = new JLabel(so.getDate().toString());
					JLabel jLabelsSavingsAmount = new JLabel(String.valueOf(so.getAmount()));
					JLabel jLabelSavingsCategory = new JLabel(savingsCategoryMap.get(so.getCategoryId()));
							
					layoutOptions.gridBagLayoutOptionsForPanelsWithThreeLabels(panelSavingsView, jLabelDate,
							jLabelSavingsCategory, jLabelsSavingsAmount);

					sum += so.getAmount();
				}
				labelSavingsSum.setText(setAmountFormat(sum));
				panelSavingsView.revalidate();
				panelSavingsView.repaint();
			}
		});
	}

	public List<String> getUserNames() throws DatabaseNotInitialized {
		HashMap<Integer, String> usersFromDatabaseMap = databaseReader.readUsersFromDatabasetoHashMap();
		
		return new ArrayList<String>(usersFromDatabaseMap.values());
	}
}
