package learning.budget;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;

import learning.budget.views.CreateBudgetOptions;

public class GenerateComponents {

	protected IDatabaseReader databaseReader;
	private HashMap<Integer, String> budgetIdNameMap;
	private DateOptions dateOptions = new DateOptions();
	private ArrayList<UsersIncomeObject> usersIncomeObjectList;
	private ArrayList<UsersObject> usersObjectList;
	private HashMap<Integer, String> usersNameHashMap;
	private HashMap<Integer, String> incomeCategoryMap;
	private HashMap<Integer, String> expenditureCategoryMap;
	private HashMap<Integer, String> savingsCategoryMap;
	private LayoutOptions layoutOptions = new LayoutOptions();
	private ArrayList<ExpendiutureObject> expenditureObjectList;
	private ArrayList<SavingsObject> savingObjectList; 
	private CreateBudgetOptions cbo = new CreateBudgetOptions();
	private TextFieldValidator textFieldValidator = new TextFieldValidator();
	private int num = cbo.getMaxNumberOfUsers();
	private int userNumber = 0;
	private int idOfBudget;
	private static String YEARANDMONTH;
	private ComboBoxAction comboBoxAction;
	private Sort sort;
	private SumOfElements sumOfElements = new SumOfElements();

	public GenerateComponents(IDatabaseReader _databaseReader) {
		databaseReader = _databaseReader;
		
		budgetIdNameMap = databaseReader.readBudgetIdNameFromDatabase();
		usersIncomeObjectList = databaseReader.readIncomefromDatabase();
		usersObjectList = databaseReader.readUsersFromDatabase();
		usersNameHashMap = databaseReader.readUsersFromDatabasetoHashMap();
		incomeCategoryMap = databaseReader.readCategoryFromDatabase("Income_category");
		expenditureCategoryMap = databaseReader.readCategoryFromDatabase("Expenditure_category");
		savingsCategoryMap = databaseReader.readCategoryFromDatabase("Savings_category");
		expenditureObjectList = databaseReader.readExpenditureFromDataBase();
		savingObjectList = databaseReader.readSavingsFromDataBase();
		comboBoxAction = new ComboBoxAction(databaseReader);
		sort = new Sort(databaseReader);
	}
	
	public String getYearAndMonth() {
		return YEARANDMONTH;
	}

