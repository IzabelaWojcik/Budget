package learning.budget;

import java.time.LocalDate;

public class UsersIncomeObject extends Transaction{
	private int idUser;
	private int idIncomeCategory;
	private double amount;
	private int idBudget;
	private LocalDate dateOfIncome;
	
	public UsersIncomeObject(int idOfUser, int idOfIncomeCategory, double valueOfAmount, LocalDate date, int idOfBudget){
		idUser = idOfUser;
		idIncomeCategory = idOfIncomeCategory;
		amount = valueOfAmount;
		dateOfIncome = date;
		idBudget = idOfBudget;
	}
	
	public int getTransactionId(){
		return idUser;
	}
	
	public int getCategoryId(){
		return idIncomeCategory;
	}
	
	public LocalDate getDate(){
		return dateOfIncome;
	}
	
	public int getBudgetId(){
		return idBudget;
	}
	
	public double getAmount(){
		return amount;
	}
}
