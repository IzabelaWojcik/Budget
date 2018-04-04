package classes;

import java.sql.Date;

public class SavingsObject {
	private int idSavingsCategory;
	private double amount;
	private int idBudget;
	private Date dateOfSavings;
	private int idSavings;
	
	public SavingsObject(int idOfSavingsCategory, double valueOfAmount, Date date, int idOfBudget){
		idSavingsCategory = idOfSavingsCategory;
		amount = valueOfAmount;
		dateOfSavings = date;
		idBudget = idOfBudget;
	}
	
	public SavingsObject(int idOfSavings, int idOfSavingsCategory, double valueOfAmount, Date date, int idOfBudget){
		idSavings = idOfSavings;
		idSavingsCategory = idOfSavingsCategory;
		amount = valueOfAmount;
		dateOfSavings = date;
		idBudget = idOfBudget;
	}
	
	public int getSavingsCategoryId(){
		return idSavingsCategory;
	}
	
	public Date getSavingsDate(){
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
