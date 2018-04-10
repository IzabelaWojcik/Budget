package learning.budget;

public class UsersObject {
	private int idUser, idBudget;
	private String userName;
	
	public UsersObject(int userId, String nameOdUser, int budgetId){
		idUser = userId;
		idBudget = budgetId;
		userName = nameOdUser;
	}
	
	public int getUserId(){
		return idUser;
	}

	public int getBudgerId(){
		return idBudget;
	}
	
	public String getUserName(){
		return userName;
	}
}
