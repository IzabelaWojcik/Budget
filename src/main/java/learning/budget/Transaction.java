package learning.budget;

import java.time.LocalDate;

public class Transaction {
	private int idOfTransactionCategory;
	private double transactionAmount;
	private int idOfBudget;
	private LocalDate dateOfTransaction;
	private int idOfTransaction;
	private String nameOfTransactionCategory;
	
	public Transaction(int idCategory, double amount, LocalDate date, int idBudget){
		idOfTransactionCategory = idCategory;
		transactionAmount = amount;
		dateOfTransaction = date;
		idOfBudget = idBudget;
	}
	
	public Transaction(int idTransaction, int idCategory, double amount, LocalDate date, int idBudget){
		idOfTransaction = idTransaction;
		idOfTransactionCategory = idCategory;
		transactionAmount = amount;
		dateOfTransaction = date;
		idOfBudget = idBudget;
	}
	
	public Transaction(int idTransaction, int idCategory, double amount, LocalDate date, int idBudget, String categoryName){
		idOfTransaction = idTransaction;
		idOfTransactionCategory = idCategory;
		transactionAmount = amount;
		dateOfTransaction = date;
		idOfBudget = idBudget;
		nameOfTransactionCategory = categoryName;
	}
	
	public int getCategoryId(){
		return idOfTransactionCategory;
	}
	
	public LocalDate getDate(){
		return dateOfTransaction;
	}
	
	public int getBudgetId(){
		return idOfBudget;
	}
	
	public int getTransactionId(){
		return idOfTransaction;
	}
	
	public double getAmount(){
		return transactionAmount;
	}
	
	public String getCategoryName() {
		return nameOfTransactionCategory;
	}
}
