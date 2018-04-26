package learning.budget;

import java.time.LocalDate;

public class ExpenditureObject {
	private int idExpenditureCategory;
	private double amount;
	private int idBudget;
	private LocalDate dateOfExpenditure;
	private int idExpenditure;
	
	public ExpenditureObject(int idOfExpenditureCategory, double valueOfAmount, LocalDate date, int idOfBudget){
		idExpenditureCategory = idOfExpenditureCategory;
		amount = valueOfAmount;
		dateOfExpenditure = date;
		idBudget = idOfBudget;
	}
	
	public ExpenditureObject(int idOfExpenditure, int idOfExpenditureCategory, double valueOfAmount, LocalDate date, int idOfBudget){
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
	
	public LocalDate getExpenditureDate(){
		return dateOfExpenditure;
	}
	
	public int getBudgetId(){
		return idBudget;
	}
	
	public double getAmount(){
		return amount;
	}
}
