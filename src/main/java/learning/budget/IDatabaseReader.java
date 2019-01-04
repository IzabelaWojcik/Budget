package learning.budget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javatuples.Triplet;

public interface IDatabaseReader {

	public List<BudgetDate> readYearsAndMonthsForConcreteBudgetFromDatabase(int budgetId) throws DatabaseNotInitialized;
	
	public List<UsersObject> readUsersFromDatabase() throws DatabaseNotInitialized;

	public ArrayList<Transaction> readIncomefromDatabase() throws DatabaseNotInitialized;

	public HashMap<Integer, String> readCategoryFromDatabase(String tablename) throws DatabaseNotInitialized;
	
	public HashMap<Integer, String> readBudgetIdNameFromDatabase() throws DatabaseNotInitialized;

	public List<Triplet<Integer, Integer, String>> readCategoryNameCategoryIdAndBudgetIdFromDatabase(String tablename) throws DatabaseNotInitialized;

	public List<Transaction> readConcreteTransactionsForAllBudgetsFromDatabase(String tablename) throws DatabaseNotInitialized;
	
	public Map<Integer, String> readCategoriesForBudgetFromDatabase(int budgetId, String categoryTablename) throws DatabaseNotInitialized;
	
	public List<Transaction> readConcreteTransactionsWithCategoryNameForConcreteBudget(String tablenameForTransaction, String tablenameForCategory, int budgetId) throws DatabaseNotInitialized;

	public List<Transaction> readIncomeForConcreteBugdetFromDatabase(int budgetId) throws DatabaseNotInitialized;

	public Map<Integer, String> readIncomeCategory() throws DatabaseNotInitialized;
	
    public HashMap<Integer, String> readUsersFromDatabasetoHashMap() throws DatabaseNotInitialized;

}
