package Tests;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;

import org.junit.Before;
import org.junit.Test;

import Tests.Helpers.TestHelper;
import Tests.Stub.ListenerStub;
import learning.views.ButtonAddIncomeData;
import learning.views.PanelAddIncome;

public class PanelAddIncomeTest {
	PanelAddIncome panelAddIncome;
	int notifierId;
	String dateString;
	java.util.Date date;
	String category;
	String user;
	String amount;
	
	@Before
	public void setup() throws ParseException {
		int identifier = 1;
		notifierId = 1;
		dateString = "15-10-2018";
		date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		amount  = "12.0";
		List<String> categories = new ArrayList<String>() {{add("category1"); add("category2"); add("category3");}};
		List<String> users = new ArrayList<String>() {{add("user1"); add("user2"); add("user3");}};
		category = categories.get(0);
		user = users.get(0);
		panelAddIncome = new PanelAddIncome(identifier);
	}
	
	@Test
	public void when_userClicksButton_then_registredLisynerRecivesItsName() {
		List<JButton> button = TestHelper.getButtons2(panelAddIncome);
		
		ListenerStub listener = new ListenerStub();
		panelAddIncome.register(listener);
		
		button.get(0).setEnabled(true);
		button.get(0).doClick();
		
		assertEquals(Arrays.asList(new ButtonAddIncomeData(notifierId, date, category, user, amount)), listener.receivedData);
		assertEquals(TestHelper.getButtons(panelAddIncome).size(), 1);
	}
}
