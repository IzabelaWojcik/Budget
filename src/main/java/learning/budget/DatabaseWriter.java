package learning.budget;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseWriter implements IDatabaseWriter{
	private Statement start = null;
	IDatabaseConnection databaseConnection;
	Connection connection; 
	
	public DatabaseWriter(IDatabaseConnection _databaseConnection) {
		databaseConnection = _databaseConnection;
		connection = databaseConnection.connectionWithDB();
	}
	public void writeCategoryMapToDatabase(HashMap<String, String> map, int idBudget, String tablename){
		for(String s : map.values()){
			try {
				start = connection.createStatement();
				start.executeUpdate("insert into " + "\"" + tablename + "\"" + "values('" + s + "\'" + "," + "\'" + idBudget + "')");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void writeCategoryListTodatabase(ArrayList<String> list, int idBudget, String tablename){
		for(String s: list){
			try {
				start = connection.createStatement();
				start.executeUpdate("insert into " + "\"" + tablename + "\"" + "values('" + s + "\'" + "," + "\'" + idBudget + "')");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void writeDayOfBeginingNewBudgetMonthToDatabase(int day, int idBudget){
		String tablename = "Budget_options";
		try {
			start = connection.createStatement();
			start.executeUpdate("insert into " + "\"" + tablename + "\"" + "values('" + day + "\'" + "," + "\'" + idBudget + "')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void writeBudgetNameToDatabase(String name){
		String tablename = "Budget_name";
		try {
			start = connection.createStatement();
			start.executeUpdate("insert into " + "\"" + tablename + "\"" + "values('" + name + "')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void writeIncomeToDatabase(double amount, java.sql.Date date, int idUser, int idIncomeCategory, int idBudget){
		String tablename = "Income";
		try {
			start = connection.createStatement();
			start.executeUpdate("insert into " + "\"" + tablename + "\"" + 
			"values(" + amount  + "," + "\'" + date + "\'" + ","  + idUser + ","  + idIncomeCategory + "," + idBudget + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void writeExpenditureOrSavingsToDatabase(double amount, java.sql.Date date, int idCategory, int idBudget, String tablename){
		try {
			start = connection.createStatement();
			start.executeUpdate("insert into " + "\"" + tablename + "\"" + 
			"values(" + amount  + "," + "\'" + date + "\'" + ","  + idCategory + "," + idBudget + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
