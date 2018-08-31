package learning.budget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.javatuples.Triplet;

import javafx.util.Pair;

public interface IDatabaseReader {

	public int readDateOfBegginingNewBudgetMonthFromDatabase(int budgetId) throws DatabaseNotInitialized;

	public ArrayList<UsersObject> readUsersFromDatabase() throws DatabaseNotInitialized;

	public HashMap<Integer, String> readUsersFromDatabasetoHashMap() throws DatabaseNotInitialized;

	public ArrayList<Transaction> readIncomefromDatabase() throws DatabaseNotInitialized;

	public HashMap<Integer, String> readCategoryFromDatabase(String tablename) throws DatabaseNotInitialized;
	
	public HashMap<Integer, String> readBudgetIdNameFromDatabase() throws DatabaseNotInitialized;

	public ArrayList<Transaction> readExpenditureFromDataBase() throws DatabaseNotInitialized;

	public ArrayList<Transaction> readExpenditureWithItsIdFromDataBase() throws DatabaseNotInitialized;

	public ArrayList<Transaction> readSavingsFromDataBase() throws DatabaseNotInitialized;

	public ArrayList<Transaction> readSavingsWithItsIdFromDataBase() throws DatabaseNotInitialized;
	
	public List<Pair<Integer, String>> readCategoryNameWithBudgetIdFromDatabase(String tablename) throws DatabaseNotInitialized;
	
	public List<Triplet<Integer, Integer, String>> readCategoryNameCategoryIdAndBudgetIdFromDatabase(String tablename) throws DatabaseNotInitialized;
}
