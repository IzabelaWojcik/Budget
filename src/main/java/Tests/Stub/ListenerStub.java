package Tests.Stub;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;

import learning.budget.views.IListener;

public class ListenerStub implements IListener{
	public List<Pair<String, String>> receivedData = new ArrayList<>();

	public void notify(String notifierId, String data) {
		receivedData.add(new Pair<>(notifierId, data));
	}
}
