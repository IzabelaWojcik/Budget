package Tests.Stub;

import java.util.ArrayList;
import java.util.List;

import learning.budget.views.IListener;

public class ListenerStub implements IListener{
	public List<String> receivedData = new ArrayList<>();

	public void notify(String data) {
		receivedData.add(data);
	}
}
