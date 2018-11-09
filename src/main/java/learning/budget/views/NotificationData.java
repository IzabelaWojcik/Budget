package learning.budget.views;

public class NotificationData {
	public int notifierId;

	public NotificationData(int notifierId) {
		this.notifierId = notifierId;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof NotificationData && ((NotificationData)obj).notifierId == notifierId;
	}
}
