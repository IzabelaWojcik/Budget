package learning.budget;

import java.time.LocalDate;

public class SavingsObject {
	private int idSavingsCategory;
	private double amount;
	private int idBudget;
	private LocalDate dateOfSavings;
	private int idSavings;
	
	public SavingsObject(int idOfSavingsCategory, double valueOfAmount, LocalDate date, int idOfBudget){
		idSavingsCategory = idOfSavingsCategory;
		amount = valueOfAmount;
		dateOfSavings = date;
		idBudget = idOfBudget;
	}
	
	public SavingsObject(int idOfSavings, int idOfSavingsCategory, double valueOfAmount, LocalDate date, int idOfBudget){
		idSavings = idOfSavings;
		idSavingsCategory = idOfSavingsCategory;
		amount = valueOfAmount;
		dateOfSavings = date;
		idBudget = idOfBudget;
	}
	
	public int getSavingsCategoryId(){
		return idSavingsCategory;
	}
	
	public LocalDate getSavingsDate(){
		return dateOfSavings;
	}
	
	public int getBudgetId(){
		return idBudget;
	}
	
	public int getSavingsId(){
		return idSavings;
	}
	
	public double getAmount(){
		return amount;
	}
}
