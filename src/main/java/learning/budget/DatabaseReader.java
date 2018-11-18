package learning.budget;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.javatuples.Triplet;

import javafx.util.Pair;

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
			// TODO check if create statment must be used twice,
			// TODO st.close();???
			start = connection.createStatement();
		} catch (NullPointerException e) {
			throw new DatabaseNotInitialized();
		}
		return rs;
	}
	
	public int readDateOfBegginingNewBudgetMonthFromDatabase(int budgetId) throws DatabaseNotInitialized{
		String tablename = "Budget_options";
		int date = 0;
		try{
			ResultSet rs = getDataFromTable(tablename);
			int idBudget = rs.getInt(3);
			if(budgetId == idBudget) date = rs.getInt(2);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return date;
	}
	
	public ArrayList<UsersObject> readUsersFromDatabase() throws DatabaseNotInitialized{
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
	
	public ArrayList<Transaction> readIncomefromDatabase() throws DatabaseNotInitialized {
		String tablename = "Income";
		ArrayList<Transaction> usersIncomeList= new ArrayList<Transaction>();
		Transaction usersIncomeObject = null;
		try {
			ResultSet rs = getDataFromTable(tablename);
			while (rs.next()) {
				int idTransaction = rs.getInt(1);
				double amount = rs.getDouble(2);
				java.sql.Date sqlDate = rs.getDate(3);
				int idUser = rs.getInt(4);
				int idIncomeCategory = rs.getInt(5);
				int idBudget = rs.getInt(6);
				
				LocalDate localDate = Instant.ofEpochMilli(sqlDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
				
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
	
	public List<Pair<Integer, String>> readCategoryNameWithBudgetIdFromDatabase(String tablename) throws DatabaseNotInitialized{
		List<Pair<Integer, String>>  categoryBudgetIdPair = new ArrayList<>();
		try{
			ResultSet rs = getDataFromTable(tablename);
			while(rs.next()){
				int idBudget = rs.getInt(3);
				String categoryName = rs.getString(2);
				Pair<Integer, String> pair= new Pair<>(idBudget, categoryName);
				categoryBudgetIdPair.add(pair);	
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return categoryBudgetIdPair;
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

	public ArrayList<Transaction> readExpenditureFromDatabase() throws DatabaseNotInitialized{
		String tablename = "Expenditure";
		ArrayList<Transaction> expenditureObjectList = new ArrayList<Transaction>();
		Transaction expenditureObject = null;
		try{
			ResultSet rs = getDataFromTable(tablename);
			while(rs.next()){
				int expenditureCategoryId = rs.getInt(4);
				int budgetId = rs.getInt(5);
				double amount = rs.getDouble(2);
				java.sql.Date sqlDate = rs.getDate(3);
				
				LocalDate localDate = Instant.ofEpochMilli(sqlDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
				
				expenditureObject  = new Transaction(expenditureCategoryId, amount, localDate, budgetId);
				expenditureObjectList.add(expenditureObject);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return expenditureObjectList;
	}
	
	public ArrayList<Transaction> readExpenditureWithItsIdFromDatabase() throws DatabaseNotInitialized{
		String tablename = "Expenditure";
		ArrayList<Transaction> expenditureObjectList = new ArrayList<Transaction>();
		Transaction expenditureObject = null;
		try{
			ResultSet rs = getDataFromTable(tablename);
			while(rs.next()){
				int idExpenditure = rs.getInt(1);
				int expenditureCategoryId = rs.getInt(4);
				int budgetId = rs.getInt(5);
				double amount = rs.getDouble(2);
				java.sql.Date sqlDate = rs.getDate(3);
				
				LocalDate localDate = Instant.ofEpochMilli(sqlDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
				
				expenditureObject  = new Transaction(idExpenditure, expenditureCategoryId, amount, localDate, budgetId);
				expenditureObjectList.add(expenditureObject);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return expenditureObjectList;
	}
	
	public ArrayList<Transaction> readSavingsFromDatabase() throws DatabaseNotInitialized{
		String tablename = "Savings";
		ArrayList<Transaction> savingsObjectList = new ArrayList<Transaction>();
		Transaction savingsObject = null;
		try{
			ResultSet rs = getDataFromTable(tablename);
			while(rs.next()){
				int savingsCategoryId = rs.getInt(4);
				int budgetId = rs.getInt(5);
				double amount = rs.getDouble(2);
				java.sql.Date sqlDate = rs.getDate(3);
				
				LocalDate localDate = Instant.ofEpochMilli(sqlDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
				
				savingsObject  = new Transaction(savingsCategoryId, amount, localDate, budgetId);
				savingsObjectList.add(savingsObject);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return savingsObjectList;
	}
	
	public ArrayList<Transaction> readSavingsWithItsIdFromDatabase() throws DatabaseNotInitialized{
		String tablename = "Savings";
		ArrayList<Transaction> savingsObjectList = new ArrayList<Transaction>();
		Transaction savingsObject = null;
		try{
			ResultSet rs = getDataFromTable(tablename);
			while(rs.next()){
				int savingsId = rs.getInt(1);
				int savingsCategoryId = rs.getInt(4);
				int budgetId = rs.getInt(5);
				double amount = rs.getDouble(2);
				java.sql.Date sqlDate = rs.getDate(3);
				
				LocalDate localDate = Instant.ofEpochMilli(sqlDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
				
				savingsObject  = new Transaction(savingsId, savingsCategoryId, amount, localDate, budgetId);
				savingsObjectList.add(savingsObject);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return savingsObjectList;
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
				java.sql.Date sqlDate = rs.getDate(3);
				
				LocalDate localDate = Instant.ofEpochMilli(sqlDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
				
				transaction  = new Transaction(transactionId, categoryId, amount, localDate, budgetId);
				transactions.add(transaction);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return transactions;
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
		
		return transactions;
	}
	
	public List<Transaction> readAllTransactionsForConcreteBudgetFromDatabase(int budgetId) throws DatabaseNotInitialized {
		List<Transaction> savings = readSavingsFromDatabase();
		List<Transaction> income = readIncomefromDatabase();
		List<Transaction> expenditure = readExpenditureFromDatabase();
		Stream<Transaction> transactions = Stream.concat(Stream.concat(savings.stream(), income.stream()), expenditure.stream());
		
		return transactions.filter(t -> t.getBudgetId() == budgetId).collect(Collectors.toList());
	}
	
	public List<LocalDate> readDatesForBudgetFromDatabase(int budgetId) throws DatabaseNotInitialized {
		List<Transaction> transactions = readAllTransactionsForConcreteBudgetFromDatabase(budgetId);
		return transactions.stream()
							.map(Transaction::getDate)
							.distinct()
							.collect(Collectors.toList());
	}
	
	public List<String> readCategoriesForBudgetFromDatabase(int budgetId, String categoryTablename) throws DatabaseNotInitialized {
		List<Triplet<Integer, Integer, String>> categories = readCategoryNameCategoryIdAndBudgetIdFromDatabase(categoryTablename);
		return categories.stream()
							.filter(c -> c.getValue0() == budgetId)
							.map(Triplet<Integer, Integer, String>::getValue2)
							.distinct()
							.collect(Collectors.toList());
	}
	
}
