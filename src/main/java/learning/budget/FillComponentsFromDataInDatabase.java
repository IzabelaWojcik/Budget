package learning.budget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FillComponentsFromDataInDatabase {
	
	public List<Transaction> fillTransactionList(List<Transaction> transactionsList, HashMap<Integer, String> categoryIdAndItsNameMap){
		List<Transaction> transactions = new ArrayList<>();
		for(Transaction t: transactionsList) {
				Transaction transaction = new Transaction(t.getTransactionId(), t.getCategoryId(), t.getAmount(), t.getDate(), t.getBudgetId(), categoryIdAndItsNameMap.get(t.getCategoryId()));
				if(!transactions.contains(transaction))	transactions.add(transaction);
		}
		
		return transactions;
	}
	
	public List<Transaction> fillTransactionIncomeList(List<Transaction> transactionsList, HashMap<Integer, String> categoryIdAndItsNameMap, HashMap<Integer, String> usersMap){
		List<Transaction> transactions = new ArrayList<>();
		for(Transaction t: transactionsList) {
				Transaction transaction = new Transaction(t.getTransactionId(), t.getCategoryId(), t.getAmount(), t.getDate(), t.getBudgetId(), categoryIdAndItsNameMap.get(t.getCategoryId()), t.getUserId(), usersMap.get(t.getUserId()));
				transactions.add(transaction);
		}
		
		return transactions;
	}
}
