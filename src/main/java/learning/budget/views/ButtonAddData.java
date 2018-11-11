package learning.budget.views;

import java.time.LocalDate;
import java.util.Date;

public class ButtonAddData extends NotificationData{
	public Date date;
	public String category;
	public String amount;
	
	public ButtonAddData(int notifierId, Date date, String category, String amount) {
		super(notifierId);
		this.date = date;
		this.category = category;
		this.amount = amount;
	}
	
}
