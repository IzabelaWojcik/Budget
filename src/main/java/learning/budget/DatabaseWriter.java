package learning.budget;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

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
	
	public void writeBudgetIdYearMonthToDatabase(int idBudget, int year, int month){
		String tablename = "Years_and_months";
		String columns = "(id_budget, year, month)";
		String update = "insert into " + "\"" + tablename + "\"" + columns + "values(" + idBudget + "," + year + "," + month + ")";
		executeUpdate(update);
	}

	public void writeCategoryListTodatabase(List<String> list, int idBudget, String tablename) {
		String columns = "(name, id_budget)";
		for (String s : list) {
			String update = "insert into " + "\"" + tablename + "\"" + columns + "values('" + s + "\'" + "," + "\'" + idBudget
					+ "')";
			executeUpdate(update);
		}
	}
	
	public void writeUsersListTodatabase(List<String> list, int idBudget) {
		String tablename = "Users";
		String columns = "(name, id_budget)";
		for (String s : list) {
			String update = "insert into " + "\"" + tablename + "\"" + columns + "values('" + s + "\'" + "," + "\'" + idBudget
					+ "')";
			executeUpdate(update);
		}
	}

	public void writeBudgetNameToDatabase(String name) {
		String tablename = "Budget_name";
		String columns = "(name)";
		String update = "insert into " + "\"" + tablename + "\"" + columns + "values('" + name + "')";
		executeUpdate(update);
	}

	public void writeIncomeToDatabase(double amount, LocalDate localDate, int idUser, int idIncomeCategory, int idBudget) {
		String tablename = "Income";
		String columns = "(amount, date, id_user, id_income_category, id_budget)";
		java.sql.Date date =java.sql.Date.valueOf(localDate);
		String update = "insert into " + "\"" + tablename + "\"" + columns + "values(" + amount + "," + "\'" + date + "\'" + ","
				+ idUser + "," + idIncomeCategory + "," + idBudget + ")";
		executeUpdate(update);
	}

	public void writeTransactionToDatabase(double amount, LocalDate localDate, int idCategory, int idBudget, String tablename) {
		java.sql.Date date = java.sql.Date.valueOf(localDate);
		String columns = "(amount, date, id_category, id_budget)";
		String update = "insert into " + "\"" + tablename + "\"" + columns + "values(" + amount + "," + "\'" + date + "\'" + ","
				+ idCategory + "," + idBudget + ")";
		executeUpdate(update);
	}
}
