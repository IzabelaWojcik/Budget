package classes;

import java.sql.Date;

public class UsersIncomeObject {
	private int idUser;
	private int idIncomeCategory;
	private double amount;
	private int idBudget;
	private Date dateOfIncome;
	
	public UsersIncomeObject(int idOfUser, int idOfIncomeCategory, double valueOfAmount, Date date, int idOfBudget){
		idUser = idOfUser;
		idIncomeCategory = idOfIncomeCategory;
		amount = valueOfAmount;
		dateOfIncome = date;
		idBudget = idOfBudget;
	}
	
	public int getUserId(){
		return idUser;
	}
	
	public int getIncomeCategoryId(){
		return idIncomeCategory;
	}
	
	public Date getIncomeDate(){
		return dateOfIncome;
	}
	
	public int getBudgetId(){
		return idBudget;
	}
	
	public double getAmount(){
		return amount;
	}
}
