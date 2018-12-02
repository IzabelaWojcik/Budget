package learning.views;

import java.util.Date;

public class ButtonAddTransactionData extends NotificationData{
	public Date date;
	public String category;
	public String amount;
	
	public ButtonAddTransactionData(int notifierId, Date date, String category, String amount) {
		super(notifierId);
		this.date = date;
		this.category = category;
		this.amount = amount;
	}
	
}
