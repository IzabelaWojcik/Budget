package learning.budget;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.javatuples.Triplet;

public class DatabaseReader implements IDatabaseReader{
	private static IDatabaseReader databaseReader; 
	static Connection connection;
	
	private DatabaseReader() {}
	
	public static IDatabaseReader getInstance() {
		if(databaseReader == null) databaseReader = new DatabaseReader();
		return databaseReader;
	}
	
	public static void setConnection(Connection con) {
		connection = con;
	}
	
	private ResultSet getDataFromTable(String tablename) throws SQLException, DatabaseNotInitialized {
		ResultSet rs;

		try {
			Statement start = connection.createStatement();
			String SQL = "Select * from " + "\"" + tablename + "\"";
			rs = start.executeQuery(SQL);
		} catch (NullPointerException e) {
			throw new DatabaseNotInitialized();
		}
		return rs;
	}
	
	public List<BudgetDate> readYearsAndMonthsForConcreteBudgetFromDatabase(int budgetId) throws DatabaseNotInitialized{
		String tablename = "Years_and_months";
		List<BudgetDate> budgetDates = new ArrayList<BudgetDate>();
		try{
			ResultSet rs = getDataFromTable(tablename);
			while(rs.next()){
				int idBudget = rs.getInt(2);
				int year = rs.getInt(3);
				int month = rs.getInt(4);
				
				if(budgetId == idBudget) {
					BudgetDate budgetDateObject = new BudgetDate(budgetId, year, month);
					budgetDates.add(budgetDateObject);
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return budgetDates;
	}
	
	public List<UsersObject> readUsersFromDatabase() throws DatabaseNotInitialized{
		String tablename = "Users";
		ArrayList<UsersObject> userList = new ArrayList<UsersObject>();
		try{
			ResultSet rs = getDataFromTable(tablename);
			while(rs.next()){
				int userId = rs.getInt(1);
				String userName = rs.getString(2);
				int budgetId = rs.getInt(3);
				UsersObject user = new UsersObject(userId, userName, budgetId);
				userList.add(user);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return userList;
	}

	public ArrayList<Transaction> readIncomefromDatabase() throws DatabaseNotInitialized {
		String tablename = "Income";
		ArrayList<Transaction> usersIncomeList= new ArrayList<Transaction>();
		Transaction usersIncomeObject = null;
		try {
			ResultSet rs = getDataFromTable(tablename);
			while (rs.next()) {
				int idTransaction = rs.getInt(1);
				double amount = rs.getDouble(2);
				String date = rs.getString(3);
				int idUser = rs.getInt(4);
				int idIncomeCategory = rs.getInt(5);
				int idBudget = rs.getInt(6);
				
				LocalDate localDate = LocalDate.parse(date);
				
				usersIncomeObject = new Transaction(idTransaction, idUser, idIncomeCategory, amount, localDate, idBudget);
				if(!usersIncomeList.contains(usersIncomeObject)) usersIncomeList.add(usersIncomeObject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersIncomeList;
	}
	
	public HashMap<Integer, String> readCategoryFromDatabase(String tablename) throws DatabaseNotInitialized{
		HashMap<Integer, String> categoryMap = new HashMap<Integer, String>();
		try{
			ResultSet rs = getDataFromTable(tablename);
			while(rs.next()){
				int idCategory = rs.getInt(1);
				String categoryName = rs.getString(2);
				categoryMap.put(idCategory, categoryName);	
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return categoryMap;
	}
	
	public List<Triplet<Integer, Integer, String>> readCategoryNameCategoryIdAndBudgetIdFromDatabase(String tablename) throws DatabaseNotInitialized{
		List<Triplet<Integer, Integer, String>>  categoryNameIdBudgetIdTripletList = new ArrayList<>();
		try{
			ResultSet rs = getDataFromTable(tablename);
			while(rs.next()){
				int idCategory = rs.getInt(1);
				int idBudget = rs.getInt(3);
				String categoryName = rs.getString(2);
				Triplet<Integer, Integer, String> triplet = new Triplet<>(idBudget, idCategory, categoryName);
				categoryNameIdBudgetIdTripletList.add(triplet);	
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return categoryNameIdBudgetIdTripletList;
	}
	
	public Map<Integer, String> readIncomeCategory() throws DatabaseNotInitialized{
		String tablename = "Income_category";
		Map<Integer, String>  categoryIdToName = new HashMap<Integer, String>();
		try{
			ResultSet rs = getDataFromTable(tablename);
			while(rs.next()){
				int idCategory = rs.getInt(1);
				String categoryName = rs.getString(2);
				categoryIdToName.put(idCategory, categoryName);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return categoryIdToName;
	}
	
	public HashMap<Integer, String> readBudgetIdNameFromDatabase() throws DatabaseNotInitialized{
		String tablename = "Budget_name";
		HashMap<Integer, String> budgetInNameMap = new HashMap<Integer, String>();
		try {
			ResultSet rs = getDataFromTable(tablename);
			while (rs.next()) {
				int key = rs.getInt(1);
				String value = rs.getString(2);
				budgetInNameMap.put(key, value);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return budgetInNameMap;
	}
	
	public List<Transaction> readConcreteTransactionsForAllBudgetsFromDatabase(String tablename) throws DatabaseNotInitialized{
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		Transaction transaction = null;
		try{
			ResultSet rs = getDataFromTable(tablename);
			while(rs.next()){
				int transactionId = rs.getInt(1);
				int categoryId = rs.getInt(4);
				int budgetId = rs.getInt(5);
				double amount = rs.getDouble(2);
				String date = rs.getString(3);
				
				LocalDate localDate = LocalDate.parse(date);
				
				transaction  = new Transaction(transactionId, categoryId, amount, localDate, budgetId);
				transactions.add(transaction);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return transactions;
	}
	
    public HashMap<Integer, String> readUsersFromDatabasetoHashMap() throws DatabaseNotInitialized {
        HashMap<Integer, String> usersFromDatabaseMap = new HashMap<Integer, String>();
        String tablename = "Users";
        try {
            ResultSet rs = getDataFromTable(tablename);
            while (rs.next()) {
                int key = rs.getInt(1);
                String value = rs.getString(2);
                usersFromDatabaseMap.put(key, value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersFromDatabaseMap;
    }

	public List<Transaction> readIncomeForConcreteBugdetFromDatabase(int budgetId) throws DatabaseNotInitialized{
		List<Transaction> incomes = readIncomefromDatabase();
		HashMap<Integer, String> users = readUsersFromDatabasetoHashMap();
		Map<Integer, String>  categories = readCategoryFromDatabase("Income_category");
		
		List<Transaction> incomesForConcreteBudget = new ArrayList<>();
		
		for(Transaction entry: incomes) {
			if (entry.getBudgetId() == budgetId){
				Transaction transaction = new Transaction(entry.getTransactionId(), entry.getCategoryId(), entry.getAmount(), entry.getDate(), entry.getBudgetId(), categories.get(entry.getCategoryId()), entry.getUserId(), users.get(entry.getUserId()));
				incomesForConcreteBudget.add(transaction);
			}
		}
		return incomesForConcreteBudget;
	}
	
	public List<Transaction> readConcreteTransactionsWithCategoryNameForConcreteBudget(String tablenameForTransaction, String tablenameForCategory, int budgetId) throws DatabaseNotInitialized{
		List<Transaction> transactions = readConcreteTransactionsForAllBudgetsFromDatabase(tablenameForTransaction);
		Map<Integer, String>  categories = readCategoryFromDatabase(tablenameForCategory);
		
		List<Transaction> transactionsForConcreteBudget = new ArrayList<>();
		
		for(Transaction entry: transactions) {
			if (entry.getBudgetId() == budgetId){
				Transaction transaction = new Transaction(entry.getTransactionId(), entry.getCategoryId(), entry.getAmount(), entry.getDate(), entry.getBudgetId(), categories.get(entry.getCategoryId()));
				transactionsForConcreteBudget.add(transaction);
			}
		}
		return transactionsForConcreteBudget;
	}
	
	public Map<Integer, String> readCategoriesForBudgetFromDatabase(int budgetId, String categoryTablename) throws DatabaseNotInitialized {
		List<Triplet<Integer, Integer, String>> categories = readCategoryNameCategoryIdAndBudgetIdFromDatabase(categoryTablename);
		Map<Integer, String> map = categories.stream()
							.filter(c -> c.getValue0() == budgetId)
							.collect(Collectors.toMap(Triplet<Integer, Integer, String>::getValue1, Triplet<Integer, Integer, String>::getValue2));
		return map;
	}
}
