package Tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.swing.JButton;

import org.javatuples.Pair;
import org.junit.Test;
import Tests.Helpers.PanelWithButtonsTestable;
import Tests.Stub.ListenerStub;

public class PanelWithButtonsTest {
	
	@Test
	public void when_userClicksButton_then_registerdListenerRecivesItsName() {
		SortedSet<String> names = new TreeSet<String>() {{add("a");}};
		PanelWithButtonsTestable panel = new PanelWithButtonsTestable("buttons", names);
		Map<String, JButton> buttonNameToButton = panel.getButton();
		
		ListenerStub listener = new ListenerStub();
		panel.register(listener);
		
		buttonNameToButton.get("a").doClick();
		
		assertEquals(Arrays.asList(new Pair<String, String>("buttons", "a")), listener.receivedData);
	}

	@Test
	public void when_userClicksOneOfButtons_then_registerdListenerRecivesItsName() {
		SortedSet<String> names = new TreeSet<String>() {{add("a"); add("b"); add("c");}};
		PanelWithButtonsTestable panel = new PanelWithButtonsTestable("buttons", names);
		Map<String, JButton> buttonNameToButton = panel.getButton();
		
		ListenerStub listener = new ListenerStub();
		panel.register(listener);
		
		buttonNameToButton.get("c").doClick();
		
		assertEquals(Arrays.asList(new Pair<String, String>("buttons", "c")), listener.receivedData);
	}

	@Test
	public void when_userClicksTwoOfButtons_then_registerdListenerRecivesItsNamesInCorrectOrder() {
		SortedSet<String> names = new TreeSet<String>() {{add("a"); add("b"); add("c");}};
		PanelWithButtonsTestable panel = new PanelWithButtonsTestable("buttons", names);
		Map<String, JButton> buttonNameToButton = panel.getButton();
		
		ListenerStub listener = new ListenerStub();
		panel.register(listener);
		
		buttonNameToButton.get("b").doClick();
		buttonNameToButton.get("a").doClick();
		buttonNameToButton.get("a").doClick();
		
		assertEquals(Arrays.asList(new Pair<String, String>("buttons", "b"), 
								   new Pair<String, String>("buttons", "a"), 
								   new Pair<String, String>("buttons", "a")), 
				listener.receivedData);
	}
}
