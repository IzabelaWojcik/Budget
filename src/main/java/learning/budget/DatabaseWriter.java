package learning.budget;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseWriter implements IDatabaseWriter {
	private Statement start = null;
	private static IDatabaseWriter databaseWriter =  null;
	static Connection connection;

	private DatabaseWriter() {}

	public static IDatabaseWriter getInstance() {
		if(databaseWriter == null) databaseWriter = new DatabaseWriter();
		return databaseWriter;
	}
	
	public static void setConnection(Connection con) {
		connection = con;
	}
	
	private void executeUpdate(String update) {
		try {
			//TODO where put start.close(); 
			start = connection.createStatement();
			start.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void writeCategoryMapToDatabase(HashMap<String, String> map, int idBudget, String tablename) {
		for (String s : map.values()) {
			String update = "insert into " + "\"" + tablename + "\"" + "values('" + s + "\'" + "," + "\'" + idBudget
					+ "')";
			executeUpdate(update);
		}
	}

	public void writeCategoryListTodatabase(ArrayList<String> list, int idBudget, String tablename) {
		for (String s : list) {
			String update = "insert into " + "\"" + tablename + "\"" + "values('" + s + "\'" + "," + "\'" + idBudget
					+ "')";
			executeUpdate(update);
		}
	}

	public void writeDayOfBeginingNewBudgetMonthToDatabase(int day, int idBudget) {
		String tablename = "Budget_options";
		String update = "insert into " + "\"" + tablename + "\"" + "values('" + day + "\'" + "," + "\'" + idBudget
				+ "')";
		executeUpdate(update);
	}

	public void writeBudgetNameToDatabase(String name) {
		String tablename = "Budget_name";
		String update = "insert into " + "\"" + tablename + "\"" + "values('" + name + "')";
		executeUpdate(update);
	}

	public void writeIncomeToDatabase(double amount, LocalDate localDate, int idUser, int idIncomeCategory, int idBudget) {
		String tablename = "Income";
		java.sql.Date date =java.sql.Date.valueOf(localDate);
		String update = "insert into " + "\"" + tablename + "\"" + "values(" + amount + "," + "\'" + date + "\'" + ","
				+ idUser + "," + idIncomeCategory + "," + idBudget + ")";
		executeUpdate(update);
	}

	public void writeExpenditureOrSavingsToDatabase(double amount, LocalDate localDate, int idCategory, int idBudget, String tablename) {
		java.sql.Date date = java.sql.Date.valueOf(localDate);
		String update = "insert into " + "\"" + tablename + "\"" + "values(" + amount + "," + "\'" + date + "\'" + ","
				+ idCategory + "," + idBudget + ")";
		executeUpdate(update);
	}
}
