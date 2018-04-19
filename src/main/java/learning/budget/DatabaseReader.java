package learning.budget;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;


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

	private ResultSet getDataFromTable(String tablename) throws SQLException {
		Statement start = connection.createStatement();
		String SQL = "Select * from " + "\"" + tablename + "\"";
		ResultSet rs = start.executeQuery(SQL);
		start = connection.createStatement();

		return rs;
	}
	
	public int readDateOfBegginingNewBudgerMonthFromDatabase(int budgetId){
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
	
	public ArrayList<UsersObject> readUsersFromDatabase(){
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

	public HashMap<Integer, String> readUsersFromDatabasetoHashMap() {
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
	
	public ArrayList<UsersIncomeObject> readIncomefromDatabase() {
		String tablename = "Income";
		ArrayList<UsersIncomeObject> usersIncomeList= new ArrayList<UsersIncomeObject>();
		UsersIncomeObject usersIncomeObject = null;
		try {
			ResultSet rs = getDataFromTable(tablename);
			while (rs.next()) {
				double amount = rs.getDouble(2);
				Date date = rs.getDate(3);
				int idUser = rs.getInt(4);
				int idIncome = rs.getInt(5);
				int idBudget = rs.getInt(6);
				usersIncomeObject = new UsersIncomeObject(idUser, idIncome, amount, date, idBudget);
				if(!usersIncomeList.contains(usersIncomeObject)) usersIncomeList.add(usersIncomeObject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersIncomeList;
	}
	
	public HashMap<Integer, String> readCategoryFromDatabase(String tablename){
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
	
	public HashMap<Integer, String> readBudgetIdNameFromDatabase(){
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

	public ArrayList<ExpenditureObject> readExpenditureFromDataBase(){
		String tablename = "Expenditure";
		ArrayList<ExpenditureObject> expenditureObjectList = new ArrayList<ExpenditureObject>();
		ExpenditureObject expenditureObject = null;
		try{
			ResultSet rs = getDataFromTable(tablename);
			while(rs.next()){
				int expenditureCategoryId = rs.getInt(4);
				int budgetId = rs.getInt(5);
				double amount = rs.getDouble(2);
				Date date = rs.getDate(3);
				expenditureObject  = new ExpenditureObject(expenditureCategoryId, amount, date, budgetId);
				expenditureObjectList.add(expenditureObject);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return expenditureObjectList;
	}

	public ArrayList<ExpenditureObject> readExpenditureWithItsIdFromDataBase(){
		String tablename = "Expenditure";
		ArrayList<ExpenditureObject> expenditureObjectList = new ArrayList<ExpenditureObject>();
		ExpenditureObject expenditureObject = null;
		try{
			ResultSet rs = getDataFromTable(tablename);
			while(rs.next()){
				int idExpenditure = rs.getInt(1);
				int expenditureCategoryId = rs.getInt(4);
				int budgetId = rs.getInt(5);
				double amount = rs.getDouble(2);
				Date date = rs.getDate(3);
				expenditureObject  = new ExpenditureObject(idExpenditure, expenditureCategoryId, amount, date, budgetId);
				expenditureObjectList.add(expenditureObject);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return expenditureObjectList;
	}
	
	public ArrayList<SavingsObject> readSavingsFromDataBase(){
		String tablename = "Savings";
		ArrayList<SavingsObject> savingsObjectList = new ArrayList<SavingsObject>();
		SavingsObject savingsObject = null;
		try{
			ResultSet rs = getDataFromTable(tablename);
			while(rs.next()){
				int savingsCategoryId = rs.getInt(4);
				int budgetId = rs.getInt(5);
				double amount = rs.getDouble(2);
				Date date = rs.getDate(3);
				savingsObject  = new SavingsObject(savingsCategoryId, amount, date, budgetId);
				savingsObjectList.add(savingsObject);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return savingsObjectList;
	}
	
	public ArrayList<SavingsObject> readSavingsWithItsIdFromDataBase(){
		String tablename = "Savings";
		ArrayList<SavingsObject> savingsObjectList = new ArrayList<SavingsObject>();
		SavingsObject savingsObject = null;
		try{
			ResultSet rs = getDataFromTable(tablename);
			while(rs.next()){
				int savingsCategoryId = rs.getInt(4);
				int budgetId = rs.getInt(5);
				double amount = rs.getDouble(2);
				Date date = rs.getDate(3);
				savingsObject  = new SavingsObject(savingsCategoryId, amount, date, budgetId);
				savingsObjectList.add(savingsObject);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return savingsObjectList;
	}
}
