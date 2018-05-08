package learning.budget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class FillComponentsFromDataInDatabase {
	private IDatabaseReader databaseReader;
	
	public FillComponentsFromDataInDatabase(IDatabaseReader _databaseReader) {
		databaseReader = _databaseReader;
	}
	
	public List<Transaction> fillSavingsListWithCategoryName(int budgetId, int year, int month) throws DatabaseNotInitialized{
		List<Transaction> savingsListWithCategoryName = new ArrayList<>();
		List<Transaction> savingsList = databaseReader.readSavingsWithItsIdFromDataBase();
		HashMap<Integer, String> categoryIdAndNameList = databaseReader.readCategoryFromDatabase("Savings_category");
		
		List<Transaction> savingsConstrained = savingsList.stream()
					.filter(so -> so.getBudgetId() == budgetId && so.getDate().getYear() == year && so.getDate().getMonthValue() == month)
					.collect(Collectors.toList());
		
		for(Transaction t: savingsConstrained) {
				Transaction transaction = new Transaction(t.getTransactionId(), t.getCategoryId(), t.getAmount(), t.getDate(), t.getBudgetId(), categoryIdAndNameList.get(t.getCategoryId()));
				if(!savingsListWithCategoryName.contains(transaction)) savingsListWithCategoryName.add(transaction);
		}
		return savingsListWithCategoryName;
	}
}
