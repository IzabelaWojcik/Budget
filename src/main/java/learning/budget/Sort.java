package learning.budget;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Sort {
	private DateOptions dateOptions = new DateOptions();
	
	public HashMap<Integer, ArrayList<Integer>> sortYearsInConcredeBudgetId(ArrayList<UsersIncomeObject> usersIncomeObjectList){
		HashMap<Integer, ArrayList<Integer>> mapOfBudgetIdAndYears = new HashMap<Integer, ArrayList<Integer>>();
		for(UsersIncomeObject uio: usersIncomeObjectList) {
			int idBudget = uio.getBudgetId();
			Date date = uio.getIncomeDate();
			//FIXME date
			int year = dateOptions.getYearFromDate(date);
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
			int idBudget = uio.getBudgetId();
			//FIXME date
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
		Collections.sort(listOfMonths,Collections.reverseOrder());
		return listOfMonths;
	}
	
	public ArrayList<ExpenditureObject> sortExpenditureAfterItsDay(ArrayList<ExpenditureObject> expenditureObjectListWithExpenditureId, int yearClicked, int monthClicked, int budgetId) {
		ArrayList<Integer> listOfDays = new ArrayList<Integer>();
		
		ArrayList<ExpenditureObject> expenditureListToSort = new ArrayList<ExpenditureObject>();
		for (ExpenditureObject eo : expenditureObjectListWithExpenditureId) {
			Date date = eo.getExpenditureDate();
			//FIXME date
			int year = dateOptions.getYearFromDate(date);
			int month = dateOptions.getMonthFromDate(date);
			int day = dateOptions.getDayFromDate(date);
			int idBudget = eo.getBudgetId();
			if (yearClicked == year && monthClicked == month && budgetId == idBudget && !expenditureListToSort.contains(eo)) {
				listOfDays.add(day);
				expenditureListToSort.add(eo);
			}
		}
		
		ArrayList<ExpenditureObject> expenditureSortedList = new ArrayList<ExpenditureObject>();
		Collections.sort(listOfDays);
		for (int dayInList : listOfDays) {
			if (!expenditureListToSort.isEmpty()) {
				for (ExpenditureObject eo : expenditureListToSort) {
					Date date = eo.getExpenditureDate();
					//FIXME date
					int day = dateOptions.getDayFromDate(date);
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
			Date date = so.getSavingsDate();
			//FIXME date
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
		Collections.sort(listOfDays);
		for (int dayInList : listOfDays) {
			if (!savingsListToSort.isEmpty()) {
				for (SavingsObject so : savingsListToSort) {
					Date date = so.getSavingsDate();
					//FIXME date
					int day = dateOptions.getDayFromDate(date);
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
