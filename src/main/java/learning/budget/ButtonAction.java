package learning.budget;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.ws.FaultAction;

//import org.junit.experimental.categories.Categories.ExcludeCategory;

import com.toedter.calendar.JDateChooser;

public class ButtonAction extends GenerateComponents {
	private DatabaseWriter databaseWriter = new DatabaseWriter();
	private IDatabaseReader databaseReader;
	private HashMap<Integer, String> incomeCategoryMap;
	private ArrayList<UsersIncomeObject> userIncomeObjectList;
	private HashMap<Integer, String> usersNameIdMap;
	private TextFieldValidator textFieldValidator = new TextFieldValidator();
	private DataFormatter dataFormatter = new DataFormatter();
	ArrayList<ExpendiutureObject> expenditureObjectList;
	ArrayList<SavingsObject> savingsObjectList;
	private HashMap<Integer, String> expenditureCategoryMap;
	private HashMap<Integer, String> savingsCategoryMap;
	private DateOptions dateOptions = new DateOptions();
	private Sort sort = new Sort();

	public ButtonAction(IDatabaseReader _databaseReader) {
		databaseReader = _databaseReader;
		incomeCategoryMap = databaseReader.readCategoryFromDatabase("Income_category");
		userIncomeObjectList = databaseReader.readIncomefromDatabase();
		usersNameIdMap = databaseReader.readUsersFromDatabasetoHashMap();
		expenditureObjectList = databaseReader.readExpenditureFromDataBase();
		savingsObjectList = databaseReader.readSavingsFromDataBase();
		expenditureCategoryMap = databaseReader.readCategoryFromDatabase("Expenditure_category");
		savingsCategoryMap = databaseReader.readCategoryFromDatabase("Savings_category");
	}
	
