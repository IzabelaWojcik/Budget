package learning.budget;

import static learning.budget.Constants.INCOME_TYPE_SALARY;

import java.util.List;
import java.util.stream.Collectors;

public class ListFilter {
	
	public List<Transaction> filterByBudgetIdYearMonth(List<Transaction> list, int budgetIdClicked, int yearClicked, int monthClicked) {
		List<Transaction> transactionsList = list.stream()
				.filter(t -> t.getBudgetId() == budgetIdClicked && t.getDate().getYear() == yearClicked && t.getDate().getMonthValue() == monthClicked)
				.collect(Collectors.toList());

		return transactionsList;
	}
	
	public List<Transaction> filterIncomeByBudgetIdYearMonthCategoryId(List<Transaction> list, int budgetIdClicked, int yearClicked, int monthClicked, boolean salary){
		List<Transaction> transactionList = filterByBudgetIdYearMonth(list, budgetIdClicked, yearClicked, monthClicked);
		List<Transaction> transactions;
		if(salary == false) {
			transactions= transactionList.stream().filter(t -> t.getCategoryId() != INCOME_TYPE_SALARY).collect(Collectors.toList());
		}else {
			transactions = transactionList.stream().filter(t -> t.getCategoryId() == INCOME_TYPE_SALARY).collect(Collectors.toList());
		}
		
		return transactions;
	}
}

