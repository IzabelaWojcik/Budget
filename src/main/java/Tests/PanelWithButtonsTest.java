package Tests;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.swing.JButton;
import org.junit.Test;
import Tests.Helpers.PanelWithButtonsTestable;
import Tests.Stub.ListenerStub;

public class PanelWithButtonsTest {

	
	@Test
	public void when_userClicksButton_then_registerdListenerRecivesItsName() {
		SortedSet<String> names = new TreeSet<String>() {{add("a");}};
		PanelWithButtonsTestable panel = new PanelWithButtonsTestable(names);
		Map<String, JButton> buttonNameToButton = panel.getButton();
		
		ListenerStub listener = new ListenerStub();
		panel.register(listener);
		
		buttonNameToButton.get("a").doClick();
		
		assertEquals("a", listener.receivedData);
	}

}