	public void addOtherIncomeToDatabaseAfterClickingButton(JButton button, JComboBox<String> cbUser,
			JComboBox<String> cbOtherIncome, JTextField txFieldAmount, JLabel lblError, JDateChooser dateChooser,
			JPanel panelOtherIncomeView, JLabel labelOtherIncomeSum) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textFieldValidator.checkIfTextFieldIsEmpty(txFieldAmount, button, lblError)) {
					double amount = Double.parseDouble(txFieldAmount.getText());
					String incomeName = cbOtherIncome.getSelectedItem().toString();
					int idIncomeCategory = 0;

					for (Entry<Integer, String> entry : incomeCategoryMap.entrySet()) {
						if (incomeName.equals(entry.getValue())) {
							idIncomeCategory = entry.getKey();
						}
					}

					int idUser = getUserIdFromUserMap(cbUser);
					int idBudget = getBudgetIdFromUserMapAndIUserIncomeMap(cbUser);

					java.sql.Date sqlDate;

					try {
						sqlDate = dataFormatter.dateFormatterForJDateChooser(dateChooser);

						databaseWriter.writeIncomeToDatabase(amount, sqlDate, idUser, idIncomeCategory, idBudget);
						txFieldAmount.setText("");
						cbUser.setSelectedIndex(0);
						cbOtherIncome.setSelectedIndex(-1);

						int monthFromClickedButton = getMonthFromButtonClicked();
						int yearFromClickedButton = getYearFromButtonClicked();

						int yearFromDateChooser = dateOptions.getYearFromDate(sqlDate);
						int monthFromDateChooser = dateOptions.getMonthFromDate(sqlDate);

						refreshOtherIncomeView(panelOtherIncomeView, cbUser, idBudget, monthFromClickedButton,
								yearFromClickedButton);
						if (yearFromClickedButton == yearFromDateChooser
								&& monthFromClickedButton == monthFromDateChooser)
							refreshSumLabel(labelOtherIncomeSum, amount);
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, "Wybierz dat�");
					}
				}
			}
		});
	}

	public void addExpenditureOrSavingsToDatabaseAfterClickingButton(JButton button, JComboBox<String> cbCategory,
			JComboBox<String> cbUser, JTextField txFieldAmount, JLabel lblError, JDateChooser dateChooser,
			HashMap<Integer, String> map, String tablename, JPanel panelExpenditureView, JPanel panelSavingsView,
			JLabel labelExpenditureSum, JLabel labelSavingsSum) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textFieldValidator.checkIfTextFieldIsEmpty(txFieldAmount, button, lblError)) {
					double amount = Double.parseDouble(txFieldAmount.getText());
					String cbValue = cbCategory.getSelectedItem().toString();
					int idCategory = 0;
					for (Entry<Integer, String> entry : map.entrySet()) {
						if (cbValue.equals(entry.getValue())) {
							idCategory = entry.getKey();
						}
					}
					int idBudget = getBudgetIdFromUserMapAndIUserIncomeMap(cbUser);

					java.sql.Date sqlDate;

					try {
						sqlDate = dataFormatter.dateFormatterForJDateChooser(dateChooser);

						databaseWriter.writeExpenditureOrSavingsToDatabase(amount, sqlDate, idCategory, idBudget,
								tablename);
						txFieldAmount.setText("");
						cbCategory.setSelectedIndex(-1);

						int monthFromClickedButton = getMonthFromButtonClicked();
						int yearFromClickedButton = getYearFromButtonClicked();

						int yearFromDateChooser = dateOptions.getYearFromDate(sqlDate);
						int monthFromDateChooser = dateOptions.getMonthFromDate(sqlDate);

						if (tablename.equals("Expenditure")) {
							refreshExpenditureView(panelExpenditureView, cbUser, idBudget, monthFromClickedButton,
									yearFromClickedButton, labelExpenditureSum);
							if (yearFromClickedButton == yearFromDateChooser
									&& monthFromClickedButton == monthFromDateChooser)
								refreshSumLabel(labelExpenditureSum, amount);
						} else if (tablename.equals("Savings")) {
							refreshSavingsView(panelSavingsView, cbUser, idBudget, monthFromClickedButton,
									yearFromClickedButton);
							if (yearFromClickedButton == yearFromDateChooser
									&& monthFromClickedButton == monthFromDateChooser)
								refreshSumLabel(labelSavingsSum, amount);
						}
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, "Wybierz dat�");
					}
				}
			}
		});
	}

	public void refreshExpenditureView(JPanel panelExpenditureView, JComboBox<String> cbUser, int budgetId,
			int monthChoosenWithButtonClicked, int yearChoosenWithButtonClicked, JLabel lblExpenditureSum) {
		panelExpenditureView.removeAll();
		expenditureObjectList = databaseReader.readExpenditureFromDataBase();
		ArrayList<ExpendiutureObject> expenditureObjectSortedList = sort
				.sortExpenditureAfterItsDay(yearChoosenWithButtonClicked, monthChoosenWithButtonClicked, budgetId);
		DateOptions dateOptions = new DateOptions();
		LayoutOptions layoutOptions = new LayoutOptions();
		int dateYear = 0, dateMonth = 0, idExpenditureCategory;
		Date date;
		double amount;
		int i = 0;

		int idBudget = getBudgetIdFromUserMapAndIUserIncomeMap(cbUser);

		int sizeOfExpenditureObjectList = expenditureObjectList.size();
		JLabel jLabelDate[] = new JLabel[sizeOfExpenditureObjectList];
		JLabel jLabelsExpenditureCategory[] = new JLabel[sizeOfExpenditureObjectList];
		JLabel jLabelsExpenditureAmount[] = new JLabel[sizeOfExpenditureObjectList];

		layoutOptions.setGridy(0);
		for (ExpendiutureObject eo : expenditureObjectSortedList) {
			idBudget = eo.getBudgetId();
			date = eo.getExpenditureDate();
			dateYear = dateOptions.getYearFromDate(date);
			dateMonth = dateOptions.getMonthFromDate(date);
			idExpenditureCategory = eo.getExpenditureCategoryId();

			if (idBudget == budgetId) {
				if (dateYear == yearChoosenWithButtonClicked && dateMonth == monthChoosenWithButtonClicked) {
					amount = eo.getAmount();
					for (Entry<Integer, String> cat : expenditureCategoryMap.entrySet()) {
						if (cat.getKey() == idExpenditureCategory)
							jLabelsExpenditureCategory[i] = new JLabel(cat.getValue());
					}
					jLabelsExpenditureAmount[i] = new JLabel(String.valueOf(amount));
					jLabelDate[i] = new JLabel(date.toString());
					layoutOptions.gridBagLayoutOptionsForPanelsWithThreeLabels(panelExpenditureView, jLabelDate[i],
							jLabelsExpenditureCategory[i], jLabelsExpenditureAmount[i]);
					i++;
					if (i >= sizeOfExpenditureObjectList)
						return;
				}
			}

		}

		panelExpenditureView.revalidate();
		panelExpenditureView.repaint();
	}

	public void refreshSavingsView(JPanel panelSavingsView, JComboBox<String> cbUser, int budgetId,
			int monthChoosenWithButtonClicked, int yearChoosenWithButtonClicked) {
		panelSavingsView.removeAll();
		savingsObjectList = databaseReader.readSavingsFromDataBase();
		ArrayList<SavingsObject> savingsObjectSortedList = sort.sortSavingsAfterItsDay(yearChoosenWithButtonClicked,
				monthChoosenWithButtonClicked, budgetId);
		DateOptions dateOptions = new DateOptions();
		LayoutOptions layoutOptions = new LayoutOptions();
		int dateYear = 0, dateMonth = 0, idSavingsCategory;
		Date date;
		double amount;
		int i = 0;

		int idBudget = getBudgetIdFromUserMapAndIUserIncomeMap(cbUser);

		int sizeOfSavingsObjectList = savingsObjectList.size();
		JLabel jLabelDate[] = new JLabel[sizeOfSavingsObjectList];
		JLabel jLabelsSavingsCategory[] = new JLabel[sizeOfSavingsObjectList];
		JLabel jLabelsSavingsAmount[] = new JLabel[sizeOfSavingsObjectList];

		layoutOptions.setGridy(0);
		for (SavingsObject so : savingsObjectSortedList) {
			idBudget = so.getBudgetId();
			date = so.getSavingsDate();
			dateYear = dateOptions.getYearFromDate(date);
			dateMonth = dateOptions.getMonthFromDate(date);
			idSavingsCategory = so.getSavingsCategoryId();
			if (idBudget == budgetId) {
				if (dateYear == yearChoosenWithButtonClicked && dateMonth == monthChoosenWithButtonClicked) {
					amount = so.getAmount();
					for (Entry<Integer, String> cat : savingsCategoryMap.entrySet()) {
						if (cat.getKey() == idSavingsCategory)
							jLabelsSavingsCategory[i] = new JLabel(cat.getValue());
					}
					jLabelsSavingsAmount[i] = new JLabel(String.valueOf(amount));
					jLabelDate[i] = new JLabel(date.toString());
					layoutOptions.gridBagLayoutOptionsForPanelsWithThreeLabels(panelSavingsView, jLabelDate[i],
							jLabelsSavingsCategory[i], jLabelsSavingsAmount[i]);
					i++;
					if (i >= sizeOfSavingsObjectList)
						return;
				}
			}
		}
		panelSavingsView.revalidate();
		panelSavingsView.repaint();
	}

	public void refreshOtherIncomeView(JPanel panelOtherIncomeView, JComboBox<String> cbUser, int budgetId,
			int monthChoosenWithButtonClicked, int yearChoosenWithButtonClicked) {
		panelOtherIncomeView.removeAll();
		userIncomeObjectList = databaseReader.readIncomefromDatabase();
		DateOptions dateOptions = new DateOptions();
		LayoutOptions layoutOptions = new LayoutOptions();
		int dateYear = 0, dateMonth = 0, idIncomeCategory;
		Date date;
		double amount;
		int i = 0;
		int idcategoryOfIncome = 1;

		int idBudget = getBudgetIdFromUserMapAndIUserIncomeMap(cbUser);

		int sizeOfIncomeObjectList = userIncomeObjectList.size();
		JLabel jLabelDate[] = new JLabel[sizeOfIncomeObjectList];
		JLabel jLabelsIncomeCategory[] = new JLabel[sizeOfIncomeObjectList];
		JLabel jLabelsIncomeAmount[] = new JLabel[sizeOfIncomeObjectList];

		layoutOptions.setGridy(0);
		for (UsersIncomeObject uio : userIncomeObjectList) {
			idBudget = uio.getBudgetId();
			date = uio.getIncomeDate();
			dateYear = dateOptions.getYearFromDate(date);
			dateMonth = dateOptions.getMonthFromDate(date);
			idIncomeCategory = uio.getIncomeCategoryId();
			if (idBudget == budgetId) {
				if (dateYear == yearChoosenWithButtonClicked && dateMonth == monthChoosenWithButtonClicked) {
					amount = uio.getAmount();
					if (idIncomeCategory != idcategoryOfIncome) {
						for (Entry<Integer, String> cat : incomeCategoryMap.entrySet()) {
							if (cat.getKey() == idIncomeCategory)
								jLabelsIncomeCategory[i] = new JLabel(cat.getValue());
						}
						jLabelsIncomeAmount[i] = new JLabel(String.valueOf(amount));
						jLabelDate[i] = new JLabel(date.toString());
						layoutOptions.gridBagLayoutOptionsForPanelsWithThreeLabels(panelOtherIncomeView, jLabelDate[i],
								jLabelsIncomeCategory[i], jLabelsIncomeAmount[i]);
						i++;
						if (i >= sizeOfIncomeObjectList)
							return;
					}
				}
			}
		}
		panelOtherIncomeView.revalidate();
		panelOtherIncomeView.repaint();
	}

	public double refreshSumLabel(JLabel labelSum, double amountToAdd) {
		double sumFromLabelDouble = Double.parseDouble(labelSum.getText());
		double sum = sumFromLabelDouble + amountToAdd;
		labelSum.setText(sum + "");
		return sum;
	}

	public int getBudgetIdFromUserMapAndIUserIncomeMap(JComboBox<String> cbUser) {
		int idBudget = 0;
		int idUser = getUserIdFromUserMap(cbUser);
		for (UsersIncomeObject uio : userIncomeObjectList) {
			if (idUser == uio.getUserId()) {
				idBudget = uio.getBudgetId();
			}
		}
		return idBudget;
	}

	public int getYearFromButtonClicked() {
		GenerateComponents generateComponents = new GenerateComponents();
		String yearAndMonthFromClickingButton = generateComponents.getYearAndMonth();
		int yearFromClickedButton = 0;
		if (yearAndMonthFromClickingButton.length() == 6) {
			yearFromClickedButton = Integer.parseInt(yearAndMonthFromClickingButton.substring(2, 6));
		} else if (yearAndMonthFromClickingButton.length() == 7) {
			yearFromClickedButton = Integer.parseInt(yearAndMonthFromClickingButton.substring(3, 7));
		}
		return yearFromClickedButton;
	}

	public int getMonthFromButtonClicked() {
		GenerateComponents generateComponents = new GenerateComponents();
		String yearAndMonthFromClickingButton = generateComponents.getYearAndMonth();
		int monthFromClickedButton = 0;
		if (yearAndMonthFromClickingButton.length() == 6) {
			monthFromClickedButton = Integer.parseInt(yearAndMonthFromClickingButton.substring(0, 1));
		} else if (yearAndMonthFromClickingButton.length() == 7) {
			monthFromClickedButton = Integer.parseInt(yearAndMonthFromClickingButton.substring(0, 2));
		}
		return monthFromClickedButton;
	}

	public int getUserIdFromUserMap(JComboBox<String> cbUser) {
		int idUser = 0;
		String userName = cbUser.getSelectedItem().toString();
		for (Entry<Integer, String> entry : usersNameIdMap.entrySet()) {
			if (userName.equals(entry.getValue())) {
				idUser = entry.getKey();
			}
		}
		return idUser;
	}
	


	///////////////////////////////////////////////////////////////////
	public void refreshPanelsWithButtonsView(JPanel panel) {

	}

}
