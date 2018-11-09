package Tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.swing.JButton;

import org.junit.Test;

import Tests.Helpers.GetButtonsTextHelper;
import Tests.Helpers.PanelForRemove;
import learning.budget.DatabaseNotInitialized;

public class GetButtonsTextHelperTest {
	@Test 
	public void getButtonsText_getButtonsFromPanel_returnButtonsTextList() throws DatabaseNotInitialized {
		GetButtonsTextHelper getButtonsHelper = new GetButtonsTextHelper();
		PanelForRemove panelForRemove = new PanelForRemove();
		
		JButton b1 = new JButton("2018");
		JButton b2 = new JButton("2019");
		JButton b3 = new JButton("2020");
		List<JButton> expected = new ArrayList<>();
		expected.add(b1);
		expected.add(b2);
		expected.add(b3);
		
		List<String> expectedButtonsNames = expected.stream().map(b -> b.getName()).collect(Collectors.toList());
		
		List<JButton> actualForGetButtons = getButtonsHelper.getButtons(panelForRemove);
		List<JButton> actualForGetButtons2 = getButtonsHelper.getButtons2(panelForRemove);
		
		assertEquals(expectedButtonsNames, actualForGetButtons.stream().map(b -> b.getName()).collect(Collectors.toList()));
		assertEquals(expectedButtonsNames, actualForGetButtons2.stream().map(b -> b.getName()).collect(Collectors.toList()));
	}
}
