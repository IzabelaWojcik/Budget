package learning.budget;

import static learning.budget.Constants.INCOME_TYPE_SALARY;
import static learning.budget.DataFormatter.setAmountFormat;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import learning.budget.views.CreateBudgetOptions;
import learning.budget.views.UserIncomeInputFiledListener;

public class GenerateComponents {
	protected IDatabaseReader databaseReader;
	protected IDatabaseWriter databaseWriter;
	private DataFormatter dataFormatter;
	private HashMap<Integer, String> budgetIdNameMap;
	private ArrayList<UsersIncomeObject> usersIncomeObjectList;
	private HashMap<Integer, String> usersNameHashMap;
	private HashMap<Integer, String> incomeCategoryMap;
	private HashMap<Integer, String> expenditureCategoryMap;
	private HashMap<Integer, String> savingsCategoryMap;
	private LayoutOptions layoutOptions = new LayoutOptions();
	private CreateBudgetOptions cbo;
	private TextFieldValidator textFieldValidator = new TextFieldValidator();
	private int num;
	private int userNumber = 0;
	private static String yearAndMonth;
	private ComboBoxAction comboBoxAction;
	private Sort sort;
	private ArrayList<ExpenditureObject> expenditureObjectListWithItsId;
	private ArrayList<SavingsObject> savingsObjectListWithItsId;

