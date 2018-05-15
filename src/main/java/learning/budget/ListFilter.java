package learning.budget;

import java.util.List;
import java.util.stream.Collectors;

public class ListFilter {
	
	public List<Transaction> filterByBudgetIdYearMonth(List<Transaction> list, int budgetId, int year, int month) {
		List<Transaction> transactionsList = list.stream()
				.filter(t -> t.getBudgetId() == budgetId && t.getDate().getYear() == year && t.getDate().getMonthValue() == month)
				.collect(Collectors.toList());
		return transactionsList;
	}
}
