package Tests;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.JButton;

import org.junit.Before;
import org.junit.Test;

import Tests.Helpers.TestHelper;
import Tests.Stub.ListenerStub;
import learning.views.ButtonAddTransactionData;
import learning.views.ButtonsData;
import learning.views.PanelAddTransaction;
import learning.views.PanelWithButtons;

public class PanelAddTransactionTest {
	PanelAddTransaction panelAddTransaction;
	int notifierId;
	String dateString;
	java.util.Date date;
	String category;
	String amount;
	
	@Before
	public void setup() throws ParseException {
		int identifier = 1;
		notifierId = 1;
		dateString = "15-10-2018";
		date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		amount  = "12.0";
		List<String> categories = new ArrayList<String>() {{add("category1"); add("category2"); add("category3");}};
		category = categories.get(0);
		panelAddTransaction = new PanelAddTransaction(identifier);
	}
	
	@Test
	public void when_userClicksButton_then_registredLisynerRecivesItsName() {
		List<JButton> button = TestHelper.getButtons2(panelAddTransaction);
		
		ListenerStub listener = new ListenerStub();
		panelAddTransaction.register(listener);
		
		button.get(0).doClick();
		
		assertEquals(Arrays.asList(new ButtonAddTransactionData(notifierId, date, category, amount)), listener.receivedData);
		assertEquals(TestHelper.getButtons(panelAddTransaction).size(), 1);
	}
}