	public GenerateComponents(IDatabaseReader _databaseReader, IDatabaseWriter _databaseWriter) throws DatabaseNotInitialized {
		databaseReader = _databaseReader;
		databaseWriter = _databaseWriter;
		
		cbo = new CreateBudgetOptions(databaseReader, databaseWriter);
		num = cbo.getMaxNumberOfUsers();
		budgetIdNameMap = databaseReader.readBudgetIdNameFromDatabase();
		usersIncomeObjectList = databaseReader.readIncomefromDatabase();
		usersNameHashMap = databaseReader.readUsersFromDatabasetoHashMap();
		incomeCategoryMap = databaseReader.readCategoryFromDatabase("Income_category");
		expenditureCategoryMap = databaseReader.readCategoryFromDatabase("Expenditure_category");
		savingsCategoryMap = databaseReader.readCategoryFromDatabase("Savings_category");
		expenditureObjectListWithItsId = databaseReader.readExpenditureWithItsIdFromDataBase();
		savingsObjectListWithItsId = databaseReader.readSavingsWithItsIdFromDataBase();
		dataFormatter = new DataFormatter();
		comboBoxAction = new ComboBoxAction(databaseReader);
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

		int budgetsQuantity = budgetIdNameMap.size();
		JButton jButtons[] = new JButton[budgetsQuantity];
		int i = 0;
		int budgetCurrentId;
		for (Entry<Integer, String> entry : budgetIdNameMap.entrySet()) {
			budgetCurrentId = entry.getKey();
			jButtons[i] = new JButton(entry.getValue());
			panelWithBudgetsName.add(jButtons[i]);
			generateYearButtonsAfterClickingBudgetNamesButtons(jButtons[i], budgetCurrentId, panelWithYears,
					panelWithMonths, panelUser, panelIncome, panelBudget, panelBudgetEmpty, panelOtherIncomeView,
					comboBoxUsers, comboBoxOtherIncome, comboBoxExpenditureCategory, comboBoxSavings,
					panelExpenditureView, panelSavingsView, lblInform, lblExpenditureSum, labelSavingsSum,
					labelOtherIncomeSum, labelIncomeSum);
			i++;
			if (i >= budgetsQuantity)
				return;
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
				setPanelBudgetInvisible(panelBudget, panelBudgetEmpty);

				List<Container> components = Arrays.asList(lblInform, panelWithYears, panelWithMonths, panelUser,
						panelIncome, panelOtherIncomeView, panelExpenditureView, panelSavingsView, comboBoxUsers,
						comboBoxOtherIncome, comboBoxExpenditureCategory, comboBoxSavings);

				components.stream().forEach(c -> {c.removeAll();});
				
				HashMap<Integer, ArrayList<Integer>> mapOfYearsInConcreteBudgetId = sort
						.sortYearsInConcredeBudgetId(usersIncomeObjectList);
				for (Entry<Integer, ArrayList<Integer>> entry : mapOfYearsInConcreteBudgetId.entrySet()) {
					Integer idBudget = entry.getKey();
					int sizeOfListOfYears = entry.getValue().size();
					JButton jButtons[] = new JButton[sizeOfListOfYears];
					if (idBudget == budgetId) {
						for (int i = 0; i < sizeOfListOfYears; i++) {
							int year = entry.getValue().get(i);
							jButtons[i] = new JButton(year + "");
							panelWithYears.add(jButtons[i]);
							generateMonthsButtonsForConcreteYearAfterClickingYearButton(jButtons[i], idBudget,
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
					// TODO handle exceptions in proper place
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
				setPanelBudgetInvisible(panelBudget, panelBudgetEmpty);
				lblInform.setText("");
				DataFormatter dataFormatter = new DataFormatter();
				panelWithMonths.removeAll();
				panelUser.removeAll();
				panelIncome.removeAll();
				panelOtherIncomeView.removeAll();
				panelExpenditureView.removeAll();
				panelSavingsView.removeAll();
				ArrayList<Integer> listOfMonths = sort.sortMonthsForConcreteYearAndBudgetId(usersIncomeObjectList, budgetId, year);
				JButton[] jButtons = new JButton[listOfMonths.size()];
				for (int i = 0; i < listOfMonths.size(); i++) {
					int month = listOfMonths.get(i);
					String monthName = dataFormatter.changeMonhNumberFromMonthName(month);
					jButtons[i] = new JButton(monthName + "");
					panelWithMonths.add(jButtons[i]);
					generateIncomeAfterClickingMonthButton(jButtons[i], panelUser, panelIncome, panelBudget,
							panelBudgetEmpty, budgetId, year, month, lblInform, labelIncomeSum);
					generateOtherIncomeAfterClickingMonthButton(jButtons[i], panelOtherIncomeView, budgetId, year,
							month, labelOtherIncomeSum);
					generateExpenditureAfterClickingMonthButton(jButtons[i], panelExpenditureView, budgetId, year,
							month, lblExpenditureSum);
					generateSavingsAfterClickingMonthButton(jButtons[i], panelSavingsView, budgetId, year, month,
							labelSavingsSum);
				}
				panelWithMonths.revalidate();
				panelWithMonths.repaint();
				panelUser.revalidate();
				panelUser.repaint();
				panelIncome.revalidate();
				panelIncome.repaint();
				panelOtherIncomeView.revalidate();
				panelOtherIncomeView.repaint();
				panelExpenditureView.revalidate();
				panelExpenditureView.repaint();
				panelSavingsView.revalidate();
				panelSavingsView.repaint();
			}
		});
	}

	public void setPanelBudgetVisible(JPanel panelBudget, JPanel panelBudgetEmpty) {
		panelBudget.setVisible(true);
		panelBudgetEmpty.setVisible(false);
	}

	public void setPanelBudgetInvisible(JPanel panelBudget, JPanel panelBudgetEmpty) {
		
	}

	//FIXME that will be to delete if i pass LocalDate to function instead of year and month, or i can pass month name
	public String generateMonthNameAndYearInfoAfterClickingMonthButton(JLabel lblInform, int year, int month) {
		String date = month + " " + year;
		yearAndMonth = date;
		DataFormatter dataFormatter = new DataFormatter();
		String monthName = dataFormatter.changeMonhNumberFromMonthName(month);
		lblInform.setText(year + " " + monthName.toUpperCase());
		return yearAndMonth;
	}
	
	public String generateMonthNameAndYearInfoAfterClickingMonthButton(JLabel lblInform, LocalDate date) {
		int year = date.getYear();
		String month = date.getMonth().toString();
		yearAndMonth = month + " " + year;
		lblInform.setText(year + " " + month.toUpperCase());
		return yearAndMonth;
	}

	protected void generateIncomeAfterClickingMonthButton(JButton button, JPanel panelUser, JPanel panelIncome,
			JPanel panelBudget, JPanel panelBudgetEmpty, int budgetId, int year, int month, JLabel lblInform,
			JLabel labelIncomeSum) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double sum = 0;
				
				List<Container> components = Arrays.asList(panelIncome, panelUser);
				components.stream().forEach(c -> {c.removeAll();});
				
				panelBudget.setVisible(true);
				panelBudgetEmpty.setVisible(false);

				yearAndMonth = generateMonthNameAndYearInfoAfterClickingMonthButton(lblInform, year, month);

				List<UsersIncomeObject> usersIncomeObjectSalaryListConstrainedByBudgetDateAndCategoryId = usersIncomeObjectList.stream()
						.filter(uio -> uio.getBudgetId() == budgetId && uio.getIncomeDate().getYear() == year
						&& uio.getIncomeDate().getMonthValue() == month 
						&& uio.getIncomeCategoryId() == INCOME_TYPE_SALARY)
						.collect(Collectors.toList());
				
				for (UsersIncomeObject uio : usersIncomeObjectSalaryListConstrainedByBudgetDateAndCategoryId) {
					JLabel jLabelUsers = new JLabel(usersNameHashMap.get(uio.getUserId()));
					JLabel jLabelIncomaAmount = new JLabel(String.valueOf(uio.getAmount()));
					
					panelUser.add(jLabelUsers);
					panelIncome.add(jLabelIncomaAmount);
					
					sum += uio.getAmount();
							
				}
				labelIncomeSum.setText(setAmountFormat(sum));
				components.stream().forEach(c -> {c.revalidate(); c.repaint();});
			}
		});
	}

	protected void generateOtherIncomeAfterClickingMonthButton(JButton button, JPanel panelOtherIncomeView,
			int budgetId, int year, int month, JLabel labelOtherIncomeSum) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double sum = 0;
				panelOtherIncomeView.removeAll();

				LayoutOptions layoutOptions = new LayoutOptions();

				List<UsersIncomeObject> userIncomeObjectListConstrainedToBudgeDateAndIncomeCategory = usersIncomeObjectList.stream()
						.filter(uio -> uio.getBudgetId() == budgetId && uio.getIncomeDate().getYear() == year
								&& uio.getIncomeDate().getMonthValue() == month
								&& uio.getIncomeCategoryId() != INCOME_TYPE_SALARY)
						.collect(Collectors.toList());

				for (UsersIncomeObject uio : userIncomeObjectListConstrainedToBudgeDateAndIncomeCategory) {
					JLabel jLabelsUsers = new JLabel(usersNameHashMap.get(uio.getUserId()));
					JLabel jLabelsIncomeCategory = new JLabel(incomeCategoryMap.get(uio.getIncomeCategoryId()));
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

	protected void generateExpenditureAfterClickingMonthButton(JButton button, JPanel panelExpenditureView,
			int budgetId, int year, int month, JLabel labelExpenditureSum) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//FIXME removed that list
				ArrayList<ExpenditureObject> expenditureObjectSortedList = sort.sortExpenditureAfterItsDay(expenditureObjectListWithItsId, year, month,
						budgetId);
				double sum = 0;
				panelExpenditureView.removeAll();
				LayoutOptions layoutOptions = new LayoutOptions();
				
				List<ExpenditureObject> expenditureObjectSortedListConstrainedToBudgetDate = expenditureObjectSortedList.stream()
						.filter(eo -> eo.getBudgetId() == budgetId && eo.getExpenditureDate().getYear() == year 
						&& eo.getExpenditureDate().getMonthValue() == month)
						.collect(Collectors.toList());
				
				for (ExpenditureObject eo : expenditureObjectSortedListConstrainedToBudgetDate) {
					JLabel jLabelDate = new JLabel(eo.getExpenditureDate().toString());
					JLabel jLabelsExpenditureAmount = new JLabel(String.valueOf(eo.getAmount()));	
					JLabel jLabelsExpenditureCategory = new JLabel(expenditureCategoryMap.get(eo.getExpenditureCategoryId()));
					
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

	protected void generateSavingsAfterClickingMonthButton(JButton button, JPanel panelSavingsView, int budgetId,
			int year, int month, JLabel labelSavingsSum) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//FIXME remove that list
				ArrayList<SavingsObject> savingsObjectSortedList = sort.sortSavingsAfterItsDay(savingsObjectListWithItsId, year, month, budgetId);
				double sum = 0;
				panelSavingsView.removeAll();
				
				LayoutOptions layoutOptions = new LayoutOptions();
				
				List<SavingsObject> savingsObjectSortedListConstrainedToBudgetDate = savingsObjectSortedList.stream()
						.filter(so -> so.getBudgetId() == budgetId && so.getSavingsDate().getYear() == year 
						&& so.getSavingsDate().getMonthValue() == month)
						.collect(Collectors.toList());
	
				for (SavingsObject so : savingsObjectSortedListConstrainedToBudgetDate) {
					JLabel jLabelDate = new JLabel(so.getSavingsDate().toString());
					JLabel jLabelsSavingsAmount = new JLabel(String.valueOf(so.getAmount()));
					JLabel jLabelSavingsCategory = new JLabel(savingsCategoryMap.get(so.getSavingsCategoryId()));
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
