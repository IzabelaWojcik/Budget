package learning.budget;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import learning.budget.views.CreateBudgetOptions;

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
	private static String YEARANDMONTH;
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
		return YEARANDMONTH;
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
				double sum = 0;
				labelIncomeSum.setText("0");
				panelBudget.setVisible(true);
				panelBudgetEmpty.setVisible(false);
				panelUser.removeAll();
				panelIncome.removeAll();
				int sizeOfUsersIncomeObjectList = usersIncomeObjectList.size();
				LocalDate date;
				JLabel lblUsers[] = new JLabel[sizeOfUsersIncomeObjectList];
				JLabel lblIncome[] = new JLabel[sizeOfUsersIncomeObjectList];
				int i = 0, idBudget, idUser, idIncomeCategory, yearOfBudget, monthOfBudget, idcategoryOfIncome = 1;
				YEARANDMONTH = generateMonthNameAndYearInfoAfterClickingMonthButton(lblInform, year, month);
				double amount;
				for (UsersIncomeObject uio : usersIncomeObjectList) {
					idBudget = uio.getBudgetId();
					date = uio.getIncomeDate();
					yearOfBudget = date.getYear();
					monthOfBudget = date.getMonthValue();
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
							
							sum += amount;
							String sumAmount = dataFormatter.setAmountFormat(sum);
							labelIncomeSum.setText(sumAmount);
							
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
				double sum = 0;
				labelOtherIncomeSum.setText("0");
				panelOtherIncomeView.removeAll();
				int sizeOfUsersIncomeObjectList = usersIncomeObjectList.size();
				JLabel jLabelsUsers[] = new JLabel[sizeOfUsersIncomeObjectList];
				JLabel jLabelsIncomeCategory[] = new JLabel[sizeOfUsersIncomeObjectList];
				JLabel jLabelsIncomeAmount[] = new JLabel[sizeOfUsersIncomeObjectList];
				int dateMonth, dateYear, idUser, idIncomeCategory, idBudget, idcategoryOfIncome = 1;
				LocalDate date;
				double amount;
				int i = 0;
				layoutOptions.setGridy(0);
				for (UsersIncomeObject uio : usersIncomeObjectList) {
					idBudget = uio.getBudgetId();
					date = uio.getIncomeDate();
					dateYear = date.getYear();
					dateMonth = date.getMonthValue();
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
								
								sum += amount;
								String sumAmount = dataFormatter.setAmountFormat(sum);
								labelOtherIncomeSum.setText(sumAmount);

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
				labelExpenditureSum.setText("0");
				ArrayList<ExpenditureObject> expenditureObjectSortedList = sort.sortExpenditureAfterItsDay(expenditureObjectListWithItsId, year, month,
						budgetId);
				panelExpenditureView.removeAll();
				int sizeOfExpenditureObjectList = expenditureObjectSortedList.size();
				JLabel jLabelDate[] = new JLabel[sizeOfExpenditureObjectList];
				JLabel jLabelsExpenditureCategory[] = new JLabel[sizeOfExpenditureObjectList];
				JLabel jLabelsExpenditureAmount[] = new JLabel[sizeOfExpenditureObjectList];
				int dateMonth, dateYear, idExpenditureCategory, idBudget;
				double amount;
				int i = 0;
				double sum = 0;
				LocalDate date;
				layoutOptions.setGridy(0);
				for (ExpenditureObject eo : expenditureObjectSortedList) {
					idBudget = eo.getBudgetId();
					date = eo.getExpenditureDate();
					dateYear = date.getYear();
					dateMonth = date.getMonthValue();
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

							sum += amount;
							String sumAmount = dataFormatter.setAmountFormat(sum);
							labelExpenditureSum.setText(sumAmount);

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
				double sum = 0;
				labelSavingsSum.setText("0");
				ArrayList<SavingsObject> savingsObjectSortedList = sort.sortSavingsAfterItsDay(savingsObjectListWithItsId, year, month, budgetId);
				panelSavingsView.removeAll();
				int sizeOfSavingsObjectSortedList = savingsObjectSortedList.size();
				JLabel jLabelDate[] = new JLabel[sizeOfSavingsObjectSortedList];
				JLabel jLabelsSavingsCategory[] = new JLabel[sizeOfSavingsObjectSortedList];
				JLabel jLabelsSavingsAmount[] = new JLabel[sizeOfSavingsObjectSortedList];
				int dateMonth, dateYear, idSavingsCategory, idBudget;
				double amount;
				int i = 0;
				LocalDate date;
				layoutOptions.setGridy(0);
				for (SavingsObject so : savingsObjectSortedList) {
					idBudget = so.getBudgetId();
					date = so.getSavingsDate();
					dateYear = date.getYear();
					dateMonth = date.getMonthValue();
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

							sum += amount;
							String sumAmount = dataFormatter.setAmountFormat(sum);
							labelSavingsSum.setText(sumAmount);
							
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

	public List<String> getUserNames() throws DatabaseNotInitialized {
		HashMap<Integer, String> usersFromDatabaseMap = databaseReader.readUsersFromDatabasetoHashMap();
		
		return new ArrayList<String>(usersFromDatabaseMap.values());
	}
	
	/////////////////FIXME dzia�a ale wyswietla uzytkownikow wszystkich budzetow,
	///////////////// zastosowanw w create budget for new m9onth
	public void generateUsersAndIncomeTextFieldsInCreateBudgetForNewMonth(JPanel panel2, JPanel panel3, JButton button) throws DatabaseNotInitialized {
		int i = 0;
		HashMap<Integer, String> usersFromDatabaseMap = databaseReader.readUsersFromDatabasetoHashMap();
		HashMap<Integer, Double> incomeMap = new HashMap<>();
		JLabel jErrorLabels[] = new JLabel[num];
		JTextField jtextFields[] = new JTextField[num];

		for (Entry<Integer, String> s : usersFromDatabaseMap.entrySet()) {
			int currentUserId = s.getKey();

			jtextFields[i] = new JTextField(10);
			JTextField currentField = jtextFields[i];

			jErrorLabels[i] = new JLabel("");
			jErrorLabels[i].setForeground(Color.RED);
			jErrorLabels[i].setPreferredSize(new Dimension(130, 20));
			jErrorLabels[i].setHorizontalAlignment(JLabel.LEFT);
			JLabel curentErrorLabel = jErrorLabels[i];

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
							//
							System.out.println(amount + " " + key);
						}

						if (incomeMap.size() == userNumber) {
							
							//
							System.out.println("income map user nr = " +incomeMap.size() + " " +  userNumber);
							button.setEnabled(true);

						} else {
							//
							System.out.println("   income map user nr " +incomeMap.size() + " " +  userNumber);

							button.setEnabled(false);
						}
					} catch (java.lang.NumberFormatException nfe) {
						curentErrorLabel.setText("Podaj kwotę");
						button.setEnabled(false);
					}
				}
			});
			i++;
			//
			System.out.println(" i " + i);
			if (i >= num) {
				System.out.println("i = " + i);
				return;
			}
		}
		panel2.validate();
		panel2.repaint();
		panel3.validate();
		panel3.repaint();
	}
}
