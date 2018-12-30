package learning.budget;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface IDatabaseWriter {
	
	public void writeBudgetIdYearMonthToDatabase(int idBudget, int year, int month);
	
	public void writeCategoryMapToDatabase(HashMap<String, String> map, int idBudget, String tablename);
	
	public void writeCategoryListTodatabase(List<String> transactionsFromCheckBoxes, int idBudget, String tablename);
	
	public void writeUsersListTodatabase(List<String> list, int idBudget);
	
	public void writeDayOfBeginingNewBudgetMonthToDatabase(int day, int idBudget);
	
	public void writeBudgetNameToDatabase(String name);
	
	public void writeIncomeToDatabase(double amount, LocalDate date, int idUser, int idIncomeCategory, int idBudget);
	
	public void writeTransactionToDatabase(double amount, LocalDate localDate, int idCategory, int idBudget, String tablename);

}
