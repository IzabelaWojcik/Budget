package learning.views;

import java.util.List;

public class ButtonCreateNewBudgetData extends NotificationData{
	public List<String> expenditureCategories;
	public List<String> duesCategories;
	public List<String> savingsCategories;
	public List<String> usersNames;
	public String budgetName;
	public CreateNewBudgetDialog dialog;
	
	public ButtonCreateNewBudgetData(int notifierId, List<String> expenditureCategories, List<String> savingsCategories,
			List<String> duesCategories, List<String> usersNames, String budgetName, CreateNewBudgetDialog dialog) {
		super(notifierId);
		this.expenditureCategories = expenditureCategories;
		this.duesCategories = duesCategories;
		this.savingsCategories = savingsCategories;
		this.usersNames = usersNames;
		this.budgetName = budgetName;
		this.dialog = dialog;
	}
}
