package learning.budget;

import static learning.budget.Constants.INCOME_TYPE_SALARY;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javafx.util.Pair;

public class ListFilter {

	public List<Transaction> filterByBudgetIdYearMonth(List<Transaction> list, int budgetIdClicked, int yearClicked, int monthClicked) {
		List<Transaction> transactionsList = list.stream().filter(t -> t.getBudgetId() == budgetIdClicked
				&& t.getDate().getYear() == yearClicked && t.getDate().getMonthValue() == monthClicked)
				.collect(Collectors.toList());

		return transactionsList;
	}

	public List<Transaction> filterIncomeByBudgetIdYearMonthIncomeType(List<Transaction> list, int budgetIdClicked, int yearClicked, int monthClicked, boolean salary) {
		List<Transaction> transactionList = filterByBudgetIdYearMonth(list, budgetIdClicked, yearClicked, monthClicked);
		List<Transaction> transactions;
		if (salary == false) {
			transactions = transactionList.stream().filter(t -> t.getCategoryId() != INCOME_TYPE_SALARY)
					.collect(Collectors.toList());
		} else {
			transactions = transactionList.stream().filter(t -> t.getCategoryId() == INCOME_TYPE_SALARY)
					.collect(Collectors.toList());
		}

		return transactions;
	}

	public List<String> filterCategoryByBudgetId(List<Pair<Integer, String>> categoryNameBudgetIdList, int budgetIdClicked) {
		ArrayList<String> categoryList = new ArrayList<String>();
		for (Pair<Integer, String> entry : categoryNameBudgetIdList) {
			if (entry.getKey() == budgetIdClicked) {
				if (!categoryList.contains(entry.getValue())) {
					categoryList.add(entry.getValue());
				}
			}
		}
		return categoryList;
	}
	
	public List<String> filterIncomeCategoryByIncomeType(HashMap<Integer, String> categoryNameAndIdHashmap, int budgetIdClicked) {
		ArrayList<String> categoryList = new ArrayList<String>();
		for (Entry<Integer, String> entry: categoryNameAndIdHashmap.entrySet()) {
			if(entry.getKey() != INCOME_TYPE_SALARY) {
				if (!categoryList.contains(entry.getValue())) {
					categoryList.add(entry.getValue());
				}
			}
		}
		return categoryList;
	}
	
	public List<String> filterUsersByBudgetId(List<UsersObject> usersObjectList, int budgetIdClicked) {
		ArrayList<String> usersNameList = new ArrayList<String>();
		for (UsersObject users: usersObjectList) {
			if (users.getBudgerId() == budgetIdClicked) {
				if (!usersNameList.contains(users.getUserName())) {
					usersNameList.add(users.getUserName());
				}
			}
		}
		return usersNameList;
	}
}
