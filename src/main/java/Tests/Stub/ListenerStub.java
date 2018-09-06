package Tests.Stub;

import learning.budget.views.IListener;

public class ListenerStub implements IListener{
	public String receivedData;

	public void notify(String data) {
		receivedData = data;
	}

}
