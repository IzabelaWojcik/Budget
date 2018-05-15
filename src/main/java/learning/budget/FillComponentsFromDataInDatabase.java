package learning.budget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FillComponentsFromDataInDatabase {
	
	public List<Transaction> fillTransactionList(List<Transaction> transactionsList, HashMap<Integer, String> categoryIdAndItsNameMap){
		List<Transaction> transactions = new ArrayList<>();
		System.out.println(transactionsList.size() + " " + categoryIdAndItsNameMap.size());
		for(Transaction t: transactionsList) {
				Transaction transaction = new Transaction(t.getTransactionId(), t.getCategoryId(), t.getAmount(), t.getDate(), t.getBudgetId(), categoryIdAndItsNameMap.get(t.getCategoryId()));
				if(!transactions.contains(transaction))	transactions.add(transaction);
		}
		
		return transactions;
	}
}
