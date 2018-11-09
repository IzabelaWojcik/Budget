package Tests.Stub;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;

import learning.budget.views.IListener;
import learning.budget.views.NotificationData;

public class ListenerStub implements IListener{
	public List<NotificationData> receivedData = new ArrayList<>();

	@Override
	public void notify(NotificationData notificationData) {
		receivedData.add(notificationData);
	}
}
