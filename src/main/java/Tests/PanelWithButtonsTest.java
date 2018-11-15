package Tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.swing.JButton;

import org.javatuples.Pair;
import org.junit.Test;

import Tests.Helpers.TestHelper;
import Tests.Stub.ListenerStub;
import learning.views.ButtonsData;
import learning.views.PanelWithButtons;

public class PanelWithButtonsTest {
	public int panelWithButtonsIdentyfier = 1;
	@Test
	public void when_userClicksButton_then_registerdListenerRecivesItsName() {
		SortedSet<String> names = new TreeSet<String>() {{add("a");}};
		PanelWithButtons panel = new PanelWithButtons(panelWithButtonsIdentyfier, names);
		List<JButton> buttons = TestHelper.getButtons2(panel);
		
		ListenerStub listener = new ListenerStub();
		panel.register(listener);
		
		buttons.get(0).doClick();
		
		assertEquals(Arrays.asList(new ButtonsData(panelWithButtonsIdentyfier, "a")), listener.receivedData);
	}

	@Test
	public void when_userClicksOneOfButtons_then_registerdListenerRecivesItsName() {
		SortedSet<String> names = new TreeSet<String>() {{add("a"); add("b"); add("c");}};
		PanelWithButtons panel = new PanelWithButtons(panelWithButtonsIdentyfier, names);
		List<JButton> buttons = TestHelper.getButtons2(panel);
		
		ListenerStub listener = new ListenerStub();
		panel.register(listener);
		
		buttons.get(2).doClick();
		
		assertEquals(Arrays.asList(new ButtonsData(panelWithButtonsIdentyfier, "c")), listener.receivedData);
	}

	@Test
	public void when_userClicksTwoOfButtons_then_registerdListenerRecivesItsNamesInCorrectOrder() {
		SortedSet<String> names = new TreeSet<String>() {{add("a"); add("b"); add("c");}};
		PanelWithButtons panel = new PanelWithButtons(panelWithButtonsIdentyfier, names);
		List<JButton> buttons = TestHelper.getButtons2(panel);
		
		ListenerStub listener = new ListenerStub();
		panel.register(listener);
		
		buttons.get(1).doClick();
		buttons.get(0).doClick();
		buttons.get(0).doClick();
		
		assertEquals(Arrays.asList(new ButtonsData(panelWithButtonsIdentyfier, "b"), 
									new ButtonsData(panelWithButtonsIdentyfier, "a"), 
									new ButtonsData(panelWithButtonsIdentyfier, "a")), 
				listener.receivedData);
	}
}
