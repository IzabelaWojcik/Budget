package learning.budget;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public interface IDatabaseWriter {
	
	public void writeCategoryMapToDatabase(HashMap<String, String> map, int idBudget, String tablename);
	
	public void writeCategoryListTodatabase(ArrayList<String> list, int idBudget, String tablename);
	
	public void writeDayOfBeginingNewBudgetMonthToDatabase(int day, int idBudget);
	
	public void writeBudgetNameToDatabase(String name);
	
	public void writeIncomeToDatabase(double amount, LocalDate date, int idUser, int idIncomeCategory, int idBudget);
	
	public void writeExpenditureOrSavingsToDatabase(double amount, LocalDate localDate, int idCategory, int idBudget, String tablename);

}
