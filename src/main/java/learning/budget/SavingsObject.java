package learning.budget;

import java.time.LocalDate;

public class SavingsObject extends Transaction{
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
	
	public int getCategoryId(){
		return idSavingsCategory;
	}
	
	public LocalDate getDate(){
		return dateOfSavings;
	}
	
	public int getBudgetId(){
		return idBudget;
	}
	
	public int getTransactionId(){
		return idSavings;
	}
	
	public double getAmount(){
		return amount;
	}
}
