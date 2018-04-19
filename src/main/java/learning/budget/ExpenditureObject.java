package learning.budget;

import java.sql.Date;

public class ExpenditureObject {
	private int idExpenditureCategory;
	private double amount;
	private int idBudget;
	private Date dateOfExpenditure;
	private int idExpenditure;
	
	public ExpenditureObject(int idOfExpenditureCategory, double valueOfAmount, Date date, int idOfBudget){
		idExpenditureCategory = idOfExpenditureCategory;
		amount = valueOfAmount;
		dateOfExpenditure = date;
		idBudget = idOfBudget;
	}
	
	public ExpenditureObject(int idOfExpenditure, int idOfExpenditureCategory, double valueOfAmount, Date date, int idOfBudget){
		idExpenditure = idOfExpenditure;
		idExpenditureCategory = idOfExpenditureCategory;
		amount = valueOfAmount;
		dateOfExpenditure = date;
		idBudget = idOfBudget;
	}
	
	public int getExpenditureCategoryId(){
		return idExpenditureCategory;
	}
	
	public int getExpenditureId(){
		return idExpenditure;
	}
	
	public Date getExpenditureDate(){
		return dateOfExpenditure;
	}
	
	public int getBudgetId(){
		return idBudget;
	}
	
	public double getAmount(){
		return amount;
	}
}
