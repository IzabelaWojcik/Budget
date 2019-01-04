package learning.budget;

public class BudgetDate{
	private int budgetId;
	private int year;
	private int month;
	
	public BudgetDate(int budgetId, int year, int month) {
		this.budgetId = budgetId;
		this.year = year;
		this.month = month;
	}

	public int getBudgetId() {
		return budgetId;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	@Override
	public String toString() {
		return this.getClass().getName() + "(" + String.valueOf(budgetId) + ":" + String.valueOf(year) + "." + String.valueOf(month) + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (!(obj instanceof BudgetDate)) return false;

		BudgetDate other = (BudgetDate) obj;
		return other.budgetId == budgetId && other.year == year && other.month == month;
	}
}
