package learning.views;

import java.time.LocalDate;
import java.util.Date;

public class ButtonAddIncomeData extends NotificationData{
	public Date date;
	public String category;
	private String user;
	public String amount;
	
	public ButtonAddIncomeData(int notifierId, Date date, String category, String user, String amount) {
		super(notifierId);
		this.date = date;
		this.category = category;
		this.user =  user;
		this.amount = amount;
	}
	
}
