package learning.budget;

import java.util.ArrayList;
import java.util.HashMap;

public interface IDatabaseReader {

	public int readDateOfBegginingNewBudgerMonthFromDatabase(int budgetId) throws DatabaseNotInitialized;

	public ArrayList<UsersObject> readUsersFromDatabase() throws DatabaseNotInitialized;

	public HashMap<Integer, String> readUsersFromDatabasetoHashMap() throws DatabaseNotInitialized;

	public ArrayList<UsersIncomeObject> readIncomefromDatabase() throws DatabaseNotInitialized;

	public HashMap<Integer, String> readCategoryFromDatabase(String tablename) throws DatabaseNotInitialized;

	public HashMap<Integer, String> readBudgetIdNameFromDatabase() throws DatabaseNotInitialized;

	public ArrayList<ExpenditureObject> readExpenditureFromDataBase() throws DatabaseNotInitialized;

	public ArrayList<ExpenditureObject> readExpenditureWithItsIdFromDataBase() throws DatabaseNotInitialized;

	public ArrayList<SavingsObject> readSavingsFromDataBase() throws DatabaseNotInitialized;

	public ArrayList<SavingsObject> readSavingsWithItsIdFromDataBase() throws DatabaseNotInitialized;

}
