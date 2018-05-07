package learning.budget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.time.LocalDate;

public class Sort {
	
	public HashMap<Integer, ArrayList<Integer>> sortYearsInConcredeBudgetId(ArrayList<UsersIncomeObject> usersIncomeObjectList){
		HashMap<Integer, ArrayList<Integer>> mapOfBudgetIdAndYears = new HashMap<Integer, ArrayList<Integer>>();
		for(UsersIncomeObject uio: usersIncomeObjectList) {
			int idBudget = uio.getBudgetId();
			LocalDate date = uio.getDate();
			int year = date.getYear();
			if (!mapOfBudgetIdAndYears.containsKey(idBudget)) {
				ArrayList<Integer> listOfYears = new ArrayList<Integer>();
				listOfYears.add(year);
				mapOfBudgetIdAndYears.put(idBudget, listOfYears);
			} else {
				ArrayList<Integer> listOfYears = mapOfBudgetIdAndYears.get(idBudget);
				if (!listOfYears.contains(year)) {
					listOfYears.add(year);
					Collections.sort(listOfYears, Collections.reverseOrder());
					mapOfBudgetIdAndYears.put(idBudget, listOfYears);
				}
			}
		}
		return mapOfBudgetIdAndYears;
	}
	
	public ArrayList<Integer> sortMonthsForConcreteYearAndBudgetId(ArrayList<UsersIncomeObject> usersIncomeObjectList, int budgetId, int year) {
		ArrayList<Integer> listOfMonths = new ArrayList<Integer>();
		for (UsersIncomeObject uio : usersIncomeObjectList) {
			LocalDate date = uio.getDate();
			if (uio.getBudgetId() == budgetId) {
				if (uio.getDate().getYear() == year) {
					int month = date.getMonthValue();
					if (!listOfMonths.contains(month)) {
						listOfMonths.add(month);
					}
				}
			}
		}
		Collections.sort(listOfMonths,Collections.reverseOrder());
		return listOfMonths;
	}
	
	public ArrayList<ExpenditureObject> sortExpenditureAfterItsDay(ArrayList<ExpenditureObject> expenditureObjectListWithExpenditureId, int yearClicked, int monthChoosenWithButtonClicked, int budgetId) {
		ArrayList<Integer> listOfDays = new ArrayList<Integer>();
		ArrayList<ExpenditureObject> expenditureListToSort = new ArrayList<ExpenditureObject>();
		for (ExpenditureObject eo : expenditureObjectListWithExpenditureId) {
			LocalDate date = eo.getDate();
			int year = date.getYear();
			int month = date.getMonthValue();
			int day = date.getDayOfMonth();
			int idBudget = eo.getBudgetId();
			if (yearClicked == year && monthChoosenWithButtonClicked == month && budgetId == idBudget && !expenditureListToSort.contains(eo)) {
				listOfDays.add(day);
				expenditureListToSort.add(eo);
			}
		}
		
		ArrayList<ExpenditureObject> expenditureSortedList = new ArrayList<ExpenditureObject>();
		Collections.sort(listOfDays);
		for (int dayInList : listOfDays) {
			if (!expenditureListToSort.isEmpty()) {
				for (ExpenditureObject eo : expenditureListToSort) {
					LocalDate date = eo.getDate();
					int day = date.getDayOfMonth();
					if (day == dayInList) {
						if (!expenditureSortedList.contains(eo)) {
							expenditureSortedList.add(eo);
						}
					}
				}
			}
		}
		return expenditureSortedList;
	}

	public ArrayList<SavingsObject> sortSavingsAfterItsDay(ArrayList<SavingsObject> savingsObjectsListWithSavingsId, int yearClicked, int monthClicked, int budgetId) {
		ArrayList<Integer> listOfDays = new ArrayList<Integer>();
		ArrayList<SavingsObject> savingsSortedList = new ArrayList<SavingsObject>();
		ArrayList<SavingsObject> savingsListToSort = new ArrayList<SavingsObject>();
		for (SavingsObject so : savingsObjectsListWithSavingsId) {
			LocalDate date = so.getDate();
			int year = date.getYear();
			int month = date.getMonthValue();
			int day = date.getDayOfMonth();
			int idBudget = so.getBudgetId();
			if (yearClicked == year && monthClicked == month && budgetId == idBudget) {
				if (!savingsListToSort.contains(so)) {
					listOfDays.add(day);
					savingsListToSort.add(so);
				}
			}
		}
		Collections.sort(listOfDays);
		for (int dayInList : listOfDays) {
			if (!savingsListToSort.isEmpty()) {
				for (SavingsObject so : savingsListToSort) {
					LocalDate date = so.getDate();
					int day = date.getDayOfMonth();
					if (day == dayInList) {
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
