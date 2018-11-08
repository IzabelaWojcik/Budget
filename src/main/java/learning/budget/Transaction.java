package learning.budget;

import java.time.LocalDate;

public class Transaction {
	public enum Mode{
		Default,
		Incomne
	}
	
	Mode mode = Mode.Default;
	
	private int idOfTransactionCategory;
	private double transactionAmount;
	private int idOfBudget;
	private LocalDate dateOfTransaction;
	private int idOfTransaction;
	private String nameOfTransactionCategory;
	private int idOfUser;
	private String nameOfTheUser;
	
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
	
	public Transaction(int idTransaction, int idUser, int idCategory, double amount, LocalDate date, int idBudget){
		idOfTransaction = idTransaction;
		idOfTransactionCategory = idCategory;
		transactionAmount = amount;
		dateOfTransaction = date;
		idOfBudget = idBudget;
		idOfUser = idUser;
		
		mode = Mode.Incomne;
	}
	
	public Transaction(int idTransaction, int idCategory, double amount, LocalDate date, int idBudget, String categoryName, int idUser, String userName){
		idOfTransaction = idTransaction;
		idOfTransactionCategory = idCategory;
		transactionAmount = amount;
		dateOfTransaction = date;
		idOfBudget = idBudget;
		nameOfTransactionCategory = categoryName;
		
		idOfUser = idUser;
		nameOfTheUser = userName;
		mode = Mode.Incomne;
	}
	
	public int getUserId() {
		if (mode != Mode.Incomne) {
			throw new IllegalArgumentException("Mode invalid: user id does not exits");
		}
		
		return idOfUser;
	}
	
	public String getUserName() {
		if (mode != Mode.Incomne) {
			throw new IllegalArgumentException("Mode invalid: user name does not exits");
		}
		
		return nameOfTheUser;
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
	
	public int getYear() {
		return getDate().getYear();
	}
	
	public int getMonth() {
		return getDate().getMonthValue();
	}
}
