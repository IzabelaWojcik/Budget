package learning.views;

public class ButtonAddNewMonthData extends NotificationData{
	public int month;
	public int year;
	public AddNewYearMonthDialog dialog;
	
	public ButtonAddNewMonthData(int notifierId, int year, int month, AddNewYearMonthDialog dialog) {
		super(notifierId);
		this.year = year;
		this.month = month;
		this.dialog = dialog;
	}
}
