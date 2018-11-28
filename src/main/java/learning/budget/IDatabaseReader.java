package learning.budget;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;

import org.javatuples.Triplet;

import javafx.util.Pair;

public interface IDatabaseReader {

	public int readDateOfBegginingNewBudgetMonthFromDatabase(int budgetId) throws DatabaseNotInitialized;

	public List<UsersObject> readUsersFromDatabase() throws DatabaseNotInitialized;

	public HashMap<Integer, String> readUsersFromDatabasetoHashMap() throws DatabaseNotInitialized;

	public ArrayList<Transaction> readIncomefromDatabase() throws DatabaseNotInitialized;

	public HashMap<Integer, String> readCategoryFromDatabase(String tablename) throws DatabaseNotInitialized;
	
	public HashMap<Integer, String> readBudgetIdNameFromDatabase() throws DatabaseNotInitialized;

	public ArrayList<Transaction> readExpenditureFromDatabase() throws DatabaseNotInitialized;

	public ArrayList<Transaction> readExpenditureWithItsIdFromDatabase() throws DatabaseNotInitialized;

	public ArrayList<Transaction> readSavingsFromDatabase() throws DatabaseNotInitialized;

	public ArrayList<Transaction> readSavingsWithItsIdFromDatabase() throws DatabaseNotInitialized;
	
	public List<Pair<Integer, String>> readCategoryNameWithBudgetIdFromDatabase(String tablename) throws DatabaseNotInitialized;
	
	public List<Triplet<Integer, Integer, String>> readCategoryNameCategoryIdAndBudgetIdFromDatabase(String tablename) throws DatabaseNotInitialized;

	public List<Transaction> readAllTransactionsForConcreteBudgetFromDatabase(int budgetId) throws DatabaseNotInitialized;

	public List<Transaction> readConcreteTransactionsForAllBudgetsFromDatabase(String tablename) throws DatabaseNotInitialized;
	
	public List<String> readCategoriesForBudgetFromDatabase(int budgetId, String categoryTablename) throws DatabaseNotInitialized;
	
	public List<Transaction> readConcreteTransactionsWithCategoryNameForConcreteBudget(String tablenameForTransaction, String tablenameForCategory, int budgetId) throws DatabaseNotInitialized;

	public List<LocalDate> readDatesForBudgetFromDatabase(int budgetId) throws DatabaseNotInitialized;

	public SortedSet<String> readBudgetNameFromDatabase() throws DatabaseNotInitialized;
	
	public List<Transaction> readIncomeForConcreteBugdetFromDatabase(int budgetId) throws DatabaseNotInitialized;
}