	protected void generateButtonsWithBudgetsNames(JPanel panelWithBudgetsName, JPanel panelWithYears,
			JPanel panelWithMonths, JPanel panelUser, JPanel panelIncome, JPanel panelBudget, JPanel panelBudgetEmpty,
			JPanel panelOtherIncomeView, JComboBox comboBoxUsers, JComboBox comboBoxOtherIncome,
			JComboBox comboBoxExpenditureCategory, JComboBox comboBoxSavings, JPanel panelExpenditureView,
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
			JPanel panelOtherIncomeView, JComboBox comboBoxUsers, JComboBox comboBoxOtherIncome,
			JComboBox comboBoxExpenditureCategory, JComboBox comboBoxSavings, JPanel panelExpenditureView,
			JPanel panelSavingsView, JLabel lblInform, JLabel lblExpenditureSum, JLabel labelSavingsSum,
			JLabel labelOtherIncomeSum, JLabel labelIncomeSum) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setPanelBudgetInvisible(panelBudget, panelBudgetEmpty);
				lblInform.setText("");
				panelWithMonths.removeAll();
				panelWithYears.removeAll();
				panelUser.removeAll();
				panelIncome.removeAll();
				panelOtherIncomeView.removeAll();
				panelExpenditureView.removeAll();
				panelSavingsView.removeAll();
				comboBoxUsers.removeAllItems();
				comboBoxOtherIncome.removeAllItems();
				comboBoxExpenditureCategory.removeAllItems();
				comboBoxSavings.removeAllItems();
				HashMap<Integer, ArrayList<Integer>> mapOfYearsInConcreteBudgetId = sort
						.mapOfSortedYearsInConcreteBudgerId();
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
				comboBoxAction.writeUsersToComboBox(comboBoxUsers, budgetId);
				comboBoxAction.writeOtherIncomeCategoryToComboBox(comboBoxOtherIncome, budgetId);
				comboBoxAction.writeExpenditureCategoryToComboBox(comboBoxExpenditureCategory, budgetId);
				comboBoxAction.writeSavingsCategoryToComboBox(comboBoxSavings, budgetId);
				panelWithYears.revalidate();
				panelWithYears.repaint();
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
				ArrayList<Integer> listOfMonths = sort.getSortedMonthsForConcreteYearAndBudgetId(budgetId, year);
				JButton[] jButtons = new JButton[listOfMonths.size()];
				for (int i = 0; i < listOfMonths.size(); i++) {
					int month = listOfMonths.get(i);
					String monthName = dataFormatter.changeMonhNumberFromMonthName(month);
					jButtons[i] = new JButton(monthName + "");
					panelWithMonths.add(jButtons[i]);
					// generateMonthNameAndYearInfoAfterClickingMonthButton(jButtons[i],
					// lblInform, year, month);
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
				sumOfElements.setSumToZero();
			}
		});
	}

	public void setPanelBudgetVisible(JPanel panelBudget, JPanel panelBudgetEmpty) {
		panelBudget.setVisible(true);
		panelBudgetEmpty.setVisible(false);
	}

	public void setPanelBudgetInvisible(JPanel panelBudget, JPanel panelBudgetEmpty) {
		panelBudget.setVisible(false);
		panelBudgetEmpty.setVisible(true);
	}

	public void generateMonthNameAndYearInfoAfterClickingMonthButton(JButton button, JLabel lblInform, int year,
			int month) {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DataFormatter dataFormatter = new DataFormatter();
				String monthName = dataFormatter.changeMonhNumberFromMonthName(month);
				lblInform.setText(year + " " + monthName.toUpperCase());
			}
		});
	}

	public String generateMonthNameAndYearInfoAfterClickingMonthButton(JLabel lblInform, int year, int month) {
		String date = month + " " + year;
		YEARANDMONTH = date;
		DataFormatter dataFormatter = new DataFormatter();
		String monthName = dataFormatter.changeMonhNumberFromMonthName(month);
		lblInform.setText(year + " " + monthName.toUpperCase());
		return YEARANDMONTH;
	}

	protected void generateIncomeAfterClickingMonthButton(JButton button, JPanel panelUser, JPanel panelIncome,
			JPanel panelBudget, JPanel panelBudgetEmpty, int budgetId, int year, int month, JLabel lblInform,
			JLabel labelIncomeSum) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				sumOfElements.setSumToZero();
				labelIncomeSum.setText("0");
				panelBudget.setVisible(true);
				panelBudgetEmpty.setVisible(false);
				panelUser.removeAll();
				panelIncome.removeAll();
				int sizeOfUsersIncomeObjectList = usersIncomeObjectList.size();
				JLabel lblUsers[] = new JLabel[sizeOfUsersIncomeObjectList];
				JLabel lblIncome[] = new JLabel[sizeOfUsersIncomeObjectList];
				int i = 0, idBudget, idUser, idIncomeCategory, yearOfBudget, monthOfBudget, idcategoryOfIncome = 1;
				YEARANDMONTH = generateMonthNameAndYearInfoAfterClickingMonthButton(lblInform, year, month);
				double amount;
				for (UsersIncomeObject uio : usersIncomeObjectList) {
					idBudget = uio.getBudgetId();
					yearOfBudget = dateOptions.getYearFromDate(uio.getIncomeDate());
					monthOfBudget = dateOptions.getMonthFromDate(uio.getIncomeDate());
					idIncomeCategory = uio.getIncomeCategoryId();
					if (idBudget == budgetId) {
						if (yearOfBudget == year && monthOfBudget == month && idIncomeCategory == idcategoryOfIncome) {
							idUser = uio.getUserId();
							amount = uio.getAmount();
							for (Entry<Integer, String> users : usersNameHashMap.entrySet()) {
								if (users.getKey() == idUser)
									lblUsers[i] = new JLabel(users.getValue());
							}
							lblIncome[i] = new JLabel(amount + "");
							panelUser.add(lblUsers[i]);
							panelIncome.add(lblIncome[i]);

							double sum = sumOfElements.sumElements(amount);
							sumOfElements.showSumInLabel(labelIncomeSum, sum);
							i++;
						}
					}
					if (i >= sizeOfUsersIncomeObjectList)
						return;
				}
				panelUser.revalidate();
				panelUser.repaint();
				panelIncome.revalidate();
				panelIncome.repaint();
			}
		});
	}

	protected void generateOtherIncomeAfterClickingMonthButton(JButton button, JPanel panelOtherIncomeView,
			int budgetId, int year, int month, JLabel labelOtherIncomeSum) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				sumOfElements.setSumToZero();
				labelOtherIncomeSum.setText("0");
				panelOtherIncomeView.removeAll();
				int sizeOfUsersIncomeObjectList = usersIncomeObjectList.size();
				JLabel jLabelsUsers[] = new JLabel[sizeOfUsersIncomeObjectList];
				JLabel jLabelsIncomeCategory[] = new JLabel[sizeOfUsersIncomeObjectList];
				JLabel jLabelsIncomeAmount[] = new JLabel[sizeOfUsersIncomeObjectList];
				int dateMonth, dateYear, idUser, idIncomeCategory, idBudget, idcategoryOfIncome = 1;
				double amount;
				int i = 0;
				layoutOptions.setGridy(0);
				for (UsersIncomeObject uio : usersIncomeObjectList) {
					idBudget = uio.getBudgetId();
					dateYear = dateOptions.getYearFromDate(uio.getIncomeDate());
					dateMonth = dateOptions.getMonthFromDate(uio.getIncomeDate());
					idIncomeCategory = uio.getIncomeCategoryId();
					if (idBudget == budgetId) {
						if (dateYear == year && dateMonth == month) {
							if (idIncomeCategory != idcategoryOfIncome) {
								idUser = uio.getUserId();
								amount = uio.getAmount();
								for (Entry<Integer, String> users : usersNameHashMap.entrySet()) {
									if (users.getKey() == idUser)
										jLabelsUsers[i] = new JLabel(users.getValue());
								}
								for (Entry<Integer, String> cat : incomeCategoryMap.entrySet()) {
									if (cat.getKey() == idIncomeCategory)
										jLabelsIncomeCategory[i] = new JLabel(cat.getValue());
								}
								jLabelsIncomeAmount[i] = new JLabel(String.valueOf(amount));
								layoutOptions.gridBagLayoutOptionsForPanelsWithThreeLabels(panelOtherIncomeView,
										jLabelsUsers[i], jLabelsIncomeCategory[i], jLabelsIncomeAmount[i]);

								double sum = sumOfElements.sumElements(amount);
								sumOfElements.showSumInLabel(labelOtherIncomeSum, sum);

								i++;
								if (i >= sizeOfUsersIncomeObjectList)
									return;
							}
						}
					}
				}
				panelOtherIncomeView.revalidate();
				panelOtherIncomeView.repaint();
			}
		});
	}

	protected void generateExpenditureAfterClickingMonthButton(JButton button, JPanel panelExpenditureView,
			int budgetId, int year, int month, JLabel labelExpenditureSum) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				sumOfElements.setSumToZero();
				labelExpenditureSum.setText("0");
				ArrayList<ExpendiutureObject> expenditureObjectSortedList = sort.sortExpenditureAfterItsDay(year, month,
						budgetId);
				panelExpenditureView.removeAll();
				int sizeOfExpenditureObjectList = expenditureObjectSortedList.size();
				JLabel jLabelDate[] = new JLabel[sizeOfExpenditureObjectList];
				JLabel jLabelsExpenditureCategory[] = new JLabel[sizeOfExpenditureObjectList];
				JLabel jLabelsExpenditureAmount[] = new JLabel[sizeOfExpenditureObjectList];
				int dateMonth, dateYear, idExpenditureCategory, idBudget;
				double amount;
				int i = 0;
				Date date;
				layoutOptions.setGridy(0);
				for (ExpendiutureObject eo : expenditureObjectSortedList) {
					idBudget = eo.getBudgetId();
					date = eo.getExpenditureDate();
					dateYear = dateOptions.getYearFromDate(date);
					dateMonth = dateOptions.getMonthFromDate(date);
					idExpenditureCategory = eo.getExpenditureCategoryId();
					if (idBudget == budgetId) {
						if (dateYear == year && dateMonth == month) {
							amount = eo.getAmount();
							for (Entry<Integer, String> cat : expenditureCategoryMap.entrySet()) {
								if (cat.getKey() == idExpenditureCategory)
									jLabelsExpenditureCategory[i] = new JLabel(cat.getValue());
							}
							jLabelsExpenditureAmount[i] = new JLabel(String.valueOf(amount));
							jLabelDate[i] = new JLabel(date.toString());
							layoutOptions.gridBagLayoutOptionsForPanelsWithThreeLabels(panelExpenditureView,
									jLabelDate[i], jLabelsExpenditureCategory[i], jLabelsExpenditureAmount[i]);

							double sum = sumOfElements.sumElements(amount);
							sumOfElements.showSumInLabel(labelExpenditureSum, sum);

							i++;
							if (i >= sizeOfExpenditureObjectList)
								return;
						}
					}
				}
				panelExpenditureView.revalidate();
				panelExpenditureView.repaint();
			}
		});
	}

	protected void generateSavingsAfterClickingMonthButton(JButton button, JPanel panelSavingsView, int budgetId,
			int year, int month, JLabel labelSavingsSum) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				sumOfElements.setSumToZero();
				labelSavingsSum.setText("0");
				ArrayList<SavingsObject> savingsObjectSortedList = sort.sortSavingsAfterItsDay(year, month, budgetId);
				panelSavingsView.removeAll();
				int sizeOfSavingsObjectSortedList = savingsObjectSortedList.size();
				JLabel jLabelDate[] = new JLabel[sizeOfSavingsObjectSortedList];
				JLabel jLabelsSavingsCategory[] = new JLabel[sizeOfSavingsObjectSortedList];
				JLabel jLabelsSavingsAmount[] = new JLabel[sizeOfSavingsObjectSortedList];
				int dateMonth, dateYear, idSavingsCategory, idBudget;
				double amount;
				int i = 0;
				Date date;
				layoutOptions.setGridy(0);
				for (SavingsObject so : savingsObjectSortedList) {
					idBudget = so.getBudgetId();
					date = so.getSavingsDate();
					dateYear = dateOptions.getYearFromDate(date);
					dateMonth = dateOptions.getMonthFromDate(date);
					idSavingsCategory = so.getSavingsCategoryId();
					if (idBudget == budgetId) {
						if (dateYear == year && dateMonth == month) {
							amount = so.getAmount();
							for (Entry<Integer, String> cat : savingsCategoryMap.entrySet()) {
								if (cat.getKey() == idSavingsCategory)
									jLabelsSavingsCategory[i] = new JLabel(cat.getValue());
							}
							jLabelsSavingsAmount[i] = new JLabel(String.valueOf(amount));
							jLabelDate[i] = new JLabel(date.toString());
							layoutOptions.gridBagLayoutOptionsForPanelsWithThreeLabels(panelSavingsView, jLabelDate[i],
									jLabelsSavingsCategory[i], jLabelsSavingsAmount[i]);

							double sum = sumOfElements.sumElements(amount);
							sumOfElements.showSumInLabel(labelSavingsSum, sum);

							i++;
							if (i >= sizeOfSavingsObjectSortedList)
								return;
						}
					}
				}
				panelSavingsView.revalidate();
				panelSavingsView.repaint();
			}
		});
	}

	///////////////// dzia�a ale wyswietla uzytkownikow wszystkich budzetow,
	///////////////// zastosowanw w create budget for new m9onth
	public void generateUsersAndIncomeTextFields(JPanel panel1, JPanel panel2, JPanel panel3, JButton button) {
		int i = 0;
		HashMap<Integer, String> usersFromDatabaseMap = databaseReader.readUsersFromDatabasetoHashMap();
		HashMap<Integer, Double> incomeMap = new HashMap<>();
		ArrayList<UsersObject> usersObjectList = databaseReader.readUsersFromDatabase();
		JLabel jLabelsUsersName[] = new JLabel[num];
		JLabel jErrorLabels[] = new JLabel[num];
		JTextField jtextFields[] = new JTextField[num];

		for (Entry<Integer, String> s : usersFromDatabaseMap.entrySet()) {
			jLabelsUsersName[i] = new JLabel(s.getValue() + ": ");
			jLabelsUsersName[i].setPreferredSize(new Dimension(90, 20));
			jLabelsUsersName[i].setHorizontalAlignment(JLabel.RIGHT);
			int currentUserId = s.getKey();

			jtextFields[i] = new JTextField(10);
			JTextField currentField = jtextFields[i];

			jErrorLabels[i] = new JLabel("");
			jErrorLabels[i].setForeground(Color.RED);
			jErrorLabels[i].setPreferredSize(new Dimension(130, 20));
			jErrorLabels[i].setHorizontalAlignment(JLabel.LEFT);
			JLabel curentErrorLabel = jErrorLabels[i];

			panel1.add(jLabelsUsersName[i]);
			panel2.add(jtextFields[i]);
			panel3.add(jErrorLabels[i]);

			userNumber++;

			jtextFields[i].addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					try {
						int key = currentUserId;
						String value = currentField.getText().toString();
						boolean isNumber = textFieldValidator.valueIsANumberForGeneratedTextFields(value,
								curentErrorLabel, button);
						if (isNumber) {
							double amount = Double.parseDouble(value);
							incomeMap.put(key, amount);
						}

						if (incomeMap.size() == userNumber) {
							button.setEnabled(true);
						} else {
							button.setEnabled(false);
						}
					} catch (java.lang.NumberFormatException nfe) {
						curentErrorLabel.setText("Podaj kwot�");
						button.setEnabled(false);
					}
				}
			});
			i++;
			if (i >= num)
				return;
		}
		panel1.validate();
		panel1.repaint();
		panel2.validate();
		panel2.repaint();
		panel3.validate();
		panel3.repaint();

	}
}
