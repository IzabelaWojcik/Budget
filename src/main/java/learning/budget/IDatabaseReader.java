package learning.budget;

import java.util.ArrayList;
import java.util.HashMap;

public interface IDatabaseReader {

	public int readDateOfBegginingNewBudgerMonthFromDatabase(int budgetId);

	public ArrayList<UsersObject> readUsersFromDatabase();

	public HashMap<Integer, String> readUsersFromDatabasetoHashMap();

	public ArrayList<UsersIncomeObject> readIncomefromDatabase();

	public HashMap<Integer, String> readCategoryFromDatabase(String tablename);

	public HashMap<Integer, String> readBudgetIdNameFromDatabase();

	public ArrayList<ExpendiutureObject> readExpenditureFromDataBase();

	public ArrayList<ExpendiutureObject> readExpenditureWithItsIdFromDataBase();

	public ArrayList<SavingsObject> readSavingsFromDataBase();

	public ArrayList<SavingsObject> readSavingsWithItsIdFromDataBase();

}
