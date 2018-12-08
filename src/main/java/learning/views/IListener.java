package learning.views;

import learning.budget.DatabaseNotInitialized;

public interface IListener {
	
	void notify(NotificationData notificationData) throws DatabaseNotInitialized;
	
}
