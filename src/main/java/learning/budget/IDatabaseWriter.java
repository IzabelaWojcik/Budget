package learning.budget;
import java.util.ArrayList;
import java.util.HashMap;

public interface IDatabaseWriter {
	
	public void writeCategoryMapToDatabase(HashMap<String, String> map, int idBudget, String tablename);
	
	public void writeCategoryListTodatabase(ArrayList<String> list, int idBudget, String tablename);
	
	public void writeDayOfBeginingNewBudgetMonthToDatabase(int day, int idBudget);
	
	public void writeBudgetNameToDatabase(String name);
	
	public void writeIncomeToDatabase(double amount, java.sql.Date date, int idUser, int idIncomeCategory, int idBudget);
	
	public void writeExpenditureOrSavingsToDatabase(double amount, java.sql.Date date, int idCategory, int idBudget, String tablename);

}
