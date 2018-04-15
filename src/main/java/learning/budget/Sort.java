package learning.budget;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class Sort {
	private DatabaseReader databaseReader = new DatabaseReader();
	private ArrayList<UsersIncomeObject> usersIncomeObjectList = databaseReader.readIncomefromDatabase();
	private DateOptions dateOptions = new DateOptions();

	public ArrayList<Integer> sortAscending(ArrayList<Integer> listToSort) {
		listToSort.sort(null);
		return listToSort;
	}

	public ArrayList<Integer> sortDescending(ArrayList<Integer> listToSort) {
		sortAscending(listToSort);
		ArrayList<Integer> reversedList = new ArrayList<Integer>();
		if (listToSort.size() > 0) {
			for (int i = listToSort.size() - 1; i >= 0; i--) {
				int element = listToSort.get(i);
				reversedList.add(element);
			}
		}
		return reversedList;
	}

	public HashMap<Integer, ArrayList<Integer>> mapOfSortedYearsInConcreteBudgerId() {
		HashMap<Integer, ArrayList<Integer>> mapOfYearsAndBudgetId = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> sortedListOfYears = new ArrayList<Integer>();
		for (UsersIncomeObject uio : usersIncomeObjectList) {
			int idBudget = uio.getBudgetId();
			Date date = uio.getIncomeDate();
			int year = dateOptions.getYearFromDate(date);
			if (!mapOfYearsAndBudgetId.containsKey(idBudget)) {
				ArrayList<Integer> listOfYears = new ArrayList<Integer>();
				listOfYears.add(year);
				mapOfYearsAndBudgetId.put(idBudget, listOfYears);
			} else {
				ArrayList<Integer> listOfYears = mapOfYearsAndBudgetId.get(idBudget);
				if (!listOfYears.contains(year)) {
					listOfYears.add(year);
					sortedListOfYears = sortDescending(listOfYears);
					mapOfYearsAndBudgetId.put(idBudget, sortedListOfYears);
				}
			}
		}
		return mapOfYearsAndBudgetId;
	}

	public ArrayList<Integer> getSortedMonthsForConcreteYearAndBudgetId(int budgetId, int year) {
		ArrayList<Integer> listOfMonths = new ArrayList<Integer>();
		ArrayList<Integer> sortedMonthsList = new ArrayList<Integer>();
		for (UsersIncomeObject uio : usersIncomeObjectList) {
			int idBudget = uio.getBudgetId();
			int yearOfBudget = dateOptions.getYearFromDate(uio.getIncomeDate());
			if (idBudget == budgetId) {
				if (yearOfBudget == year) {
					int month = dateOptions.getMonthFromDate(uio.getIncomeDate());
					if (!listOfMonths.contains(month)) {
						listOfMonths.add(month);
					}
				}
			}
		}
		sortedMonthsList = sortDescending(listOfMonths);
		return sortedMonthsList;
	}

	public ArrayList<ExpendiutureObject> sortExpenditureAfterItsDay(int yearClicked, int monthClicked, int budgetId) {
		ArrayList<ExpendiutureObject> expenditureObjectListWithExpenditureId = databaseReader
				.readExpenditureWithItsIdFromDataBase();
		ArrayList<Integer> listOfDays = new ArrayList<Integer>();
		ArrayList<ExpendiutureObject> expenditureSortedList = new ArrayList<ExpendiutureObject>();
		ArrayList<ExpendiutureObject> expenditureListToSort = new ArrayList<ExpendiutureObject>();
		for (ExpendiutureObject eo : expenditureObjectListWithExpenditureId) {
			Date date = eo.getExpenditureDate();
			int year = dateOptions.getYearFromDate(date);
			int month = dateOptions.getMonthFromDate(date);
			int day = dateOptions.getDayFromDate(date);
			int idBudget = eo.getBudgetId();
			if (yearClicked == year && monthClicked == month && budgetId == idBudget) {
				if (!expenditureListToSort.contains(eo)) {
					listOfDays.add(day);
					expenditureListToSort.add(eo);
				}

			}
		}
		sortAscending(listOfDays);
		for (int days : listOfDays) {
			if (!expenditureListToSort.isEmpty()) {
				for (ExpendiutureObject eo : expenditureListToSort) {
					Date date = eo.getExpenditureDate();
					int day = dateOptions.getDayFromDate(date);
					if (day == days) {
						if (!expenditureSortedList.contains(eo)) {
							expenditureSortedList.add(eo);
						}
					}
				}
			}
		}
		return expenditureSortedList;
	}

	public ArrayList<SavingsObject> sortSavingsAfterItsDay(int yearClicked, int monthClicked, int budgetId) {
		ArrayList<SavingsObject> savingsObjectsListWithSavingsId = databaseReader
				.readSavingsWithItsIdFromDataBase();
		ArrayList<Integer> listOfDays = new ArrayList<Integer>();
		ArrayList<SavingsObject> savingsSortedList = new ArrayList<SavingsObject>();
		ArrayList<SavingsObject> savingsListToSort = new ArrayList<SavingsObject>();
		for (SavingsObject so : savingsObjectsListWithSavingsId) {
			Date date = so.getSavingsDate();
			int year = dateOptions.getYearFromDate(date);
			int month = dateOptions.getMonthFromDate(date);
			int day = dateOptions.getDayFromDate(date);
			int idBudget = so.getBudgetId();
			if (yearClicked == year && monthClicked == month && budgetId == idBudget) {
				if (!savingsListToSort.contains(so)) {
					listOfDays.add(day);
					savingsListToSort.add(so);
				}
			}
		}
		sortAscending(listOfDays);
		for (int days : listOfDays) {
			if (!savingsListToSort.isEmpty()) {
				for (SavingsObject so : savingsListToSort) {
					Date date = so.getSavingsDate();
					int day = dateOptions.getDayFromDate(date);
					if (day == days) {
						if (!savingsSortedList.contains(so)) {
							savingsSortedList.add(so);
						}
					}
				}
			}
		}
		return savingsSortedList;
	}
}
