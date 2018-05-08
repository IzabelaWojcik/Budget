package learning.budget;

import java.util.ArrayList;
import java.util.HashMap;

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

}
