package learning.budget;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseWriter implements IDatabaseWriter {
	private Statement start = null;
	Connection connection;

	public DatabaseWriter(Connection con) {
		connection = con;
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

	public void writeIncomeToDatabase(double amount, java.sql.Date date, int idUser, int idIncomeCategory,
			int idBudget) {
		String tablename = "Income";
		String update = "insert into " + "\"" + tablename + "\"" + "values(" + amount + "," + "\'" + date + "\'" + ","
				+ idUser + "," + idIncomeCategory + "," + idBudget + ")";
		executeUpdate(update);
	}

	private void executeUpdate(String update) {
		try {
			start = connection.createStatement();
			start.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void writeExpenditureOrSavingsToDatabase(double amount, java.sql.Date date, int idCategory, int idBudget,
			String tablename) {
		String update = "insert into " + "\"" + tablename + "\"" + "values(" + amount + "," + "\'" + date + "\'" + ","
				+ idCategory + "," + idBudget + ")";
		executeUpdate(update);
	}
}
